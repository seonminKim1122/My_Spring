package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

//    @Autowired
    private final MemberRepository memberRepository;
//    @Autowired
    private final DiscountPolicy discountPolicy;

//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
    /*
    수정자 주입이나 필드 주입 방식은 빈 등록 단계, 의존관계 주입 단계로 나눌 수 있지만
    생성자 주입 방식은 빈을 생성하기 위해 생성자를 호출하게 되므로 생성과 의존관계 주입이 동시에 일어난다고 본다
    생성자 주입, 수정자 주입, 필드 주입 다 이용하고 있다면 다 호출된다.
    */

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

    @Autowired // 생성자가 하나만 있으면 Autowired 를 생략해도 스프링 빈은 자동으로 의존관계 주입이 된다
    // @Autowired 는 required = true 가 default 라 주입할 빈이 없으면 오류가 난다
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
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
