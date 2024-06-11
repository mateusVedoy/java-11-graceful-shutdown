package com.lesson.graceful_shutdown;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class ShutDownEventHandler implements ApplicationListener<ContextClosedEvent> {

    @Autowired
    private Interceptor interceptor;
    @Autowired
    private ConsumerFake fakeConsumer;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("finishing all interceptor and fake consumer");
        interceptor.changeState();
        fakeConsumer.close();
        System.out.println("have been finished");
        try {
            Thread.sleep(30*1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("30s period ended. Application shutting down");
    }
}
