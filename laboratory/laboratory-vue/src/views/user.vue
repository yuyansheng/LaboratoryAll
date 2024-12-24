<script>
export default {
  name: "login",
  props: {
    userId:{
      type:Number,
      required:true,
    }
  },
  computed: {
    isPageUserEmpty() {
      return Object.keys(this.pageUser).length === 0;
    }
  },
  data(){
    return {
      logining:false,
      pageUser:{},
      memberLoginRequest: {
        email: '',
        password: '',
      },
      rules: {
        email: [{required: true, message: 'please enter your email', trigger: 'blur'}],
        password: [{required: true, message: 'enter your password', trigger: 'blur'}]
      },
      checked: false
    }
  },
  methods: {
    async login() {
      try {
        this.logining = true;
        const response = await fetch(this.$baseURL+'/member/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(this.memberLoginRequest)
        });

        if (!response.ok) {
          throw new Error('登录失败，请检查邮箱和密码');
        }
        this.logining = false;
        const data = await response.json();
        console.log(data)
        this.$set(this,'pageUser',data)
        this.$nextTick(() => {
          // 在 DOM 更新后执行需要的操作
        });
        console.log(this.pageUser)
        this.$user = this.pageUser;
      } catch (error) {
        this.logining = false
        this.$message({
          showClose: true,
          message: '登录失败，请检查邮箱和密码',
          type: 'error'
        });
      }
    }
  },
  mounted() {
    console.log(this.pageUser)
  }
}
</script>
<template>
  <div style="width: 100%;height: 100%">
    <div v-if="isPageUserEmpty" class="login-container">
      <el-form :model="memberLoginRequest" :rules="rules"
               status-icon
               ref="ruleForm2"
               label-position="left"
               label-width="0px"
               class="login-page">
        <el-form-item prop="username">
          <el-input type="text"
                    v-model="memberLoginRequest.email"
                    auto-complete="off"
                    placeholder="email"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password"
                    v-model="memberLoginRequest.password"
                    auto-complete="off"
                    placeholder="password"
          ></el-input>
        </el-form-item>
        <el-form-item style="width:100%;">
          <el-button type="primary" style="width:100%;color: black;background-color: #569696" @click="login" :loading="logining">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div v-else>
      <div style="margin-left: 20px;margin-right: 20px">
        <div class="image-name" >
          <el-avatar shape="square" :size="70" :src="require('@/assets/text.png')"></el-avatar>
          <div>
            <div style="font-size: 20px">
              {{this.pageUser.name}}
            </div>
            <div v-if="this.pageUser.contactWay!==''" style="margin-top: 10px">
              contact Way: {{this.pageUser.contactWay}}
            </div>
          </div>
        </div>
        <div class="intro">
          {{this.pageUser.intro}}
        </div>
      </div>
    </div>
  </div>

</template>



<style scoped>
.intro {
  margin-top: 20px;
  word-wrap: break-word;
  color: black;
}
.image-name {
  color: black;
  display: flex; /* 使用 Flexbox */
  justify-content: flex-start; /* 水平居中整个 flex 容器 */
  align-items: center; /* 垂直居中整个 flex 容器 */
  gap: 20px; /* 左右组件之间的间距 */
}

.login-container {
  width: 100%;
  height: 100%;
}
.login-page {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  width: 80%;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  margin: 0;
  position: relative;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

label.el-checkbox.rememberme {
  margin: 0px 0px 15px;
  text-align: left;
}

.title{
  text-align: center;
}
</style>
