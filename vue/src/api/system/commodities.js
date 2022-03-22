import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listCommodities(query) {
    return request({
        url: '/system/commodities/list',
        method: 'get',
        params: query
    })
}

// 查询【请填写功能名称】详细
export function getCommodities(commodityId) {
    return request({
        url: '/system/commodities/' + commodityId,
        method: 'get'
    })
}

// 新增【请填写功能名称】
export function addCommodities(data) {
    return request({
        url: '/system/commodities',
        method: 'post',
        data: data
    })
}

// 修改【请填写功能名称】
export function updateCommodities(data) {
    return request({
        url: '/system/commodities',
        method: 'put',
        data: data
    })
}

// 删除【请填写功能名称】
export function delCommodities(commodityId) {
    return request({
        url: '/system/commodities/' + commodityId,
        method: 'delete'
    })
}

// 导出【请填写功能名称】
export function exportCommodities(query) {
    return request({
        url: '/system/commodities/export',
        method: 'get',
        params: query
    })
}