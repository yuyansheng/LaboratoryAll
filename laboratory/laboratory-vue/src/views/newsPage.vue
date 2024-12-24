<script >
import axios from "axios";
import {nextTick} from "vue";

export default {
  data() {
    return {
      data:{
        fileType:''
      },
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
        id:1,
        image:require('@/assets/text.png'),
        title:"sdflsakfjasdf",
        releaseTime:Date.now(),
        content:"fsfdfadfadsfafewfsdfwsefse sf",
        authorId:"",
        updateTime:Date.now(),
        intro:"fasdfdsfadf fasf sfasdf as sf asf safdaegesf sfa sf asf s saf sdf a saf sdaf  sadf sdf sadf sa fs af a adsf ads as ssd as  afd as",
        read_times:123,
        is_delete:false,},
      tempNews:{},
      tempContent:"",
      isEditMod:true
    };
  },
  methods: {
    timestampToDate(timestamp){
      let date = new Date(timestamp)
      return date.toLocaleDateString()
    },
    uploadData(){
      return this.data
    },
    getUploadUrl() {
      // 可以在这里动态生成上传地址，可以基于当前环境、用户权限等条件生成
      return this.$baseURL+'/file/upload';
    },
    handleSuccess(response, file, fileList) {
      // 后端返回的文件URL
      console.log('文件上传成功，URL为：', response);
      // 可以在这里将URL保存起来或者展示给用户
    },
    beforeUpload(file) {
      // 可以在这里添加文件上传前的处理，如文件大小、格式的检查

      // 传输文件类型枚举
       // 这里假设是图片类型
      this.data.fileType = "NEWS_IMAGE"
      // 返回 true 表示继续上传，false 表示取消上传
      return true;
    },
    changeEditMod(){
      this.isEditMod = !this.isEditMod
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
  },
  mounted(){
    this.loadNewsPage(this.$route.params.newsId)
    console.log(this.news.id);
    this.tempContent = this.news.content;
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
      <el-button size="mini" @click="changeEditMod" style="color:#569696;justify-self: flex-end;" plain>{{isEditMod?'预览':'编辑'}}</el-button>
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
              :data="data"
              :on-success="handleSuccess"
              :before-upload="beforeUpload">
              <img v-if="tempNews.image" :src="tempNews.image" class="news-image">
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

