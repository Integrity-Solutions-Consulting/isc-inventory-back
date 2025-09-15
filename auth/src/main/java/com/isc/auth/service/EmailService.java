package com.isc.auth.service;

public interface EmailService 
{
    void sendForgotPasswordEmail(String emailTo, String token);
    void sendUserCreatedEmail(String emailTo, String password);
}
