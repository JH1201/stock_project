package com.example.demo.service;

import com.example.demo.domain.CorpOverview;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CorpOverviewService {

    public List<CorpOverview> getCorpOverview(String corpCode) throws IOException {

        /*
        RestTemplate restTemplate = new RestTemplate();
        String firstApiUrl = "https://opendart.fss.or.kr/api/corpCode.xml?crtfc_key=464f642d37bb4aa8fdd334d4a557b57c770efd24&corp_name=다코";
        ResponseEntity<byte[]> firstApiResponse = restTemplate.getForEntity(firstApiUrl, byte[].class);
        byte[] dataFromFirstApi = firstApiResponse.getBody();

// Zip 파일 해제
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(dataFromFirstApi);
        ZipInputStream zipInputStream = new ZipInputStream(byteArrayInputStream);
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource source = new InputSource(zipInputStream);
        Document doc = builder.parse(source);

// XML에서 필요한 정보 추출
        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile("//list/corp_code"); // XML에서 corp_code 태그 값 추출
        String corpCode = (String) expr.evaluate(doc, XPathConstants.STRING);

         */

// 두 번째 API 호출
        RestTemplate restTemplate = new RestTemplate();
        String secondApiUrl = "https://opendart.fss.or.kr/api/company.json?crtfc_key=464f642d37bb4aa8fdd334d4a557b57c770efd24&corp_code=" + corpCode;
        ResponseEntity<String> secondApiResponse = restTemplate.getForEntity(secondApiUrl, String.class);
        String dataFromSecondApi = secondApiResponse.getBody();
        //System.out.println(dataFromSecondApi);

        return processJsonContent(dataFromSecondApi);

    }


    private static List<CorpOverview> processJsonContent(String dataFromSecondApi) throws JsonProcessingException {

        // JSON 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(dataFromSecondApi);

        // Gson 인스턴스 생성
        Gson gson = new Gson();

        // JSON을 Java 객체로 변환
        CorpOverview corpOverview = gson.fromJson(String.valueOf(rootNode), CorpOverview.class);

        /*

        System.out.println("corp_code = " + corpOverview.getCorp_code());

        System.out.println("corp_name = " + corpOverview.getCorp_name());

        System.out.println("corp_name_eng = " + corpOverview.getCorp_name_eng());

        System.out.println("stock_name: " + corpOverview.getStock_name());

        System.out.println("ceo_name = " + corpOverview.getCeo_name());

        System.out.println("corp_cls = " + corpOverview.getCorp_cls());

        System.out.println("jurir_no = " + corpOverview.getJurir_no());

        System.out.println("bizr_no = " + corpOverview.getBizr_no());

        System.out.println("adres = " + corpOverview.getAdres());

        System.out.println("hm_url = " + corpOverview.getHm_url());

        System.out.println("phn_no = " + corpOverview.getPhn_no());

        System.out.println("fax_no = " + corpOverview.getFax_no());

        System.out.println("induty_code = " + corpOverview.getInduty_code());

        System.out.println("est_dt = " + corpOverview.getEst_dt());

        System.out.println("acc_mt = " + corpOverview.getAcc_mt());

         */


        List<CorpOverview> list = new ArrayList<>();
        list.add(corpOverview);

        return list;




    }




}
