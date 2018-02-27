package pl.codewise.internships;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

public class MessageQueueTest {
    @Test
    public void queueTest(){
        MessageQueue mq = new MessageQueueImpl();
        for(int i=0;i<40;i++){
            mq.add(new Message("a",0));
        }

        for(int i=0;i<60;i++){
            mq.add(new Message("b",1));
        }
        assertEquals(MessageQueue.MAX_SIZE,mq.snapshot().getMessages().size());
        assertEquals(60,mq.numberOfErrorMessages());
    }
    @Test
    public void concurrentTest(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        MessageQueue mq = new MessageQueueImpl();
        for(int i=0;i<40;i++){
            executorService.execute(()->mq.add(new Message("a",0)));
        }

        for(int i=0;i<60;i++){
            executorService.execute(()->mq.add(new Message("b",1)));
        }
        // i know it's ugly, but there was no time left :(
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(MessageQueue.MAX_SIZE,mq.snapshot().getMessages().size());
        assertEquals(60,mq.numberOfErrorMessages());
    }
}