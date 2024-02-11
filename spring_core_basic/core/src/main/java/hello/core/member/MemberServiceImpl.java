package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // MemberRepository(추상화)에도 의존하고 있지만 MemoryMemberRepository(구현체)에도 의존하고 있다
    private final MemberRepository memberRepository;

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
