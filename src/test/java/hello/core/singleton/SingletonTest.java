package hello.core.singleton;

import ch.qos.logback.core.net.SyslogOutputStream;
import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);


    }
}