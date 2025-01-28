import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import index from '@/views/index'
import news from '@/views/news'
import publications from '@/views/publications'
import research from '@/views/research'
import software from '@/views/software'
import team from '@/views/team'
import newsPage from '@/views/newsPage'
import user from '@/views/user'
import  resources from '@/views/resources'
import contact from "../views/contact.vue";
import ResourceDetail from "../views/ResourceDetail.vue";
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: index,
    },
    {
      path: '/resources',
      name: 'resources',
      component: resources,
    },
    {
      path: '/news',
      name: 'news',
      component: news,
      meta:{
        canAdd:true,
        formType:'news'
      }
    },
    {
      path:'/newsPage/:newsId',
      name: 'newsPage',
      component: newsPage,
    },
    {
      path: '/publications',
      name: 'publications',
      component: publications,
      meta:{canAdd:true,formType: 'publications'}
    },
    {
      path: '/research',
      name: 'research',
      component: research,
      meta:{
        canAdd:true,
        formType:'research'
      }
    },
    {
      path: '/software',
      name: 'software',
      component: software,
      meta:{
        canAdd:true,
        formType:'software'
      }
    },
    {
      path:'/contact',
      name:'contact',
      component: contact
    },
    {
      path: '/resource/:id',
      name: 'ResourceDetail',
      component: ResourceDetail,
      props: true, // 使 id 可以作为 prop 传递给组件
    },
    {
      path: '/team',
      name: 'team',
      component: team,
      meta:{
        canAdd:true,
        formType:'team'
      }
    },
    {
      path: '/user/:id',
      name: 'user',
      component: user,
    },
    {
      path:'/user',
      redirect: '/user/-1',
    }
  ]
})
