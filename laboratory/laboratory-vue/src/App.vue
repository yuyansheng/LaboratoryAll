<template>
  <div id="app" class="parent">
<!--    用户弹窗-->
    <el-drawer
      :title="this.isLogin?'login':'user page'"
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
    <div style="display: flex; align-items: center;flex-direction: column;width: 100%;background-color: cornsilk ">
      <router-view/>
    </div>
<!--    添加按钮 需要在index里面-->
    <el-button
      v-if="shouldShowAddButton"
      class="add-button"
      type="primary"
      icon="el-icon-plus"
      @click="openAddAndEdit"
    />
    <div class="footer">
      <!-- 页脚区域 -->
      <p>这里是页脚，保持一定的底部留白</p>
    </div>
  </div>
</template>

<script>
import user from "./views/user.vue";
import authority from "./utils/authority";
import addAndEdit from "./views/addAndEditDialog.vue";
import AddAndEditDialog from "./views/addAndEditDialog.vue";

export default {
  components: {
    AddAndEditDialog,
    user,
    addAndEdit,
  },
  data() {
    return {
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
    shouldShowAddButton(){
      // if(!authority.canUse(this.$loginUser.user,1)){
      //   return false;
      // }
      if(this.$route.meta.hasOwnProperty('canAdd')){
        return this.$route.meta.canAdd
      }
      else return false;
    },

    isLogin() {
      console.log(this.$loginUser.user)
      return authority.isLogin(this.$loginUser.user);
    },
  },
  methods:{
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
<style scoped>
.footer {
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

.parent {
  display: flex;
  flex-direction: column;
  min-height: 90vh;
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
