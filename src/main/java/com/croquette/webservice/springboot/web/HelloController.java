package com.croquette.webservice.springboot.web;

import com.croquette.webservice.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 줌
// 예전 : @ResponseBody를 각 메소드 마다 선언 했던 것을 한번에 사용할 수 있게 해줌
@RestController
public class HelloController {
    // HTTP Method인 Get 요청을 받을 수 있는 API를 만들어 줌
    // 예전 : @RequestMapping(method = RequestMethod.GET)으로 사용됨
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    // @RequestParam : 외부에서 API로 넘긴 파라미터를 가져오는 annotation
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
