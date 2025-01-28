package com.ruoyi.laboratory.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.laboratory.domain.Member;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.laboratory.domain.Team;
import com.ruoyi.laboratory.service.ITeamService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 团队Controller
 *
 * @author 张森
 * @date 2025-01-04
 */
@RestController
@RequestMapping("/laboratory/team")
public class TeamController extends BaseController
{
    @Autowired
    private ITeamService teamService;

    /**
     * 查询团队列表
     */
    @Anonymous
    @GetMapping("/list")
    public TableDataInfo list(Team team)
    {
        startPage();
        List<Team> list = teamService.selectTeamList(team);
        return getDataTable(list);
    }

    /**
     * 导出团队列表
     */
    @PreAuthorize("@ss.hasPermi('laboratory:team:export')")
    @Log(title = "团队", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Team team)
    {
        List<Team> list = teamService.selectTeamList(team);
        ExcelUtil<Team> util = new ExcelUtil<Team>(Team.class);
        util.exportExcel(response, list, "团队数据");
    }

    /**
     * 获取团队详细信息
     */
    @PreAuthorize("@ss.hasPermi('laboratory:team:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(teamService.selectTeamById(id));
    }

    /**
     * 新增团队
     */
    @PreAuthorize("@ss.hasPermi('laboratory:team:add')")
    @Log(title = "团队", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Team team)
    {
        return toAjax(teamService.insertTeam(team));
    }

    /**
     * 修改团队
     */
    @PreAuthorize("@ss.hasPermi('laboratory:team:edit')")
    @Log(title = "团队", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Team team)
    {
        return toAjax(teamService.updateTeam(team));
    }

    /**
     * 删除团队
     */
    @PreAuthorize("@ss.hasPermi('laboratory:team:remove')")
    @Log(title = "团队", businessType = BusinessType.DELETE)
	  @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(teamService.deleteTeamByIds(ids));
    }

    @Anonymous
    @GetMapping("/members")
    public List<Map<String,Object>> getTeamMemberNameAndId(@RequestParam("teamId") Long teamId){
      List<Member> allMembers = teamService.findAllMembersByTeamId(teamId);
       List<Map<String,Object>> result = new ArrayList<>();
      for(Member member : allMembers){
        Map<String,Object> map = new HashMap<>();
        map.put("memberId",member.getId());
        map.put("name",member.getName());
        result.add(map);
     }
      System.out.println("!!!!!!!!!!!!"+result);
    return result;
  }

}
