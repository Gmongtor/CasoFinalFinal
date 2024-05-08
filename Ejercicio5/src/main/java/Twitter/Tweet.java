package Twitter;

import java.time.LocalDateTime;

public class Tweet {
    protected UserAccount sender;
    protected String message;
    protected LocalDateTime time;

    public Tweet(UserAccount sender, String message) {
        if (message.length() > 140) {
            throw new IllegalArgumentException("Message cannot exceed 140 characters");
        }
        this.sender = sender;
        this.message = message;
        this.time = LocalDateTime.now(); // Use LocalDateTime for precise time
    }

    // Getters
    public UserAccount getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        // Format the time in a more readable format
        return "Tweet from " + sender.getAlias() + " at " + time.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + ": " + message;
    }
}




