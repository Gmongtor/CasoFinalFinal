package Twitter;
public class DirectMessage extends Tweet {
    private UserAccount receiver;

    public DirectMessage(UserAccount sender, UserAccount receiver, String message) {
        super(sender, message);
        this.receiver = receiver;
    }

    public UserAccount getReceiver() {
        return receiver;
    }

    @Override
    public String toString() {
        return "Direct message from " + sender.getAlias() + " to " + receiver.getAlias() + " at " + time + ": " + message;
    }
}

