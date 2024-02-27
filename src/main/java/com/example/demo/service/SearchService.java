package com.example.demo.service;

import com.example.demo.domain.CorpInfo;
import com.example.demo.mapper.UniqueMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final UniqueMapper uniqueMapper;

    public SearchService(UniqueMapper uniqueMapper) {
        this.uniqueMapper = uniqueMapper;
    }

    public List<CorpInfo> findByName(String query) {
        return uniqueMapper.findCorpByName(query);
    }

}
