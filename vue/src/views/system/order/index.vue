<template>
  <div class="app-container">
    <el-form :inline="true" :model="queryParams" label-width="68px" ref="queryForm" v-show="showSearch">
      <!--<el-form-item label="用户标识" prop="openid">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入用户标识"
          size="small"
          v-model="queryParams.openid"
        />
      </el-form-item>
      <el-form-item label="是否关注公众账号" prop="isSubscribe">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入是否关注公众账号"
          size="small"
          v-model="queryParams.isSubscribe"
        />-->
      </el-form-item>
      <el-form-item label="交易类型" prop="tradeType">
        <el-select clearable placeholder="请选择交易类型" size="small"
                   v-model="queryParams.tradeType">
          <el-option
            v-for="dict in tradeTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <!--<el-form-item label="付款银行" prop="bankType">
        <el-select clearable placeholder="请选择付款银行" size="small"
                   v-model="queryParams.bankType">
          <el-option label="请选择字典生成" value=""/>
        </el-select>
      </el-form-item>
      <el-form-item label="货币类型" prop="feeType">
        <el-select clearable placeholder="请选择货币类型" size="small"
                   v-model="queryParams.feeType">
          <el-option label="请选择字典生成" value=""/>
        </el-select>
      </el-form-item>-->
      <el-form-item label="订单金额" prop="totalFee">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入订单金额"
          size="small"
          v-model="queryParams.totalFee"
        />
      </el-form-item>
      <el-form-item label="应结订单金额" prop="settlementTotalFee">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入应结订单金额"
          size="small"
          v-model="queryParams.settlementTotalFee"
        />
      </el-form-item>
      <!--<el-form-item label="代金券金额" prop="couponFee">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入代金券金额"
          size="small"
          v-model="queryParams.couponFee"
        />
      </el-form-item>-->
      <!--<el-form-item label="现金支付货币类型" prop="cashFeeType">
        <el-select clearable placeholder="请选择现金支付货币类型" size="small"
                   v-model="queryParams.cashFeeType">
          <el-option label="请选择字典生成" value=""/>
        </el-select>
      </el-form-item>
      <el-form-item label="现金支付金额" prop="cashFee">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入现金支付金额"
          size="small"
          v-model="queryParams.cashFee"
        />
      </el-form-item>-->
      <el-form-item label="微信支付订单号" prop="transactionId">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入微信支付订单号"
          size="small"
          v-model="queryParams.transactionId"
        />
      </el-form-item>
      <!--<el-form-item label="商家数据包" prop="attach">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入商家数据包"
          size="small"
          v-model="queryParams.attach"
        />
      </el-form-item>-->
      <el-form-item label="支付完成时间" prop="timeEnd">
        <el-date-picker clearable placeholder="选择支付完成时间"
                        size="small"
                        type="date"
                        v-model="queryParams.timeEnd"
                        value-format="yyyy-MM-dd">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="商品ID" prop="commodityId">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入商品ID"
          size="small"
          v-model="queryParams.commodityId"
        />
      </el-form-item>
      <el-form-item label="终端ID" prop="spbillCreateIp">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入终端ID"
          size="small"
          v-model="queryParams.spbillCreateIp"
        />
      </el-form-item>
      <el-form-item label="订单是否开发票" prop="isReceipt">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入订单是否开发票"
          size="small"
          v-model="queryParams.isReceipt"
        />
      </el-form-item>
      <el-form-item>
        <el-button @click="handleQuery" icon="el-icon-search" size="mini" type="primary">搜索</el-button>
        <el-button @click="resetQuery" icon="el-icon-refresh" size="mini">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!--<el-col :span="1.5">
        <el-button
          @click="handleAdd"
          icon="el-icon-plus"
          plain
          size="mini"
          type="primary"
          v-hasPermi="['system:order:add']"
        >新增
        </el-button>
      </el-col>-->
      <!--<el-col :span="1.5">
        <el-button
          :disabled="single"
          @click="handleUpdate"
          icon="el-icon-edit"
          plain
          size="mini"
          type="success"
          v-hasPermi="['system:order:edit']"
        >修改
        </el-button>
      </el-col>-->
      <el-col :span="1.5">
        <el-button
          :disabled="multiple"
          @click="handleDelete"
          icon="el-icon-delete"
          plain
          size="mini"
          type="danger"
          v-hasPermi="['system:order:remove']"
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
          v-hasPermi="['system:order:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table :data="orderList" @selection-change="handleSelectionChange" v-loading="loading">
      <el-table-column align="center" type="selection" width="55"/>
      <el-table-column align="center" label="订单ID" prop="orderId"/>
      <el-table-column align="center" label="用户标识" prop="openid"/>
      <el-table-column align="center" label="是否关注公众账号" :formatter="yesNoFormat" prop="isSubscribe"/>


      <el-table-column
        label="交易类型"
        align="center"
        prop="tradeType"
        :formatter="tradeTypeFormat"
        width="100"
      />


      <el-table-column align="center" label="付款银行" prop="bankType"/>
      <el-table-column align="center" label="货币类型" prop="feeType"/>
      <el-table-column align="center" :formatter="totalFeeFormat" label="订单金额" prop="totalFee"/>
      <el-table-column align="center" label="备注" prop="remark"/>
      <el-table-column align="center" label="应结订单金额" prop="settlementTotalFee"/>
      <el-table-column align="center" label="代金券金额" prop="couponFee"/>
      <el-table-column align="center" label="现金支付货币类型" prop="cashFeeType"/>
      <el-table-column align="center" label="现金支付金额" prop="cashFee"/>
      <el-table-column align="center" label="微信支付订单号" prop="transactionId" width="150"/>
      <el-table-column align="center" label="商户订单号" prop="outTradeNo" width="150"/>
      <el-table-column align="center" label="支付完成时间" prop="timeEnd" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.timeEnd, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="营销详情" prop="promotionDetail"/>
      <el-table-column align="center" label="商品ID" prop="commodityId"/>
      <el-table-column align="center" label="终端ID" prop="spbillCreateIp"/>
      <el-table-column align="center" label="订单是否开发票" :formatter="yesNoFormat2" prop="isReceipt"/>
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template slot-scope="scope">
          <el-button
            @click="handleUpdate(scope.row)"
            icon="el-icon-edit"
            size="mini"
            type="text"
            v-hasPermi="['system:order:edit']"
          >xxx
          </el-button>
          <el-button
            @click="returnGoods(scope.row)"
            icon="el-icon-delete"
            size="mini"
            type="text"
            :disabled="scope.row.isSubscribe == 'Y' ? true : false "
            v-hasPermi="['system:order:remove']"
          >退货
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
        <el-form-item label="用户标识" prop="openid">
          <el-input placeholder="请输入用户标识" v-model="form.openid"/>
        </el-form-item>
        <el-form-item label="是否关注公众账号" prop="isSubscribe">
          <el-input placeholder="请输入是否关注公众账号" v-model="form.isSubscribe"/>
        </el-form-item>
        <el-form-item label="交易类型" prop="tradeType">
          <el-select placeholder="请选择交易类型" v-model="form.tradeType">
            <el-option label="请选择字典生成" value=""/>
          </el-select>
        </el-form-item>
        <el-form-item label="付款银行" prop="bankType">
          <el-select placeholder="请选择付款银行" v-model="form.bankType">
            <el-option label="请选择字典生成" value=""/>
          </el-select>
        </el-form-item>
        <el-form-item label="货币类型" prop="feeType">
          <el-select placeholder="请选择货币类型" v-model="form.feeType">
            <el-option label="请选择字典生成" value=""/>
          </el-select>
        </el-form-item>
        <el-form-item label="订单金额" prop="totalFee">
          <el-input placeholder="请输入订单金额" v-model="form.totalFee"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input placeholder="请输入内容" type="textarea" v-model="form.remark"/>
        </el-form-item>
        <el-form-item label="应结订单金额" prop="settlementTotalFee">
          <el-input placeholder="请输入应结订单金额" v-model="form.settlementTotalFee"/>
        </el-form-item>
        <el-form-item label="代金券金额" prop="couponFee">
          <el-input placeholder="请输入代金券金额" v-model="form.couponFee"/>
        </el-form-item>
        <el-form-item label="现金支付货币类型" prop="cashFeeType">
          <el-select placeholder="请选择现金支付货币类型" v-model="form.cashFeeType">
            <el-option label="请选择字典生成" value=""/>
          </el-select>
        </el-form-item>
        <el-form-item label="现金支付金额" prop="cashFee">
          <el-input placeholder="请输入现金支付金额" v-model="form.cashFee"/>
        </el-form-item>
        <el-form-item label="微信支付订单号" prop="transactionId">
          <el-input placeholder="请输入微信支付订单号" v-model="form.transactionId"/>
        </el-form-item>
        <el-form-item label="商家数据包" prop="attach">
          <el-input placeholder="请输入商家数据包" v-model="form.attach"/>
        </el-form-item>
        <el-form-item label="支付完成时间" prop="timeEnd">
          <el-date-picker clearable placeholder="选择支付完成时间"
                          size="small"
                          type="date"
                          v-model="form.timeEnd"
                          value-format="yyyy-MM-dd">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="营销详情" prop="promotionDetail">
          <el-input placeholder="请输入内容" type="textarea" v-model="form.promotionDetail"/>
        </el-form-item>
        <el-form-item label="商品ID" prop="commodityId">
          <el-input placeholder="请输入商品ID" v-model="form.commodityId"/>
        </el-form-item>
        <el-form-item label="终端ID" prop="spbillCreateIp">
          <el-input placeholder="请输入终端ID" v-model="form.spbillCreateIp"/>
        </el-form-item>
        <el-form-item label="订单是否开发票" prop="isReceipt">
          <el-input placeholder="请输入订单是否开发票" v-model="form.isReceipt"/>
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
    listOrder,
    getOrder,
    delOrder,
    returnGoods,
    addOrder,
    updateOrder,
    exportOrder
  } from "@/api/system/order";

  export default {
    name: "Order",
    components: {},
    data() {
      return {
        tradeTypeOptions : [],
        yesNoOptions: [],
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
          openid: null,
          isSubscribe: null,
          tradeType: null,
          bankType: null,
          feeType: null,
          totalFee: null,
          settlementTotalFee: null,
          couponFee: null,
          cashFeeType: null,
          cashFee: null,
          transactionId: null,
          attach: null,
          timeEnd: null,
          promotionDetail: null,
          commodityId: null,
          spbillCreateIp: null,
          isReceipt: null
        },
        // 表单参数
        form: {}
        ,
        // 表单校验
        rules: {
          openid: [
            {
              required: true,
              message: "用户标识不能为空", trigger: "blur"
            }
          ],
          isSubscribe: [
            {
              required: true,
              message: "是否关注公众账号不能为空", trigger: "blur"
            }
          ],
          tradeType: [
            {
              required: true,
              message: "交易类型不能为空", trigger: "change"
            }
          ],
          bankType: [
            {
              required: true,
              message: "付款银行不能为空", trigger: "change"
            }
          ],
          feeType: [
            {
              required: true,
              message: "货币类型不能为空", trigger: "change"
            }
          ],
          totalFee: [
            {
              required: true,
              message: "订单金额不能为空", trigger: "blur"
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
          cashFeeType: [
            {
              required: true,
              message: "现金支付货币类型不能为空", trigger: "change"
            }
          ],
          cashFee: [
            {
              required: true,
              message: "现金支付金额不能为空", trigger: "blur"
            }
          ],
          transactionId: [
            {
              required: true,
              message: "微信支付订单号不能为空", trigger: "blur"
            }
          ],
          attach: [
            {
              required: true,
              message: "商家数据包不能为空", trigger: "blur"
            }
          ],
          timeEnd: [
            {
              required: true,
              message: "支付完成时间不能为空", trigger: "blur"
            }
          ],
          promotionDetail: [
            {
              required: true,
              message: "营销详情不能为空", trigger: "blur"
            }
          ],
          commodityId: [
            {
              required: true,
              message: "商品ID不能为空", trigger: "blur"
            }
          ],
          spbillCreateIp: [
            {
              required: true,
              message: "终端ID不能为空", trigger: "blur"
            }
          ],
          isReceipt: [
            {
              required: true,
              message: "订单是否开发票不能为空", trigger: "blur"
            }
          ]
        }
      }
        ;
    },
    created() {
      this.getList();
      this.getDicts("wx_trade_type").then(response => {
        this.tradeTypeOptions = response.data;
      });
      this.getDicts("sys_yes_no").then(response => {
        this.yesNoOptions = response.data;
      });
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
      tradeTypeFormat(row) {
        return this.selectDictLabel(this.tradeTypeOptions, row.tradeType);
      },
      yesNoFormat(row) {
        return this.selectDictLabel(this.yesNoOptions, row.isSubscribe);
      },
      yesNoFormat2(row) {
        return this.selectDictLabel(this.yesNoOptions, row.isReceipt);
      },
      //订单金额分转元
      totalFeeFormat(row) {
        return (row.totalFee / 100).toFixed(2)
      },
      // 表单重置
      reset() {
        this.form = {
          orderId: null,
          openid: null,
          isSubscribe: null,
          tradeType: null,
          bankType: null,
          feeType: null,
          totalFee: null,
          remark: null,
          settlementTotalFee: null,
          createBy: null,
          couponFee: null,
          createTime: null,
          cashFeeType: null,
          updateBy: null,
          cashFee: null,
          updateTime: null,
          transactionId: null,
          outTradeNo: null,
          attach: null,
          timeEnd: null,
          promotionDetail: null,
          commodityId: null,
          spbillCreateIp: null,
          isReceipt: null
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
            this
              .form = response.data;
            this
              .open = true;
            this
              .title = "";
          }
        )

      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
            if (valid) {
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
        this.$confirm('是否确认删除订单编号为"' + orderIds + '"的数据项?', "警告", {
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

      /** 退货按钮 */
      returnGoods(row) {
        const orderIds = row.orderId || this.ids;
        this.$confirm('是否确认退货，订单编号为"' + orderIds + '"?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return returnGoods(row);
        }).then(() => {
            this.getList();
            this.msgSuccess("退货成功");
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
