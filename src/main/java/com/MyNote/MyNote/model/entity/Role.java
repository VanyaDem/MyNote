package com.MyNote.MyNote.model.entity;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return "USER";
    }

}
