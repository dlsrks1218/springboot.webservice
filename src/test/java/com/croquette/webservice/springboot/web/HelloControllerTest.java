package com.croquette.webservice.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;

// 테스트 진행 시 JUnit에 내장된 실행자 외에 다른 실행자(SpringRunner, 스프링 실행자)를 실행 시킴
// 스프링 부트 테스트와 JUnit 사이 연결자 역할
@RunWith(SpringRunner.class)
// 여러 스프링 테스트 annotation 중 Web(Spring MVC)에 집중할 수 있는 annotation
// 선언 시 @Controller, @ControllerAdvice 등 사용 가능
// @Service, @component, @Repository 등 사용 불가
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
    // 스프링이 관리하는 Bean을 주입 받음
    @Autowired
    // 웹 API 테스트 시 사용
    // 스프링 MVC 테스트의 시작점
    // 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트 가능
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";
        // MockMvc를 통해 /hello 주소로 HTTP GET 요청, 체이닝이 지원되어 여러 검증 기능을 이어 선언 가능
        mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        // param
                        // API 테스트할 때 사용될 요청 파라미터 설정
                        // 값은 String만 허용
                        // 숫자/날짜 등의 데이터도 작성할 때는 문자열로 변경해야만 가능
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.name", is(name)))
                .andExpect((ResultMatcher) jsonPath("$.amount", is(amount)));
    }
}
