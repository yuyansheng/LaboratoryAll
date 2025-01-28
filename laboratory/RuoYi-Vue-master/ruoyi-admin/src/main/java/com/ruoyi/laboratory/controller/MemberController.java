package com.ruoyi.laboratory.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.laboratory.domain.Member;
import com.ruoyi.laboratory.service.IMemberService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 成员Controller
 *
 * @author 张森
 * @date 2025-01-04
 */
@RestController
@RequestMapping("/laboratory/member")
public class MemberController extends BaseController
{
    @Autowired
    private IMemberService memberService;

    /**
     * 查询成员列表
     */
    @PreAuthorize("@ss.hasPermi('laboratory:member:list')")
    @GetMapping("/list")
    public TableDataInfo list(Member member)
    {
        startPage();
        List<Member> list = memberService.selectMemberList(member);
        return getDataTable(list);
    }

    @Anonymous
    @PutMapping("/register")
    public void Register(@RequestBody Member member) {
      System.out.println(member);
      memberService.insertMember(member);
    }

    /**
     * 导出成员列表
     */
    @PreAuthorize("@ss.hasPermi('laboratory:member:export')")
    @Log(title = "成员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Member member)
    {
        List<Member> list = memberService.selectMemberList(member);
        ExcelUtil<Member> util = new ExcelUtil<Member>(Member.class);
        util.exportExcel(response, list, "成员数据");
    }

    @PutMapping("/update")
    public AjaxResult updateMember( @RequestBody Member member) {
      return toAjax(memberService.updateMember(member));
    }
    /**
     * 获取成员详细信息
     */
    @Anonymous
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(memberService.selectMemberById(id));
    }

    /**
     * 新增成员
     */
    @PreAuthorize("@ss.hasPermi('laboratory:member:add')")
    @Log(title = "成员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Member member)
    {
        return toAjax(memberService.insertMember(member));
    }

    /**
     * 修改成员
     */
    @PreAuthorize("@ss.hasPermi('laboratory:member:edit')")
    @Log(title = "成员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Member member)
    {
        return toAjax(memberService.updateMember(member));
    }

    /**
     * 删除成员
     */
    @PreAuthorize("@ss.hasPermi('laboratory:member:remove')")
    @Log(title = "成员", businessType = BusinessType.DELETE)
  	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(memberService.deleteMemberByIds(ids));
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

  @Anonymous
  @PostMapping("/login")
  public ResponseEntity<?> loginMember(@RequestBody MemberLoginRequest request) {

    String email = request.getEmail();
    String password = request.getPassword();
    System.out.println("!!!!!"+email+password);
    Member member = memberService.login(email, password);
    if (member == null) {
      // 如果成员为空，返回适当的响应
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("登录失败，邮箱或密码错误");
    } else {
      // 成功找到成员，返回成功响应
      return ResponseEntity.ok(member);
    }
  }
}
