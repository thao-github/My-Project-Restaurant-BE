package com.myprojectmd5.dto.response;
import com.myprojectmd5.model.Users;


public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Users account;

    public JwtResponse() {
    }

    public JwtResponse(String token, Users account) {
        this.token = token;
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Users getAccount() {
        return account;
    }

    public void setAccount(Users account) {
        this.account = account;
    }
}
