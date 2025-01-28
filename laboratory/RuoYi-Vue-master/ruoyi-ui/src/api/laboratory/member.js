import request from '@/utils/request'

// 查询成员列表
export function listMember(query) {
  return request({
    url: '/laboratory/member/list',
    method: 'get',
    params: query
  })
}

// 查询成员详细
export function getMember(id) {
  return request({
    url: '/laboratory/member/' + id,
    method: 'get'
  })
}

// 新增成员
export function addMember(data) {
  return request({
    url: '/laboratory/member',
    method: 'post',
    data: data
  })
}

// 修改成员
export function updateMember(data) {
  return request({
    url: '/laboratory/member',
    method: 'put',
    data: data
  })
}

// 删除成员
export function delMember(id) {
  return request({
    url: '/laboratory/member/' + id,
    method: 'delete'
  })
}
