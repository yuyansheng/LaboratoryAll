<script>
export default {
  data(){
    return {
      teams:[{
        id:0
      }],
   }
  }
}
</script>

<template>
  <div class="parent">
    <div v-for="(item,index) in teams" :key="index" class="left-alignment">
      <div style="justify-items: flex-start;display: flex;align-items: center;">
        <a class="name" :href="item.url">{{item.name}}</a>
      </div>
      <div style="justify-items: flex-start;display: flex;width: 100%;margin-top: 5px">
        <div class="intro">{{item.intro}}</div>
      </div>
      <div class="begin-time">{{item.beginTime}}</div>
    </div>
  </div>
</template>

<style scoped>
.begin-time{
  color: black;
}
.name{
  margin-left: 10px;
  color: #569696;
}
.intro {
  width: 50%;
  word-wrap: break-word;
  color: black;
  margin-left: 10px;
}
.parent {
  margin-top: 20px;
  margin-bottom: 20px;
  display: flex;
  justify-content: flex-start; /* 元素水平靠左 */
  align-items: flex-start; /* 垂直靠上 */
  width: 55%;
  padding: 40px 60px;
}
.left-alignment {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 100%;
}
</style>
