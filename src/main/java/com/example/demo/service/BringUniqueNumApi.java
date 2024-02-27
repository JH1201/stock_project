package com.example.demo.service;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class BringUniqueNumApi {

    public void saveUniqueNumber() throws IOException {
        String apiUrl = "https://opendart.fss.or.kr/api/corpCode.xml?crtfc_key=464f642d37bb4aa8fdd334d4a557b57c770efd24";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
        headers.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));

        ResponseEntity<Resource> response = restTemplate.getForEntity(apiUrl, Resource.class);

        // 가져온 Zip 파일을 읽기 위해 InputStream을 얻습니다.
        InputStream inputStream = response.getBody().getInputStream();


        // Zip 파일의 내용을 바이트 배열로 읽어 반환합니다.
        byte[] zipContent;
        try {
            zipContent = readZipContent(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            // 오류 처리 필요
            return;
        }

        // 가져온 Zip 파일의 내용을 바이트 배열로 반환합니다.
        // 반환된 데이터는 다른 부분에서 필요한 방식으로 처리할 수 있습니다.
        // 예를 들어, 이 메서드를 호출한 곳에서 파일로 저장하거나 다른 곳에 전달할 수 있습니다.
        processZipContent(zipContent);
    }


    private static byte[] readZipContent(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            ZipEntry zipEntry;
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                zipInputStream.closeEntry();
            }
        }
        return outputStream.toByteArray();
    }

    private static void processZipContent(byte[] zipContent) throws UnsupportedEncodingException {
        // 이 메서드에서는 zipContent를 필요한 방식으로 처리할 수 있습니다.
        // 예를 들어, 바이트 배열을 문자열로 변환하거나 파일로 저장할 수 있습니다.
        // 필요한 작업을 수행한 후에 반환하거나 출력하도록 구현하세요.

        try {
            // 바이트 배열을 문자열로 변환
            String xmlString = new String(zipContent);

            // XML 파싱
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(zipContent));

            // 루트 요소 가져오기
            Element root = doc.getDocumentElement();

            // 리스트 요소 가져오기
            NodeList listNodes = root.getElementsByTagName("list");

            // H2 데이터베이스 연결
            Connection conn = DriverManager.getConnection("jdbc:h2:mem:mydb", "sa", "");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO corp_info (corp_code, corp_name, stock_code, modify_date) VALUES (?, ?, ?, ?)");

            // 각 리스트 요소를 반복하면서 데이터베이스에 삽입
            for (int i = 0; i < listNodes.getLength(); i++) {
                Element listElement = (Element) listNodes.item(i);
                String corpCode = listElement.getElementsByTagName("corp_code").item(0).getTextContent();
                String corpName = listElement.getElementsByTagName("corp_name").item(0).getTextContent();
                String stockCode = listElement.getElementsByTagName("stock_code").item(0).getTextContent();
                String modifyDate = listElement.getElementsByTagName("modify_date").item(0).getTextContent();

                // PreparedStatement에 값 설정
                pstmt.setString(1, corpCode);
                pstmt.setString(2, corpName);
                pstmt.setString(3, stockCode);
                pstmt.setString(4, modifyDate);

                // SQL 실행
                pstmt.executeUpdate();
            }

            // 자원 정리
            pstmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
