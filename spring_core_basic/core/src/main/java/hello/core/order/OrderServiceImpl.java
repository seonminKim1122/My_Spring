package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // final 키워드가 붙은 멤버 변수들로 구성된 생성자 자동으로 만들어 줌 -> 스프링은 생성자가 하나면 @Autowired 생략 가능 -> 이거 하나로 의존관계 자동 주입까지 끝
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    // DiscountPolicy 타입 -> fixDiscountPolicy, rateDiscountPolicy 빈이 2개라서 문제가 생긴다
    /*
    해결책
    1. @Autowired : 해당 타입의 빈이 여러 개면 필드명, 파라미터명으로 매칭
    2. @Qualifier : 추가 구분자(추가적인 방법 제공이지 빈 이름을 바꾸는 건 아니다!!)
    3. @Primary : 우선순위를 지정하는 방법(제일 많이 사용하고 제일 편함)
    
    만약 @Qualifier 와 @Primary 가 둘 다 있으면 @Qualifier 가 우선순위
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,
                            DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    
    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
