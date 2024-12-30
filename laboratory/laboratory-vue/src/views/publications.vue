<script>
import axios from 'axios';

export default {
  data() {
    return {
      publications: [] // 用于存放出版物数据
    };
  },
  created() {
    this.fetchPublications(); // 页面加载时获取出版物数据
  },
  methods: {
    async fetchPublications() {
      try {
        const response = await axios.get(this.$baseURL+'/publication/all'); // 根据后端API地址进行调整
        this.publications = response.data;
      } catch (error) {
        console.error('获取出版物数据失败:', error);
      }
    }
  }
};
</script>

<template>
  <div class="publication-list">
    <!-- 使用 el-row 和 el-col 实现响应式布局 -->
    <el-row :gutter="20" justify="start" align="top">
      <el-col :span="8" v-for="(publication, index) in publications" :key="index">
        <el-card class="publication-card">
          <div class="card-body">
            <h3>{{ publication.name }}</h3>
            <p><strong>出版物简介:</strong> {{ publication.intro }}</p>
            <p><strong>作者:</strong> {{ publication.author }}</p>
            <p><strong>发布日期:</strong> {{ publication.releaseDate }}</p>
            <p><strong>期刊名称:</strong> {{ publication.periodicalName }}</p>
            <!-- 点击下载链接 -->
            <el-link :href="publication.downloadUrl" target="_blank">点击查阅</el-link>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>
.publication-list {
  width: 80%;
  background-color: transparent; /* 设置背景透明 */
  padding: 20px;
}

.el-row {
  display: flex;
  flex-wrap: wrap;
}

.publication-card {
  margin-bottom: 20px;
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 10px;
  height: auto; /* 让卡片的高度适应内容 */
}

.card-body {
  padding: 20px;
}

.el-card {
  border: 1px solid rgba(0, 0, 0, 0.2);
  border-radius: 8px;
}

.el-link {
  display: inline-block;
  margin-top: 10px;
}

.el-col {
  display: flex;
  flex-direction: column; /* 使每个 el-col 元素纵向排列 */
}
</style>
