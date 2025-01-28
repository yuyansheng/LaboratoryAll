<script>
import axios from "axios";
import {API_PATH} from "../config";

export default {
  data(){
    return {
      researches:[
        {
          id:0,
          intro:"safhlfhlwiebfdsjfnfjjasfdafdfadfasdfasadfsd fasdf asdf asd a s dfsafdsfaf sancie",
          name:"lean;fdlskcnsef",
          url:"https://chat3.eqing.tech/#/chat",
          beginTimes:Date.now()
        }
      ]
    }
  },

  methods:{
    goToUrl(url) {
      window.open(url, '_blank');
    },
    loadAllResearch() {
      axios
        .get(API_PATH + '/laboratory/research/list')
        .then((response) => {
          this.researches = response.data.rows;
          console.log(this.researches);
        })
        .catch((error) => {
          console.error('请求数据失败', error);
        });
    },
  },

  mounted() {
    this.loadAllResearch()
  }
}
</script>

<template>
    <el-container>
      <el-main>
        <el-table :data="researches" stripe>
          <el-table-column prop="name" label="name" width="200">
            <template slot-scope="scope">
              <el-button type="text" @click="goToUrl(scope.row.url)">
                {{ scope.row.name }}
              </el-button>
            </template>
          </el-table-column>
          <el-table-column prop="intro" label="intro" width="400"></el-table-column>
          <el-table-column prop="beginTimes" label="begin times" width="200">
            <template slot-scope="scope">
              <div>
                {{(new Date(scope.row.beginTimes)).toLocaleDateString('en-GB', { year: 'numeric', month: 'short', day: 'numeric' })}}
              </div>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
</template>

<style>
/* 加入更强的选择器 */
.el-container .el-main .el-table,
.el-container .el-table__expanded-cell{
  background-color: transparent !important;
  margin: 0px;
  border-radius: 8px;
}

.el-container .el-table th,
.el-container .el-table tr,
.el-container .el-table td {
  background-color: transparent !important;
}
</style>

<style scoped>
/* 表格的其他自定义样式 */
.el-button {
  color: #409eff !important;
  text-decoration: underline !important;
}

.el-button:hover {
  color: #66b1ff !important;
}

.el-header {
  background-color: rgba(245, 245, 245, 0.4); /* 添加透明度 */
  padding: 20px;
  text-align: center;
}
</style>
