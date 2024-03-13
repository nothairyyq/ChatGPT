package com.priska.chatgptapi.domain.security.model.vo;

import org.apache.shiro.authc.AuthenticationToken;


//Token 的对象信息，你可以设置用户ID、用户密码
public class JwtToken implements AuthenticationToken{
    private String jwt;
    public JwtToken(String jwt){
        this.jwt = jwt;
    }

    @Override
    public Object getPrincipal(){
        return jwt;
    }

    @Override
    public Object getCredentials() {
        return jwt;
    }


}
