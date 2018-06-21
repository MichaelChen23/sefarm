package com.sefarm.config.shiro;

import com.sefarm.common.vo.SysUserVO;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

import java.util.List;

/**
 * 定义shiroRealm所需要数据的接口
 *
 * @author mc
 * @date 2018-6-20
 */
public interface IShiro {

    /**
     * 根据账号获取登录用户信息
     * @param username
     * @return
     */
    SysUserVO getSysUserByUsername(String username);

    /**
     * 根据用户角色id获取用户权限列表
     * @param sysRoleId
     * @return
     */
    List<String> findPermissionsBySysRoleId(Long sysRoleId);

    /**
     * 获取shiro的认证信息
     * @param sysUserVO
     * @param realmName
     * @return
     */
    SimpleAuthenticationInfo info(SysUserVO sysUserVO, String realmName);
}
