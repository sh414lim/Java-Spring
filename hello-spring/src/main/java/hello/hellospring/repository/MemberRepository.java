package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);//회원이 저장소에 저장
    Optional<Member> findById(Long id);  //id로 회원을 찾는다
    Optional<Member> findByName(String name);
    List<Member> findAll(); //저장된 모든 리스트 반환
}
