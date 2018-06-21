package com.sefarm.config.shiro;

import com.sefarm.common.vo.SysUserVO;
import com.sefarm.util.ShiroUtil;

/**
 * 前端Beetl框架所用的Shiro处理类
 *
 * @author mc
 * @date 2018-6-21
 */
public class ShiroHandler {

    /**
     * 获取系统用户信息
     *
     * @return
     */
    public SysUserVO getSysUser() {
        return ShiroUtil.getSysUser();
    }

    /**
     * 验证当前用户是否拥有指定权限
     *
     * @param permission
     *            权限名
     * @return 拥有权限：true，否则false
     */
    public boolean hasPermission(String permission) {
        return ShiroUtil.hasPermission(permission);
    }

}
