package com.sefarm.config.shiro;

import com.sefarm.common.vo.SysUserVO;
import com.sefarm.util.ShiroUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Shiro的自定义Realm，相当于安全database，保存用户信息
 *
 * @author mc
 * @date 2018-6-21
 */
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        IShiro shiroFactory = ShiroFactory.getInstance();
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SysUserVO sysUserVO = shiroFactory.getSysUserByUsername(token.getUsername());
        SimpleAuthenticationInfo info = shiroFactory.info(sysUserVO, super.getName());
        return info;
    }

    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        IShiro shiroFactory = ShiroFactory.getInstance();
        SysUserVO sysUserVO = (SysUserVO) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //初始设计为单角色，多权限url
        info.addRole(sysUserVO.getSysRoleName());
        Set<String> permissionSet = new HashSet<>();
        List<String> permissions = shiroFactory.findPermissionsBySysRoleId(sysUserVO.getSysRoleId());
        if (permissions != null && permissions.size() > 0) {
            for (String permission : permissions) {
                if (StringUtils.isNotBlank(permission)) {
                    permissionSet.add(permission);
                }
            }
        }
        info.addStringPermissions(permissionSet);
        return info;
    }

    /**
     * 设置认证加密方式
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(ShiroUtil.hashAlgorithmName);
        md5CredentialsMatcher.setHashIterations(ShiroUtil.hashIterations);
        super.setCredentialsMatcher(md5CredentialsMatcher);
    }

}
