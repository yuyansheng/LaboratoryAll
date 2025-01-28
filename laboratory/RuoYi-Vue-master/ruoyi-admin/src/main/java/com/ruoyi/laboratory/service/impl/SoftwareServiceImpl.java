package com.ruoyi.laboratory.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.laboratory.mapper.SoftwareMapper;
import com.ruoyi.laboratory.domain.Software;
import com.ruoyi.laboratory.service.ISoftwareService;

/**
 * 软件Service业务层处理
 *
 * @author 张森
 * @date 2025-01-04
 */
@Service
public class SoftwareServiceImpl implements ISoftwareService
{
    @Autowired
    private SoftwareMapper softwareMapper;

    /**
     * 查询软件
     *
     * @param id 软件主键
     * @return 软件
     */
    @Override
    public Software selectSoftwareById(String id)
    {
        return softwareMapper.selectSoftwareById(id);
    }

    /**
     * 查询软件列表
     *
     * @param software 软件
     * @return 软件
     */
    @Override
    public List<Software> selectSoftwareList(Software software)
    {
        return softwareMapper.selectSoftwareList(software);
    }

    /**
     * 新增软件
     *
     * @param software 软件
     * @return 结果
     */
    @Override
    public int insertSoftware(Software software)
    {
        return softwareMapper.insertSoftware(software);
    }

    /**
     * 修改软件
     *
     * @param software 软件
     * @return 结果
     */
    @Override
    public int updateSoftware(Software software)
    {
        return softwareMapper.updateSoftware(software);
    }

    /**
     * 批量删除软件
     *
     * @param ids 需要删除的软件主键
     * @return 结果
     */
    @Override
    public int deleteSoftwareByIds(String[] ids)
    {
        return softwareMapper.deleteSoftwareByIds(ids);
    }

    /**
     * 删除软件信息
     *
     * @param id 软件主键
     * @return 结果
     */
    @Override
    public int deleteSoftwareById(String id)
    {
        return softwareMapper.deleteSoftwareById(id);
    }
}
