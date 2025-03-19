<script>
import axios from "axios";
import process from "shelljs";
import {API_PATH} from "../config";

export default {
  data() {
    return {
      pageSize:5,
      pageNum:1,
      totalNewsCount:0,
      news:[{
       id:1,
       image:"https://imgs.699pic.com/images/500/359/293.jpg!seo.v1",
       title:"sdflsakfjasdf",
       releaseTime:Date.now(),
       content:"fsfdfadfadsfafewfsdfwsefse sf",
       authorId:"",
       intro:"fasdfdsfadf fasf sfasdf as sf asf safdaegesf sfa sf asf s saf sdf a saf sdaf  sadf sdf sadf sa fs af a adsf ads as ssd as  afd as",
       readTimes:123,
       is_delete:false,
     },
       {
         id:2,
         image:require('@/assets/text.png'),
         title:"sdflsakfjasdf",
         releaseTime:Date.now(),
         content:"fsfdfadfadsfafewfsdfwsefsef",
         authorId:"",
         intro:"fasdfdsfadf",
         readTimes:123,
         is_delete:false,
       }]

    };
  },
  methods: {
    handlePageChange(pageNum){
      this.pageNum=pageNum
      console.log(this.pageNum+" "+this.pageSize)
      this.loadNews()
      this.$refs.news.scrollIntoView({ behavior: 'smooth' });
    },
    API_PATH() {
      return API_PATH
    },
    /// 加载新闻
    loadNews() {
      console.log("!!!!"+process.env.VUE_APP_API_PATH)
      axios.get(API_PATH+'/laboratory/news/list',
        {
          params:{
            pageSize:this.pageSize,
            pageNum:this.pageNum,
            releaseTime:new Date().toISOString().split('T')[0],
            orderByColumn:'releasetime',
            isAsc:'descending',
            isDelete:'0',
          },
        })
        .then((response)=>{
          this.news=response.data.rows;
          console.log(response)
          this.totalNewsCount=response.data.total
          this.news.forEach(news=>{
            axios.get(API_PATH+'/laboratory/member/'+news.authorId).then((response)=>{
              this.$set(news,'member',response.data.data)
              console.log(response.data)
            })
          })
          this.totalNewsCount=response.data.total
          console.log(this.news)
        }).catch((error)=>{
          console.error('请求数据失败',error)
      })
    },
    updateViews(index){
      const today = new Date().toISOString().split('T')[0]; // 获取今天的日期
      const currentNewsId = this.news[index].id;

// 从 localStorage 获取该新闻的最后触发时间
      const lastTriggeredTime = JSON.parse(localStorage.getItem('lastTriggeredTime')) || {};

// 获取当前新闻的最后触发时间
      const lastTriggerDate = lastTriggeredTime[currentNewsId];
      console.log(lastTriggerDate)
// 如果新闻的最后触发日期与今天相同，则不允许再次触发
      if (lastTriggerDate === today) {
        return;
      }

// 更新 localStorage 中该新闻的最后触发时间为今天
      lastTriggeredTime[currentNewsId] = today;
      localStorage.setItem('lastTriggeredTime', JSON.stringify(lastTriggeredTime));

// 更新新闻的阅读次数
      this.news[index].readTimes =  (parseInt(this.news[index].readTimes) || 0) + 1;
      axios.put(API_PATH + '/laboratory/news/update/views',null, {
        params:{
          views: this.news[index].readTimes,
          newsId: this.news[index].id
        }
      })
        .then(response => {
          console.log('Update successful', response);
        })
        .catch(error => {
          console.error('Error updating news:', error);
        });
    },
    handleClick(index,newsId){
      this.updateViews(index)
      this.$router.push({name:'newsPage',params:{newsId:newsId}})
    },
  },
  mounted() {
    this.loadNews();
  },

};
</script>

<template>
  <div class="parent" ref="news">
    <div v-for="(item,index) in news" :key="index" class="news-parent" @click="handleClick(index,item.id)">
      <img class="news-image" :src="API_PATH() + item.image" alt="news-image">
      <div class="news-data">

        <div class="avatar-username" v-if="item.hasOwnProperty('member')">
          <div>
            <el-avatar :size="30" :src="API_PATH()+item.member.image"></el-avatar>
          </div>
          <div class="user-date-text">
            <div>{{item.member.name}}</div>
            <div>{{(new Date(item.releaseTime)).toLocaleDateString('en-GB', { year: 'numeric', month: 'short', day: 'numeric' }) }}</div>
          </div>
        </div>

        <div class="news-title">
          {{item.title}}
        </div>

        <div class="news-intro">
          {{item.intro}}
        </div>

        <el-divider class="news-diver" ></el-divider>
        <div style="font-size: 13px;color: #569696" >
          {{item.readTimes}} views
        </div>

      </div>

    </div>
    <div class="left-alignment">
      <el-pagination
        layout="prev, pager, next"
        @current-change="handlePageChange"
        :page-size="this.pageSize"
        :current-page="this.pageNum"
        :total="totalNewsCount">
      </el-pagination>
    </div>
  </div>
</template>

<style scoped>
.news-diver{
  margin: 10px auto;
}
.news-intro{
  display: -webkit-box;
  height: 40%;
  -webkit-box-orient: vertical;
  overflow: hidden;
  text-overflow: ellipsis;
  color: #569696;
  width: 95%;
  word-wrap: break-word;
  -webkit-line-clamp: 3;
}
.news-title{
  font-size: 22px;
  margin-bottom: 10px;
  color: #345a5a;
}
.user-date-text {
  margin-left: 10px;
  display: flex;
  flex-direction: column; /* 垂直排列子项 */
  align-items: flex-start; /* 水平右对齐 */
  justify-content: space-between;
  font-size: 12px;
  color: #569696;
  margin-bottom: 0;
}
.avatar-username{
  display: flex;
  justify-content: flex-start;
  height: 30px;
  margin-bottom: 10px;
}

.parent {
  margin-top: 20px;
  width: 70%; /* 设置宽度为100% */
  display: flex;
  justify-content: center; /* 居中所有新闻项 */
  flex-wrap: wrap; /* 允许换行 */
}
.news-image{
  width: 50%;
  height: 100%;
  object-fit: fill;
}
.news-data{
  width: 50%;
  height: 100%;
  padding-top: 10px;
  padding-left: 10px;
  padding-right: 10px;
  align-items: center;
}
.news-parent {
  border: 1px solid darkgray;
  cursor: pointer;
  display: flex;
  justify-content: space-between;
  align-items: center; /* 垂直居中 */
  width: 85%;
  height: 250px;
  background-color: rgba(255, 255, 255, 0.5);
  margin-bottom: 10px;
}
.left-alignment {
  margin-top: 20px;
  margin-bottom: 20px;
  display: flex;
  justify-content: flex-start;
  width: 100%;
}
</style>
