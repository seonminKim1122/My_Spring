package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    // MemberRepository(추상화)에도 의존하고 있지만 MemoryMemberRepository(구현체)에도 의존하고 있다
    private final MemberRepository memberRepository;

    @Autowired // 컴포넌트 스캔 방식을 사용하면 config 클래스에 아무 것도 없으므로 자동 의존 관계 주입을 위해 Autowired 추가
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
        
    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
