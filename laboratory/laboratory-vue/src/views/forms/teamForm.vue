<script>
export  default {
  props:{
    formData: {
      type:Object,
      required:true
    },
  },
  data(){
    return {
      localFormData:this.formData,
      teamLeaders: [],
      loading: false,
      members: [], // 示例搜索结果
    }
  },
  methods: {
    handleSearch(query) {
      console.log('搜索关键词:', query); // 调试输出搜索关键词
      if(query!==''){
        this.loading = true;
        this.searchMembers(query)
      } else {
        this.members = []
      }
      // 在这里你可以调用API来根据 query 获取实时建议
    },
    async searchMembers(query){
      try {
        const response = await this.$axios.get(this.$baseURL + `/member/searchMembersByName`,{
          params:{
            name:query
          }
        })
        this.members = response.data
      }catch (e){
        this.members = []
      }finally {
        this.loading = false;
      }
    },

    // 选择项变化时
    handleSelectChange(value) {
      // 这里可以做一些处理，比如将选择的值加入 selectedTags
      console.log("已选择的标签: ", value);
    },

    searchTeamLeaders(query) {
      console.log(query)
      if (query !== '') {
        this.loading = true;
        // 通过API获取团队领导数据
        this.$axios.get(this.$baseURL + `/member/searchMembersByName`,{
          params:{
            name:query
          }
        }).then(response => {
          this.teamLeaders = response.data;
          this.loading = false;
        });
      } else {
        this.teamLeaders = [];
      }
    },
    submitForm() {
      console.log(this.localFormData)
      this.$axios.post(this.$baseURL + '/team/add', this.formData)
        .then(response => {
          // 假设返回的响应结构类似： { data: { teamId: '123' } }
          const teamId = response.data;
          console.log(response.data)
          for (let i = 0;i<this.localFormData.members.length;i++) {
            this.$axios.put(this.$baseURL+'/teamMembers/add',{
              teamId:teamId,
              memberId:this.localFormData.members[i],
            })
            console.log(teamId +"     "+ this.localFormData.members[i])
          }
          // 显示成功消息
          this.$message.success(`团队创建成功`);
          // this.$router.push('/team')
          this.$emit("submitForm")
        })
        .catch(error => {
          // 错误处理
          this.$message.error('创建团队失败');
        });
    }
  },
  created() {

  }
}
</script>

<template>
  <div>
    <el-form :model="localFormData" ref="formRef">
      <el-form-item label="团队名称" prop="name">
        <el-input v-model="localFormData.name" placeholder="请输入团队名称"></el-input>
     </el-form-item>

     <el-form-item label="团队简介" prop="intro">
       <el-input type="textarea" autosize v-model="localFormData.intro" placeholder="请输入团队简介"></el-input>
      </el-form-item>

     <el-form-item label="团队邮箱" prop="email">
       <el-input v-model="localFormData.email" placeholder="请输入团队邮箱"></el-input>
     </el-form-item>

     <el-form-item label="团队领导" prop="teamLeaderId">
        <el-select v-model="localFormData.teamLeaderId"
                   placeholder="请选择团队领导"
                   filterable remote
                   :remote-method="searchTeamLeaders"
                   :loading="loading">
         <el-option
           v-for="leader in teamLeaders"
           :key="leader.id"
           :label="leader.name"
           :value="leader.id"
          ></el-option>
       </el-select>
      </el-form-item>

      <el-form-item label=成员 prop="members" >
        <!-- 输入框 -->
        <el-select
          v-model="localFormData.members"
          multiple
          clearable
          filterable
          remote
          placeholder="请选择或输入搜索内容"
          :remote-method="handleSearch"
          :loading="loading"
        >
          <!-- 下拉框选项 -->
          <el-option
            v-for="(item) in members"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="操作">
        <el-button type="primary" @click="submitForm">提交</el-button>
     </el-form-item>
    </el-form>
  </div>
</template>

<style scoped>

</style>
