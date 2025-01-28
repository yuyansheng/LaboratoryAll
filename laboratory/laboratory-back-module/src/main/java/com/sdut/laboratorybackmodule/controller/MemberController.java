package com.sdut.laboratorybackmodule.controller;

import com.sdut.laboratorybackmodule.entity.Member;
import com.sdut.laboratorybackmodule.service.IMemberService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = "*")
public class MemberController {
    @Autowired
    IMemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<?> loginMember(@RequestBody MemberLoginRequest request) {
        String email = request.getEmail();
        String password = request.getPassword();
        Member member = memberService.login(email, password);
        if (member == null) {
            // 如果成员为空，返回适当的响应
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("登录失败，邮箱或密码错误");
        } else {
            // 成功找到成员，返回成功响应
            return ResponseEntity.ok(member);
        }
    }

    @GetMapping("/searchMembersByName")
    public List<MemberDTO> searchMembersByName(@RequestParam("name") String name) {
        System.out.println(name+"ASDSDASDSADASDSADDS");
        List<Member> members = memberService.searchMembersByName(name);
        return members.stream()
                .map(member -> new MemberDTO(member.getId(), member.getName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/all")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/getMemberById/{id}")
    public Member getMemberById(@PathVariable int id) {
        return memberService.getMemberById(id);
    }

    @PutMapping("/add")
    public void addMember(@RequestBody Member member) {
        System.out.println(member);
        memberService.insertMember(member);
    }

    @PutMapping("/update}")
    public void updateMember( @RequestBody Member member) {
        memberService.updateMember(member);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMember(@PathVariable int id) {
        memberService.deleteMember(id);
    }

    // 定义登录请求的数据模型
    @Getter @Setter
    static class MemberLoginRequest {
        private String email;
        private String password;

        @Override
        public String toString() {
            return super.toString()+ email + password;
        }
    }

    @Getter @Setter
    public static class MemberDTO {
        private Integer id;
        private String name;
        // 构造函数
        public MemberDTO(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
    }

}
