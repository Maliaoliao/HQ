<template>
  <div class="app-container">
    <el-form :inline="true" :model="queryParams" label-width="68px" ref="queryForm" v-show="showSearch">
      <el-form-item label="商品编码" prop="commodityCode">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入商品编码"
          size="small"
          v-model="queryParams.commodityCode"
        />
      </el-form-item>
      <el-form-item label="商品名称" prop="commodityName">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入商品名称"
          size="small"
          v-model="queryParams.commodityName"
        />
      </el-form-item>
      <el-form-item label="商品价格" prop="commodityPrice">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入商品价格"
          size="small"
          v-model="queryParams.commodityPrice"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery" icon="el-icon-search" size="mini" type="primary">搜索</el-button>
        <el-button @click="resetQuery" icon="el-icon-refresh" size="mini">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          @click="handleAdd"
          icon="el-icon-plus"
          plain
          size="mini"
          type="primary"
          v-hasPermi="['system:commodities:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          :disabled="single"
          @click="handleUpdate"
          icon="el-icon-edit"
          plain
          size="mini"
          type="success"
          v-hasPermi="['system:commodities:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          :disabled="multiple"
          @click="handleDelete"
          icon="el-icon-delete"
          plain
          size="mini"
          type="danger"
          v-hasPermi="['system:commodities:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          @click="handleExport"
          icon="el-icon-download"
          plain
          size="mini"
          type="warning"
          v-hasPermi="['system:commodities:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table :data="commoditiesList" @selection-change="handleSelectionChange" v-loading="loading">
      <el-table-column align="center" type="selection" width="55"/>
      <el-table-column align="center" label="商品ID" prop="commodityId"/>
      <el-table-column align="center" label="商品编码" prop="commodityCode"/>
      <el-table-column align="center" label="商品名称" prop="commodityName"/>
      <el-table-column align="center" label="商品价格" prop="commodityPrice"/>
      <el-table-column align="center" label="备注" prop="remark"/>
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template slot-scope="scope">
          <el-button
            @click="handleUpdate(scope.row)"
            icon="el-icon-edit"
            size="mini"
            type="text"
            v-hasPermi="['system:commodities:edit']"
          >修改
          </el-button>
          <el-button
            @click="handleDelete(scope.row)"
            icon="el-icon-delete"
            size="mini"
            type="text"
            v-hasPermi="['system:commodities:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      :limit.sync="queryParams.pageSize"
      :page.sync="queryParams.pageNum"
      :total="total"
      @pagination="getList"
      v-show="total>0"
    />

    <!-- 添加或修改【请填写功能名称】对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="500px">
      <el-form :model="form" :rules="rules" label-width="80px" ref="form">
        <el-form-item label="商品编码" prop="commodityCode">
          <el-input placeholder="请输入商品编码" v-model="form.commodityCode"/>
        </el-form-item>
        <el-form-item label="商品名称" prop="commodityName">
          <el-input placeholder="请输入商品名称" v-model="form.commodityName"/>
        </el-form-item>
        <el-form-item label="商品价格" prop="commodityPrice">
          <el-input placeholder="请输入商品价格" v-model="form.commodityPrice"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input placeholder="请输入内容" type="textarea" v-model="form.remark"/>
        </el-form-item>
      </el-form>
      <div class="dialog-footer" slot="footer">
        <el-button @click="submitForm" type="primary">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {
    addCommodities,
    delCommodities,
    getCommodities,
    listCommodities,
    updateCommodities
  } from "@/api/system/commodities";

  export default {
    name: "Commodities",
    components: {},
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 【请填写功能名称】表格数据
        commoditiesList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          commodityCode: null,
          commodityName: null,
          commodityPrice: null,
        },
        // 表单参数
        form: {}
        ,
        // 表单校验
        rules: {
          commodityCode: [
            {
              required: true,
              message: "商品编码不能为空", trigger: "blur"
            }
          ],
          commodityName: [
            {
              required: true,
              message: "商品名称不能为空", trigger: "blur"
            }
          ],
          commodityPrice: [
            {
              required: true,
              message: "商品价格不能为空", trigger: "blur"
            }
          ],
          createBy: [
            {
              required: true,
              message: "创建者不能为空", trigger: "blur"
            }
          ],
          createTime: [
            {
              required: true,
              message: "创建时间不能为空", trigger: "blur"
            }
          ],
        }
      }
        ;
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询【请填写功能名称】列表 */
      getList() {
        this.loading = true;
        listCommodities(this.queryParams).then(response => {
          this.commoditiesList = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      )

      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          commodityId: null,
          commodityCode: null,
          commodityName: null,
          commodityPrice: null,
          remark: null,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null
        }
        ;
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.commodityId
      )
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加【请填写功能名称】";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const commodityId =
          row.commodityId || this.ids
        getCommodities(commodityId).then(response => {
          this
          .form = response.data;
          this
          .open = true;
          this
          .title = "修改【请填写功能名称】";
        }
      )

      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if(valid) {
            if (this.form.commodityId != null) {
              updateCommodities(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            )

            } else {
              addCommodities(this.form).then(response => {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            )

            }
          }
        }
      )

      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const commodityIds = row.commodityId || this.ids;
        this.$confirm('是否确认删除【请填写功能名称】编号为"' + commodityIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delCommodities(commodityIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }
      )
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有【请填写功能名称】数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return exportCommodities(queryParams);
        }).then(response => {
          this.download(response.msg);
        }
      )
      }
    }
  };
</script>
