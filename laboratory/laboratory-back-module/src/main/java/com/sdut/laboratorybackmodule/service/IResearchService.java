package com.sdut.laboratorybackmodule.service;

import com.sdut.laboratorybackmodule.entity.Research;

import java.util.List;

public interface IResearchService {

    void insertResearch(Research research) ;

    Research getResearchById(Long id) ;

    List<Research> getAllResearches() ;

    void updateResearch(Research research);

    void deleteResearch(Long id) ;
}
