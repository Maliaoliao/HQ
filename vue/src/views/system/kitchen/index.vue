<template>
  <div class="app-container">
    <el-form :inline="true" :model="queryParams" label-width="68px" ref="queryForm" v-show="showSearch">
      <el-form-item label="厨房名称" prop="kitchenCode">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入厨房名称"
          size="small"
          v-model="queryParams.kitchenCode"
        />
      </el-form-item>
      <el-form-item label="厨房地址" prop="kitchenPosition">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入厨房地址"
          size="small"
          v-model="queryParams.kitchenPosition"
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
          v-hasPermi="['system:kitchen:add']"
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
          v-hasPermi="['system:kitchen:edit']"
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
          v-hasPermi="['system:kitchen:remove']"
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
          v-hasPermi="['system:kitchen:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table :data="kitchenList" @selection-change="handleSelectionChange" v-loading="loading">
      <el-table-column align="center" type="selection" width="55"/>
      <el-table-column align="center" label="厨房ID" prop="kitchenId"/>
      <el-table-column align="center" label="厨房名称" prop="kitchenCode"/>
      <el-table-column align="center" label="厨房地址" prop="kitchenPosition"/>
      <el-table-column align="center" label="厨房经理" prop="kitchenDirectorUserId"/>
      <el-table-column align="center" label="厨房合同编码" prop="kitchenContractCode"/>
      <el-table-column align="center" label="厨房开始租赁日期" prop="kitchenBeginTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.kitchenBeginTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="厨房结束租赁日期" prop="kitchenEndTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.kitchenEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="厨房租金" prop="kitchenRental"/>
      <el-table-column align="center" label="厨房押金" prop="kitchenForegift"/>
      <el-table-column align="center" label="房租到期是否提醒" prop="kitchenAttermRemind"/>
      <el-table-column align="center" label="厨房状态" prop="kitchenStatus"/>
      <el-table-column align="center" label="备注" prop="remark"/>
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template slot-scope="scope">
          <el-button
            @click="handleUpdate(scope.row)"
            icon="el-icon-edit"
            size="mini"
            type="text"
            v-hasPermi="['system:kitchen:edit']"
          >修改
          </el-button>
          <el-button
            @click="handleDelete(scope.row)"
            icon="el-icon-delete"
            size="mini"
            type="text"
            v-hasPermi="['system:kitchen:remove']"
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

    <!-- 添加或修改厨房对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-col :span="12">
        <el-form-item label="厨房名称" prop="kitchenCode">
          <el-input placeholder="请输入厨房名称" v-model="form.kitchenCode"/>
        </el-form-item>
        </el-col>
          <el-col :span="12">
        <el-form-item label="厨房地址" prop="kitchenPosition">
          <el-input placeholder="请输入厨房地址" v-model="form.kitchenPosition"/>
        </el-form-item>
          </el-col>
            <el-col :span="12">
        <el-form-item label="厨房经理" prop="kitchenDirectorUserId">
          <el-input placeholder="请输入厨房经理" v-model="form.kitchenDirectorUserId"/>
        </el-form-item>
            </el-col>
              <el-col :span="12">
        <el-form-item label="厨房合同编码" prop="kitchenContractCode">
          <el-input placeholder="请输入厨房合同编码" v-model="form.kitchenContractCode"/>
        </el-form-item>
              </el-col>
                <el-col :span="12">
        <el-form-item label="厨房开始租赁日期" prop="kitchenBeginTime">
          <el-date-picker clearable placeholder="选择厨房开始租赁日期"
                          size="small"
                          type="date"
                          v-model="form.kitchenBeginTime"
                          value-format="yyyy-MM-dd">
          </el-date-picker>
        </el-form-item>
                </el-col>
        <el-col :span="12">
        <el-form-item label="厨房结束租赁日期" prop="kitchenEndTime">
          <el-date-picker clearable placeholder="选择厨房结束租赁日期"
                          size="small"
                          type="date"
                          v-model="form.kitchenEndTime"
                          value-format="yyyy-MM-dd">
          </el-date-picker>
        </el-form-item>
        </el-col>
        <el-col :span="12">
        <el-form-item label="厨房租金" prop="kitchenRental">
          <el-input placeholder="请输入厨房租金" v-model="form.kitchenRental"/>
        </el-form-item>
        </el-col>
        <el-col :span="12">
        <el-form-item label="厨房押金" prop="kitchenForegift">
          <el-input placeholder="请输入厨房押金" v-model="form.kitchenForegift"/>
        </el-form-item>
        </el-col>
        <el-col :span="12">
        <el-form-item label="房租到期是否提醒 0不提醒 1提醒" prop="kitchenAttermRemind">
          <el-input placeholder="请输入房租到期是否提醒 0不提醒 1提醒" v-model="form.kitchenAttermRemind"/>
        </el-form-item>
        </el-col>
        <el-col :span="12">
        <el-form-item label="厨房状态 0 停用 1启用">
          <el-radio-group v-model="form.kitchenStatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        </el-col>
        <el-col :span="12">
        <el-form-item label="备注" prop="remark">
          <el-input placeholder="请输入内容" type="textarea" v-model="form.remark"/>
        </el-form-item>
        </el-col>
      </el-form>
      <div class="dialog-footer" slot="footer">
        <el-button @click="submitForm" type="primary">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {addKitchen, delKitchen, getKitchen, listKitchen, updateKitchen} from "@/api/system/kitchen";

  export default {
    name: "Kitchen",
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
        // 厨房表格数据
        kitchenList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          kitchenCode: null,
          kitchenPosition: null,
          kitchenDirectorUserId: null,
          kitchenContractCode: null,
          kitchenBeginTime: null,
          kitchenEndTime: null,
          kitchenRental: null,
          kitchenForegift: null,
          kitchenAttermRemind: null,
          kitchenStatus: null,
        },
        // 表单参数
        form: {}
        ,
        // 表单校验
        rules: {
          kitchenCode: [
            {
              required: true,
              message: "厨房名称不能为空", trigger: "blur"
            }
          ],
          kitchenPosition: [
            {
              required: true,
              message: "厨房地址不能为空", trigger: "blur"
            }
          ],
          kitchenDirectorUserId: [
            {
              required: true,
              message: "厨房经理不能为空", trigger: "blur"
            }
          ],
          kitchenContractCode: [
            {
              required: true,
              message: "厨房合同编码不能为空", trigger: "blur"
            }
          ],
          kitchenBeginTime: [
            {
              required: true,
              message: "厨房开始租赁日期不能为空", trigger: "blur"
            }
          ],
          kitchenEndTime: [
            {
              required: true,
              message: "厨房结束租赁日期不能为空", trigger: "blur"
            }
          ],
          kitchenRental: [
            {
              required: true,
              message: "厨房租金不能为空", trigger: "blur"
            }
          ],
          kitchenForegift: [
            {
              required: true,
              message: "厨房押金不能为空", trigger: "blur"
            }
          ],
          kitchenAttermRemind: [
            {
              required: true,
              message: "房租到期是否提醒 0不提醒 1提醒不能为空", trigger: "blur"
            }
          ],
          kitchenStatus: [
            {
              required: true,
              message: "厨房状态 0 停用 1启用不能为空", trigger: "blur"
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
      this.getDicts("sys_kitchen_status").then(response => {
        this.kitchenStatus = response.data;
      });
    },
    methods: {
      /** 查询厨房列表 */
      getList() {
        this.loading = true;
        listKitchen(this.queryParams).then(response => {
          this.kitchenList = response.rows;
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
          kitchenId: null,
          kitchenCode: null,
          kitchenPosition: null,
          kitchenDirectorUserId: null,
          kitchenContractCode: null,
          kitchenBeginTime: null,
          kitchenEndTime: null,
          kitchenRental: null,
          kitchenForegift: null,
          kitchenAttermRemind: null,
          kitchenStatus: "0",
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
        this.ids = selection.map(item => item.kitchenId
      )
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "新增厨房";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const kitchenId =
          row.kitchenId || this.ids
        getKitchen(kitchenId).then(response => {
          this
          .form = response.data;
          this
          .open = true;
          this
          .title = "厨房修改";
        }
      )

      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if(valid) {
            if (this.form.kitchenId != null) {
              updateKitchen(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            )

            } else {
              addKitchen(this.form).then(response => {
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
        const kitchenIds = row.kitchenId || this.ids;
        this.$confirm('是否确认删除厨房编号为"' + kitchenIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return delKitchen(kitchenIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }
      )
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有厨房数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return
        exportKitchen(queryParams);
        }).then(response => {
          this.download(response.msg);
        }
      )
      }
    }
  };
</script>
