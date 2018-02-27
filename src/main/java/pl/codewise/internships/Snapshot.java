package pl.codewise.internships;

import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class Snapshot {
    private final List<TimedMessage> messages;

    private Snapshot(List<TimedMessage> messages) {
        this.messages = messages;
    }

    public static Snapshot of(ConcurrentLinkedQueue<TimedMessage> messages, int MAX_SIZE) {

        TimedMessage[] messagesAsArr = messages.toArray(new TimedMessage[0]);
        ArrayUtils.reverse(messagesAsArr);
        List<TimedMessage> filteredMessages = Arrays.stream(messagesAsArr)
                .filter(TimedMessage::isNotDeprecated)
                .limit(MAX_SIZE)
                .collect(Collectors.toList());
        return new Snapshot(filteredMessages);
    }

    public long numberOfErrorMessages() {
        return messages.stream()
                .filter(msg -> msg.getErrorCode() != 0)
                .count();
    }

    public List<TimedMessage> getMessages() {
        return messages;
    }
}
