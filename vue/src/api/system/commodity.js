import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listCommodity(query) {
  return request({
    url: '/system/commodity/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getCommodity(commodityId) {
  return request({
    url: '/system/commodity/' + commodityId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addCommodity(data) {
  return request({
    url: '/system/commodity',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateCommodity(data) {
  return request({
    url: '/system/commodity',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delCommodity(commodityId) {
  return request({
    url: '/system/commodity/' + commodityId,
    method: 'delete'
  })
}

// 导出【请填写功能名称】
export function exportCommodity(query) {
  return request({
    url: '/system/commodity/export',
    method: 'get',
    params: query
  })
}
