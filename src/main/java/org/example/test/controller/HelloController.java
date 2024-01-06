package org.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.event.HierarchyListener;

@Controller
public class HelloController {

    @GetMapping("hello") //hello 라는 요청이 들어오면
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello"; //resources폴더 아래 있는 templates폴더 아래 있는 .html 리턴
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http body부에 이 데이터를 직접 넣어줌 / 템플릿 엔진과 차이는 View가 없고 문자 그대로 넘어감
    public String helloString(@RequestParam("name")String name){
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody //JSON으로 반환하는게 기본
    public Hello helloApi(@RequestParam("name")String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 위에처럼 문자가 아닌 객체를 반환 / JSON 방식 key,value로 이루어진 구조
        //객체가 오면 JSON방식으로 데이터를 만들어서 응답에 반환
    }
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
