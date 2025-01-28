import request from '@/utils/request'

// 查询研究列表
export function listResearch(query) {
  return request({
    url: '/laboratory/research/list',
    method: 'get',
    params: query
  })
}

// 查询研究详细
export function getResearch(id) {
  return request({
    url: '/laboratory/research/' + id,
    method: 'get'
  })
}

// 新增研究
export function addResearch(data) {
  return request({
    url: '/laboratory/research',
    method: 'post',
    data: data
  })
}

// 修改研究
export function updateResearch(data) {
  return request({
    url: '/laboratory/research',
    method: 'put',
    data: data
  })
}

// 删除研究
export function delResearch(id) {
  return request({
    url: '/laboratory/research/' + id,
    method: 'delete'
  })
}
