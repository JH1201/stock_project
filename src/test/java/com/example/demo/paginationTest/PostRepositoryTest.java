package com.example.demo.paginationTest;

import com.example.demo.domain.CorpInfo;
import com.example.demo.pagingMapper.Pagination;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private Pagination pagination;

    @BeforeEach
    public void cleanup() {
        pagination.deleteAll();
    }

    @Test
    @DisplayName("Testtt")
    public void test() {
        // given
        String corp_code = "12345678";
        String corp_name = "Samsung";
        String stock_code = "123";
        String modify_date = "20240101";

        pagination.save(CorpInfo.builder()
                .corp_code(corp_code)
                .corp_name(corp_name)
                .stock_code(stock_code)
                .modify_date(modify_date)
                .build());


        // when
        List<CorpInfo> postsList = pagination.findAll();

        // then
        CorpInfo corpInfo = postsList.get(0);
        Assertions.assertEquals(corp_code, corpInfo.getCorp_code());
        Assertions.assertEquals(corp_name, corpInfo.getCorp_name());
        Assertions.assertEquals(stock_code, corpInfo.getStock_code());
        Assertions.assertEquals(modify_date, corpInfo.getModify_date());
    }
}
