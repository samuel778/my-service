package com.micro.core.config.shiro;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.google.gson.Gson;
import com.micro.core.config.jwt.JwtToken;
import com.micro.core.config.jwt.JwtTokenUtils;
import com.micro.domain.entry.User;
import com.micro.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 自定义权限匹配和账号密码匹配
 *
 * @author saml
 */
@Slf4j
@Service
public class JwtShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("————权限认证————");
        LoginUser loginUser = (LoginUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //设置该用户拥有的权限
        Set<String> permCodeSet = new HashSet<>(16);
//        for (SysPermission permission : loginUser.getPermissionList()) {
//            permCodeSet.add(permission.getPermCode());
//        }
        info.setStringPermissions(permCodeSet);
        return info;
    }

    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return (token instanceof JwtToken);
    }

    /**
     * 身份证认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String jwtToken = (String) token.getCredentials();

        String key;
        try {
            key = jwtTokenUtils.getKeyByToken(jwtToken);
        } catch (SignatureVerificationException | TokenExpiredException | IllegalArgumentException e) {
            //令牌无效
            throw new AuthenticationException("errJwt");
        }

        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>(16);
        map = gson.fromJson(key, map.getClass());
        if (map.get("user_id") == null) {
            throw new AuthenticationException("token认证失败！");
        }
        Integer userId = ((Double) map.get("user_id")).intValue();

        User user = userRepository.selectByPrimaryKey(userId);
        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(new LoginUser(new Date(), user.getId(), user), jwtToken, getName());

    }
}
