<script>
import {toFormData} from "axios";

export default {
  props:{
    formData: {
      type:Object,
      required:true
    },
    isAddMode: {
      type:Boolean,
      required: false,
      default: true
    }
  },
  data() {
    return {
      localFormData:this.formData,
      rules: {
        name: [
          {required: true, message: '请输入出版物名称', trigger: 'blur'}
        ],
        releaseDate: [
          {required: true, message: '请选择发布日期', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm() {
      if(this.isAddMode){
        this.$axios.post(this.$baseURL + '/publication/add', this.localFormData)
          .then(response => {
            // 显示成功消息
            this.$message.success(`出版物创建成功`);
            this.$emit("submitForm")
          })
          .catch(error => {
            // 错误处理
            this.$message.error('出版物创建失败');
          });
      } else {
        this.$axios.post(this.$baseURL + `/publication/update/${this.localFormData.id}`, this.localFormData)
          .then(response => {
            // 显示成功消息
            this.$message.success(`出版物创建成功`);
            this.$emit("submitForm")
          })
          .catch(error => {
            // 错误处理
            this.$message.error('出版物创建失败');
          });
      }

    },
    resetForm() {
      this.$refs.formRef.resetFields();
    }
  }
}
</script>

<template>
  <el-form :model="localFormData" :rules="rules" ref="formRef" label-width="120px">
    <el-form-item label="出版物名称" prop="name">
      <el-input v-model="localFormData.name" placeholder="请输入出版物名称" />
    </el-form-item>

    <el-form-item label="出版物简介" prop="intro">
      <el-input type="textarea" v-model="localFormData.intro" placeholder="请输入出版物简介" />
    </el-form-item>

    <el-form-item label="出版物查阅地址" prop="downloadUrl">
      <el-input v-model="localFormData.downloadUrl" placeholder="请输入出版物查阅地址" />
    </el-form-item>

    <el-form-item label="出版物作者" prop="author">
      <el-input v-model="localFormData.author" placeholder="请输入出版物作者" />
    </el-form-item>

    <el-form-item label="发布日期" prop="releaseDate">
      <el-date-picker v-model="localFormData.releaseDate" type="date" placeholder="请选择发布日期" />
    </el-form-item>

    <el-form-item label="期刊名称" prop="periodicalName">
      <el-input v-model="localFormData.periodicalName" placeholder="请输入期刊名称" />
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="submitForm">提交</el-button>
    </el-form-item>
  </el-form>
</template>

<style scoped>

</style>
