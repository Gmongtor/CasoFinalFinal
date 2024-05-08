package Twitter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static Map<String, UserAccount> accounts = new HashMap<>();
    private static UserAccount currentUser;
    private static JTextArea timelineArea;
    private static DefaultListModel<String> userListModel = new DefaultListModel<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Mini Twitter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        createLoginPanel(frame);

        frame.setVisible(true);
    }

    private static void createLoginPanel(JFrame frame) {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        JTextField aliasInput = new JTextField(20);
        JTextField emailInput = new JTextField(20);
        JButton loginButton = new JButton("Login / Register");

        loginPanel.add(new JLabel("Alias:"));
        loginPanel.add(aliasInput);
        loginPanel.add(new JLabel("Email:"));
        loginPanel.add(emailInput);
        loginPanel.add(loginButton);
        loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        frame.add(loginPanel);

        loginButton.addActionListener(e -> {
            String alias = aliasInput.getText();
            String email = emailInput.getText();
            if (Utils.isValidAlias(alias) && Utils.isValidEmail(email)) {
                currentUser = accounts.computeIfAbsent(alias, k -> new UserAccount(alias, email));
                frame.getContentPane().removeAll();
                setupUserInterface(frame);
                frame.validate();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid alias or email", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private static void setupUserInterface(JFrame frame) {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        JTextField followTextField = new JTextField(10);
        JButton followButton = new JButton("Follow");
        JTextField tweetTextField = new JTextField(10);
        JButton tweetButton = new JButton("Tweet");

        northPanel.add(new JLabel("Follow User:"));
        northPanel.add(followTextField);
        northPanel.add(followButton);
        northPanel.add(new JLabel("Your Tweet:"));
        northPanel.add(tweetTextField);
        northPanel.add(tweetButton);

        timelineArea = new JTextArea();
        timelineArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(timelineArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Timeline"));

        JList<String> userList = new JList<>(userListModel);
        userList.setBorder(BorderFactory.createTitledBorder("Users"));

        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(userList, BorderLayout.EAST);

        followButton.addActionListener(e -> {
            String aliasToFollow = followTextField.getText();
            UserAccount toFollow = accounts.get(aliasToFollow);
            if (toFollow != null && currentUser != null) {
                currentUser.follow(toFollow);
                timelineArea.append("Following " + aliasToFollow + "\n");
            } else {
                JOptionPane.showMessageDialog(frame, "User not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        tweetButton.addActionListener(e -> {
            String content = tweetTextField.getText();
            if (currentUser != null && !content.isEmpty()) {
                Tweet tweet = new Tweet(content);
                currentUser.tweet(tweet);
                timelineArea.append(currentUser.getAlias() + ": " + content + "\n");
            }
        });

        frame.getContentPane().add(mainPanel);
        updateUsersList();
    }

    private static void updateUsersList() {
        userListModel.clear();
        accounts.keySet().forEach(userListModel::addElement);
    }
}




