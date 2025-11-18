package com.example.meongnyang_wiki.controller;



import ch.qos.logback.core.model.Model;
import com.example.meongnyang_wiki.repository.MainRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    private final MainRepository mainRepository;

    public MainController(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }
    @ResponseBody
    @GetMapping("test")
    public int test() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("value", "test"); // 필요하면 파라미터 추가, 안 쓰면 없어도 됨

        return mainRepository.test(map);
    }
    @GetMapping("")
    public String main(Model model, @RequestParam HashMap<String, Object> map) throws Exception {

        return "";
    }

}
