<script>
import teamForm from "./forms/teamForm.vue";
import researchForm from "./forms/researchForm.vue";
import softwareForm from "./forms/softwareForm.vue";
import publicationsForm from "./forms/publicationsForm.vue";

export default {
  components: {teamForm},
  computed:{
    getFormComponent(){
      switch (this.formType) {
        case "team": {
          console.log("ttttttttt")
          return teamForm
        }
        case "research":{
          console.log("research")
          return researchForm
        }
        case "software":{
          console.log("software")
          return softwareForm
        }
        case "publications":{
          return publicationsForm
        }
      }
      return null
    },
  },
  props: {
    showAddAndEdit: { // 控制弹窗显示
      type: Boolean,
      default: false
    },
    formType: { // 用来决定表单类型
      type: String,
      default: ''
    },
    formData: {
      type: Object,
      required: false,
      default: () => ({})  // 使用工厂函数返回默认值
    }
  },

  data() {
    return {
      pageShow:false
    };
  },
  methods: {
    handleClose() {
      this.$emit('update:showAddAndEdit', false); // 关闭弹窗时更新父组件的 visible
    },

    submitForm() {
      window.location.reload()
      this.$emit('update:showAddAndEdit', false); // 提交后关闭弹窗
    }
  },
  created() {
    console.log(this.formType)
    if(this.formType==='news'){
      this.$router.push({name: 'newsPage'})
      this.$emit('update:showAddAndEdit', false);
    }
    this.pageShow = this.showAddAndEdit
  },
};
</script>

<template>
  <el-dialog :visible.sync="pageShow" title="动态表单" @close="handleClose">
    <el-form :model="formData" ref="formRef">

      <component :is="getFormComponent" :formData="formData" @submitForm="submitForm" />
    </el-form>
  </el-dialog>
</template>

<style scoped>

</style>

<!--<team-form v-if="formType === 'team'" :formData="formData" />-->
