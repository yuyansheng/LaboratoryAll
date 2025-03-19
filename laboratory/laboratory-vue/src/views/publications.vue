<script>
import axios from 'axios';
import {API_PATH} from "../config";

export default {
  data() {
    return {
      pageSize:10,
      pageNum:1,
      totalNewsCount:0,
      publications: [], // 用于存放出版物数据
      expandedPublication: null, // 用于记录哪个出版物被展开
      queryName:""// 用于查询名字和作者
    };
  },
  created() {
    this.fetchPublications(); // 页面加载时获取出版物数据
  },
  methods: {
    handlePageChange(pageNum){
      this.pageNum=pageNum
      console.log(this.pageNum+" "+this.pageSize)
      this.expandedPublication=null
      this.fetchPublications()
      this.$refs.publicationList.scrollIntoView({ behavior: 'smooth' });  // 平滑滚动到页面顶部
    },
    check(index){
      if(!this.query)
        return true
      if(this.publications[index].name.includes(this.query))
        return true
      if(this.publications[index].author.includes(this.query))
        return true
      else return false
    },
    async fetchPublications() {
      try {
        const response = await axios.get(API_PATH+'/laboratory/publication/list',{
          params:{
            pageNum:this.pageNum,
            pageSize:this.pageSize,
            name:this.queryName,
            orderByColumn:'releasedate',
            isAsc:'descending',
          }
        }); // 根据后端API地址进行调整
        console.log(response)
        this.totalNewsCount=response.data.total

        this.publications = response.data.rows;
      } catch (error) {
        console.error('获取出版物数据失败:', error);
      }
    },
    toggleDetail(index) {
      // 切换展开或收起详细信息
      if (this.expandedPublication === index) {
        this.expandedPublication = null;
      } else {
        this.expandedPublication = index;
      }
    },
    handleSearch(){
      this.fetchPublications()
      this.expandedPublication=null // 用于记录哪个出版物被展开
    }
  }
};
</script>

<template>
  <div class="publication-list" ref="publicationList">
<!--    搜索框-->
    <div class="search-container">
      <el-input
        v-model="queryName"
        placeholder="Search by name"
        class="search-input"/>
      <el-button @click="handleSearch" class="search-button">Search</el-button>
    </div>
    <!-- 使用 el-row 和 el-col 实现每行一个出版物 -->
    <el-row :gutter="20" justify="center" align="top">
      <el-col :span="20" v-for="(publication, index) in publications" :key="index">
        <div class="publication-card" @click="toggleDetail(index)">
          <div class="card-body">
            <div style="font-size: 20px;margin-top: 10px"> <strong>{{ publication.name }}</strong> </div>
            <div class="details-container">
              <div
                class="expanded-details"
                :class="{'expanded': expandedPublication === index}">
                <p><strong>Intro:</strong> {{ publication.intro }}</p>
                <p><strong>Author:</strong> {{ publication.author }}</p>
                <p><strong>Periodical:</strong> {{ publication.periodicalName }}</p>
                <!-- 点击跳转链接 -->
                <el-link :href="publication.downloadUrl" target="_blank">Click to view</el-link>
              </div>
            </div>
            <p class="card-date">Release Date:  {{ (new Date(publication.releaseDate)).toLocaleDateString('en-GB', {month: 'short', year: 'numeric', day: 'numeric' }) }}</p>
          </div>
        </div>
      </el-col>
    </el-row>
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
.left-alignment {
  margin-top: 20px;
  margin-bottom: 20px;
  display: flex;
  justify-content: flex-start;
  width: 100%;
}
.search-container {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 20px;
  width: 100%;
}

.search-input {
  width: 200px;
  margin-right: 10px;
}

.search-button {
  height: 36px;
}

.publication-list {
  flex-direction: column; /* 纵向排列子元素 */
  justify-content: flex-start; /* 子元素顶部对齐 */
  align-items: center; /* 子元素水平居中 */
  width: 70%;
  background-color: transparent; /* 设置背景透明 */
  padding: 20px;
}

.el-row {
  display: flex;
  flex-direction: column;
  align-items: center; /* 使 el-col 居中对齐 */
}

.publication-card {
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 10px;
  margin-bottom: 10px; /* 减少外边距 */
  display: flex;
  flex-direction: column;
  cursor: pointer; /* 鼠标悬停时显示可点击 */
  transition: transform 0.2s ease-in-out; /* 添加点击区域的过渡效果 */
}

.publication-card:hover {
  transform: scale(1.02); /* 鼠标悬停时稍微放大 */
}

.card-body {

  padding: 8px; /* 内边距减少 */
  flex-grow: 1;
}

.details-container {
  overflow: hidden; /* 确保超出部分隐藏 */
}

.expanded-details {
  max-height: 0; /* 初始时内容是隐藏的 */
  padding-top: 0; /* 初始时 padding 为 0 */
  padding-bottom: 0; /* 初始时 padding 为 0 */
  opacity: 0; /* 初始时设置透明度 */
  visibility: hidden; /* 初始时隐藏 */
  transition: max-height 0.6s ease, opacity 0.4s ease, padding 0.6s ease; /* 调整动画时长和过渡效果 */
}

.expanded-details.expanded {
  max-height: 1000px; /* 展开时的最大高度，足够容纳内容 */
  opacity: 1; /* 展开时显示内容 */
  visibility: visible; /* 展开时显示内容 */
  padding-top: 5px; /* 展开时设置顶部 padding */
  padding-bottom: 5px; /* 展开时设置底部 padding */
}

.expanded-details p {
  margin-bottom: 6px; /* 更小的间距 */
}

.el-link {
  display: inline-block;
  margin-top: 8px;
}

.card-date {
  font-size: 10px;
  color: #777;
  margin-top: 10px;
  text-align: right;
}
</style>
