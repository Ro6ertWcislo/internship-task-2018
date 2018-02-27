package pl.codewise.internships;

public class Message {
    // github test

    private final String userAgent;
    private final int errorCode;

    public Message(String userAgent, int errorCode) {
        this.userAgent = userAgent;
        this.errorCode = errorCode;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
