package pl.codewise.internships;


import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageQueueImpl implements MessageQueue {
    private final ConcurrentLinkedQueue<TimedMessage> messageQueue = new ConcurrentLinkedQueue<>();
    private final QueueCleaner cleaner = new QueueCleaner();

    public void add(Message message) {
        messageQueue.add(new TimedMessage(message));
        cleaner.defaultSchedule(messageQueue, MAX_SIZE);
    }

    public int getQueueSize() {
        return messageQueue.size();
    }

    public Snapshot snapshot() {
        return Snapshot.of(messageQueue, MAX_SIZE);
    }

    public long numberOfErrorMessages() {
        return snapshot().numberOfErrorMessages();
    }
}
