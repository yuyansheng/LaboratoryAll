package com.ruoyi.laboratory.service;

import java.util.List;
import com.ruoyi.laboratory.domain.Software;

/**
 * 软件Service接口
 *
 * @author 张森
 * @date 2025-01-04
 */
public interface ISoftwareService
{
    /**
     * 查询软件
     *
     * @param id 软件主键
     * @return 软件
     */
    public Software selectSoftwareById(String id);

    /**
     * 查询软件列表
     *
     * @param software 软件
     * @return 软件集合
     */
    public List<Software> selectSoftwareList(Software software);

    /**
     * 新增软件
     *
     * @param software 软件
     * @return 结果
     */
    public int insertSoftware(Software software);

    /**
     * 修改软件
     *
     * @param software 软件
     * @return 结果
     */
    public int updateSoftware(Software software);

    /**
     * 批量删除软件
     *
     * @param ids 需要删除的软件主键集合
     * @return 结果
     */
    public int deleteSoftwareByIds(String[] ids);

    /**
     * 删除软件信息
     *
     * @param id 软件主键
     * @return 结果
     */
    public int deleteSoftwareById(String id);
}
