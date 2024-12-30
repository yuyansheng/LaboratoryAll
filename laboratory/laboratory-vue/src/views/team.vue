<script>
import axios from "axios";
import user from "./user.vue";

export default {
  components: {user},
  data() {
    return {
      drawer: false,
      userId:-1,
      teams: [],
    };
  },
  methods: {
    openMemberInfo(memberId){
      this.userId=memberId;
      this.drawer=true;
    },
    loadAllTeam() {
      axios
        .get(this.$baseURL + '/team/all')
        .then((response) => {
          this.teams = response.data;
          console.log(this.teams);
          // 获取所有成员
          this.teams.forEach(team => {
            this.getTeamMembersByTeamId(team)
            this.getLeaderName(team)
          });
        })
        .catch((error) => {
          console.error('请求数据失败', error);
        });
    },
    async getLeaderName(team){
      try {
        if(!team.hasOwnProperty('teamLeaderId')){
          throw new Error('team请求错误');
        }
        const response = await axios.get(this.$baseURL+'/member/getMemberById/'+ team.teamLeaderId)
        this.$set(team,'teamLeader',response.data.name)
      }catch (e){
       console.error('请求领导id')
      }
    },
    async getTeamMembersByTeamId(team) {
      try {
        const response = await axios.get(this.$baseURL + '/team/members', {
          params: {
            teamId: team.id,
          },
        });
        console.log(response.data);
        // 找到对应的 team 并将成员数据存入该团队
          this.$set(team,'members',response.data)
      } catch (error) {
        console.error('请求数据失败', error);
        return []; // 请求失败，返回空数组
      }
    },
  },
  mounted() {
    this.loadAllTeam();
  },
};
</script>

<template>
  <div class="parent">
    <el-drawer
      title="user page"
      :visible.sync="drawer"
      size="30%">
      <user v-if="drawer" :user-id="userId" user-page-mode="visit"/>
    </el-drawer>
    <div v-for="(item, index) in teams" :key="index" class="left-alignment">
      <div>{{ item.name }}</div>
      <div >team leader: <span style="text-decoration-line: underline;cursor: pointer" @click="openMemberInfo(item.teamLeaderId)">{{ item.teamLeader }}</span></div>
      <div class="team-member">
        <div class="team-member-label">team member:</div>
        <div class="members">
          <div v-for="(member, idx) in item.members" :key="idx" class="member" @click="openMemberInfo(member.memberId)">
            {{ member.name }}
          </div>
        </div>
      </div>
      <div>{{ item.intro }}</div>
    </div>
  </div>
</template>

<style scoped>
.team-member {
  display: flex; /* Use flexbox to arrange the label and members in a row */
  align-items: center; /* Align vertically if needed */
}

.team-member-label {
  margin-right: 10px; /* Space between "team member" label and members */
}

.members {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.member {
  text-decoration-line: underline;
  cursor: pointer;
  margin: 0;
  padding: 5px 0;
  justify-self: flex-start;
  white-space: nowrap;
}

.parent {
  margin-top: 20px;
  margin-bottom: 20px;
  display: flex;
  justify-content: flex-start;
  align-items: flex-start;
  width: 55%;
  padding: 40px 60px;
}
</style>
