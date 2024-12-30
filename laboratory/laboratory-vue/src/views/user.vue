<script>
import axios from "axios";

export default {
  name: "login",
  props: {
    userId: {
      type: Number,
      required: true,
      default: -1
    },
    userPageMode:{
      type: String,
      required: true,
      default: 'visit' // visit 访问 ，login 登录 ， selfUserPage 查看自己的页面 ，register 注册
    }
  },
  data(){
    return {
      registerForm: {
        email: "",
        code: "",
        password: "",
        confirmPassword: "",
        name: "",
        role:1000
      },
      localPageMode:this.userPageMode,
      logining:false,
      pageUser:{},
      memberLoginRequest: {
        email: '',
        password: '',
      },
      registerRules: {
        email: [
          { required: true, message: "请输入邮箱", trigger: "blur" },
          { type: "email", message: "请输入有效的邮箱地址", trigger: "blur" },
        ],
        code: [{ required: true, message: "请输入验证码", trigger: "blur" }],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 6, message: "密码长度不能少于6位", trigger: "blur" },
        ],
        confirmPassword: [
          { required: true, message: "请确认密码", trigger: "blur" },
          {
            validator: (rule, value, callback) => {
              if (value !== this.registerForm.password) {
                callback(new Error("两次输入的密码不一致"));
              } else {
                callback();
              }
            },
            trigger: "blur",
          },
        ],
        name: [{ required: false, message: "请输入姓名", trigger: "blur" }],
      },
      isCodeSent: false,
      countdown: 60,
      rules: {
        email: [{required: true, message: 'please enter your email', trigger: 'blur'}],
        password: [{required: true, message: 'enter your password', trigger: 'blur'}]
      },
      checked: false
    }
  },
  methods: {
    async getMember() {
      try {
        const response = await axios.get(this.$baseURL+'/member/getMemberById/'+this.userId);
        this.pageUser = response.data
        console.log(this.pageUser)
      } catch (e){
        console.log('寻找用户失败',e)
      }
    },


    async sendCode() {
      if (!this.registerForm.email) {
        this.$message.error("请先输入邮箱");
        return;
      }
      try {
        const response = await axios.post(this.$baseURL+'/email/sendCode', null, {
          params: { email: this.registerForm.email },
        });
        console.log(response)
        this.isCodeSent = true;
        this.$message.success("验证码已发送");
        this.startCountdown();
      } catch (error) {
        console.error('Failed to send code: ' + error.response.data)
      }
    },
    startCountdown() {
      this.countdown = 60;
      const interval = setInterval(() => {
        this.countdown -= 1;
        if (this.countdown <= 0) {
          clearInterval(interval);
          this.isCodeSent = false;
        }
      }, 1000);
    },
    async verifyCode() {
      try {
        const response = await axios.post(this.$baseURL+'/email/verifyCode', null, {
          params: {
            email: this.registerForm.email,
            code: this.registerForm.code
          },
        });
        if(response.data==='Verification successful') {
          return true
        } else {
          this.$message.error('verify code error')
          return false
        }
      } catch (error) {
        this.message = 'Verification failed: ' + error.response.data;
        return false
      }
    },
    async onSubmit() {
      if(await this.verifyCode()){
        try {
          const response = await axios.put(this.$baseURL+'/member/add',this.registerForm);
          this.$message.success('register success')
          this.localPageMode = 'login'
        } catch (e){
          this.$message.error('register error')
        }
      }
    },
    toRegister (){
      this.localPageMode="register"
    },
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
        this.logining = false;
        if (!response.ok) {
          throw new Error('登录失败，请检查邮箱和密码');
        }
        this.pageUser = await response.json()
        this.$loginUser.user = this.pageUser
        this.$loginUser = this.pageUser
        console.log(this.$loginUser)
        this.localPageMode='selfUserPage'
      } catch (error) {
        this.$message({
          showClose: true,
          message: '登录失败，请检查邮箱和密码',
          type: 'error'
        });
      }
    }
  },
  mounted() {
    if(this.localPageMode==='visit') {
      this.getMember()
    } else if(this.localPageMode==='selfUserPage') {
      this.pageUser = this.$loginUser.user
    }
  }
}
</script>
<template>
  <div style="width: 100%;height: 100%">
<!--    注册-->
    <div v-if="localPageMode==='register'" class="login-container">
      <el-form :model="registerForm" :rules="registerRules" ref="registerForm" label-width="100px">
        <!-- 邮箱 -->
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="registerForm.email" placeholder="请输入邮箱"></el-input>
        </el-form-item>

        <!-- 验证码 -->
        <el-form-item label="验证码" prop="code">
          <el-row :gutter=10>
            <el-col :span="11">
              <el-input v-model="registerForm.code" placeholder="请输入验证码"></el-input>
            </el-col>
            <el-col :span="9">
              <el-button
                type="primary"
                @click="sendCode"
                :disabled="isCodeSent"
                style="width: 100%;"
              >
                {{ isCodeSent ? `${countdown}s` : 'send code' }}
              </el-button>
            </el-col>
          </el-row>
        </el-form-item>

        <!-- 密码 -->
        <el-form-item label="密码" prop="password">
          <el-input v-model="registerForm.password" placeholder="请输入密码" show-password></el-input>
        </el-form-item>

        <!-- 确认密码 -->
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input
            v-model="registerForm.confirmPassword"
            placeholder="请再次输入密码"
            show-password
          ></el-input>
        </el-form-item>

        <!-- 姓名 -->
        <el-form-item label="姓名" prop="name">
          <el-input v-model="registerForm.name" placeholder="请输入姓名"></el-input>
        </el-form-item>

        <!-- 注册按钮 -->
        <el-form-item>
          <el-button type="primary" @click="onSubmit">注册</el-button>
        </el-form-item>
      </el-form>
    </div>

    <!--    登录-->
    <div v-if="localPageMode==='login'" class="login-container">
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
          <el-button type="primary" style="width:40%;color: white;background-color: #569696" @click="login" :loading="logining">Login</el-button>
          <el-button type="primary" style="width:40%;color: white;background-color: #569696" @click="toRegister">Register</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div v-if="localPageMode==='selfUserPage'||localPageMode==='visit'">
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
  width: 90%;
  height: 100%;
}
.login-page {
  width: 90%;
  margin-left: 30px;
  -webkit-border-radius: 5px;
  border-radius: 5px;
  padding: 35px 35px 15px;
  background: #fff;
  border: 1px solid #eaeaea;
  position: relative;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

label.el-checkbox.rememberme {
  margin: 0 0 15px;
  text-align: left;
}

.title{
  text-align: center;
}
</style>
