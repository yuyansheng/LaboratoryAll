import request from '@/utils/request'

// 查询软件列表
export function listSoftware(query) {
  return request({
    url: '/laboratory/software/list',
    method: 'get',
    params: query
  })
}

// 查询软件详细
export function getSoftware(id) {
  return request({
    url: '/laboratory/software/' + id,
    method: 'get'
  })
}

// 新增软件
export function addSoftware(data) {
  return request({
    url: '/laboratory/software',
    method: 'post',
    data: data
  })
}

// 修改软件
export function updateSoftware(data) {
  return request({
    url: '/laboratory/software',
    method: 'put',
    data: data
  })
}

// 删除软件
export function delSoftware(id) {
  return request({
    url: '/laboratory/software/' + id,
    method: 'delete'
  })
}
