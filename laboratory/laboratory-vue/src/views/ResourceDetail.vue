<template>
  <div class="parent">
      <div slot="header" class="clearfix">
        <span>{{ resource.name }}</span>
        <span class="release-date">{{ resource.release_date }}</span>
      </div>
      <div class="card-body" v-html="resource.content"></div>
  </div>
</template>

<script>
import axios from "axios";
import {API_PATH} from "../config";

export default {
  props: ['id'],
  data() {
    return {
      resource: {},
    };
  },
  created() {
    this.fetchResourceDetail();
  },
  methods: {
    fetchResourceDetail() {
      axios.get(API_PATH+'/laboratory/resources/'+ this.id).then(response=>{
        this.resource=response.data.data
      });
    },
  },
};
</script>

<style scoped>
.parent {
  margin-top: 20px;
  margin-bottom: 20px;
  border: 1px solid darkgray;
  background-color: rgba(255, 255, 255, 0.5);
  display: flex;
  flex-direction: column;
  align-items: flex-start; /* 垂直靠上 */
  width: 55%;
  min-height: 800px;
  padding: 40px 60px;
}
</style>
.resource-detail {
  margin: 20px;
}

.card-body {
  font-size: 16px;
  line-height: 1.6;
}
</style>
