package com.mycompany.mywebapp.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository repo;

    @Test
    public void testAddNew(){ //새로운 데이터 넣기
        User user=new User();
        user.setEmail("ajou1234@ajou.ac.kr");
        user.setPassword("123");
        user.setFirstName("ajou");
        user.setLastName("1234");

        User savedUser=repo.save(user); //데이터베이스에 저장

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }
    @Test
    public void testListAll(){ //전체 데이터 가져오기
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for(User user: users){
            System.out.println(user);
        }
    }
    @Test
    public void testUpdate(){ //유저 업데이트
        Integer userId=1;
        Optional<User> optionalUser= repo.findById(userId);
        User user=optionalUser.get();
        user.setPassword("jk123456");
        repo.save(user);

        User updatedUser=repo.findById(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("jk123456");
    }
    @Test
    public void testGet(){
        Integer userId=2;
        Optional<User> optionalUser= repo.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());

    }
    @Test
    public void testDelete(){ //데이터 삭제
        Integer userId=2;
        repo.deleteById(userId);

        Optional<User> optionalUser= repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
