export default {
  isLogin(user){
    if(Object.keys(user).length === 0 && user.constructor === Object){
      return true
    }
    return false
  },

  canUse(user,needRole){
    //没有登录不可以使用对应模块
    if(this.isLogin(user)) {
      return false;
    }
    return user.role <= needRole;
  }
}
