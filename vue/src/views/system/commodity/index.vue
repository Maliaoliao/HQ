<template>
  <div class="app-container">
    <el-form :inline="true" :model="queryParams" label-width="68px" ref="queryForm" v-show="showSearch">
      <el-form-item label="名称" prop="name">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入名称"
          size="small"
          v-model="queryParams.name"
        />
      </el-form-item>
      <el-form-item label="价格(分)" prop="price">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入价格(分)"
          size="small"
          v-model="queryParams.price"
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
      <el-form-item label="餐箱编号" prop="boxNum">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入餐箱编号"
          size="small"
          v-model="queryParams.boxNum"
        />
      </el-form-item>
      <el-form-item label="是否失效" prop="commodityEnabled">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入是否失效"
          size="small"
          v-model="queryParams.commodityEnabled"
        />
      </el-form-item>
      <el-form-item label="主菜" prop="mainCourse">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入主菜"
          size="small"
          v-model="queryParams.mainCourse"
        />
      </el-form-item>
      <el-form-item label="次菜" prop="minorDish">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入次菜"
          size="small"
          v-model="queryParams.minorDish"
        />
      </el-form-item>
      <el-form-item label="辅菜" prop="sideDish">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入辅菜"
          size="small"
          v-model="queryParams.sideDish"
        />
      </el-form-item>
      <el-form-item label="主食" prop="stapleFood">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入主食"
          size="small"
          v-model="queryParams.stapleFood"
        />
      </el-form-item>
      <el-form-item label="描述" prop="description">
        <el-input
          @keyup.enter.native="handleQuery"
          clearable
          placeholder="请输入描述"
          size="small"
          v-model="queryParams.description"
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
          v-hasPermi="['system:commodity:add']"
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
          v-hasPermi="['system:commodity:edit']"
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
          v-hasPermi="['system:commodity:remove']"
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
          v-hasPermi="['system:commodity:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table :data="commodityList" @selection-change="handleSelectionChange" v-loading="loading">
      <el-table-column align="center" type="selection" width="55"/>
      <el-table-column align="center" label="商品ID" prop="commodityId"/>
      <el-table-column align="center" label="名称" prop="name"/>
      <el-table-column align="center" label="价格(分)" prop="price"/>
      <el-table-column align="center" label="厨房ID" prop="kitchenId"/>
      <el-table-column align="center" label="餐箱编号" prop="boxNum"/>
      <el-table-column align="center" label="是否失效" prop="commodityEnabled"/>
      <el-table-column align="center" label="备注" prop="remark"/>
      <el-table-column align="center" label="主菜" prop="mainCourse"/>
      <el-table-column align="center" label="次菜" prop="minorDish"/>
      <el-table-column align="center" label="辅菜" prop="sideDish"/>
      <el-table-column align="center" label="主食" prop="stapleFood"/>
      <el-table-column align="center" label="描述" prop="description"/>
      <el-table-column align="center" label="商品海报" prop="picture">
        <template scope="scope">
          <el-popover
            placement="top-start"
            title="标题"
            width="200"
            trigger="hover"
            content="预览大图，不会做！！" >

            <el-image slot="reference" width="120px" height="120px" src="https://img2.baidu.com/it/u=1395980100,2999837177&fm=253&fmt=auto&app=120&f=JPEG?w=1200&h=675" />
          </el-popover>
          </template>
      </el-table-column>

      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template slot-scope="scope">
          <el-button
            @click="handleUpdate(scope.row)"
            icon="el-icon-edit"
            size="mini"
            type="text"
            v-hasPermi="['system:commodity:edit']"
          >修改
          </el-button>
          <el-button
            @click="handleDelete(scope.row)"
            icon="el-icon-delete"
            size="mini"
            type="text"
            v-hasPermi="['system:commodity:remove']"
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
        <el-form-item label="名称" prop="name">
          <el-input placeholder="请输入名称" v-model="form.name"/>
        </el-form-item>
        <el-form-item label="价格(分)" prop="price">
          <el-input placeholder="请输入价格(分)" v-model="form.price"/>
        </el-form-item>
        <el-form-item label="厨房ID" prop="kitchenId">
          <el-input placeholder="请输入厨房ID" v-model="form.kitchenId"/>
        </el-form-item>
        <el-form-item label="餐箱编号" prop="boxNum">
          <el-input placeholder="请输入餐箱编号" v-model="form.boxNum"/>
        </el-form-item>
        <el-form-item label="是否失效" prop="commodityEnabled">
          <el-input placeholder="请输入是否失效" v-model="form.commodityEnabled"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input placeholder="请输入内容" type="textarea" v-model="form.remark"/>
        </el-form-item>
        <el-form-item label="主菜" prop="mainCourse">
          <el-input placeholder="请输入主菜" v-model="form.mainCourse"/>
        </el-form-item>
        <el-form-item label="次菜" prop="minorDish">
          <el-input placeholder="请输入次菜" v-model="form.minorDish"/>
        </el-form-item>
        <el-form-item label="辅菜" prop="sideDish">
          <el-input placeholder="请输入辅菜" v-model="form.sideDish"/>
        </el-form-item>
        <el-form-item label="主食" prop="stapleFood">
          <el-input placeholder="请输入主食" v-model="form.stapleFood"/>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input placeholder="请输入描述" v-model="form.description"/>
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
    listCommodity,
    getCommodity,
    delCommodity,
    addCommodity,
    updateCommodity,
    exportCommodity
  } from "@/api/system/commodity";

  export default {
    name: "Commodity",
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
        commodityList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          name: null,
          price: null,
          kitchenId: null,
          boxNum: null,
          commodityEnabled: null,
          mainCourse: null,
          minorDish: null,
          sideDish: null,
          stapleFood: null,
          description: null,
          picture: null
        },
        // 表单参数
        form: {}
        ,
        // 表单校验
        rules: {
          name: [
            {
              required: true,
              message: "名称不能为空", trigger: "blur"
            }
          ],
          price: [
            {
              required: true,
              message: "价格(分)不能为空", trigger: "blur"
            }
          ],
          kitchenId: [
            {
              required: true,
              message: "厨房ID不能为空", trigger: "blur"
            }
          ],
          boxNum: [
            {
              required: true,
              message: "餐箱编号不能为空", trigger: "blur"
            }
          ],
          commodityEnabled: [
            {
              required: true,
              message: "是否失效不能为空", trigger: "blur"
            }
          ],
          mainCourse: [
            {
              required: true,
              message: "主菜不能为空", trigger: "blur"
            }
          ],
          createBy: [
            {
              required: true,
              message: "创建者不能为空", trigger: "blur"
            }
          ],
          minorDish: [
            {
              required: true,
              message: "次菜不能为空", trigger: "blur"
            }
          ],
          createTime: [
            {
              required: true,
              message: "创建时间不能为空", trigger: "blur"
            }
          ],
          sideDish: [
            {
              required: true,
              message: "辅菜不能为空", trigger: "blur"
            }
          ],
          stapleFood: [
            {
              required: true,
              message: "主食不能为空", trigger: "blur"
            }
          ],
          description: [
            {
              required: true,
              message: "描述不能为空", trigger: "blur"
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
          name: null,
          price: null,
          kitchenId: null,
          boxNum: null,
          commodityEnabled: null,
          remark: null,
          mainCourse: null,
          createBy: null,
          minorDish: null,
          createTime: null,
          sideDish: null,
          updateBy: null,
          stapleFood: null,
          updateTime: null,
          description: null,
          picture: null
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
            if (valid) {
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
          return
          exportCommodity(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
      }
    }
  };
</script>
