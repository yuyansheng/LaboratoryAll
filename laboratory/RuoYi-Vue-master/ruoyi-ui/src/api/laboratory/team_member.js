import request from '@/utils/request'

// 查询团队成员列表
export function listTeam_member(query) {
  return request({
    url: '/laboratory/team_member/list',
    method: 'get',
    params: query
  })
}

// 查询团队成员详细
export function getTeam_member(teamId) {
  return request({
    url: '/laboratory/team_member/' + teamId,
    method: 'get'
  })
}

// 新增团队成员
export function addTeam_member(data) {
  return request({
    url: '/laboratory/team_member',
    method: 'post',
    data: data
  })
}

// 修改团队成员
export function updateTeam_member(data) {
  return request({
    url: '/laboratory/team_member',
    method: 'put',
    data: data
  })
}

// 删除团队成员
export function delTeam_member(teamId) {
  return request({
    url: '/laboratory/team_member/' + teamId,
    method: 'delete'
  })
}
