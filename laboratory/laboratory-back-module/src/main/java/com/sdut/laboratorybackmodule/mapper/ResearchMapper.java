package com.sdut.laboratorybackmodule.mapper;

import com.sdut.laboratorybackmodule.entity.Research;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResearchMapper {
    void insertResearch(Research research);

    Research getResearchById(Long id);

    List<Research> getAllResearches();

    void updateResearch(Research research);

    void deleteResearch(Long id);
}
