package Twitter;

public class Retweet extends Tweet {
    private Tweet originalTweet;

    public Retweet(UserAccount sender, String message, Tweet originalTweet) {
        super(sender, message);
        this.originalTweet = originalTweet;
    }

    public Tweet getOriginalTweet() {
        return originalTweet;
    }

    @Override
    public String toString() {
        return "Retweet by " + sender.getAlias() + " of " + originalTweet.getSender().getAlias() + "'s tweet at " + time + ": " + message;
    }
}
