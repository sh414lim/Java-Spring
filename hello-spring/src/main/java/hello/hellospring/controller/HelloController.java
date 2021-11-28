package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//정적컨텐츠
@Controller // 스프링은 선언해주어야한다
public class HelloController {

        @GetMapping("hello") //wep 애플리케이션에서 /hello 들어오면 이 메서드 호출
    public String hello(Model model){
        model.addAttribute("date","spring!!");
        return "hello";

            }

            //mvc 패턴
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){//파라미터를 받는다
        model.addAttribute("name",name);
        return "hello-template";

    }

    //api 그대로 이 소스를 내려준다
    @GetMapping("hello-string")
    @ResponseBody   //http body  직접 데이터를 넣어주겟다
    public  String helloString(@RequestParam("name") String name){
            return "hello" + name;
    }

    //JSON 방식 api
    //객체 반환
    @GetMapping("hello-api")
    @ResponseBody //http 응답에 객체를 스프링은 기본 default 는 기본 json 방식으로 http에 반환 한다.
    public Hello helloApi(@RequestParam("name")String name){ //RequestParam ?
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    //static class? -> class 안에서 class 사용
    static class Hello{

     private String name;


     //getter,setter -> 자바빈 규약 라이브러리에서 쓴다 메서드를 통해 private 변수에 접근
        //프로퍼티 접근 방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
