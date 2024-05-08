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
            return; // Evita seguir a null, a sí mismo o seguir múltiples veces
        }
        this.following.add(other);
        other.followers.add(this); // Añade este usuario a los seguidores del otro
    }

    public void tweet(Tweet tweet) {
        if (tweet == null) {
            return; // Evita añadir tweets nulos
        }
        this.tweets.add(tweet);
        for (UserAccount follower : this.followers) {
            follower.receiveTweet(tweet); // Propaga el tweet a los seguidores
        }
    }

    public void receiveTweet(Tweet tweet) {
        if (tweet != null) {
            this.tweets.add(tweet); // Añade el tweet recibido al conjunto de tweets
        }
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

    public String getAlias() {
        return alias;
    }
    public Set<Tweet> getTweets() {
        return tweets;
    }
    public Set<UserAccount> getFollowers() {
        return followers;
    }
    public Set<UserAccount> getFollowing() {
        return following;
    }
    public void sendDirectMessage(DirectMessage dm) {
        if (dm.getReceiver() == this) { // Asegúrate de que el DM está destinado a este usuario
            receiveTweet(dm); // Puedes manejar los DMs como tweets especiales o adaptarlo a tus necesidades
        }
    }

}



