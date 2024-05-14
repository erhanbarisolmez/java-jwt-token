package com.security.jwttoken.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
   ROLE_USER,
   ROLE_ADMIN,
   ROLE_MOD,
   ROLE_SADMIN;

   @Override
   public String getAuthority(){
    return name();
   }
}
