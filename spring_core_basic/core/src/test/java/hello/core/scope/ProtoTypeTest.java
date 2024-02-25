package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class ProtoTypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);

        // 프로토타입은 요청 때마다 빈을 생성하므로 초기화도 2번!
        // 싱글톤은 스프링컨테이너가 관리하고 있던 이미 생성된 빈을 반환
        ProtoTypeBean protoTypeBean1 = ac.getBean(ProtoTypeBean.class);
        System.out.println("find PrototypeBean1");
        ProtoTypeBean protoTypeBean2 = ac.getBean(ProtoTypeBean.class); // getBean 요청이 들어올 때 빈 생성
        System.out.println("find PrototypeBean2");

        System.out.println("protoTypeBean1 = " + protoTypeBean1);
        System.out.println("protoTypeBean2 = " + protoTypeBean2);

        assertThat(protoTypeBean1).isNotSameAs(protoTypeBean2);
        ac.close();
    }

    @Scope("prototype")
    static class ProtoTypeBean {
        @PostConstruct
        public void init() {
            System.out.println("ProtoTypeBean.init");
        }

        // 프로토타입 빈은 생성까지만 스프링 컨테이너가 관리하므로 소멸 전 콜백은 되지 않는다
        @PreDestroy
        public void destroy() {
            System.out.println("ProtoTypeBean.destroy");
        }
    }
}
