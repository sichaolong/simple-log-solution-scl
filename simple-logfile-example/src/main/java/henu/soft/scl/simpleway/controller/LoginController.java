package henu.soft.scl.simpleway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sichaolong
 * @date 2022/9/7 22:57
 */
@RestController
@Slf4j
public class LoginController {

    @RequestMapping("/login")
    public String login(){
        log.info("login...");

        return "login...";
    }
}
