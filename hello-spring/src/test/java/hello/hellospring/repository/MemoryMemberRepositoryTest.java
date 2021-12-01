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
    //만들지 않으면 테스트의 순서가 비보장
    @AfterEach
        public void afferEach(){
    repositiry.clearStore(); //메소드가 끝날때마다 저장소를 비운다
        }

         @Test //테스트 //@test import 이걸 실행할 수 있다.
         public void save() {
             Member member = new Member();
             member.setName("spring");

             repositiry.save(member); //레퍼지토리에 세이브
             Member result = repositiry.findById(member.getId()).get(); //확인 옵셔널에서는 get 으로 꺼냄(테스트 에서)
//             Assertions.assertEquals(member,result); 멤버가 find 햇을때 나와야된다
             assertThat(member).isEqualTo(result); //테스트 비교 member 가 result 랑 똑같다 바로 알려줌

         }
     }
    @Test
     public void findByname(){ //이름으로 찾음
        Member member1 = new Member();
        member1.setName("spring1"); //set
        repositiry.save(member1); //레퍼지토리에 member1 을 저장

        Member member2  = new Member();
        member2.setName("spring2");
        repositiry.save(member2);

        Member result = repositiry.findByName("spring1").get(); //get 옵셔널 까서 거냄


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
