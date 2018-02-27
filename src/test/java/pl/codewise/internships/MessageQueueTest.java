package pl.codewise.internships;

import org.junit.Test;

import static org.junit.Assert.*;

public class MessageQueueTest {
    @Test
    public void test1(){
        MessageQueue mq = new MessageQueueImpl();
        for(int i=0;i<40;i++){
            mq.add(new Message("a",0));
        }

        for(int i=0;i<60;i++){
            mq.add(new Message("b",1));
        }
        assertEquals(mq.snapshot().getMessages().size(),100);
        assertEquals(60,mq.numberOfErrorMessages());
    }
}