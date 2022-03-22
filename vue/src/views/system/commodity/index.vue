<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商品编码" prop="commodityCode">
        <el-input
          v-model="queryParams.commodityCode"
          placeholder="请输入商品编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品名称" prop="commodityName">
        <el-input
          v-model="queryParams.commodityName"
          placeholder="请输入商品名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品价格" prop="commodityPrice">
        <el-input
          v-model="queryParams.commodityPrice"
          placeholder="请输入商品价格"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:commodity:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:commodity:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:commodity:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:commodity:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="commodityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="商品ID" align="center" prop="commodityId"/>
      <el-table-column label="商品编码" align="center" prop="commodityCode"/>
      <el-table-column label="商品名称" align="center" prop="commodityName"/>
      <el-table-column label="商品价格" align="center" prop="commodityPrice"/>
      <el-table-column label="商品画像" align="center" prop="commodityPicture"/>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:commodity:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:commodity:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改【请填写功能名称】对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品编码" prop="commodityCode">
          <el-input v-model="form.commodityCode" placeholder="请输入商品编码"/>
        </el-form-item>
        <el-form-item label="商品名称" prop="commodityName">
          <el-input v-model="form.commodityName" placeholder="请输入商品名称"/>
        </el-form-item>
        <el-form-item label="商品价格" prop="commodityPrice">
          <el-input v-model="form.commodityPrice" placeholder="请输入商品价格"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {
    listCommodity,
    getCommodity,
    delCommodity,
    addCommodity,
    updateCommodity,
    exportCommodity } from "@/api/system/commodity";

  export default {
    name: "Commodity",
    components: {
    },
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
        commodityList: [],
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
          commodityPicture: null,
        },
        // 表单参数
        form: {
        }
        ,
        // 表单校验
        rules: {
          commodityCode: [
            {
              required: true,
              message: "商品编码不能为空", trigger: "blur" }
          ],
          commodityName: [
            {
              required: true,
              message: "商品名称不能为空", trigger: "blur" }
          ],
          commodityPrice: [
            {
              required: true,
              message: "商品价格不能为空", trigger: "blur" }
          ],
          createBy: [
            {
              required: true,
              message: "创建者不能为空", trigger: "blur" }
          ],
          createTime: [
            {
              required: true,
              message: "创建时间不能为空", trigger: "blur" }
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
        listCommodity(this.queryParams).then(response => {
          this.commodityList = response.rows;
          this.total = response.total;
          this.loading = false;
        })
        ;
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
          commodityPicture: null,
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
        this.ids = selection.map(item => item.commodityId)
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
        getCommodity(commodityId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改【请填写功能名称】";
        })
        ;
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if(valid) {
            if (this.form.commodityId != null) {
              updateCommodity(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              })
              ;
            } else {
              addCommodity(this.form).then(response => {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              })
              ;
            }
          }
        }
      )
        ;
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const commodityIds = row.commodityId || this.ids;
        this.$confirm('是否确认删除【请填写功能名称】编号为"' + commodityIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delCommodity(commodityIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有【请填写功能名称】数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return exportCommodity(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
      }
    }
  };
</script>
