package hello.core.singleton;

import ch.qos.logback.core.net.SyslogOutputStream;
import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        //1. 조회: 앱 호출시 객체 생성
        MemberService memberService1 = appConfig.memberService();
        //2. 조회: 앱 호출시 객체 생성
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1: " + memberService1);
        System.out.println("memberService2: " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);

    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1: " + singletonService1);
        System.out.println("singletonService2: " + singletonService2);

        assertThat(singletonService2).isSameAs(singletonService1);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1: " + memberService1);
        System.out.println("memberService2: " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);

    }
}
