package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.repository.jdbcMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    //스프링부트가 설정파일을보고 데이터소스를 만들어준다
    DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean // 스프링 빈을 내가 직접 등록한다
        public MemverService memberService(){
        return new MemverService(memberRepository()); //멤버 레퍼지토리를 서비스에 엮어준다
    }

    @Bean
    public MemberRepository memberRepository(){ //인터페이스

//        return new MemoryMemberRepository(); //구현체 등록
        return new jdbcMemberRepository(dataSource);
    }

}
