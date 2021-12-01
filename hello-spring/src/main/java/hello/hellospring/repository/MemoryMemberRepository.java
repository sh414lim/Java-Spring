package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//구현체 생성

//@Repository
public class MemoryMemberRepository implements MemberRepository { //MemberRepository 을 MemoryMemberRespositoty  를 implemets

    private static Map<Long,Member> store = new HashMap<>(); //공유 될수 있는 저장소는 컨커런스 를 써야된다? 세이브를 할때 저장 <키,값>
    private static Long sequence =0L; //키 값을 생성해주는 실무에서는 동시성 문제로 어텀 롱 이런거 씀

    @Override
    public Member save(Member member) {
          member.setId(++sequence); //아이디값 세팅 시퀀스 값을 올려주고
          store.put(member.getId(),member);//store 에 저장 아이디랑 멤버는 이미 넘어옴 맵에 저장
          return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //결과가 없으면 null Optional.ofNullable 감싸면 null 이여도 반환
        return Optional.ofNullable(store.get(id)); //찾는 아이디 값이 null 일시를 대비
    }

    @Override
    public Optional<Member> findByName(String name) {
        //루프생성 자바 람다
       return store.values().stream()  //store 에서 루프를 돌림
                .filter(member -> member.getName().equals(name)) //member.getname 파라미터로 name 이랑 같은지 비교
                .findAny(); //찾으면 반환 하나라도 찾는거(findAny); 끝까지 없으먄 옵셔널 null 반환
    }

    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values()); //store 에 잇는 멤버 반환
    }

    public void clearStore(){

        store.clear();//스토어를 싹 비운다
     }
}
