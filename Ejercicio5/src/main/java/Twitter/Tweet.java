package Twitter;

import java.time.LocalDate;

public class Tweet {
    protected UserAccount sender;
    protected String message;
    protected LocalDate time;

    public Tweet(UserAccount sender, String message) {
        if (message.length() > 140) {
            throw new IllegalArgumentException("Message cannot exceed 140 characters");
        }
        this.sender = sender;
        this.message = message;
        this.time = LocalDate.now(); // Current date
    }

    // Getters
    public UserAccount getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public LocalDate getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Tweet from " + sender.getAlias() + " at " + time + ": " + message;
    }
}



