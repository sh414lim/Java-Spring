package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//회원 객체를 저장하는 저장소 인터페이스 - 4가지 기능 구현
public interface MemberRepository {
    Member save(Member member);//회원이 저장소에 저장
    Optional<Member> findById(Long id);  //id로 회원을 찾는다
    Optional<Member> findByName(String name);
    List<Member> findAll(); //저장된 모든 리스트 반환
}

// 옵셔녈 -> findById, findByName 을 가져오는데 NULL 일때 ->null 을 그대로 반환 하지않고 옵셔널로 감싸서 반환
