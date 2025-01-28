import request from '@/utils/request'

// 查询Style列表
export function listStyle(query) {
  return request({
    url: '/laboratory/style/list',
    method: 'get',
    params: query
  })
}

// 查询Style详细
export function getStyle(id) {
  return request({
    url: '/laboratory/style/' + id,
    method: 'get'
  })
}

// 新增Style
export function addStyle(data) {
  return request({
    url: '/laboratory/style',
    method: 'post',
    data: data
  })
}

// 修改Style
export function updateStyle(data) {
  return request({
    url: '/laboratory/style',
    method: 'put',
    data: data
  })
}

// 删除Style
export function delStyle(id) {
  return request({
    url: '/laboratory/style/' + id,
    method: 'delete'
  })
}
