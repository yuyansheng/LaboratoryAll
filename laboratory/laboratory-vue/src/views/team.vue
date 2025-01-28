<script>
import axios from "axios";
import user from "./user.vue";
import { API_PATH } from "../config";

export default {
  components: { user },
  data() {
    return {
      drawer: false,
      userId: -1,
      teams: [],
    };
  },
  methods: {
    openMemberInfo(memberId) {
      this.userId = memberId;
      this.drawer = true;
    },
    loadAllTeam() {
      axios
        .get(API_PATH + '/laboratory/team/list')
        .then((response) => {
          this.teams = response.data.rows;
          console.log(this.teams);
          // 获取所有成员
          this.teams.forEach(team => {
            this.getTeamMembersByTeamId(team);
            this.getLeaderName(team);
          });
        })
        .catch((error) => {
          console.error('请求数据失败', error);
        });
    },
    async getLeaderName(team) {
      try {
        if (!team.hasOwnProperty('teamLeaderId')) {
          throw new Error('team请求错误');
        }
        const response = await axios.get(API_PATH + '/laboratory/member/' + team.teamLeaderId);
        this.$set(team, 'teamLeader', response.data.data.name);
      } catch (e) {
        console.error('请求领导id');
      }
    },
    async getTeamMembersByTeamId(team) {
      try {
        const response = await axios.get(API_PATH + '/laboratory/team/members', {
          params: {
            teamId: team.id,
          },
        });
        console.log(response.data);
        // 找到对应的 team 并将成员数据存入该团队
        this.$set(team, 'members', response.data);
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
      :append-to-body="true"
      :modal-append-to-body="false"
      title="user page"
      :visible.sync="drawer"
      size="30%">
      <user v-if="drawer" :user-id="userId" user-page-mode="visit"/>
    </el-drawer>

    <el-row v-for="(item, index) in teams" :key="index" class="team-card" :gutter="20">
      <el-col :span="24">
        <el-card class="team-card-content" shadow="always">
          <div class="team-info">
            <div class="team-details">
              <div class="team-name">{{ item.name }}</div>
              <div class="team-leader">
                <strong>leader:</strong>
                <span
                  style="text-decoration-line: underline; cursor: pointer"
                  @click="openMemberInfo(item.teamLeaderId)">
                  {{ item.teamLeader }}
                </span>
              </div>
              <div class="team-member">
                <div class="team-member-label"><strong>members:</strong></div>
                <div class="members" style="margin-left: 20px">
                  <el-button v-if="item.members.length !== 0"
                             v-for="(member, idx) in item.members"
                             :key="idx"
                             class="member"
                             @click="openMemberInfo(member.memberId)"
                             style="font-size: 18px"
                             type="text">
                    {{ member.name }}
                  </el-button>
                  <div v-if="item.members.length === 0">not have</div>
                </div>
              </div>
              <div class="team-email">{{ item.email }}</div>
            </div>

            <div class="team-intro">
              <strong>intro: </strong>{{ item.intro }}
            </div> <!-- 将简介放在右侧 -->
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<style scoped>
.parent {
  margin-top: 20px;
  margin-bottom: 20px;
  display: flex;
  flex-direction: column; /* 纵向排列子元素 */
  justify-content: flex-start; /* 子元素顶部对齐 */
  align-items: center; /* 子元素水平居中 */
  width: 55%;
  padding: 40px 60px;
}

.team-card {
  width: 100%;
  margin-bottom: 20px;
}

.team-card-content {
  padding: 8px;
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 8px;
}

.team-info {
  display: flex;
  justify-content: space-between; /* 两边对齐 */
  width: 100%;
  align-items: flex-start;
}

.team-details {
  flex: 1; /* 左侧部分占满剩余空间 */
}

.team-name {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
}

.team-leader {
  font-size: 16px;
  margin-bottom: 10px;
}

.team-member {
  display: flex;
  flex-direction: column;
  margin-bottom: 10px;
}

.team-member-label {
  font-size: 16px;
  margin-bottom: 5px;
}

.members {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}

.member {
  text-decoration-line: underline;
  cursor: pointer;
}

.team-intro {
  flex-wrap: wrap; /* 允许换行 */
  align-items: flex-start;
  width: 50%; /* 右侧简介部分宽度 */
  font-size: 14px;
  margin-left: 20px; /* 与其他内容有些间距 */
}

.team-email {
  font-size: 14px;
  color: #888;
}
</style>
