package hello.hellospring.service;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginServiceTest {

    @Autowired LoginService loginService;

    @Test
    public void login(){
        Member member=new Member();
        member.setUserid("test1");
        member.setUserpassword("1234");
        member.setLevel(1);

        Member result=loginService.login(member.getUserid(), member.getUserpassword());


        assertThat(result).isNotNull();

    }
}
