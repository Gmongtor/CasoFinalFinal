package Twitter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<String, UserAccount> accounts = new HashMap<>();
    private static UserAccount currentUser;
    private static JTextArea timelineArea = new JTextArea();  // Initialization to avoid null reference
    private static DefaultListModel<String> userListModel = new DefaultListModel<>();
    private static JList<String> userList = new JList<>(userListModel);
    private static JFrame frame;
    private static final String USERS_FILE = "users.txt";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
        accounts = FileManager.loadUsersFromFile(USERS_FILE);
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Mini Twitter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        JPanel loginPanel = createLoginPanel(cardLayout, cardPanel);
        JPanel mainPanel = createMainPanel(cardLayout, cardPanel);

        cardPanel.add(loginPanel, "Login");
        cardPanel.add(mainPanel, "Main");
        frame.getContentPane().add(cardPanel);

        frame.setVisible(true);
    }

    private static JPanel createLoginPanel(CardLayout cardLayout, JPanel cardPanel) {
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
        loginPanel.setBackground(Color.WHITE); // Light background for the login panel

        loginButton.addActionListener(e -> {
            String alias = aliasInput.getText();
            String email = emailInput.getText();
            if (Utils.isValidAlias(alias) && Utils.isValidEmail(email)) {
                currentUser = accounts.computeIfAbsent(alias, k -> new UserAccount(alias, email));
                updateUsersList();
                cardLayout.show(cardPanel, "Main");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid alias or email", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        return loginPanel;
    }

    private static JPanel createMainPanel(CardLayout cardLayout, JPanel cardPanel) {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(245, 248, 250)); // Background color similar to Twitter

        JPanel northPanel = createNorthPanel(cardLayout, cardPanel);
        JScrollPane scrollPane = new JScrollPane(timelineArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Timeline"));
        userList.setBorder(BorderFactory.createTitledBorder("Users"));
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(userList, BorderLayout.EAST);

        return mainPanel;
    }

    private static JPanel createNorthPanel(CardLayout cardLayout, JPanel cardPanel) {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.setBackground(Color.WHITE);

        JTextField followTextField = new JTextField(10);
        JButton followButton = new JButton("Follow");
        JTextField tweetTextField = new JTextField(10);
        JButton tweetButton = new JButton("Tweet");
        JButton retweetButton = new JButton("Retweet Last");
        JButton dmButton = new JButton("Send DM");
        JTextField dmTextField = new JTextField(10);
        JButton logoutButton = new JButton("Logout");
        JButton detailsButton = new JButton("My Details");
        JButton sortAscButton = new JButton("Sort Asc");
        JButton sortDescButton = new JButton("Sort Desc");

        northPanel.add(new JLabel("Follow User:"));
        northPanel.add(followTextField);
        northPanel.add(followButton);
        northPanel.add(new JLabel("Your Tweet:"));
        northPanel.add(tweetTextField);
        northPanel.add(tweetButton);
        northPanel.add(retweetButton);
        northPanel.add(new JLabel("DM User:"));
        northPanel.add(dmTextField);
        northPanel.add(dmButton);
        northPanel.add(logoutButton);
        northPanel.add(detailsButton);
        northPanel.add(sortAscButton);
        northPanel.add(sortDescButton);

        setupListeners(followButton, followTextField, tweetButton, tweetTextField, retweetButton, dmButton, dmTextField, logoutButton, detailsButton, sortAscButton, sortDescButton, cardLayout, cardPanel);

        return northPanel;
    }

    private static void setupListeners(JButton followButton, JTextField followTextField, JButton tweetButton, JTextField tweetTextField, JButton retweetButton, JButton dmButton, JTextField dmTextField, JButton logoutButton, JButton detailsButton, JButton sortAscButton, JButton sortDescButton, CardLayout cardLayout, JPanel cardPanel) {
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
                try {
                    currentUser.tweet(new Tweet(currentUser, content));
                    timelineArea.append("Tweet: " + content + "\n");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        retweetButton.addActionListener(e -> {
            if (currentUser != null && !currentUser.getTweets().isEmpty()) {
                Tweet lastTweet = currentUser.getTweets().stream().reduce((first, second) -> second).orElse(null);
                Retweet retweet = new Retweet(currentUser, lastTweet);
                currentUser.tweet(retweet);
                timelineArea.append(retweet.toString() + "\n");
            }
        });

        dmButton.addActionListener(e -> {
            String receiverAlias = dmTextField.getText();
            String message = tweetTextField.getText();
            UserAccount receiver = accounts.get(receiverAlias);
            if (currentUser != null && receiver != null && !message.isEmpty()) {
                currentUser.sendDirectMessage(receiver, message);
                timelineArea.append("DM to " + receiverAlias + ": " + message + "\n");
            } else {
                JOptionPane.showMessageDialog(frame, "DM failed: Check user and message", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        logoutButton.addActionListener(e -> {
            currentUser = null;
            updateUsersList();
            cardLayout.show(cardPanel, "Login");
        });

        detailsButton.addActionListener(e -> {
            if (currentUser != null) {
                String userDetails = "Followers: " + currentUser.getFollowers().size() +
                        "\nFollowing: " + currentUser.getFollowing().size() +
                        "\nTweets: " + currentUser.getTweets().size();
                JOptionPane.showMessageDialog(frame, userDetails, "My Details", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        sortAscButton.addActionListener(e -> {
            sortUsersByEmail(true);
            updateUsersList();
        });

        sortDescButton.addActionListener(e -> {
            sortUsersByEmail(false);
            updateUsersList();
        });
    }

    private static void updateUsersList() {
        userListModel.clear();
        accounts.keySet().forEach(userListModel::addElement);
    }

    public static void sortUsersByEmail(boolean ascending) {
        ArrayList<UserAccount> users = new ArrayList<>(accounts.values());
        if (ascending) {
            Collections.sort(users, (u1, u2) -> u1.getEmail().compareToIgnoreCase(u2.getEmail()));
        } else {
            Collections.sort(users, (u1, u2) -> u2.getEmail().compareToIgnoreCase(u1.getEmail()));
        }
        accounts.clear();
        users.forEach(user -> accounts.put(user.getAlias(), user));
    }

    public static void addUser(String alias, String email) {
        accounts.put(alias, new UserAccount(alias, email));
    }

    public static void loadUsersFromFile(String filename) {
        accounts = FileManager.loadUsersFromFile(filename);
    }
}










