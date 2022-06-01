package br.com.projeto.api.modelo;

import org.springframework.stereotype.Component;

@Component
public class CreateUserResponse {

    private String msg;
    private boolean emailExists;
 
    public String getMsg() {
        return msg;
    }

    public boolean isEmailExists() {
        return emailExists;
    }

    public void setEmailExists(boolean emailExists) {
        this.emailExists = emailExists;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
