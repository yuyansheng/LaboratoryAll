<template>
  <el-card class="box-card">
    <!-- Dialog 对话框 弹出新增和修改表单 -->
    <el-row>
      <el-button size="mini" type="primary" @click="add">新增</el-button>
      <el-dialog :title="title" :visible.sync="dialogFormVisible" width="30%">
        <el-form :model="form" :rules="rules" ref="form">
          <el-form-item label="id:" hidden>
            <el-input v-model="form.id"></el-input>
          </el-form-item>
          <el-form-item label="姓名:" prop="name">
            <el-input v-model="form.name" placeholder="请输入姓名" style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="年龄:" prop="age">
            <el-input v-model.number="form.age" placeholder="请输入年龄" style="width:80%"></el-input>
          </el-form-item>
          <el-form-item label="性别:" prop="gender">
            <el-select v-model="form.gender" placeholder="请选择性别" style="width:80%">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="邮箱:" prop="email">
            <el-input v-model="form.email" placeholder="请输入邮箱" style="width:80%"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submit()">提 交</el-button>
        </div>
      </el-dialog>
    </el-row>
    <div v-for="(item, index) in tableData" :key="item.id">
      <p>Index: {{ index }}</p>
      <p>ID: {{ item.id }}</p>
      <p>姓名: {{ item.name }}</p>
      <p>年龄: {{ item.age }}</p>
      <p>性别: {{ item.gender }}</p>
      <p>邮箱: {{ item.email }}</p>
    </div>
    <!-- 表格 -->
    <el-table
      ref="singleTable"
      :data="tableData"
      style="width: 100%">
      <el-table-column
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        property="id"
        label="ID"
        width="50"
        align="center">
      </el-table-column>
      <el-table-column
        property="name"
        label="姓名"
        width="120"
        align="center">
      </el-table-column>
      <el-table-column
        property="age"
        label="年龄"
        width="120"
        align="center">
      </el-table-column>
      <el-table-column
        property="gender"
        label="性别"
        width="120"
        align="center">
      </el-table-column>
      <el-table-column
        property="email"
        label="邮箱"
        align="center">
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="edit(scope.$index, scope.row)">编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="remove(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<script>
export default {
  name: "student",
  data() {
    return {
      title: '',
      currentRow: null,
      dialogFormVisible: false,
      form: {},
      tableData: [],
      rules: {
        name: [{required: true, message: '请输入姓名', trigger: 'blur'}],
        age: [{required: true, message: '请输入年龄', trigger: 'blur'},
          {type: 'number', message: '年龄必须为数字值', trigger: 'blur'},
          {pattern: /^(0|[1-9]\d?|200)$/, message: '范围在0-200', trigger: 'blur'}],
        gender: [{required: true, message: '请选择性别', trigger: 'change'}],
        email: [{required: true, message: '请输入邮箱', trigger: 'blur'}]
      }
    }
  },
  methods: {
    // 表单重置初始化
    reset() {
      this.form = {
        id: null,
        name: null,
        age: null,
        gender: null,
        email: null
      }
    },

    // 增
    add() {
      this.reset()
      this.dialogFormVisible = true
      this.title = "新增学生数据"
    },

    // 删
    remove(index, row) {
      console.log(row.id)
      this.$axios({
        method: 'post',
        url: 'http://localhost:9090/student/remove/' + row.id,
      }).then((response) => {
        this.$message({
          message: '删除成功!',
          type: 'success'
        });
        this.getList();
      }).catch((error) => {
      })
    },

    // 改
    edit(index, row) {
      this.reset()
      this.form = JSON.parse(JSON.stringify(row));
      this.dialogFormVisible = true
      this.title = "修改学生数据"
    },

    //查
    getList() {
      this.$axios({
        method: 'get',
        url: this.baseURL + '/student/info',
      }).then((response) => {
        this.tableData = JSON.parse(JSON.stringify(response.data));
      }).catch((error) => {
      })
    },

    //提交按钮
    submit() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.id == null) {
            this.$axios({
              method: 'post',
              data: this.form,
              url: 'http://localhost:9090/student/add',
            }).then((response) => {
              this.$message({
                message: '新增成功!',
                type: 'success'
              });
              this.dialogFormVisible = false
              this.getList();
            }).catch((error) => {
            })
          } else {
            this.$axios({
              method: 'post',
              data: this.form,
              url: 'http://localhost:9090/student/edit',
            }).then((response) => {
              this.$message({
                message: '修改成功!',
                type: 'success'
              });
              this.getList();
              this.dialogFormVisible = false
            }).catch((error) => {
            })
          }
        } else {
          return false;
        }
      })
    }
  },
  mounted() {
    this.getList();
  }
}
</script>

<style scoped>
</style>
