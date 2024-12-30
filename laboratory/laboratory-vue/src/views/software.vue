<script>
import axios from "axios";

export default {
  data() {
    return {
      softwareList: []
    };
  },
  methods: {
    loadSoftwarePage(){
      axios.get(this.$baseURL+'/software/all')
      .then((response)=>{
        this.softwareList=response.data;
        console.log(this.softwareList)
      }).catch((error)=>{
        console.error('请求数据失败',error)
      })
    },
    download(fileType,fileName){
      axios({url:this.$baseURL+'/file/download',
        method:'GET',
        params:{
          fileName:fileName,
          fileType:fileType
        },
        responseType:'blob',
      }).then((response)=>{
        const blob=new Blob([response.data],{type:'application/octet-stream'})
        const link = document.createElement('a');
        link.href = URL.createObjectURL(blob);
        link.download = fileName;
        link.click();
      })
        .catch(error => {
          console.error("下载失败", error);
        });
    }
  },

  mounted() {
    this.loadSoftwarePage()
  }
};
</script>

<template>
  <div class="software-list">
    <el-card
      v-for="software in softwareList"
      :key="software.id"
      class="software-card"
      :body-style="{ padding: '20px' }"
    >
      <div slot="header" class="card-header">
        <span class="card-title">{{ software.name }}</span>
      </div>
      <p class="card-intro">{{ software.intro }}</p>
      <div class="button-group">
        <el-button
          type="primary"
          size="small"
          @click="download('SOFTWARE',software.downloadUrl)"
          class="download-btn"
        >
          下载软件
        </el-button>
        <el-button
          type="info"
          size="small"
          @click="download('README_TEXT',software.documentUrl)"
          class="document-btn"
        >
          下载说明文档
        </el-button>
      </div>
    </el-card>
  </div>
</template>


<style scoped>
.software-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(350px, 1fr));
  gap: 20px;
  padding: 20px;
}

.software-card {
  border-radius: 12px;
  background-color: rgba(255, 255, 255, 0.6);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: transform 0.3s ease;
}

.software-card:hover {
  transform: translateY(-5px);
}

.card-header {
  font-size: 20px;
  font-weight: bold;
  color: #333;
  margin-bottom: 8px;
}

.card-title {
  font-size: 1.4em;
}

.card-intro {
  color: #666;
  line-height: 1.5;
  margin-bottom: 15px;
  font-size: 14px;
}

.button-group {
  display: flex;
  gap: 10px;
  justify-content: flex-start;
}

.download-btn,
.document-btn {
  width: 140px;
}

.el-button {
  border-radius: 4px;
}
</style>
