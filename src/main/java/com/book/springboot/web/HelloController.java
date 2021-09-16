package com.book.springboot.web;

import com.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    //hello dto에서 name, amount로 넘겨준 데이터를 , String name, int amount로 정리해준다다
    // request Param은 form tag 통해서 url로 들어올 때 사용, 자동으로 name, amount와 매핑해준다
    @GetMapping("/hello/dto")
   public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }

}
