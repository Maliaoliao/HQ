package com.hq.web.controller.system;

import java.util.List;
import java.util.Set;

import com.hq.common.core.domain.model.MpLoginBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.hq.common.constant.Constants;
import com.hq.common.core.domain.AjaxResult;
import com.hq.common.core.domain.entity.SysMenu;
import com.hq.common.core.domain.entity.SysUser;
import com.hq.common.core.domain.model.LoginBody;
import com.hq.common.core.domain.model.LoginUser;
import com.hq.common.utils.ServletUtils;
import com.hq.framework.web.service.SysLoginService;
import com.hq.framework.web.service.SysPermissionService;
import com.hq.framework.web.service.TokenService;
import com.hq.system.service.ISysMenuService;

/**
 * 登录验证
 *
 * @author mall
 */
@RestController
public class SysLoginController {
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * @return
     * @Method 微信小程序用户登录
     * @Description TODO
     * @Param null
     * @date 2021-08-26,0026 11:47
     * @author admin
     */
    @PostMapping("/wxLogin")
    public AjaxResult wxLogin(@RequestBody MpLoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.mpLlogin(loginBody);
        ajax.put(Constants.TOKEN, token);

        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
