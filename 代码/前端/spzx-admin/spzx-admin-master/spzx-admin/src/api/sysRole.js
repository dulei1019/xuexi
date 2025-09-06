import request from '@/utils/request'

const base_api = '/admin/system/sysRole'

// 分页查询角色数据
export const GetSysRoleListByPage = (current , limit ,queryDto) => {
    return request({
        url: `${base_api}/findByPage/${current}/${limit}`,//路径
        method: 'post',//提交方式
        //接口@RequestBody  前端data:  名称，以jison格式传输
        //接口没有注解，前端 params:名称
        params: queryDto //其他参数
    })
}

// 添加角色请求方法
export const SaveSysRole = (sysRole) => {
    return request({
        url: `${base_api}/saveSysRole`,
        method: 'post',
        data: sysRole,
    })
}

// 保存修改
export const UpdateSysRole = (sysRole) => {
    return request({
        url: `${base_api}/updateSysRole`,
        method: 'put',
        data: sysRole,
    })
}
// 角色删除
export const DeleteSysRole = (roleId) => {
    return request({
        url:  `${base_api}/deleteById/${roleId}`,
        method: 'delete',
        
    })
}
// 查询所有的角色数据
export const GetAllRoleList = (userId) => {
    return request({
        url: '/admin/system/sysRole/findAllRoles/'+userId,
        method: 'get'
    })
}
// 查询指定角色所对应的菜单id
export const GetSysRoleMenuIds = (roleId) => {
    return request({
        url: "/admin/system/sysRoleMenu/findSysRoleMenuByRoleId/"+ roleId,
        method: 'get'
    })
}

// 根据角色分配菜单请求方法
export const DoAssignMenuIdToSysRole = (assignMenuDto) => {
    return request({
        url: "/admin/system/sysRoleMenu/doAssign",
        method: 'post',
        data: assignMenuDto
    })
}


