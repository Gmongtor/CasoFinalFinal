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
            return; // Prevent following null, oneself, or following twice
        }
        this.following.add(other);
        other.followers.add(this);
    }

    public void tweet(Tweet tweet) {
        this.tweets.add(tweet);
        for (UserAccount follower : this.followers) {
            follower.receiveTweet(tweet);
        }
    }

    public void receiveTweet(Tweet tweet) {
        this.tweets.add(tweet);
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "alias='" + alias + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}


