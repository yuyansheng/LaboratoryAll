<script >
import axios from "axios";
import authority from "../utils/authority";
import {API_PATH} from "../config";


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
    API_PATH() {
      return API_PATH
    },
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
      this.tempNews.image = response.filePath;  // response 包含图片的文件名或路径
      console.log(this.tempNews.image)
      console.log(this.$baseURL)

    },
    beforeUpload(file) {
      // 可以在这里添加文件上传前的处理，如文件大小、格式的检查

      return true;
    },
    changeEditMod(){
      this.isEditMod = !this.isEditMod
      console.log(this.tempImageURL)
    },

    loadNewsPage(newsId) {
      axios.get(API_PATH+`/laboratory/news/${newsId}`)
        .then((response)=>{
          console.log(response)
          this.news=response.data.data
          console.log(this.news)
          this.tempNews = this.news;
          axios.get(API_PATH+'/laboratory/member/'+this.news.authorId).then(response=>{
            this.$set(this.news,'member',response.data.data)
          })
        }).catch((error)=>{
        console.error('请求数据失败',error)
      })
    },
  },

  created() {
    console.log()
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
        <el-avatar :size="30" :src="API_PATH()+news.member.image"></el-avatar>
      </div>
      <div class="text">{{news.member.name}}</div>
      <div class="text">{{(new Date(news.releaseTime)).toLocaleDateString('en-GB', { year: 'numeric', month: 'short', day: 'numeric' })}}</div>
      <el-button v-if="authority.canUse(this.$loginUser.user,1)" size="mini" @click="changeEditMod" style="color:#569696;justify-self: flex-end;" plain>{{isEditMod?'预览':'编辑'}}</el-button>
    </div>

      <div v-if="!isEditMod">
        <div  class="title">{{this.tempNews.title}}</div>
        <div class="text">Updated: {{(new Date(news.updateTime)).toLocaleDateString('en-GB', { year: 'numeric', month: 'short', day: 'numeric' })}}</div>
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
              <img v-if="this.tempNews.image" :src="this.$baseURL+'/'+this.tempNews.image" class="news-image" />
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
  flex-direction: column;
  align-items: flex-start; /* 垂直靠上 */
  width: 55%;
  min-height: 800px;
  padding: 40px 60px;
}
</style>

