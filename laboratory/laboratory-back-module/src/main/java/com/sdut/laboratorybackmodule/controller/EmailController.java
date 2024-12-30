package com.sdut.laboratorybackmodule.controller;

import com.sdut.laboratorybackmodule.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    private IEmailService emailService;

    // 发送验证码接口
    @PostMapping("/sendCode")
    public String sendCode(@RequestParam String email) {
        emailService.sendVerificationCode(email);
        return "Verification code sent to " + email;
    }

    // 验证验证码接口
    @PostMapping("/verifyCode")
    public String verifyCode(@RequestParam String email, @RequestParam String code) {
        boolean isValid = emailService.verifyCode(email, code);
        return isValid ? "Verification successful" : "Invalid or expired verification code";
    }
}