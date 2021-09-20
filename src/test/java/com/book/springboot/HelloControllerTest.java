package com.book.springboot;

import com.book.springboot.config.auth.SecurityConfig;
import com.book.springboot.web.HelloController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class) // 스프링 부트와 junit사이의 연결자
@WebMvcTest(controllers = HelloController.class,
                            excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                            classes = SecurityConfig.class)}) //Controller만 테스트 해줄 때 이 annotation을 사용
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc; // 웹 API 테스트할 때 사용, 이 클래스를 통해 HTTP GET, POST  등에 대한 API 테스트 가능

    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";
        mvc.perform(get("/hello")) //MockMvc를 통해 /hello 주소로 get요청
                .andExpect(status().isOk())// mvc perform의 결과 검증, header status 검증(200인지)
                .andExpect(content().string(hello)); // Controller 의 return 값이 hello(hello페이지로 매핑됨)이기 때문에 테스트 통과

    }
    @WithMockUser(roles = "USER")
    @Test
    public void hello_dto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                        .param("name",name)
                        .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
    }
}
