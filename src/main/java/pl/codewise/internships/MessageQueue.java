package pl.codewise.internships;


public interface MessageQueue {
    int MAX_SIZE = 100;

    void add(Message message);

    Snapshot snapshot();

    long numberOfErrorMessages();
}
