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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Twitter Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Prompt for initial user creation
        createInitialUser(frame);

        frame.setVisible(true);
    }

    private static void createInitialUser(JFrame frame) {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JTextField aliasInput = new JTextField(20);
        JTextField emailInput = new JTextField(20);
        JButton createButton = new JButton("Create User");

        panel.add(new JLabel("Enter Alias:"));
        panel.add(aliasInput);
        panel.add(new JLabel("Enter Email:"));
        panel.add(emailInput);
        panel.add(createButton);

        frame.add(panel);

        createButton.addActionListener(e -> {
            String alias = aliasInput.getText();
            String email = emailInput.getText();
            if (Utils.isValidAlias(alias) && Utils.isValidEmail(email)) {
                currentUser = new UserAccount(alias, email);
                accounts.put(alias, currentUser);
                frame.getContentPane().removeAll();
                setupUserInterface(frame);
                frame.validate();
                JOptionPane.showMessageDialog(frame, "User created successfully: " + currentUser, "User Created", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid alias or email", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private static void setupUserInterface(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new GridLayout(0, 1));
        JTextField followTextField = new JTextField();
        JButton followButton = new JButton("Follow User");
        JButton tweetButton = new JButton("Tweet");
        JTextField tweetTextField = new JTextField();

        northPanel.add(new JLabel("Alias to follow:"));
        northPanel.add(followTextField);
        northPanel.add(followButton);
        northPanel.add(new JLabel("Your Tweet:"));
        northPanel.add(tweetTextField);
        northPanel.add(tweetButton);

        timelineArea = new JTextArea(10, 30);
        timelineArea.setEditable(false);

        followButton.addActionListener(e -> {
            String aliasToFollow = followTextField.getText();
            UserAccount toFollow = accounts.get(aliasToFollow);
            if (toFollow != null && currentUser != null) {
                currentUser.follow(toFollow);
                JOptionPane.showMessageDialog(frame, "Following " + aliasToFollow, "Follow Successful", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "User not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        tweetButton.addActionListener(e -> {
            String content = tweetTextField.getText();
            if (currentUser != null) {
                Tweet tweet = new Tweet(content);
                currentUser.tweet(tweet);
                timelineArea.append("You tweeted: " + content + "\n");
            }
        });

        panel.add(northPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(timelineArea), BorderLayout.CENTER);
        frame.add(panel);
    }
}


