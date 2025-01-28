<script>
import axios from "axios";
import {API_PATH} from "../config";

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
      default: 'visit' // visit 访问 ，login 登录 ， selfUserPage 查看自己的页面 ，register 注册 ,updatePassword 修改密码
    }
  },
  data(){
    return {
      form: {
      },
      emailForm: {
        email: ''
      },
      rules: {
        name: [{ required: true, message: 'Please enter member name', trigger: 'blur' }],
        contact_way: [{ required: true, message: 'Please enter contact details', trigger: 'blur' }]
      },
      dialogVisible: false,
      isEditMod:false,
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
      loginRules: {
        email: [{required: true, message: 'please enter your email', trigger: 'blur'}],
        password: [{required: true, message: 'enter your password', trigger: 'blur'}]
      },
      checked: false
    }
  },
  methods: {
    API_PATH() {
      return API_PATH
    },
    async getMember() {
      try {
        const response = await axios.get(API_PATH+'/laboratory/member/'+this.userId);
        this.pageUser = response.data.data
        console.log(this.pageUser)
      } catch (e){
        console.log('寻找用户失败',e)
      }
    },
    editPassword(){
      this.localPageMode='updatePassword'
      this.form=this.pageUser
      console.log(this.pageUser)
    },
    async sendCode(email) {
      console.log(this.pageUser)
      if (!email) {
        this.$message.error("请先输入邮箱");
        return;
      }
      try {
        const response = await axios.post(API_PATH+'/email/sendCode', null, {
          params: { email: email },
        });

        console.log(response)
        this.isCodeSent = true;
        this.$message.success(response.data);
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
    async verifyCode(form) {
      try {
        const response = await axios.post(API_PATH+'/email/verifyCode', null, {
          params: {
            email: form.email,
            code: form.code
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
    async submitNewPassword(){
      if(this.form.newPassword===''||this.form.confirmPassword===''){
        this.$message.error('Please enter password')
        return
      } else if(this.form.newPassword!==this.form.confirmPassword){
        this.$message.error('The two passwords are inconsistent\n')
        return
      }
      if(await this.verifyCode(this.form)){
        this.form.password=this.form.newPassword
        try {
          const response = await axios.put(API_PATH+'/laboratory/member/update',this.form);
          this.$message.success('update success')
          this.localPageMode = 'login'
          this.backToSelfUserPage()
          this.$loginUser.user.password=this.form.password
          this.pageUser=this.$loginUser.user
          this.form={}
          this.logout()
        } catch (e){
          this.$message.error('update error')
        }
      }
    },
    async onRegisterSubmit() {
      if(this.registerForm.password===''||this.registerForm.confirmPassword===''){
        this.$message.error('Please enter password')
        return
      } else if(this.registerForm.password!==this.registerForm.confirmPassword){
        this.$message.error('The two passwords are inconsistent\n')
        return
      }
      if(await this.verifyCode(this.registerForm)){
        try {
          const response = await axios.put(API_PATH+'/laboratory/member/register',this.registerForm);
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
        console.log(JSON.stringify(this.memberLoginRequest))
        const response = await fetch(API_PATH+'/laboratory/member/login', {
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
        localStorage.setItem('user_login_info', JSON.stringify(this.memberLoginRequest))
        this.memberLoginRequest= {
          email: '',
          password: '',
        }
        this.localPageMode='selfUserPage'
      } catch (error) {
        console.log(error)
        this.$message({
          showClose: true,
          message: '登录失败，请检查邮箱和密码',
          type: 'error'
        });
      }
    },
    backToSelfUserPage(){
      if(Object.keys(this.pageUser).length!==0){
        this.localPageMode='selfUserPage'
        this.isEditMod=false
      }
    },
    editProfile(){
      this.isEditMod=true
      this.form=this.pageUser
    },
    logout(){
      this.localPageMode='login'
      this.pageUser={}
      this.$loginUser.user={}
    },
    handleSubmit() {
      axios.put(API_PATH+'/laboratory/member/update',this.form).then(response=>{
        console.log(response)
        if(response.data.code===200){
          this.$message.success('Form submitted successfully!');
          this.backToSelfUserPage()
          this.pageUser=this.form;
          this.$loginUser.user=this.form;
          this.form=null;
        }else  {
          this.$message.error('Please fill in the required fields!');
        }
      }).catch(e=>{
        this.$message.error('Please fill in the required fields!'+e);
      })
    },

    handleAvatarSuccess(response, file) {
      this.form.image = response.fileName;
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error('Avatar must be in JPG format!');
      }
      if (!isLt2M) {
        this.$message.error('Avatar size must be smaller than 2MB!');
      }
      return isJPG && isLt2M;
    },
    checkPassword(rule, value, callback) {
      if (value !== this.form.newPassword) {
        callback(new Error('The two passwords do not match'));
      } else {
        callback();
      }
    },
  },
  mounted() {
    console.log(this.pageUser.user)
    if(this.localPageMode==='visit') {
      this.getMember()
    } else if(this.localPageMode==='selfUserPage') {
      this.pageUser = this.$loginUser.user
    } else if(this.localPageMode==='login') {
      let temLoginInfo = JSON.parse(localStorage.getItem('user_login_info'));
      if(temLoginInfo) {
        this.memberLoginRequest = temLoginInfo;
        this.login()
      }
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
        <el-form-item label="email" prop="email">
          <el-input v-model="registerForm.email" placeholder="请输入邮箱"></el-input>
        </el-form-item>

        <!-- 验证码 -->
        <el-form-item label="code" prop="code">
          <el-row :gutter=10>
            <el-col :span="11">
              <el-input v-model="registerForm.code" placeholder="请输入验证码"></el-input>
            </el-col>
            <el-col :span="9">
              <el-button
                type="primary"
                @click="sendCode(registerForm.email)"
                :disabled="isCodeSent"
                style="width: 100%;"
              >
                {{ isCodeSent ? `${countdown}s` : 'send code' }}
              </el-button>
            </el-col>
          </el-row>
        </el-form-item>

        <!-- 密码 -->
        <el-form-item label="password" prop="password">
          <el-input v-model="registerForm.password" placeholder="请输入密码" show-password></el-input>
        </el-form-item>

        <!-- 确认密码 -->
        <el-form-item label="confirm password" prop="confirmPassword">
          <el-input
            v-model="registerForm.confirmPassword"
            placeholder="请再次输入密码"
            show-password
          ></el-input>
        </el-form-item>

        <!-- 姓名 -->
        <el-form-item label="name" prop="name">
          <el-input v-model="registerForm.name" placeholder="请输入姓名"></el-input>
        </el-form-item>

        <!-- 注册按钮 -->
        <el-form-item>
          <el-button type="primary" @click="onRegisterSubmit">register</el-button>
        </el-form-item>
      </el-form>
    </div>

    <!--    登录-->
    <div v-if="localPageMode==='login'" class="login-container">
      <el-form :model="memberLoginRequest" :rules="loginRules"
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

<!--    展示页面-->
    <div v-if="(localPageMode==='selfUserPage'||localPageMode==='visit')&&!isEditMod">
      <div style="margin-left: 20px;margin-right: 20px">
        <div class="image-name" >
          <el-avatar shape="square" :size="70" :src="API_PATH()+pageUser.image"></el-avatar>
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
        <div class="button-container">

        </div>
      </div>

      <div v-if="localPageMode==='selfUserPage'" class="button-container">
        <el-button type="primary" @click="editProfile" style="margin-right: 20px;">Edit Profile</el-button>
        <el-button type="primary" @click="editPassword">Edit password</el-button>
        <el-button type="danger" @click="logout">Log out</el-button>
      </div>
    </div>
<!--    修改密码-->
    <div v-if="this.localPageMode==='updatePassword'">
      <el-form :model="form" ref="form" label-width="120px">
        <el-form-item label="Email">
          <el-input v-model="form.email" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="code" prop="code">
          <el-row :gutter=10>
            <el-col :span="11">
              <el-input v-model="form.code" placeholder="Enter code"></el-input>
            </el-col>
            <el-col :span="9">
              <el-button
                type="primary"
                @click="sendCode(pageUser.email)"
                :disabled="isCodeSent"
                style="width: 100%;"
              >
                {{ isCodeSent ? `${countdown}s` : 'send code' }}
              </el-button>
            </el-col>
          </el-row>
        </el-form-item>

        <!-- New Password Field -->
        <el-form-item label="New Password" :rules="[{ required: true, message: 'Please input your password', trigger: 'blur' }]">
          <el-input v-model="form.newPassword" type="password"></el-input>
        </el-form-item>

        <!-- Confirm Password Field -->
        <el-form-item label="Confirm Password" :rules="[
      { required: true, message: 'Please confirm your password', trigger: 'blur' },
      { validator: checkPassword, trigger: 'blur' }
      ]">
          <el-input v-model="form.confirmPassword" type="password"></el-input>
        </el-form-item>

        <!-- Submit Button -->
        <el-form-item>
          <el-button type="primary" @click="submitNewPassword">Submit</el-button>
        </el-form-item>
      </el-form>

    </div>


<!--编辑个人信息-->
    <div v-if="isEditMod" style="margin-right: 15px">
      <el-form :model="form" :rules="rules" ref="form" label-width="120px" @submit.native.prevent="submitForm">
        <el-form-item label="avatar" prop="image" >
          <el-upload style="margin-top: -10px"
            v-model="form.image"
            class="avatar-preview"
            :action="API_PATH()+ '/common/upload'"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="form.image" :src="API_PATH()+this.form.image" class="avatar-preview"  alt="avatar"/>
            <i v-else class="el-icon-plus avatar-uploader-icon" style="width: 50px;height: 50px;border-radius: 50%"></i>
          </el-upload>
        </el-form-item>

        <el-form-item label="Name" prop="name">
          <el-input v-model="form.name" placeholder="Enter name" />
        </el-form-item>

        <el-form-item label="Introduction" prop="intro">
          <el-input v-model="form.intro" type="textarea" placeholder="Enter a brief introduction" />
        </el-form-item>

        <el-form-item label="Contact Way" prop="contactWay">
          <el-input v-model="form.contactWay" placeholder="Enter contact details" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="handleSubmit">Submit</el-button>
          <el-button type="primary" @click="backToSelfUserPage">Go Back</el-button>
        </el-form-item>

      </el-form>
    </div>
  </div>

</template>



<style scoped>
.avatar-preview {
  margin-top: 10px;
  padding: 0;
  height: 50px;
  width: 50px;
}
.avatar-preview img {
  border-radius: 50%;
  object-fit: cover;
}
.button-container {
  position: absolute;  /* 绝对定位 */
  bottom: 20px;        /* 距离父容器底部 20px */
  width: 100%;
  text-align: center;
}
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
