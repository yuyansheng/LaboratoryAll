package com.ruoyi.laboratory.service;

public interface IEmailService {
    // 生成随机验证码
    String generateCode() ;

    // 发送邮件
    public Boolean sendEmail(String to, String subject, String content);

    // 核心业务逻辑：发送验证码并存入 Redis
    public Boolean sendVerificationCode(String email);

    // 验证验证码
    public boolean verifyCode(String email, String code) ;
}
