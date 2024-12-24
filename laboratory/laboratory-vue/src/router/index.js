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

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: index,
    },
    {
      path: '/news',
      name: 'news',
      component: news,
      meta:{canAdd:true}
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
      meta:{canAdd:true}
    },
    {
      path: '/research',
      name: 'research',
      component: research,
      meta:{canAdd:true}
    },
    {
      path: '/software',
      name: 'software',
      component: software,
      meta:{canAdd:true}
    },
    {
      path: '/team',
      name: 'team',
      component: team,
      meta:{canAdd:true}
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
