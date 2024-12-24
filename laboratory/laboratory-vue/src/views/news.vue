<script>
import axios from "axios";

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
    loadNews() {
      axios.get(this.$baseURL+'/news/paged',
        {
          params:{
            pageSize:this.pageSize,
            pageNum:this.pageNum,
          },
        })
        .then((response)=>{
          this.news=response.data.data;
          this.totalNewsCount=response.data.totalNewsCount
          console.log(this.news)
        }).catch((error)=>{
          console.error('请求数据失败',error)
      })
    },
    updateViews(index){
      this.news[index].readTimes += 1
      axios.put(this.$baseURL+'/news/update',this.news[index])
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
    timestampToDate(timestamp){
      let date = new Date(timestamp)
      return date.toLocaleDateString()
    }
  },
  mounted() {
    this.loadNews();
  }
};
</script>

<template>
  <div class="parent">
    <div v-for="(item,index) in news" :key="index" class="news-parent" @click="handleClick(index,item.id)">
      <img class="news-image" :src="item.image" alt="news-image">
      <div class="news-data">

        <div class="avatar-username">
          <div>
            <el-avatar :size="30" src="@/assets/text.png"></el-avatar>
          </div>
          <div class="user-date-text">
            <div>name</div>
            <div>{{timestampToDate(item.releaseTime)}}</div>
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
        :page-size="this.pageSize"
        :current-page="this.pageNum"
        :total="news.length">
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
  width: 70%;

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
  display: flex;
  justify-content: flex-start;
  width: 100%;
}
</style>
