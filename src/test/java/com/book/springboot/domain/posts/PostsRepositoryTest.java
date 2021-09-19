package com.book.springboot.domain.posts;

import org.apache.tomcat.jni.Local;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest // 별다른 세팅 없이도 h2 데이터 베이스 돌아감
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After // 테스트가 종료되면 , 수행된다 더불어 AfterEach라는 애노테이션도 있음
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        String title = "게시글  제목";
        String content = "게시글 내용";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("재훈")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        String title = "게시글  제목";
        String content = "게시글 내용";

        LocalDateTime now = LocalDateTime.of(2021,9,16,19,18,0);
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("wogns")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts post = postsList.get(0);
        assertThat(post.getCreateDate()).isAfter(now);
        assertThat(post.getModifiedDate()).isAfter(now);

    }
}
