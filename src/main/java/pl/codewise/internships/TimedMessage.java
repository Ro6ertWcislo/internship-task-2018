package pl.codewise.internships;

import java.sql.Timestamp;

public class TimedMessage {
    private final Message message;
    private final Timestamp timestamp;
    final static long MESSAGE_TTL = 1000 * 60 * 5; // TTL - time to live in miliseconds

    public TimedMessage(Message message) {
        this.message = message;
        this.timestamp = new Timestamp(System.currentTimeMillis());

    }

    public boolean isNotDeprecated() {
        return (System.currentTimeMillis() - timestamp.getTime()) < MESSAGE_TTL;
    }

    public String getUserAgent() {
        return message.getUserAgent();
    }

    public int getErrorCode() {
        return message.getErrorCode();
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
