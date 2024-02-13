package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class); // TestBean 을 빈으로 등록하면서 Autowired 는 다 동작

    }

    static class TestBean {

        @Autowired(required = false) // 자동 주입할 빈이 없으면 아예 수정자가 호출이 안 된다.
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) { // 자동 주입할 빈이 없으면 null 값이 할당
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) { // 자동 주입할 빈이 없으면 Optional.empty
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
