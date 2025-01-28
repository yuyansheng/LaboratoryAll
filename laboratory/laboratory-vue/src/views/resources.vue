<template>
  <div class="resource-list">
    <el-row :gutter="20">
      <!-- 遍历资源并展示每个资源 -->
      <el-col style="margin-bottom: 10px" :span="24" v-for="resource in resources" :key="resource.id">
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
</template>

<script>
import axios from "axios";
import {API_PATH} from "../config";

export default {
  data() {
    return {
      resources: [],
    };
  },

  methods: {
    handleCardClick(resourceId) {
      console.log("Card clicked with ID: ", resourceId);
      this.$router.push({ name: 'ResourceDetail', params: { id: resourceId } });
    },
    loadResources(){
      axios.get(API_PATH+ '/laboratory/resources/list').then(response=>{
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
.resource-list {
  margin: 20px;
  width: 60%;
}

.resource-card {
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
