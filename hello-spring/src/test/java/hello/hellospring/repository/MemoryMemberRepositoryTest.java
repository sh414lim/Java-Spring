package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

//순서에 의존해서 만들면 안된다!
//테스트는 서로 순서에 의존 관계를 만들면 안된다
//테스트를 먼저 만들고 구현 클래스가 꽂히는지 보면서 만드는 개발 TDD 테스트 주도개발! 중요!
class MemoryMemberRepositoryTest {

     MemoryMemberRepository repositiry = new MemoryMemberRepository(); //구현 클래스

     //테스트 메서드가 끝날때 마다 클리어 메소드
    @AfterEach
        public void afferEach(){
    repositiry.clearStore(); //메소드가 끝날때마다 저장소를 비운다
        }

         @Test //테스트
         public void save(){
             Member member = new Member();
             member.setName("spring");

             repositiry.save(member);
             Member result = repositiry.findById(member.getId()).get();
//             Assertions.assertEquals(member,null);
             assertThat(member).isEqualTo(result); //테스트 비교

     }
    @Test
     public void findByname(){
        Member member1 = new Member();
        member1.setName("spring1");
        repositiry.save(member1);

        Member member2  = new Member();
        member2.setName("spring2");
        repositiry.save(member2);

        Member result = repositiry.findByName("spring1").get();


        assertThat(result).isEqualTo(member1);


    }
    @Test
    public void findAll(){
             Member member1 = new Member();
             member1.setName("spring1");
             repositiry.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repositiry.save(member2);

        List<Member> result = repositiry.findAll();

        assertThat(result.size()).isEqualTo(2);


    }
}
