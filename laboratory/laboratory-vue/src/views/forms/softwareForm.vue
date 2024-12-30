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
      uploadParams:{
        fileType:""
      },
      localFormData:this.formData,
      softwareFileList: [],  // 用于控制软件文件上传
      documentFileList: [],  // 用于控制说明文档上传
      softwareFileUrl: null,  // 存储上传的软件下载地址
      documentFileUrl: null,  // 存储上传的文档地址
      rules: {
        name: [{required: true, message: '请输入软件名称', trigger: 'blur'}],
        intro: [{required: false, message: '请输入软件简介', trigger: 'blur'}],
      },
    };
  },
  methods: {
    // 校验上传文件
    beforeUploadSoftware(file) {
      console.log(file)

      this.uploadParams.fileType="SOFTWARE"
      return true;
    },
    beforeUploadReadme(file) {
      this.uploadParams.fileType="README_TEXT"
      return true;
    },
    // 处理软件文件上传成功
    handleSoftwareFileSuccess(response) {
      console.log(response)
      this.softwareFileUrl = response; // 假设响应中返回了文件的URL
      this.localFormData.downloadUrl = response;
      this.softwareFileList = [];  // 清空文件列表，确保只能上传一个文件
    },

    // 处理说明文档文件上传成功
    handleDocumentFileSuccess(response) {
      this.documentFileUrl = response; // 假设响应中返回了文件的URL
      this.localFormData.documentUrl = response;
      this.documentFileList = [];  // 清空文件列表，确保只能上传一个文件
    },

    // 提交表单
    handleSubmit() {
      console.log(this.localFormData)
      this.$axios.post(this.$baseURL + '/software/add', this.formData)
        .then(response => {
          // 显示成功消息
          this.$message.success(`软件创建成功`);
          // this.$router.push('/team')
          this.$emit("submitForm")
        })
        .catch(error => {
          // 错误处理
          this.$message.error('创建软件失败');
        });
    },
    getUploadUrl() {
      // 可以在这里动态生成上传地址，可以基于当前环境、用户权限等条件生成
      return this.$baseURL+'/file/upload';
    },
  },
};
</script>

<template>
  <el-form :model="localFormData" :rules="rules" ref="form" label-width="120px" @submit.prevent="handleSubmit">
    <el-form-item label="软件名称" prop="name">
      <el-input v-model="localFormData.name" placeholder="请输入软件名称"></el-input>
    </el-form-item>

    <el-form-item label="软件简介" prop="intro">
      <el-input type="textarea" v-model="localFormData.intro" placeholder="请输入软件简介" :rows="4"></el-input>
    </el-form-item>

    <el-form-item label="上传软件文件" prop="software_file">
      <el-upload
        :action="getUploadUrl()"
        :data="uploadParams"
        :on-success="handleSoftwareFileSuccess"
        :before-upload="beforeUploadSoftware"
        :file-list="softwareFileList"
        :show-file-list="true"
        accept=".zip,.tar,.rar"
        :limit="1"
        :auto-upload="true"
        ref="softwareFileUpload"
        trigger="button">
        <el-button type="primary">选择软件文件</el-button>
      </el-upload>
      <el-tag v-if="softwareFileUrl" type="success" disable-transitions>{{ softwareFileUrl.split('_').slice(1).join('_') }}</el-tag>
    </el-form-item>

    <el-form-item label="上传说明文档" prop="document_file">
      <el-upload
        :action="getUploadUrl()"
        :data="uploadParams"
        :on-success="handleDocumentFileSuccess"
        :before-upload="beforeUploadReadme"
        :file-list="documentFileList"
        :show-file-list="true"
        accept=".pdf,doc,.docx"
        :limit="1"
        :auto-upload="true"
        ref="documentFileUpload"
        trigger="button">
        <el-button type="primary">选择说明文档</el-button>
      </el-upload>
      <el-tag v-if="documentFileUrl" type="success" disable-transitions>{{ documentFileUrl.split('_').slice(1).join('_') }}</el-tag>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="handleSubmit">提交</el-button>
    </el-form-item>
  </el-form>
</template>



<style scoped>
/* 你可以根据需要添加自定义样式 */
</style>
