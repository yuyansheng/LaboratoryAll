package com.sdut.laboratorybackmodule.service.impl;

import com.sdut.laboratorybackmodule.entity.Software;
import com.sdut.laboratorybackmodule.mapper.SoftwareMapper;
import com.sdut.laboratorybackmodule.service.ISoftwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareServiceImpl implements ISoftwareService {
    @Autowired
    private SoftwareMapper softwareMapper;

    @Override
    public void addSoftware(Software software) {
        softwareMapper.insertSoftware(software);
    }

    @Override
    public Software getSoftwareById(Long id) {
        return softwareMapper.getSoftwareById(id);
    }

    @Override
    public List<Software> getAllSoftware() {
        return softwareMapper.getAllSoftware();
    }

    @Override
    public void updateSoftware(Software software) {
        softwareMapper.updateSoftware(software);
    }

    @Override
    public void deleteSoftware(Long id) {
        softwareMapper.deleteSoftware(id);
    }
}
