package com.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
//보통 myBatis에서 Dao라고 불리는 DB Layer 접근자임
// JpaRepository를 상속하는 것만으로 기본적인 crud 기능은 모두 수행할 수 있음
public interface PostsRepository extends JpaRepository<Posts,Long> {
}
