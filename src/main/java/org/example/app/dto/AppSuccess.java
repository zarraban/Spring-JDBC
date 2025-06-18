package org.example.app.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppSuccess {
    private int statusCode;
    private String message;
    public AppSuccess(){

    }
    public AppSuccess(int statusCode, String message){
        this.message = message;
        this.statusCode = statusCode;

    }
}
