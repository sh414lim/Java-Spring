package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//순수 자바코트이므로 스프링컨테이너가 알 수 있는 방법이 없다
//@Service // 스프링이 멤버서비스를 서비스로 등록
public class  MemverService {
    //같은 intance 를 사용하기 위해 설정
//    private final MemberRepository memberRepository =new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    //디펜더비 인젝션? DI
    //외부에서 넣어주게 바꾼다
//    @Autowired
    public MemverService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원 가입
    //같은 이름 잇는 중복회원 안된다
    public Long join(Member member){
        //        result.orElseGet() -> 값이 잇으면 꺼내고 없으면 여기 메서드를 실행해라
        valiDuplicateMember(member);//중복회원 검증
        memberRepository.save(member); //저장
        return member.getId();  //회원 가입시 아이디 반환
    }

    private void valiDuplicateMember(Member member) {
        //null 이 아니면 ifpresent 로직 발생 optional 이여서 가능(null 가능성이 있으면)
        //Optional<Member> result = 이렇게 해도 되지만 옵셔녈을쓸때 바로 반환 안이쁨 리절트가 반환이 됫기때문에 바로 들어갈수 잇다
        memberRepository.findByName(member.getName())// 멤버 레퍼지토리에서 findByname 로 찾아본다
                .ifPresent(m ->
                {
                    throw new IllegalStateException("이미 회원이 존재합니다");
                }
        );
    }

    //전제 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }


    public Optional<Member> findOne(Long memberId){
    return memberRepository.findById(memberId);
    }
}
