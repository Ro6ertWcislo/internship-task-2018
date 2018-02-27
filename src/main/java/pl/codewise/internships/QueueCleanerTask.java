package pl.codewise.internships;

import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueCleanerTask extends TimerTask{
    private final ConcurrentLinkedQueue queue;
    private final int maxSize;

    public QueueCleanerTask(ConcurrentLinkedQueue queue, int maxSize) {
        this.queue = queue;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        int itemsToDelete = queue.size() - maxSize;
        for(int i=0; i< itemsToDelete;i++)
            queue.poll();
    }
}
