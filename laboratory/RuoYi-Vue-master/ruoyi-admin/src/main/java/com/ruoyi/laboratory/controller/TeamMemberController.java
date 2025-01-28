package com.ruoyi.laboratory.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.laboratory.domain.TeamMember;
import com.ruoyi.laboratory.service.ITeamMemberService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 团队成员Controller
 *
 * @author 张森
 * @date 2025-01-04
 */
@RestController
@RequestMapping("/laboratory/team_member")
public class TeamMemberController extends BaseController
{
    @Autowired
    private ITeamMemberService teamMemberService;

    /**
     * 查询团队成员列表
     */
    @PreAuthorize("@ss.hasPermi('laboratory:team_member:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeamMember teamMember)
    {
        startPage();
        List<TeamMember> list = teamMemberService.selectTeamMemberList(teamMember);
        return getDataTable(list);
    }

    /**
     * 导出团队成员列表
     */
    @PreAuthorize("@ss.hasPermi('laboratory:team_member:export')")
    @Log(title = "团队成员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TeamMember teamMember)
    {
        List<TeamMember> list = teamMemberService.selectTeamMemberList(teamMember);
        ExcelUtil<TeamMember> util = new ExcelUtil<TeamMember>(TeamMember.class);
        util.exportExcel(response, list, "团队成员数据");
    }

    /**
     * 获取团队成员详细信息
     */
    @PreAuthorize("@ss.hasPermi('laboratory:team_member:query')")
    @GetMapping(value = "/{teamId}")
    public AjaxResult getInfo(@PathVariable("teamId") String teamId)
    {
        return success(teamMemberService.selectTeamMemberByTeamId(teamId));
    }

    /**
     * 新增团队成员
     */
    @PreAuthorize("@ss.hasPermi('laboratory:team_member:add')")
    @Log(title = "团队成员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeamMember teamMember)
    {
        return toAjax(teamMemberService.insertTeamMember(teamMember));
    }

    /**
     * 修改团队成员
     */
    @PreAuthorize("@ss.hasPermi('laboratory:team_member:edit')")
    @Log(title = "团队成员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeamMember teamMember)
    {
        return toAjax(teamMemberService.updateTeamMember(teamMember));
    }

    /**
     * 删除团队成员
     */
    @PreAuthorize("@ss.hasPermi('laboratory:team_member:remove')")
    @Log(title = "团队成员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teamIds}")
    public AjaxResult remove(@PathVariable String[] teamIds)
    {
      System.out.println(teamIds[0]);
      System.out.println("!23232332312321312331312");

      return toAjax(teamMemberService.deleteTeamMemberByTeamIds(teamIds)>=0);
    }
}
