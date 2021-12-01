package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemverServiceTest {

//    MemverService memverService =new MemverService(); //멤버 서비스 생성
    //    MemoryMemberRepository memoryMemberRepository =new MemoryMemberRepository();


    MemverService memverService;
    MemoryMemberRepository memoryMemberRepository;


    //memberservice 를 생성할때 new MemoryMemberRepositiry(); 를 직접 넣어주어야한다

    @BeforeEach //동작하기전에 넣어준다
    public void beforeEach(){
//        memberRepository = new MemoryMemberRepository(); 해결요망.... 외부에서 값이 들어가지 않는다
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memverService =new MemverService(memberRepository ); //레퍼지토리를 넣어주어야한다;
    }


    @AfterEach
    public void affterEach(){
        memoryMemberRepository.clearStore();
    }

    @Test
    void join() {                    // given when then 문법 추천 (테스트 패턴)
        //given
        Member member =new Member();
        member.setName("spring");

        //when
        Long saveId = memverService.join(member); //join 검증


        //then
        Member findMember = memverService.findOne(saveId).get(); //우리가 저장을 한게 리퍼지토리에 잇는지 확인
        assertThat(member.getName()).isEqualTo(findMember.getName()) ; //member 이름이 findeMember 과 같은지 실행
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 =new Member();
        member1.setName("spring");


        Member member2 =new Member();
        member2.setName("spring");

        //when
        memverService.join(member1);

        //우리가 쓴 IllegalStateException 넣고 람다
        // command option v 단추키 기억

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memverService.join(member2));//예외가 터져야한다

        assertThat(e.getMessage()).isEqualTo("이미 회원이 존재합니다");

//        try{
//                memverService.join(member2);
//                fail(); // 테스트 예외가 발생햐여한다
//        }catch(IllegalStateException e){
//        assertThat(e.getMessage()).isEqualTo("이미 회원이 존재합니다1");
//        }

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}