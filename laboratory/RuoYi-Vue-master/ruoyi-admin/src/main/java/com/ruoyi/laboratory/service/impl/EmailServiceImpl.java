package com.ruoyi.laboratory.service.impl;

import com.ruoyi.laboratory.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    // 生成随机验证码
    public String generateCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // 生成 6 位随机数
        return String.valueOf(code);
    }

    // 发送邮件
    public Boolean sendEmail(String to, String subject, String content) {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setTo(to);
      message.setFrom(from);
      message.setSubject(subject);
      message.setText(content);

      try {
        mailSender.send(message);  // 尝试发送邮件
        return true;  // 发送成功
      } catch (Exception e) {
        System.err.println("Error sending email to: " + to);
        e.printStackTrace();  // 打印错误信息
        return false;  // 发送失败
      }
    }

    // 核心业务逻辑：检查验证码是否有效，若无有效验证码则发送
    public Boolean sendVerificationCode(String email) {
        // 检查是否存在有效期内的验证码
        String storedCode="";
        if (hasValidVerificationCode(email)) {//有则重新发送验证码
            String key = "email:code:" + email;
            storedCode = redisTemplate.opsForValue().get(key);

            // 如果存在有效验证码，则不重新发送
            System.out.println("There is already a valid verification code sent to: " + email);

        } else {
            // 生成验证码并发送
            storedCode = generateCode();
            // 将验证码存入 Redis，设置 5 分钟有效期
            redisTemplate.opsForValue().set("email:code:" + email, storedCode, 5, TimeUnit.MINUTES);

        }
        // 发送邮件
        String subject = "Your Verification Code";
        String content = "Your verification code is: " + storedCode + "\nIt is valid for 5 minutes.";
      try {
        boolean emailSent = sendEmail(email, subject, content);
        return emailSent;  // 返回发送结果
      } catch (EmailSendException e) {
        // 捕获发送失败异常并返回失败
        System.out.println("Error: " + e.getMessage());
        return false;  // 发送失败
      }
    }

    // 验证验证码
    public boolean verifyCode(String email, String code) {
        String key = "email:code:" + email;
        String storedCode = redisTemplate.opsForValue().get(key);
        System.out.println(code+"         "+storedCode);
        // 检查验证码是否匹配
        if (storedCode != null && storedCode.equals(code)) {
            // 验证通过后删除 Redis 中的验证码
            redisTemplate.delete(key);
            return true;
        }
        return false;
    }

    // 检查是否存在有效期内的验证码
    public boolean hasValidVerificationCode(String email) {
        String key = "email:code:" + email;

        // 检查 Redis 中是否存在验证码，并且验证该验证码是否仍在有效期内
        Long ttl = redisTemplate.getExpire(key, TimeUnit.MINUTES); // 获取验证码的有效期

        // 如果 TTL 为负数，说明键不存在或者已经过期
        if (ttl != null && ttl > 0) {
            // 有效期内的验证码存在
            return true;
        } else {
            // 没有有效期内的验证码
            return false;
        }
    }
    static class EmailSendException extends RuntimeException {
    public EmailSendException(String message) {
      super(message);
    }
  }
}
