<template>
  <div>
    <el-menu
      mode="horizontal"
      background-color="#ffffff"
      text-color="#333333"
      active-text-color="#409EFF"
      class="custom-menu"
    >
      <el-menu-item
        v-for="(item, index) in menuItems"
        :key="index"
        :index="String(index)"
      >
        {{ item }}
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
export default {
  data() {
    return {
      menuItems: ["Home", "About", "Services", "Portfolio", "Contact"], // 动态导航内容
    };
  },
};
</script>

<style>
.custom-menu {
  display: inline-flex; /* 让导航菜单宽度根据内容自适应 */
  justify-content: center; /* 居中对齐 */
  border: 1px solid #ccc; /* 外边框 */
  border-radius: 5px; /* 圆角外边框 */
  padding: 0; /* 移除菜单的默认内边距 */
  width: auto; /* 让菜单的宽度根据内容动态调整 */
  margin: 20px auto; /* 水平居中 */
}

.custom-menu .el-menu-item {
  padding: 0 20px; /* 每个菜单项的内边距 */
  white-space: nowrap; /* 防止文字换行 */
  text-align: center; /* 文本居中 */
}

.custom-menu .el-menu-item:hover {
  background-color: #f0f0f0; /* 鼠标悬浮时的背景色 */
}
</style>
