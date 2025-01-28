
<script>
import user from "./views/user.vue";
import authority from "./utils/authority";
import addAndEdit from "./views/addAndEditDialog.vue";
import AddAndEditDialog from "./views/addAndEditDialog.vue";
import axios from "axios";
import {API_PATH} from "./config";

export default {
  components: {
    AddAndEditDialog,
    user,
    addAndEdit,
  },
  data() {
    return {
      style:null,
      showAddAndEdit:false,
      userId:-1,
      formType:'',
      drawer:false,
      menuItems: ["Welcome!", "News", "Research", "Team", "Publications","Software","Resources",
        "Contact"], // 导航菜单项
      pageURLs:["/","/news","/research","/team","/publications","/software","/resources","/contact"],//对应页面跳转url
    };
  },
  computed: {
    backgroundStyle() {
      return {
        backgroundImage: `url(${this.style?API_PATH+this.style.backImage:'\'./assets/backimage.png\''})`,
        backgroundSize: '100% 100%',
        backgroundAttachment: 'fixed',
        backgroundPosition: 'center',
      };
    },

    isLogin() {
      console.log(this.$loginUser.user)
      return authority.isLogin(this.$loginUser.user);
    },

  },
  mounted() {
    this.loadStyle()
  },
  methods:{
    API_PATH() {
      return API_PATH
    },
    loadStyle(){
      axios.get(API_PATH+'/laboratory/style/list',{
        params:{
          pageNum:1,
          pageSize:1,
          effectiveDate:new Date().toISOString().split('T')[0],
          orderByColumn:'effectiveDate',
          isAsc:'descending'
        }
      }).then(response=>{
        this.style=response.data.rows[0];
        console.log(this.style)
        localStorage.setItem("pageStyle", JSON.stringify(this.style));
      }).catch(e=>{
        console.error(e,'加载错误')
      })
    },
    openAddAndEdit() {
      console.log(this.$route.meta)
      if(!this.$route.meta.hasOwnProperty('formType')){
        return;
      }
      this.formType =  this.$route.meta.formType;
      this.showAddAndEdit = true
    },
    pageTurns(index){
      console.log(index)
      this.$router.push(this.pageURLs[index])
    },
    openSelfUser(){
      this.drawer = true
    }
  }
};
</script>

<template>
  <div id="app" class="appParent" :style="backgroundStyle">
<!--    用户弹窗-->
    <el-drawer
      :visible.sync="drawer"
      size="30%">
      <user v-if="drawer" :user-id="userId" :user-page-mode="isLogin?'login':'selfUserPage'"/>
    </el-drawer>
<!--    添加或者编辑的表单-->
    <add-and-edit-dialog
      v-if="showAddAndEdit"
      :showAddAndEdit.sync="showAddAndEdit"
      :formType="formType"
      @update:showAddAndEdit="showAddAndEdit = $event"
    />
<!--    导航栏-->
    <div style="background-color: white;width: 100%;display: flex;align-items: center;justify-content: center;flex-direction: column;">
      <img class="home-page-log-image" :src="API_PATH()+this.style.logoImage" alt="log">
      <div class="right-alignment">
        <div class="school-text">
          <p>Shandong University of Technology</p>
        </div>
        <div>
          <el-menu
            mode="horizontal"
            background-color="#ffffff"
            text-color="#333333"
            class="custom-menu"
            default-active="0"
          >
            <el-menu-item
              @click="pageTurns(index)"
              v-for="(item, index) in menuItems"
              :key="index"
              :index="String(index)"
            >
              {{ item }}
            </el-menu-item>
            <el-menu-item @click="openSelfUser">user</el-menu-item>
          </el-menu>

        </div>
      </div>
    </div>
    <div style="display: flex;min-height: 80vh; align-items: center;flex-direction: column;width: 100%;background-color: rgba(255, 255, 255, 0.5); backdrop-filter: blur(10px) ">
      <router-view/>
    </div>

    <div class="footer">
      <!-- 页脚区域 -->
      <p>这里是页脚，保持一定的底部留白</p>
    </div>
  </div>
</template>

<style scoped>
.home-page-log-image{
  width: 50%;
}
.footer {
  width: 100%;
  background-color: #f1f1f1;
  padding: 20px;
  text-align: center;
  margin-top: auto;  /* 保证页脚始终位于页面底部 */
}
.add-button {
  background-color: #569696;
  position: fixed;
  bottom: 40px;
  right: 40px;
  font-size:40px;
  width: 60px; /* 按钮宽度 */
  height: 60px; /* 按钮高度 */
  border-radius: 50%; /* 圆形 */
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}
.school-text {
  display: flex;
  flex-direction: column; /* 垂直排列子项 */
  align-items: flex-end; /* 水平右对齐 */
  font-size: 10px;
  font-style: italic;
  margin-bottom: 0;
  color: darkgray;
}
.right-alignment{
  display: flex;
  flex-direction: column; /* 垂直排列子项 */
  align-items: flex-end; /* 水平右对齐 */
}

.appParent {
  /* 设置背景图 */


  /* 设置页面的透明度和模糊效果 */
  position: relative;
  min-height: 100vh;
  overflow: hidden;

  /* 透明度控制显示 */
  background-color: rgba(255, 255, 255, 0.5); /* 透明层可通过改变透明度来控制背景的显示 */
  display: flex;
  flex-direction: column;
  align-items: center;      /* 水平居中 */
  justify-content: flex-start; /* 向上对齐 */
  width: 100%;
  margin: 0;
  padding: 0;
}

.custom-menu {
  overflow: hidden;
  display: inline-flex;              /* 使用 flex 布局 */
  justify-content: center;    /* 水平居中 */
  padding: 0;                 /* 移除内边距 */
  border: 3px solid #fadfd7;     /* 外边框 */
  width: auto;                /* 宽度根据内容自适应 */
  height: auto;
  margin-top: 0;
  margin-bottom: 10px;               /* 外边距 */
}

.custom-menu .el-menu-item {
  height: auto;
  font-size: 18px;
  line-height: 40px;
  padding-top: 0;
  padding-bottom: 0;
  text-align: center;         /* 保证文本居中 */
  border-bottom: 1px solid #ddd; /* 菜单项的分隔线 */
}
/* 鼠标指向时的背景色 */
.el-menu-item:hover {
  background-color: #569696 !important;
}

/* 激活状态的背景色 */
.el-menu-item.is-active {
  background-color: #569696 !important;
}
</style>
