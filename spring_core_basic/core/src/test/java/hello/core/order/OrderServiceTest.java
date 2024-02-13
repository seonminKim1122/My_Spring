package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

//    @Test
//    void fieldInjectionTest() { // MemberRepository 를 필드 주입 방식을 통해 주입 하고 있기 때문에 DI 프레임워크에 독립적인 테스트를 실행할 수가 없다
//        OrderServiceImpl orderService = new OrderServiceImpl();
////        orderService.setDiscountPolicy(new RateDiscountPolicy());
////        orderService.setMemberRepository(new MemoryMemberRepository());
//        orderService.createOrder(1L, "itemA", 10000);
//    }
}
