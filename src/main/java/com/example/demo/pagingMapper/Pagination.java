package com.example.demo.pagingMapper;

import com.example.demo.domain.CorpInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pagination extends JpaRepository<CorpInfo, Long> {
    Page<CorpInfo> findAll(Pageable pageable);

}
