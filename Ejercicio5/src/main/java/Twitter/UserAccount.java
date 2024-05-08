package Twitter;

import java.util.HashSet;
import java.util.Set;

public class UserAccount {
    private String alias;
    private String email;
    private Set<Tweet> tweets;
    private Set<UserAccount> followers;
    private Set<UserAccount> following;

    public UserAccount(String alias, String email) {
        if (!Utils.isValidAlias(alias) || !Utils.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid alias or email");
        }
        this.alias = alias;
        this.email = email;
        this.tweets = new HashSet<>();
        this.followers = new HashSet<>();
        this.following = new HashSet<>();
    }

    public void follow(UserAccount other) {
        if (other == null || this == other || this.following.contains(other)) {
            return;
        }
        this.following.add(other);
        other.followers.add(this);
    }

    public void tweet(Tweet tweet) {
        if (tweet == null) {
            return;
        }
        this.tweets.add(tweet);
        this.followers.forEach(follower -> follower.receiveTweet(tweet));
    }

    public void receiveTweet(Tweet tweet) {
        if (tweet != null) {
            this.tweets.add(tweet);
        }
    }

    public void sendDirectMessage(UserAccount receiver, String message) {
        if (receiver != null && message != null && !message.isEmpty()) {
            DirectMessage dm = new DirectMessage(this, receiver, message);
            receiver.receiveDirectMessage(dm);
            // Store this DM or log it as needed
        }
    }

    public void receiveDirectMessage(DirectMessage dm) {
        // You could store received messages or update a user interface component here
        System.out.println("DM from " + dm.getSender().getAlias() + ": " + dm.getMessage());
    }

    public void publishTweet(String message) throws IllegalArgumentException {
        Tweet newTweet = new Tweet(this, message);
        this.tweet(newTweet);
    }

    // Utility methods to simplify the management of relationships
    public void unfollow(UserAccount other) {
        if (other == null || !this.following.contains(other)) {
            return;
        }
        this.following.remove(other);
        other.followers.remove(this);
    }
    public void addUser(String alias, String email) {
        Main.addUser(alias, email);
    }

    public static void loadUsersFromFile(String filename) {
        Main.loadUsersFromFile(filename);
    }

    public static void sortUsersByEmail() {
        Main.sortUsersByEmail();
    }

    // Getters and setters
    public String getAlias() {
        return alias;
    }

    public String getEmail() {
        return email;
    }

    public Set<Tweet> getTweets() {
        return new HashSet<>(tweets); // Return a copy to protect the internal structure
    }

    public Set<UserAccount> getFollowers() {
        return new HashSet<>(followers); // Return a copy to protect the internal structure
    }

    public Set<UserAccount> getFollowing() {
        return new HashSet<>(following); // Return a copy to protect the internal structure
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "alias='" + alias + '\'' +
                ", email='" + email + '\'' +
                ", followers=" + followers.size() +
                ", following=" + following.size() +
                ", tweets=" + tweets.size() +
                '}';
    }
}




