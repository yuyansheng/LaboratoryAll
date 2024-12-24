package com.sdut.laboratorybackmodule.service;

import com.sdut.laboratorybackmodule.entity.Software;

import java.util.List;

public interface ISoftwareService {
    void addSoftware(Software software);

    Software getSoftwareById(Long id);

    List<Software> getAllSoftware();

    void updateSoftware(Software software);

    void deleteSoftware(Long id);
}
