package com.sdut.laboratorybackmodule.service.impl;

import com.sdut.laboratorybackmodule.entity.Research;
import com.sdut.laboratorybackmodule.mapper.ResearchMapper;
import com.sdut.laboratorybackmodule.service.IResearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResearchServiceImpl implements IResearchService {
    @Autowired
    private ResearchMapper researchMapper;

    public void insertResearch(Research research) {
        researchMapper.insertResearch(research);
    }

    public Research getResearchById(Long id) {
        return researchMapper.getResearchById(id);
    }

    public List<Research> getAllResearches() {
        return researchMapper.getAllResearches();
    }

    public void updateResearch(Research research) {
        researchMapper.updateResearch(research);
    }

    public void deleteResearch(Long id) {
        researchMapper.deleteResearch(id);
    }
}
