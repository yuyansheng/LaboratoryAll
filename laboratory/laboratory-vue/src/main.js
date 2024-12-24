// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import axios from 'axios'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';// 引入

import Quill from "quill";
import 'quill/dist/quill.snow.css'; //引入quill

import VueQuillEditor from 'vue-quill-editor';

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.use(VueQuillEditor);
Vue.prototype.$axios = axios
Vue.prototype.$quill = Quill;
Vue.prototype.$baseURL = 'http://localhost:9090'
/* eslint-disable no-new */
let user = {
  id:-1,
  intro:"safsasdfsdasdfasdfasdf  saf s asf dsf dsaf sdf asd sdf asdf asd sdf asf sdf dfadfasafs",
  imageUrl:"",
  email:"",
  role:0,
  password:"",
  name:"yuyansheng",
  contactWay:"13123123",
}
Vue.prototype.$user = user;
new Vue({
  el: '#app',
  router,
  axios,
  components: { App },
  template: '<App/>'
})
