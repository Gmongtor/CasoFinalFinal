package Twitter;

public class Retweet extends Tweet {
    private Tweet originalTweet;

    public Retweet(UserAccount sender, Tweet originalTweet) {
        super(sender, "RT: " + originalTweet.getMessage());
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
