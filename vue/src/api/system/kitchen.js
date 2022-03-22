import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listKitchen(query) {
    return request({
        url: '/system/kitchen/list',
        method: 'get',
        params: query
    })
}

// 查询【请填写功能名称】详细
export function getKitchen(kitchenId) {
    return request({
        url: '/system/kitchen/' + kitchenId,
        method: 'get'
    })
}

// 新增【请填写功能名称】
export function addKitchen(data) {
    return request({
        url: '/system/kitchen',
        method: 'post',
        data: data
    })
}

// 修改【请填写功能名称】
export function updateKitchen(data) {
    return request({
        url: '/system/kitchen',
        method: 'put',
        data: data
    })
}

// 删除【请填写功能名称】
export function delKitchen(kitchenId) {
    return request({
        url: '/system/kitchen/' + kitchenId,
        method: 'delete'
    })
}

// 导出【请填写功能名称】
export function exportKitchen(query) {
    return request({
        url: '/system/kitchen/export',
        method: 'get',
        params: query
    })
}