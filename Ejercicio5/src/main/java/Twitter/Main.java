package Twitter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static Map<String, UserAccount> accounts = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Twitter Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Labels and text fields
        JLabel userLabel = new JLabel("Alias:");
        userLabel.setBounds(10, 20, 80, 25);
        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(10, 50, 80, 25);
        JTextField emailText = new JTextField(20);
        emailText.setBounds(100, 50, 165, 25);

        // Create User button
        JButton createButton = new JButton("Create User");
        createButton.setBounds(280, 35, 165, 25);
        createButton.addActionListener(e -> {
            String alias = userText.getText();
            String email = emailText.getText();
            if (Utils.isValidAlias(alias) && Utils.isValidEmail(email)) {
                UserAccount user = new UserAccount(alias, email);
                accounts.put(alias, user);
                JOptionPane.showMessageDialog(null, "User created: " + user);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid alias or email", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Follow another user
        JLabel followLabel = new JLabel("Follow Alias:");
        followLabel.setBounds(10, 100, 80, 25);
        JTextField followText = new JTextField(20);
        followText.setBounds(100, 100, 165, 25);
        JButton followButton = new JButton("Follow User");
        followButton.setBounds(280, 100, 165, 25);
        followButton.addActionListener(e -> {
            UserAccount user = accounts.get(userText.getText());
            UserAccount toFollow = accounts.get(followText.getText());
            if (user != null && toFollow != null) {
                user.follow(toFollow);
                JOptionPane.showMessageDialog(null, "Now following: " + toFollow);
            } else {
                JOptionPane.showMessageDialog(null, "User not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Tweet
        JLabel tweetLabel = new JLabel("Tweet:");
        tweetLabel.setBounds(10, 150, 80, 25);
        JTextField tweetText = new JTextField(20);
        tweetText.setBounds(100, 150, 165, 25);
        JButton tweetButton = new JButton("Post Tweet");
        tweetButton.setBounds(280, 150, 165, 25);
        tweetButton.addActionListener(e -> {
            UserAccount user = accounts.get(userText.getText());
            if (user != null) {
                Tweet tweet = new Tweet(tweetText.getText());
                user.tweet(tweet);
                JOptionPane.showMessageDialog(null, "Tweet posted: " + tweet);
            } else {
                JOptionPane.showMessageDialog(null, "User not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add components to panel
        panel.add(userLabel);
        panel.add(userText);
        panel.add(emailLabel);
        panel.add(emailText);
        panel.add(createButton);
        panel.add(followLabel);
        panel.add(followText);
        panel.add(followButton);
        panel.add(tweetLabel);
        panel.add(tweetText);
        panel.add(tweetButton);
    }
}

