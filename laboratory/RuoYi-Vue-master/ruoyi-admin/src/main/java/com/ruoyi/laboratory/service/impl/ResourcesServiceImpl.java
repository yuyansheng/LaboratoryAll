package com.ruoyi.laboratory.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.laboratory.mapper.ResourcesMapper;
import com.ruoyi.laboratory.domain.Resources;
import com.ruoyi.laboratory.service.IResourcesService;

/**
 * 资源Service业务层处理
 *
 * @author 展示
 * @date 2025-01-04
 */
@Service
public class ResourcesServiceImpl implements IResourcesService
{
    @Autowired
    private ResourcesMapper resourcesMapper;

    /**
     * 查询资源
     *
     * @param id 资源主键
     * @return 资源
     */
    @Override
    public Resources selectResourcesById(String id)
    {
        return resourcesMapper.selectResourcesById(id);
    }

    /**
     * 查询资源列表
     *
     * @param resources 资源
     * @return 资源
     */
    @Override
    public List<Resources> selectResourcesList(Resources resources)
    {
        return resourcesMapper.selectResourcesList(resources);
    }

    /**
     * 新增资源
     *
     * @param resources 资源
     * @return 结果
     */
    @Override
    public int insertResources(Resources resources)
    {
        return resourcesMapper.insertResources(resources);
    }

    /**
     * 修改资源
     *
     * @param resources 资源
     * @return 结果
     */
    @Override
    public int updateResources(Resources resources)
    {
        return resourcesMapper.updateResources(resources);
    }

    /**
     * 批量删除资源
     *
     * @param ids 需要删除的资源主键
     * @return 结果
     */
    @Override
    public int deleteResourcesByIds(String[] ids)
    {
        return resourcesMapper.deleteResourcesByIds(ids);
    }

    /**
     * 删除资源信息
     *
     * @param id 资源主键
     * @return 结果
     */
    @Override
    public int deleteResourcesById(String id)
    {
        return resourcesMapper.deleteResourcesById(id);
    }
}
