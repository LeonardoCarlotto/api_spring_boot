package br.com.projeto.api.modelo;

import org.springframework.stereotype.Component;

@Component
public class Response {

    private String msg;
    private String name;
    private String personNumber;
    private String email;
    private boolean exists;

    
    public String getPersonNumber() {
        return personNumber;
    }
    
    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public void setPersonNumber(String personNumber) {
        this.personNumber = personNumber;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
}
