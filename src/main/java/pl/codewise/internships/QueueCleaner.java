package pl.codewise.internships;


import java.util.Timer;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
* Brief explanation of why is this class even here
* The easiest way to control the queue size would be deleting one element each time a new one is added if size is greater than 100.
* But in order to avoid the race condition, such operation should be atomic. That means, the thread should lock a queue,
* add, delete and release the lock. For many threads such solution would be inefficient.
* This solution is asynchronic, queue size is sometimes bigger than 100 but then the cleaner comes each 5 seconds and
* reduces the size back to 100. It is better becaouse it's non-blocking and avoids race conditions.
* It can be done, as the implementation of ConcurrentLinkedQueue is "wait-free", as described in documentation.
* */
public class QueueCleaner {
    public static int DELAY = 5000;

    private final Timer timer = new Timer();

    public void defaultSchedule(ConcurrentLinkedQueue queue, int maxSize) {
        QueueCleanerTask task = new QueueCleanerTask(queue, maxSize);
        timer.scheduleAtFixedRate(task, DELAY, DELAY);
    }
}
