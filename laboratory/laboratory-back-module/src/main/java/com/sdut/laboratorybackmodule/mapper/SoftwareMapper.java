package com.sdut.laboratorybackmodule.mapper;

import com.sdut.laboratorybackmodule.entity.Software;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SoftwareMapper {
    void insertSoftware(Software software);

    // 根据id查询软件
    Software getSoftwareById(Long id);

    // 查询所有软件
    List<Software> getAllSoftware();

    // 更新软件信息
    void updateSoftware(Software software);

    // 删除软件
    void deleteSoftware(Long id);
}
