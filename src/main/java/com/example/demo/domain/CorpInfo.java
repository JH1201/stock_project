package com.example.demo.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class CorpInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String corp_code;

    @NotNull
    @Column
    private String corp_name;


    @Column
    private String stock_code;

    @NotNull
    @Column
    private String modify_date;


    @Builder
    public CorpInfo(Long id, String corp_code, String corp_name, String stock_code, String modify_date) {
        this.id = id;
        this.corp_code = corp_code;
        this.corp_name = corp_name;
        this.stock_code = stock_code;
        this.modify_date = modify_date;
    }


}
