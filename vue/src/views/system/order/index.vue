<template>
  <div class="app-container">
    <el-form :inline="true" :model="queryParams" label-width="68px" ref="queryForm" v-show="showSearch">
      <el-form-item label="订单关联设备编号" prop="orderNum">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入订单关联设备编号"
          size="small"
          v-model="queryParams.orderNum"
        />
      </el-form-item>
      <el-form-item label="订单金额" prop="orderAmount">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入订单金额"
          size="small"
          v-model="queryParams.orderAmount"
        />
      </el-form-item>
      <el-form-item label="订单优惠金额" prop="orderPreferentialAmount">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入订单优惠金额"
          size="small"
          v-model="queryParams.orderPreferentialAmount"
        />
      </el-form-item>
      <el-form-item label="支付渠道" prop="paymenChannel">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入支付渠道"
          size="small"
          v-model="queryParams.paymenChannel"
        />
      </el-form-item>
      <el-form-item label="支付渠道唯一编码" prop="paymenChannelId">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入支付渠道唯一编码"
          size="small"
          v-model="queryParams.paymenChannelId"
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
          @click="handleExport"
          icon="el-icon-download"
          plain
          size="mini"
          type="warning"
          v-hasPermi="['system:order:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table :data="orderList" @selection-change="handleSelectionChange" v-loading="loading">
      <el-table-column align="center" type="selection" width="55"/>
      <el-table-column align="center" label="订单ID" prop="orderId"/>
      <el-table-column align="center" label="订单关联设备编号" prop="orderNum"/>
      <el-table-column align="center" label="订单金额" prop="orderAmount"/>
      <el-table-column align="center" label="订单优惠金额" prop="orderPreferentialAmount"/>
      <el-table-column align="center" label="支付渠道" prop="paymenChannel"/>
      <el-table-column align="center" label="支付渠道唯一编码" prop="paymenChannelId"/>
      <el-table-column align="center" label="备注" prop="remark"/>
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
        <el-form-item label="订单关联设备编号" prop="orderNum">
          <el-input placeholder="请输入订单关联设备编号" v-model="form.orderNum"/>
        </el-form-item>
        <el-form-item label="订单金额" prop="orderAmount">
          <el-input placeholder="请输入订单金额" v-model="form.orderAmount"/>
        </el-form-item>
        <el-form-item label="订单优惠金额" prop="orderPreferentialAmount">
          <el-input placeholder="请输入订单优惠金额" v-model="form.orderPreferentialAmount"/>
        </el-form-item>
        <el-form-item label="支付渠道" prop="paymenChannel">
          <el-input placeholder="请输入支付渠道" v-model="form.paymenChannel"/>
        </el-form-item>
        <el-form-item label="支付渠道唯一编码" prop="paymenChannelId">
          <el-input placeholder="请输入支付渠道唯一编码" v-model="form.paymenChannelId"/>
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
  import {addOrder, delOrder, getOrder, listOrder, updateOrder} from "@/api/system/order";

  export default {
    name: "Order",
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
        orderList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          orderNum: null,
          orderAmount: null,
          orderPreferentialAmount: null,
          paymenChannel: null,
          paymenChannelId: null,
        },
        // 表单参数
        form: {}
        ,
        // 表单校验
        rules: {
          orderNum: [
            {
              required: true,
              message: "订单关联设备编号不能为空", trigger: "blur"
            }
          ],
          orderAmount: [
            {
              required: true,
              message: "订单金额不能为空", trigger: "blur"
            }
          ],
          paymenChannel: [
            {
              required: true,
              message: "支付渠道不能为空", trigger: "blur"
            }
          ],
          paymenChannelId: [
            {
              required: true,
              message: "支付渠道唯一编码不能为空", trigger: "blur"
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
        listOrder(this.queryParams).then(response => {
          this.orderList = response.rows;
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
          orderId: null,
          orderNum: null,
          orderAmount: null,
          orderPreferentialAmount: null,
          paymenChannel: null,
          paymenChannelId: null,
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
        this.ids = selection.map(item => item.orderId
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
        const orderId =
          row.orderId || this.ids
        getOrder(orderId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改【请填写功能名称】";
        }
      )

      },
      /** 提交按钮 */
      submitForm() {
        this.refs["form"].validate(valid => {
          if(valid) {
            if (this.form.orderId != null) {
              updateOrder(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            )

            } else {
              addOrder(this.form).then(response => {
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
        const orderIds = row.orderId || this.ids;
        this.$confirm('是否确认删除【请填写功能名称】编号为"' + orderIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delOrder(orderIds);
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
          return exportOrder(queryParams);
        }).then(response => {
          this.download(response.msg);
        }
      )
      }
    }
  };
</script>
