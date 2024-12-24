<script>
export default {
  data(){
    return {
      researches:[
        {
          id:0,
          intro:"safhlfhlwiebfdsjfnfjjasfdafdfadfasdfasadfsd fasdf asdf asd a s dfsafdsfaf sancie",
          name:"lean;fdlskcnsef",
          url:"https://chat3.eqing.tech/#/chat",
          beginTime:Date.now()
        }
      ]
    }
  }
}
</script>

<template>
  <div class="parent">
    <div v-for="(item,index) in researches" :key="index" class="left-alignment">
      <div style="justify-items: flex-start;display: flex;align-items: center;">
        <div class="dot"></div>
        <a class="name" :href="item.url">{{item.name}}</a>
      </div>
      <div style="justify-items: flex-start;display: flex;width: 100%;margin-top: 5px">
        <div class="intro-dot"></div>
        <div class="intro">{{item.intro}}</div>
      </div>
      <div class="begin-time">{{item.beginTime}}</div>
    </div>
  </div>
</template>

<style scoped>
.dot {
  width: 8px;
  height: 8px;
  background-color: black;
  border-radius: 50%;
}
.intro-dot {
  margin-left: 20px;
  margin-top: 9px;
  width: 6px;
  height: 6px;
  border: 2px solid black;
  border-radius: 50%;
}
.begin-time{
  color: black;
  margin-top: 5px;
}
.name{
  margin-left: 10px;
  color: #569696;
  font-size: 20px;
}
.intro {
  width: 60%;
  word-wrap: break-word;
  color: black;
  margin-left: 8px;
  font-size: 18px;
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
