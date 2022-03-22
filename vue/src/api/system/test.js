import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listTest(query) {
    return request({
        url: '/system/test/list',
        method: 'get',
        params: query
    })
}

// 查询【请填写功能名称】详细
export function getTest(test) {
    return request({
        url: '/system/test/' + test,
        method: 'get'
    })
}

// 新增【请填写功能名称】
export function addTest(data) {
    return request({
        url: '/system/test',
        method: 'post',
        data: data
    })
}

// 修改【请填写功能名称】
export function updateTest(data) {
    return request({
        url: '/system/test',
        method: 'put',
        data: data
    })
}

// 删除【请填写功能名称】
export function delTest(test) {
    return request({
        url: '/system/test/' + test,
        method: 'delete'
    })
}

// 导出【请填写功能名称】
export function exportTest(query) {
    return request({
        url: '/system/test/export',
        method: 'get',
        params: query
    })
}
