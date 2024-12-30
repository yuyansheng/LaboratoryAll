<script>
export default {
  props:{
    formData: {
      type:Object,
      required:true
    },
  },
  data() {
    return {
      localFormData:this.formData,
      rules: {
        name: [
          { required: true, message: '请输入研究项目名称', trigger: 'blur' }
        ],
        intro: [
          { required: false, message: '请输入研究简介', trigger: 'blur' }
        ],
        url: [
          { required: false, message: '请输入研究项目地址', trigger: 'blur' }
        ],
        beginTimes: [
          { required: false, message: '请选择研究开始时间', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm() {

      this.localFormData.beginTimes = new Date(this.localFormData.beginTimes).getTime();
      console.log(this.localFormData)
      this.$axios.post(this.$baseURL + '/research/add', this.localFormData)
        .then(response => {


          // 显示成功消息
          this.$message.success(`团队创建成功`);
          this.$emit("submitForm")
        })
        .catch(error => {
          // 错误处理
          this.$message.error('创建团队失败');
        });
    },
  }
}
</script>

<template>
  <el-form :model="localFormData" :rules="rules" ref="form" label-width="120px" class="demo-ruleForm">
    <el-form-item label="研究项目名称" prop="name">
      <el-input v-model="localFormData.name" placeholder="请输入研究项目名称"></el-input>
    </el-form-item>

    <el-form-item label="研究简介" prop="intro">
      <el-input v-model="localFormData.intro" placeholder="请输入研究简介"></el-input>
    </el-form-item>

    <el-form-item label="研究项目地址" prop="url">
      <el-input v-model="localFormData.url" placeholder="请输入研究项目地址"></el-input>
    </el-form-item>

    <el-form-item label="研究开始时间" prop="beginTimes">
      <el-date-picker
        v-model="localFormData.beginTimes"
        type="datetime"
        placeholder="请选择研究开始时间"
        :clearable="true"
      ></el-date-picker>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="submitForm">提交</el-button>
    </el-form-item>
  </el-form>
</template>

<style scoped>

</style>
