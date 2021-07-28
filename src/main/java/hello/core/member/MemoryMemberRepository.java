package hello.core.member;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class MemoryMemberRepository implements  MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    //실무에서는 동시성 문제로 concurrentHashMap사용

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
