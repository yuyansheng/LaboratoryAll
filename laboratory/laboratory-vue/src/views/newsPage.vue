<script >
import axios from "axios";
import authority from "../utils/authority";


export default {
  computed: {
    authority() {
      return authority
    },
    getImageURL(){
      return this.tempImageURL;
    }
  },
  props:{
    isAddMode:{
      type: Boolean,
      required: false,
      default: false
    }
  },

  data() {
    return {
      tempImageURL:"",
      pageMode:"preview", // preview 预览， addNews添加新新闻
      newsUploadData:{
        fileType:"NEWS_IMAGE"
      },
      tempNews:{},
      isEditMod:false,
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
      news:{
        image:"",
        title:"",
        releaseTime:Date.now(),
        content:"",
        authorId:"",
        updateTime:Date.now(),
        intro:"",
        readTimes:0,
        isDelete:false,
      },
    };
  },
  methods: {
    timestampToDate(timestamp){
      let date = new Date(timestamp)
      return date.toLocaleDateString()
    },
    getUploadUrl() {
      // 可以在这里动态生成上传地址，可以基于当前环境、用户权限等条件生成
      return this.$baseURL+'/file/upload';
    },
    handleSuccess(response, file, fileList) {
      console.log('文件上传成功，URL为：', response);
      this.tempNews.image = response;  // 假设 response 包含图片的文件名或路径
      this.loadNewsImage();  // 直接调用loadNewsImage并更新tempImageURL
    },
    beforeUpload(file) {
      // 可以在这里添加文件上传前的处理，如文件大小、格式的检查

      return true;
    },
    changeEditMod(){
      this.isEditMod = !this.isEditMod
      console.log(this.tempImageURL)
      if(this.tempImageURL===""&&this.isEditMod){
        this.loadNewsImage()
      }
    },

    loadNewsPage(newsId) {
      axios.get(this.$baseURL+`/news/getNews/${newsId}`)
        .then((response)=>{
          this.news=response.data
          console.log(this.news)
          this.tempNews = this.news;
        }).catch((error)=>{
        console.error('请求数据失败',error)
      })
    },
    async loadNewsImage() {
      try {
        const response = await axios.get(this.$baseURL+'/file/load', {
          params: {
            fileName: this.tempNews.image,
            fileType: "NEWS_IMAGE"
          },
          responseType: 'arraybuffer',  // 返回二进制数据
        });

        const contentType = response.headers['content-type'];
        const base64Image = this.arrayBufferToBase64(response.data);
        this.tempImageURL = `data:${contentType};base64,${base64Image}`; // 更新 tempImageURL
      } catch (e) {
        console.error("文件加载失败", e);
        this.tempImageURL = ""; // 加载失败时清空 tempImageURL
      }
    },
    arrayBufferToBase64(buffer) {
      const byteArray = new Uint8Array(buffer);
      let binary = '';
      for (let i = 0; i < byteArray.length; i++) {
        binary += String.fromCharCode(byteArray[i]);
      }
      return window.btoa(binary); // 转换为 Base64
    }
  },

  created() {
    console.log("asdfsdfasdfasdfasfsadf")
  },
  mounted(){
    if(!this.$route.params.hasOwnProperty('newsId')){
      this.pageMode='addNews'
      this.isEditMod = true
    } else {
      this.loadNewsPage(this.$route.params.newsId)
    }
    this.tempNews = this.news
  }
}
</script>

<template>
  <div class="parent">
    <div class="avatar-username">
      <div>
        <el-avatar :size="30" src="@/assets/text.png"></el-avatar>
      </div>
      <div class="text">name</div>
      <div class="text">{{timestampToDate(this.news.releaseTime)}}</div>
      <el-button v-if="authority.canUse(this.$loginUser.user,1)" size="mini" @click="changeEditMod" style="color:#569696;justify-self: flex-end;" plain>{{isEditMod?'预览':'编辑'}}</el-button>
    </div>
      <div v-if="!isEditMod">
        <div  class="title">{{this.tempNews.title}}</div>
        <div class="text">Updated: {{timestampToDate(this.news.updateTime)}}</div>
        <div class="main-body" v-html="this.tempNews.content"></div>
      </div>
      <div v-if="isEditMod">
        <el-form label-position="left" :model="tempNews" label-width="80px">
        <el-form-item label="title">
          <el-input v-model="tempNews.title"></el-input>
        </el-form-item>
          <el-form-item label="content">
            <quill-editor v-model="tempNews.content"></quill-editor>
          </el-form-item>
          <el-form-item label="intro">
            <el-input type="textarea" v-model="tempNews.intro"></el-input>
          </el-form-item>
          <el-form-item label="Cover Image">
            <el-upload
              v-model="tempNews.image"
              class="upload-demo"
              :action="getUploadUrl()"
              :show-file-list="false"
              :data="newsUploadData"
              :on-success="handleSuccess"
              :before-upload="beforeUpload">
              <img v-if="tempImageURL" :src="tempImageURL" class="news-image" />
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
        </el-form>
      </div>
  </div>
</template>

<style scoped>
.main-body{
  margin-top: 20px;
  font-size:20px;
}
.text {
  display: flex;
  font-size: 12px;
  margin-bottom: 0;
}
.news-image {
  width: 300px;
  height: 200px;
}
.avatar-username {
  display: grid;
  grid-auto-flow: column; /* 按列排布 */
  align-items: center;
  width: 100%;
  justify-content: flex-start; /* 左对齐 */
}

.avatar-username > * {
  margin-right: 10px; /* 每个元素右侧的间距为 20px */
}

.title {
  margin-top: 20px;
  margin-bottom: 10px;
  color: #345a5a;
  font-weight: bold;
  font-size: 25px;
}
.parent {
  margin-top: 20px;
  margin-bottom: 20px;
  border: 1px solid darkgray;
  background-color: rgba(255, 255, 255, 0.5);
  display: flex;
  justify-content: flex-start; /* 元素水平靠左 */
  align-items: flex-start; /* 垂直靠上 */
  width: 55%;
  min-height: 800px;
  padding: 40px 60px;
}
</style>

