package com.book.springboot.config.auth.dto;

import com.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

//session user에는 인증된 사용자 정보만 필요
//직렬화할 수 있는 dto를 만들어줘서 정보 교환
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }

}
