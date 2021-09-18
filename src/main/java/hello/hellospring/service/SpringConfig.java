package hello.hellospring.service;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {


    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em){
        this.em=em;
    }


    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository(),passwordEncoder());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);

    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
