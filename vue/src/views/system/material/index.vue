<template>
  <div class="app-container">
    <el-form :inline="true" :model="queryParams" label-width="68px" ref="queryForm" v-show="showSearch">
      <el-form-item label="物料名称" prop="materialName">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入物料名称"
          size="small"
          v-model="queryParams.materialName"
        />
      </el-form-item>
      <el-form-item label="厨房ID" prop="kitchenId">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入厨房ID"
          size="small"
          v-model="queryParams.kitchenId"
        />
      </el-form-item>
      <el-form-item label="物料复核状态0未复核 1已复核" prop="materialCheckStatus">
        <el-select clearable placeholder="请选择物料复核状态0未复核 1已复核" size="small"
                   v-model="queryParams.materialCheckStatus">
          <el-option label="请选择字典生成" value=""/>
        </el-select>
      </el-form-item>
      <el-form-item label="物料复核人员ID" prop="materialCheckUserId">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入物料复核人员ID"
          size="small"
          v-model="queryParams.materialCheckUserId"
        />
      </el-form-item>
      <el-form-item label="复核日期" prop="materialCheckDate">
        <el-date-picker clearable placeholder="选择复核日期"
                        size="small"
                        type="date"
                        v-model="queryParams.materialCheckDate"
                        value-format="yyyy-MM-dd">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="采购数量" prop="materialQuantity">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入采购数量"
          size="small"
          v-model="queryParams.materialQuantity"
        />
      </el-form-item>
      <el-form-item label="单位" prop="materialUnit">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入单位"
          size="small"
          v-model="queryParams.materialUnit"
        />
      </el-form-item>
      <el-form-item label="采购金额" prop="paymenAmount">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入采购金额"
          size="small"
          v-model="queryParams.paymenAmount"
        />
      </el-form-item>
      <el-form-item label="采购渠道" prop="paymenCanal">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入采购渠道"
          size="small"
          v-model="queryParams.paymenCanal"
        />
      </el-form-item>
      <el-form-item label="支付方式" prop="paymenWay">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入支付方式"
          size="small"
          v-model="queryParams.paymenWay"
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
          v-hasPermi="['system:material:add']"
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
          v-hasPermi="['system:material:edit']"
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
          v-hasPermi="['system:material:remove']"
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
          v-hasPermi="['system:material:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table :data="materialList" @selection-change="handleSelectionChange" v-loading="loading">
      <el-table-column align="center" type="selection" width="55"/>
      <el-table-column align="center" label="物料ID" prop="materialId"/>
      <el-table-column align="center" label="物料名称" prop="materialName"/>
      <el-table-column align="center" label="厨房ID" prop="kitchenId"/>
      <el-table-column align="center" label="物料复核状态0未复核 1已复核" prop="materialCheckStatus"/>
      <el-table-column align="center" label="物料复核人员ID" prop="materialCheckUserId"/>
      <el-table-column align="center" label="复核日期" prop="materialCheckDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.materialCheckDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="采购数量" prop="materialQuantity"/>
      <el-table-column align="center" label="单位" prop="materialUnit"/>
      <el-table-column align="center" label="采购金额" prop="paymenAmount"/>
      <el-table-column align="center" label="采购渠道" prop="paymenCanal"/>
      <el-table-column align="center" label="支付方式" prop="paymenWay"/>
      <el-table-column align="center" label="支付ID" prop="paymenOrderId"/>
      <el-table-column align="center" label="备注" prop="remark"/>
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template slot-scope="scope">
          <el-button
            @click="handleUpdate(scope.row)"
            icon="el-icon-edit"
            size="mini"
            type="text"
            v-hasPermi="['system:material:edit']"
          >修改
          </el-button>
          <el-button
            @click="handleDelete(scope.row)"
            icon="el-icon-delete"
            size="mini"
            type="text"
            v-hasPermi="['system:material:remove']"
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
        <el-form-item label="物料名称" prop="materialName">
          <el-input placeholder="请输入物料名称" v-model="form.materialName"/>
        </el-form-item>
        <el-form-item label="厨房ID" prop="kitchenId">
          <el-input placeholder="请输入厨房ID" v-model="form.kitchenId"/>
        </el-form-item>
        <el-form-item label="物料复核状态0未复核 1已复核">
          <el-radio-group v-model="form.materialCheckStatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="物料复核人员ID" prop="materialCheckUserId">
          <el-input placeholder="请输入物料复核人员ID" v-model="form.materialCheckUserId"/>
        </el-form-item>
        <el-form-item label="复核日期" prop="materialCheckDate">
          <el-date-picker clearable placeholder="选择复核日期"
                          size="small"
                          type="date"
                          v-model="form.materialCheckDate"
                          value-format="yyyy-MM-dd">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="采购数量" prop="materialQuantity">
          <el-input placeholder="请输入采购数量" v-model="form.materialQuantity"/>
        </el-form-item>
        <el-form-item label="单位" prop="materialUnit">
          <el-input placeholder="请输入单位" v-model="form.materialUnit"/>
        </el-form-item>
        <el-form-item label="采购金额" prop="paymenAmount">
          <el-input placeholder="请输入采购金额" v-model="form.paymenAmount"/>
        </el-form-item>
        <el-form-item label="采购渠道" prop="paymenCanal">
          <el-input placeholder="请输入采购渠道" v-model="form.paymenCanal"/>
        </el-form-item>
        <el-form-item label="支付方式" prop="paymenWay">
          <el-input placeholder="请输入支付方式" v-model="form.paymenWay"/>
        </el-form-item>
        <el-form-item label="支付ID" prop="paymenOrderId">
          <el-input placeholder="请输入内容" type="textarea" v-model="form.paymenOrderId"/>
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
    addMaterial,
    delMaterial,
    exportMaterial,
    getMaterial,
    listMaterial,
    updateMaterial
  } from "@/api/system/material";

  export default {
    name: "Material",
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
        materialList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          materialName: null,
          kitchenId: null,
          materialCheckStatus: null,
          materialCheckUserId: null,
          materialCheckDate: null,
          materialQuantity: null,
          materialUnit: null,
          paymenAmount: null,
          paymenCanal: null,
          paymenWay: null,
          paymenOrderId: null,
        },
        // 表单参数
        form: {}
        ,
        // 表单校验
        rules: {
          materialName: [
            {
              required: true,
              message: "物料名称不能为空", trigger: "blur"
            }
          ],
          kitchenId: [
            {
              required: true,
              message: "厨房ID不能为空", trigger: "blur"
            }
          ],
          materialCheckStatus: [
            {
              required: true,
              message: "物料复核状态0未复核 1已复核不能为空", trigger: "blur"
            }
          ],
          materialQuantity: [
            {
              required: true,
              message: "采购数量不能为空", trigger: "blur"
            }
          ],
          materialUnit: [
            {
              required: true,
              message: "单位不能为空", trigger: "blur"
            }
          ],
          paymenAmount: [
            {
              required: true,
              message: "采购金额不能为空", trigger: "blur"
            }
          ],
          paymenCanal: [
            {
              required: true,
              message: "采购渠道不能为空", trigger: "blur"
            }
          ],
          paymenWay: [
            {
              required: true,
              message: "支付方式不能为空", trigger: "blur"
            }
          ],
          paymenOrderId: [
            {
              required: true,
              message: "支付ID不能为空", trigger: "blur"
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
        listMaterial(this.queryParams).then(response => {
          this.materialList = response.rows;
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
          materialId: null,
          materialName: null,
          kitchenId: null,
          materialCheckStatus: "0",
          materialCheckUserId: null,
          materialCheckDate: null,
          materialQuantity: null,
          materialUnit: null,
          paymenAmount: null,
          paymenCanal: null,
          paymenWay: null,
          paymenOrderId: null,
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
        this.ids = selection.map(item => item.materialId)
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
        const materialId =
          row.materialId || this.ids
        getMaterial(materialId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改【请填写功能名称】";
        })
        ;
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
            if (valid) {
              if (this.form.materialId != null) {
                updateMaterial(this.form).then(response => {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                })
                ;
              } else {
                addMaterial(this.form).then(response => {
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
        const materialIds = row.materialId || this.ids;
        this.$confirm('是否确认删除【请填写功能名称】编号为"' + materialIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delMaterial(materialIds);
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
          return exportMaterial(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
      }
    }
  };
</script>
