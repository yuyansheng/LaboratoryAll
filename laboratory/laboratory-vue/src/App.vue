<template>
  <div id="app" class="parent">
    <el-drawer
      :title="$user.id===-1?'login':'user'"
      :visible.sync="drawer"
      size="30%">
      <user :user-id="userId"/>
    </el-drawer>

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
  </div>
</template>

<script>
import user from "./views/user.vue";

export default {
  components: {user},
  data() {
    return {
      userId:0,
      drawer:false,
      menuItems: ["Welcome!", "News", "Research", "Team", "Publications","Software","Resources",
        "Contact"], // 导航菜单项
      pageURLs:["/","/news","/research","/team","/publications","/software","resources","/contact"],//对应页面跳转url
    };
  },
  methods:{
    pageTurns(index){
      console.log(index)
      this.$router.push(this.pageURLs[index])
    },
    openSelfUser(){
      this.userId=this.$user.id
      this.drawer = true
    }
  }
};
</script>
<style>
body {
  color: #569696;
}
</style>
<style scoped>
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
  flex-direction: column;   /* 使用垂直布局，确保菜单和内容在竖直方向上顺序排列 */
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
