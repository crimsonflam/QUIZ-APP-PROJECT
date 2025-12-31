

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton, guestButton;
    private userDAO userDao;
    
    public login() {
        userDao = new userDAO();
        initComponents();
        setupLayout();
        setupListeners();
    }
    
    private void initComponents() {
        setTitle("QUIZ MASTER - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        
        loginButton = new JButton("LOGIN");
        registerButton = new JButton("REGISTER");
        guestButton = new JButton("Continue as Guest");
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        
        // Title panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("QUIZ MASTER");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 5, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        formPanel.add(new JLabel("Username:"));
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        
        // Guest button panel
        JPanel guestPanel = new JPanel();
        guestPanel.add(guestButton);
        
        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel, BorderLayout.CENTER);
        add(guestPanel, BorderLayout.SOUTH);
    }
    
    private void setupListeners() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        });
        
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegister();
            }
        });
        
        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                continueAsGuest();
            }
        });
        
        // Enter key to login
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        });
    }
    
    private void attemptLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password", 
                                        "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int userId = userDao.login(username, password);
        
        if (userId > 0) {
            JOptionPane.showMessageDialog(this, "Login successful!", 
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
            openDashboard(userId);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", 
                                        "Login Failed", JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
        }
    }
    
    private void openRegister() {
        registre registerScreen = new registre(this);
        registerScreen.setVisible(true);
        this.setVisible(false);
    }
    
    private void openDashboard(int userId) {
        dashboard dashboardScreen = new dashboard(userId);
        dashboardScreen.setVisible(true);
        this.dispose();
    }
    
    private void continueAsGuest() {
        // Open Quiz_menu directly without saving scores
        Quiz_menu quizMenu = new Quiz_menu(0, null); // 0 indicates guest mode
        quizMenu.setVisible(true);
        this.dispose();
    }
    
    public static void main(String[] args) {
        // Test database connection
        if (!db_manager.testConnection()) {
            JOptionPane.showMessageDialog(null, 
                "Database connection failed!\nPlease check your setup.",
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new login().setVisible(true);
            }
        });
    }
}