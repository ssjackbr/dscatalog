package br.com.ignidigital.dscatalog.dto;

import net.bytebuddy.implementation.bind.annotation.Super;

public class UserInsertDTO extends UserDTO {
    private static final long serialVersionUID = 1L;

    private String password;

     UserInsertDTO() {
         super();
     }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
