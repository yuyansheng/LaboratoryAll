package com.ruoyi.laboratory.controller;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.laboratory.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    private IEmailService emailService;

    // 发送验证码接口

  @Anonymous
    @PostMapping("/sendCode")
    public String sendCode(@RequestParam String email) {
         System.out.println(email);

        boolean isSend= emailService.sendVerificationCode(email);
        if (isSend) {
          return "Verification code sent to " + email;
        } else {
          return "Failed to send verification code to " + email;
        }
    }

    // 验证验证码接口
  @Anonymous
    @PostMapping("/verifyCode")
    public String verifyCode(@RequestParam String email, @RequestParam String code) {
        boolean isValid = emailService.verifyCode(email, code);
        return isValid ? "Verification successful" : "Invalid or expired verification code";
    }
}
