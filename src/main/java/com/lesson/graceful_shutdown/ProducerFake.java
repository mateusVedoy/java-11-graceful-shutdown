package com.lesson.graceful_shutdown;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ProducerFake implements ApplicationRunner {

    private Boolean end = false;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public void start() {
        while(!end) {
            System.out.println("fake producer - sending messages");
            sleep();
        }
    }

    public void close() {
        end = true;
    }

    private void sleep() {
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        executorService.submit(this::start);
    }
}
