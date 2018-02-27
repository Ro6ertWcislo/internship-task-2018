package pl.codewise.internships;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueueCleanerTest {
    @Test
    /*
    * test fills the queue with
    * */
    public void queueTest(){
        MessageQueueImpl mq = new MessageQueueImpl();
        for(int i=0;i<MessageQueue.MAX_SIZE;i++){
            mq.add(new Message("a",0));
        }

        assertEquals(MessageQueue.MAX_SIZE,mq.getQueueSize());
        try {
            Thread.sleep((int)(1.5 * QueueCleaner.DELAY));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(100,mq.getQueueSize());
    }
}
