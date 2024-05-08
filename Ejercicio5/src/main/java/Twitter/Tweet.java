package Twitter;

public class Tweet {
    private String content;

    public Tweet(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Tweet{" + "content='" + content + '\'' + '}';
    }
}

