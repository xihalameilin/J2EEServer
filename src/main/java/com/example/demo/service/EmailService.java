package com.example.demo.service;



public interface EmailService {
    public void sendMessage(String receiveMail) throws  Exception;

    public String getIndentityCode();
}
