package com.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본 생성자가 생김  null check 안됨
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //회원이 생성될 때 임의의 번호를 통해 다른 테이블과 연결시켜줘야한다, 가령 id, 주민번호로 매핑하는 것은 무결성이 훼손된다.
    private Long id;

    @Column(length = 500, nullable = false) // varchar 의 경우 기본값이 255, 500으로 늘려주려면 이런 애노테이션 달아주면됨.
    private String title;

    @Column(columnDefinition = "TEXT" , nullable = false) // 자료형을 Text로 바꾸려면 이런 애노테이션 달아주면 됨.
    private String content;

    private String author;

    // Entity에 setter를 두는 것은 인스턴스 값이 언제 어디서 변해야하는지 코드상으로 명확하게 구분할 수가 없어 기능 변경시 굉장히 복잡해짐
    // 대신 목적과 의도를 나타낼 수 있는 메서드를 추가하여 값을 변경시켜 주면 좋음
    // 생성자를 통해 값을 입력해줄 수 있는데, 이는 Builder를 통해 알아보기 쉽게 작성할 수 있음
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author =author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}

// Column 속성값 한번 쭉 봐보자자
