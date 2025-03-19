<template xmlns="http://www.w3.org/1999/html">
  <div class="resources">
    <div class="search-container">
      <el-input
        v-model="queryName"
        placeholder="Search by name"
        class="search-input"/>
      <el-button @click="handleSearch" class="search-button">Search</el-button>
    </div>
  <div class="resource-list" ref="resources">
    <el-row :gutter="20">
      <!-- 遍历资源并展示每个资源 -->
      <el-col style="margin-bottom: 10px" :span="24" v-for="resource in resources"  :key="resource.id">
        <el-card class="resource-card" @click.native="handleCardClick(resource.id)">
          <div slot="header" class="clearfix">
            <span>{{ resource.name }}</span>
            <span class="release-date">{{ (new Date(resource.releaseDate)).toLocaleDateString('en-GB', { year: 'numeric', month: 'short', day: 'numeric' }) }}</span>
          </div>
          <div class="card-body">
            <p>{{ resource.intro }}</p>
          </div>
        </el-card>
      </el-col>
    </el-row>

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

<script>
import axios from "axios";
import {API_PATH} from "../config";

export default {
  data() {
    return {
      pageSize:5,
      pageNum:1,
      totalNewsCount:0,
      resources: [],
      queryName:""
    };
  },

  methods: {
    handlePageChange(pageNum){
      this.pageNum=pageNum
      console.log(this.pageNum+" "+this.pageSize)
      this.expandedPublication=null
      this.loadResources()
      this.$refs.resources.scrollIntoView({ behavior: 'smooth' });  // 平滑滚动到页面顶部
    },
    handleSearch(){
      this.loadResources()
    },
    handleCardClick(resourceId) {
      console.log("Card clicked with ID: ", resourceId);
      this.$router.push({ name: 'ResourceDetail', params: { id: resourceId } });
    },
    loadResources(){
      axios.get(API_PATH+ '/laboratory/resources/list',{
        params:{
          pageNum:this.pageNum,
          pageSize:this.pageSize,
          name:this.queryName,
          orderByColumn:'releasedate',
          isAsc:'descending',
        }
      }).then(response=>{
        this.totalNewsCount=response.data.total
        this.resources=response.data.rows
      })
    }
  },
  mounted() {
    this.loadResources()
  }
};
</script>

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

.resources {
  flex-direction: column; /* 纵向排列子元素 */
  justify-content: flex-start; /* 子元素顶部对齐 */
  align-items: center; /* 子元素水平居中 */
  width: 70%;
  background-color: transparent; /* 设置背景透明 */
  padding: 20px;
}
.resource-list {
  margin: 20px;
  width: 95%;
}

.resource-card {
  background-color: rgba(255,255,255,0.6);
  cursor: pointer;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease;
}

.resource-card:hover {
  transform: scale(1.05);
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2);
}

.release-date {
  float: right;
  font-size: 14px;
  color: #999;
}

.card-body p {
  font-size: 16px;
  color: #333;
}
</style>
