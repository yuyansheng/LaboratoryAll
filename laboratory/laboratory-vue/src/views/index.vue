<template>
  <div class="parent">
   <p class="title">Welcome to the IDEA lab!</p>
    <el-button v-if="$user.id>=2" size="mini" @click="changeEditMod" style="color:#569696;justify-self: flex-end;" plain>{{isEditMod?'预览':'编辑'}}</el-button>
    <el-divider class="title-divider"></el-divider> <!-- 横向分割线 -->
    <div v-if="isEditMod&&$user.id>=2" class="welcome-text">
      <quill-editor
        v-model="editorContent"
        ref="editor"
        :options="editorOptions"
      />
      <div v-if="!isEditMod">
        <div v-html="displayContent"></div>
      </div>
    </div>

  </div>

</template>

<script>
export default {
  data() {
    return {
      isEditMod:false,
      editorContent: '', // 编辑器内容
      displayContent: '', // 用于展示的内容
      editorOptions: {
        theme: 'snow',
        placeholder: '在这里输入内容...',
        modules: {
          toolbar: [
            [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
            ['bold', 'italic', 'underline', 'strike', 'blockquote', 'code-block'],
            [{ 'color': [] }, { 'background': [] }],
            [{ 'script': 'sub' }, { 'script': 'super' }],
            [{ 'list': 'ordered' }, { 'list': 'bullet' }],
            [{ 'indent': '-1' }, { 'indent': '+1' }],
            [{ 'align': [] }],
            ['link', 'image'],
            ['formula'], // 插入公式和表格（需要插件支持）
            ['clean'], // 清除格式
            [{ 'font': [] }],
            [{ 'size': ['small', 'normal', 'large', 'huge'] }]
          ],
        },
      },
    };
  },
  methods: {
    saveContent() {
      const content = this.editorContent;
      this.displayContent = this.editorContent;
      console.log(this.displayContent)
      fetch('http://localhost:3000/api/save', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ content }),
      })
        .then((response) => response.json())
        .then((data) => {
          console.log('保存成功', data);
        });
    },
    loadContent() {
      fetch('http://localhost:3000/api/content/<id>')
        .then((response) => response.json())
        .then((data) => {
          this.editorContent = data.content;
          this.displayContent = data.content;
        });
    },
  },
};
</script>

<style scoped>
.welcome-text {
  margin: 20px;
  width: 90%;
}
.parent {
  display: flex;
  justify-content: center; /* 水平居中 */
  width: 70%;
}
.title {
  color: #345a5a;
  font-weight: bold;
  font-size: 20px;
}
.title-divider {
  border-top: 2px solid black; /* 设置分割线的粗细和颜色 */
  width: 150px; /* 设置分割线的宽度 */
  margin: 10px auto;
}
</style>


