package com.sandro.hobayan.recipesharingplatform.signin_signup;

public class UserAccount {
    private String Username;
    private String email;

    public UserAccount(){

    }

    public UserAccount(String username, String email) {
        Username = username;
        this.email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
