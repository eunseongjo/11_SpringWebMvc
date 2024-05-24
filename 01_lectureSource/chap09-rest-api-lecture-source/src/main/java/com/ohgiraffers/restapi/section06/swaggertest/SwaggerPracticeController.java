package com.ohgiraffers.restapi.section06.swaggertest;

import com.ohgiraffers.restapi.section05.swagger.UserDTO;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/swaggerTest")
public class SwaggerPracticeController {

    private List<UserDTO> users;

    public SwaggerPracticeController(){
        users = new ArrayList<>();

        users.add(new UserDTO(1, "user01", "pass01", "코알라", new Date()));
        users.add(new UserDTO(2, "user02", "pass02", "판다", new Date()));
        users.add(new UserDTO(3, "user03", "pass03", "람쥐", new Date()));
    }

    /* 회원 전체 조회*/
    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> findAllUser(){

        //응답 헤더 설정
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType((new MediaType("application", "json", Charset.forName("UTF-8"))));

        //응답 데이터 설정
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("users", users);

        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", responseMap);

        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
    }

    /* 회원 상세 조회 */
    @GetMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> findUserByNo(@PathVariable int userNo){
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).collect(Collectors.toList()).get(0);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("users", users);

        return ResponseEntity.ok().headers(headers).body(new ResponseMessage(200, "조회 성공", responseMap));
    }



}
