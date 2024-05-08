package Twitter;

public class Utils {
    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    public static boolean isValidAlias(String alias) {
        return alias != null && alias.matches("[a-zA-Z0-9]+");
    }
}


