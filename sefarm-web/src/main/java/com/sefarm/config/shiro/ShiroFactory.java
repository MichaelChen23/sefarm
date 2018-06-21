package com.sefarm.config.shiro;

import com.sefarm.common.Constant;
import com.sefarm.common.vo.SysUserVO;
import com.sefarm.service.system.ISysMenuService;
import com.sefarm.service.system.ISysUserService;
import com.sefarm.util.SpringContextHolder;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Shiro工厂类，实现shiro需要的接口
 *
 * @author mc
 * @date 2018-6-21
 */
@Service
@DependsOn("springContextHolder")
public class ShiroFactory implements IShiro {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysMenuService sysMenuService;

    public static IShiro getInstance() {
        return SpringContextHolder.getBean(IShiro.class);
    }

    @Override
    public SysUserVO getSysUserByUsername(String username) {
        SysUserVO sysUserVO = sysUserService.getSysUserVOByUsername(username);
        //帐号不存在
        if (sysUserVO == null) {
            throw new CredentialsException();
        } else {
            //帐号禁用
            if (!Constant.STATUS_UNLOCK.equals(sysUserVO.getStatus())) {
                throw new LockedAccountException();
            }
        }
        return sysUserVO;
    }

    @Override
    public List<String> findPermissionsBySysRoleId(Long sysRoleId) {
        List<String> urls = sysMenuService.getMenuUrlsByRoleId(sysRoleId);
        return urls;
    }

    @Override
    public SimpleAuthenticationInfo info(SysUserVO sysUserVO, String realmName) {
        String credentials = sysUserVO.getPassword();
        // 密码加盐处理
        String source = sysUserVO.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        return new SimpleAuthenticationInfo(sysUserVO, credentials, credentialsSalt, realmName);
    }
}
