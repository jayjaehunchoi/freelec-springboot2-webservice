package com.book.springboot.web;

import com.book.springboot.domain.posts.Posts;
import com.book.springboot.domain.posts.PostsRepository;
import com.book.springboot.service.posts.PostsService;
import com.book.springboot.web.dto.PostsListResponseDto;
import com.book.springboot.web.dto.PostsSaveRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PostsRepository postsRepository;

//    @Autowired
//    private MockMvc mockMvc;
    @Autowired
    private PostsService postsService;

    @AfterEach
    public void delete(){
        postsRepository.deleteAll();
    }
    @Test
    public void 메인페이지_로딩(){
        String body = this.restTemplate.getForObject("/",String.class); // body에 HTML 전부 따라옴
        assertThat(body).contains("스프링부트로 시작하는 웹 서비스 Ver.2");
    }

    @Test
    public void 글등록기능() throws Exception{

        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .title("title")
                .content("content")
                .author("author")
                .build();
        postsService.save(dto);
        PostsListResponseDto post = postsService.findAllDesc().get(0);

        assertThat(dto.getTitle()).isEqualTo(post.getTitle());

//        mockMvc.perform(get("/posts/save"))
//                .andExpect(status().isOk());


    }


}
