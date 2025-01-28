import request from '@/utils/request'

// 查询出版物列表
export function listPublication(query) {
  return request({
    url: '/laboratory/publication/list',
    method: 'get',
    params: query
  })
}

// 查询出版物详细
export function getPublication(id) {
  return request({
    url: '/laboratory/publication/' + id,
    method: 'get'
  })
}

// 新增出版物
export function addPublication(data) {
  return request({
    url: '/laboratory/publication',
    method: 'post',
    data: data
  })
}

// 修改出版物
export function updatePublication(data) {
  return request({
    url: '/laboratory/publication',
    method: 'put',
    data: data
  })
}

// 删除出版物
export function delPublication(id) {
  return request({
    url: '/laboratory/publication/' + id,
    method: 'delete'
  })
}
