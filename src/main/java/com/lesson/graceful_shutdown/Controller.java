package com.lesson.graceful_shutdown;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {


    @GetMapping("/")
    public String hello() {
        System.out.println("Simulando processamento com delay de 15s");
        delay();
        System.out.println("fim do delay. Devolvendo dados para API");
        return "Hello, World!";
    }

    private void delay() {
        try {
            Thread.sleep(15*1000);
        } catch (InterruptedException e) {
            System.out.println("erro ao aplicar delay");
        }
    }
}
