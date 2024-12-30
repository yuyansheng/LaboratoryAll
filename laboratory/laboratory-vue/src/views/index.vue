<template>
  <div class="parent">
   <p class="title">Welcome to the IDEA lab!</p>
    <div v-if="authority.canUse(this.$loginUser.user,1)">
      <el-button  size="mini" @click="changeEditMod" style="color:#569696;justify-self: flex-end;" plain>{{isEditMod?'preview':'edit'}}</el-button>
      <el-button  size="mini" @click="saveWelcomeText" style="color:#569696;justify-self: flex-end;" plain>save</el-button>
      <el-button  size="mini" @click="reset" style="color:#569696;justify-self: flex-end;" plain>reset</el-button>
    </div>
    <el-divider class="title-divider"></el-divider> <!-- 横向分割线 -->
    <div  class="welcome-text">
      <quill-editor v-if="isEditMod"
        v-model="editorContent"
        ref="editor"
        :options="editorOptions"
      />
      <div v-if="!isEditMod" v-html="editorContent"></div>
    </div>
  </div>

</template>

<script>
import authority from "../utils/authority";
import axios from "axios";
export default {
  computed: {
    authority() {
      return authority
    },
  },
  data() {
    return {
      isEditMod:false,
      editorContent: '', // 编辑器内容
      displayContent: '', // 用于展示的内容
      editorOptions: {
        theme: 'snow',
        placeholder: '在这里输入内容...',
        modules: {
          toolbar: [
            [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
            ['bold', 'italic', 'underline', 'strike', 'blockquote', 'code-block'],
            [{ 'color': [] }, { 'background': [] }],
            [{ 'script': 'sub' }, { 'script': 'super' }],
            [{ 'list': 'ordered' }, { 'list': 'bullet' }],
            [{ 'indent': '-1' }, { 'indent': '+1' }],
            [{ 'align': [] }],
            ['link', 'image'],
            ['formula'], // 插入公式和表格（需要插件支持）
            ['clean'], // 清除格式
            [{ 'font': [] }],
            [{ 'size': ['small', 'normal', 'large', 'huge'] }]
          ],
        },
      },
    };
  },
  methods: {
    changeEditMod(){
      this.isEditMod = !this.isEditMod;
    },
    createFileFromContent(content) {
      // 将内容转为 Blob 对象
      const blob = new Blob([content], { type: 'text/plain' });
      // 使用 Blob 创建文件对象
      const file = new File([blob], 'welcomeText.txt', { type: 'text/plain' });
      return file;
    },
    uploadFile(file) {
      const formData = new FormData();
      formData.append('file', file);
      formData.append('fileType',"WELCOME_TEXT")
      formData.append('allowedRepetition',true)
      axios.post(this.$baseURL+'/file/upload', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      })
        .then(response => {
          this.$message.success('save success')
          console.log('上传成功', response.data);
        })
        .catch(error => {
          console.error('上传失败', error);
        });
    },
    saveWelcomeText() {
      const file = this.createFileFromContent(this.editorContent);
      this.uploadFile(file);
    },
    reset() {
      this.editorContent = this.displayContent
    },
    loadContent() {
      axios.get(this.$baseURL + '/file/load', {
        params: {
          fileType: "WELCOME_TEXT", // 用于指定后端文件类型
          fileName: "welcomeText.txt"
        },
        responseType: 'arraybuffer', // 接收字节流
      })
        .then(response => {
          const fileContent = new TextDecoder('utf-8').decode(response.data); // 假设后端直接返回文本内容
          this.displayContent = fileContent; // 设置展示内容
          this.editorContent = fileContent; // 同步设置编辑器内容
        })
        .catch(error => {
          console.error('加载失败', error);
          this.$message.error('加载失败，请稍后重试');
        });
    },
  },
  mounted() {
    this.loadContent()
  }
};
</script>

<style scoped>

.welcome-text {
  margin: 20px;
  width: 90%;
}
.parent {
  flex-direction: column; /* 纵向排列子元素 */
  justify-content: flex-start; /* 子元素顶部对齐 */
  align-items: center; /* 子元素水平居中 */
  display: flex;
  width: 70%;
}
.title {
  color: #345a5a;
  font-weight: bold;
  font-size: 20px;
}
.title-divider {
  border-top: 2px solid black; /* 设置分割线的粗细和颜色 */
  width: 150px; /* 设置分割线的宽度 */
  margin: 10px auto;
}
</style>


