<script>
import axios from "axios";
import { API_PATH } from "../config";
import { saveAs } from 'file-saver';

export default {
  data() {
    return {
      softwareList: []
    };
  },
  methods: {
    loadSoftwarePage() {
      axios.get(API_PATH + '/laboratory/software/list')
        .then((response) => {
          console.log(response);
          this.softwareList = response.data.rows;
          console.log(this.softwareList);
        }).catch((error) => {
        console.error('请求数据失败', error);
      });
    },
    tansParams(params) {
      let result = '';
      for (const propName of Object.keys(params)) {
        const value = params[propName];
        var part = encodeURIComponent(propName) + "=";
        if (value !== null && value !== "" && typeof (value) !== "undefined") {
          if (typeof value === 'object') {
            for (const key of Object.keys(value)) {
              if (value[key] !== null && value[key] !== "" && typeof (value[key]) !== 'undefined') {
                let params = propName + '[' + key + ']';
                var subPart = encodeURIComponent(params) + "=";
                result += subPart + encodeURIComponent(value[key]) + "&";
              }
            }
          } else {
            result += part + encodeURIComponent(value) + "&";
          }
        }
      }
      return result;
    },
    updateDownloadTimes(index){
      const today = new Date().toISOString().split('T')[0]; // 获取今天的日期
      const currentNewsId = this.softwareList[index].id;


      const lastTriggeredDownloadTime = JSON.parse(localStorage.getItem('lastTriggeredDownloadTime')) || {};

// 获取当前新闻的最后触发时间
      const lastTriggerDate = lastTriggeredDownloadTime[currentNewsId];
      console.log(lastTriggerDate)
// 如果新闻的最后触发日期与今天相同，则不允许再次触发
      if (lastTriggerDate === today) {
        return;
      }

// 更新 localStorage 中该新闻的最后触发时间为今天
      lastTriggeredDownloadTime[currentNewsId] = today;
      localStorage.setItem('lastTriggeredDownloadTime', JSON.stringify(lastTriggeredDownloadTime));

// 更新新闻的阅读次数
      this.softwareList[index].downloadTimes =  (parseInt(this.softwareList[index].downloadTimes) || 0) + 1;
      axios.put(API_PATH + '/laboratory/software/update/downloadTimes',null, {
        params:{
          downloadTimes: this.softwareList[index].downloadTimes,
          id: this.softwareList[index].id
        }
      })
        .then(response => {
          console.log('Update successful', response);
        })
        .catch(error => {
          console.error('Error updating news:', error);
        });
    },
    download(filePath,index) {
      this.updateDownloadTimes(index)
      let list = filePath.split("/");
      let fileName = list[list.length - 1];

      let params = {
        resource: filePath
      };
      let url = API_PATH + "/common/download/resource";
      axios.post(url, params, {
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        responseType: 'blob', // 重要：设置返回类型为blob
        transformRequest: [(params) => { return this.tansParams(params) }] // 参数转化
      })
        .then((response) => {
          const blob = new Blob([response.data]); // 创建 Blob 对象
          saveAs(blob, fileName); // 使用 file-saver 保存文件
        })
        .catch((error) => {
          console.error(error);
          this.$message.error('下载文件出现错误，请联系管理员！');
        });
    }
  },

  mounted() {
    this.loadSoftwarePage();
  }
};
</script>

<template>
  <div class="software-list">
    <el-card
      v-for="(software,index) in softwareList"
      :key="software.id"
      class="software-card"
      :body-style="{ padding: '20px' }"
    >
      <div slot="header" class="card-header">
        <span class="card-title">{{ software.name }}</span>
      </div>
      <p class="card-intro">{{ software.intro }}</p>
      <div class="card-footer">
        <span class="download-count">Downloads: {{ software.downloadTimes }}</span>
        <span class="release-date">Release Date: {{  (new Date(software.releaseDate)).toLocaleDateString('en-GB', { year: 'numeric', month: 'short', day: 'numeric' }) }}</span>
      </div>
      <div class="button-group">
        <el-button
          type="primary"
          size="small"
          @click="download(software.downloadUrl,index)"
          class="download-btn"
        >
          Download
        </el-button>
        <el-button
          type="info"
          size="small"
          @click="download(software.documentUrl,index)"
          class="document-btn"
        >
          Readme
        </el-button>
      </div>
    </el-card>
  </div>
</template>

<style scoped>
.software-list {
  flex-direction: column; /* 纵向排列子元素 */
  justify-content: flex-start; /* 子元素顶部对齐 */
  align-items: center; /* 子元素水平居中 */
  display: flex;
  width: 60%;
  grid-template-columns: repeat(auto-fill, minmax(400px, 1fr));
  gap: 20px;
  padding: 20px;
}

.software-card {
  width: 100%;
  border-radius: 12px;
  background-color: rgba(255, 255, 255, 0.5);
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
  margin-bottom: 10px;
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

.card-footer {
  display: flex;
  justify-content: space-between;
  font-size: 12px;
  color: #888;
  margin-bottom: 15px;
}

.download-count,
.release-date {
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

.download-btn {
  background-color: #007BFF;
  color: white;
}

.download-btn:hover {
  background-color: #0056b3;
}

.document-btn {
  background-color: #17a2b8;
  color: white;
}

.document-btn:hover {
  background-color: #117a8b;
}
</style>
