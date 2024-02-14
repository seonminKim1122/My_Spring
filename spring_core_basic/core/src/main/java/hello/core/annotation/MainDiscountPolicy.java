package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}

/*
@Qualifier 랑 똑같이 붙일 수 있게 할 용도
@Qualifier("이름") 은 문자열이라 컴파일 타임에 에러가 발생하지 않으니 이런 어노테이션을 만들어서
오타에 의한 에러 방지 가능
*/