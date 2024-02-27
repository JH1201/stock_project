package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorpOverview {

    String corp_code; // 회사코드
    String corp_name; //정식명칭
    String corp_name_eng; // 영문명칭

    String stock_name; //종목명
    String ceo_nm; //대표자명
    String corp_cls; //법인구분 Y/N

    String jurir_no; //법인등록번호
    String bizr_no; //사업자등록번호
    String adres; //주소

    String hm_url; //홈페이지
    String phn_no; //전화번호
    String fax_no; //팩스번호

    String induty_code; //업종코드
    String est_dt; //설립입
    String acc_mt; //결산원
}
