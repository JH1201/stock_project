package com.example.demo.mapper;

import com.example.demo.domain.CorpInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniqueMapper {

    List<CorpInfo> findCorpByName(String name);

}
