package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long,Member> store = new HashMap<>(); //공유 될수 있는 저장소는 컨커런스 를 써야된다?
    private static Long sequence =0L; //키 값을 생성해주는

    @Override
    public Member save(Member member) {
          member.setId(++sequence); //아이디값 세팅
          store.put(member.getId(),member);
          return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //찾는 아이디 값이 null 일시를 대비
    }

    @Override
    public Optional<Member> findByName(String name) {
        //루프생성 자바 람다
       return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store 에 잇는 멤버 반환
    }

    public void clearStore(){
        store.clear();//스토어를 싹 비운다
     }
}
