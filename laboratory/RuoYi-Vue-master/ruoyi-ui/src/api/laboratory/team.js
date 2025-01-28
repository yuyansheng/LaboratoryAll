import request from '@/utils/request'

// 查询团队列表
export function listTeam(query) {
  return request({
    url: '/laboratory/team/list',
    method: 'get',
    params: query
  })
}

// 查询团队详细
export function getTeam(id) {
  return request({
    url: '/laboratory/team/' + id,
    method: 'get'
  })
}

// 新增团队
export function addTeam(data) {
  return request({
    url: '/laboratory/team',
    method: 'post',
    data: data
  })
}

// 修改团队
export function updateTeam(data) {
  return request({
    url: '/laboratory/team',
    method: 'put',
    data: data
  })
}

// 删除团队
export function delTeam(id) {
  return request({
    url: '/laboratory/team/' + id,
    method: 'delete'
  })
}
