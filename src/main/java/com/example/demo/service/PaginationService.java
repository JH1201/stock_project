package com.example.demo.service;

import com.example.demo.domain.CorpInfo;
import com.example.demo.pagingMapper.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class PaginationService {

    private final Pagination pagination;

    public PaginationService(Pagination pagination) {
        this.pagination = pagination;
    }


    @Transactional(readOnly = true)
    public Page<CorpInfo> paging(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return pagination.findAll(pageable);

    }


}


