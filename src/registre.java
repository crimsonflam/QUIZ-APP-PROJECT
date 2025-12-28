

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class registre extends JFrame {
    private JTextField usernameField, emailField;
    private JPasswordField passwordField, confirmField;
    private JButton createButton, backButton;
    private JLabel usernameStatus, passwordStatus;
    private userDAO userDao;
    private login parent;
    
    public registre(login parent) {
        this.parent = parent;
        userDao = new userDAO();
        initComponents();
        setupLayout();
        setupListeners();
    }
    
    private void initComponents() {
        setTitle("CREATE ACCOUNT");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);
        
        usernameField = new JTextField(15);
        emailField = new JTextField(15);
        passwordField = new JPasswordField(15);
        confirmField = new JPasswordField(15);
        
        createButton = new JButton("CREATE ACCOUNT");
        backButton = new JButton("BACK");
        
        usernameStatus = new JLabel(" ");
        passwordStatus = new JLabel(" ");
        
        usernameStatus.setForeground(Color.GRAY);
        passwordStatus.setForeground(Color.GRAY);
    }
    
   private void setupLayout() {
    // Use BoxLayout for vertical arrangement
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
    
    // Title
    JLabel titleLabel = new JLabel("CREATE ACCOUNT", SwingConstants.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
    titleLabel.setForeground(new Color(25, 25, 112));
    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    // Add space
    mainPanel.add(titleLabel);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
    
    // Form panel - SIMPLE 4 rows
    JPanel formPanel = new JPanel(new GridLayout(4, 2, 15, 15));
    
    // Row 1: Username
    formPanel.add(new JLabel("Username:"));
    formPanel.add(usernameField);
    
    // Row 2: Email
    formPanel.add(new JLabel("Email:"));
    formPanel.add(emailField);
    
    // Row 3: Password
    formPanel.add(new JLabel("Password:"));
    formPanel.add(passwordField);
    
    // Row 4: Confirm
    formPanel.add(new JLabel("Confirm:"));
    formPanel.add(confirmField);
    
    // Status labels panel
    JPanel statusPanel = new JPanel(new GridLayout(2, 1, 5, 5));
    statusPanel.add(usernameStatus);
    statusPanel.add(passwordStatus);
    
    // Buttons
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
    
    // Style buttons
    createButton.setPreferredSize(new Dimension(180, 40));
    createButton.setBackground(new Color(50, 150, 50));
    createButton.setForeground(Color.WHITE);
    createButton.setFont(new Font("Arial", Font.BOLD, 14));
    
    backButton.setPreferredSize(new Dimension(120, 40));
    backButton.setBackground(new Color(200, 50, 50));
    backButton.setForeground(Color.WHITE);
    backButton.setFont(new Font("Arial", Font.PLAIN, 14));
    
    buttonPanel.add(createButton);
    buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));
    buttonPanel.add(backButton);
    
    // Assemble
    mainPanel.add(formPanel);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    mainPanel.add(statusPanel);
    mainPanel.add(buttonPanel);
    
    add(mainPanel);
}
    private void setupListeners() {
        // Real-time username check
        usernameField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { checkUsername(); }
            public void removeUpdate(DocumentEvent e) { checkUsername(); }
            public void insertUpdate(DocumentEvent e) { checkUsername(); }
        });
        
        // Real-time password match check
        DocumentListener passwordListener = new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { checkPasswords(); }
            public void removeUpdate(DocumentEvent e) { checkPasswords(); }
            public void insertUpdate(DocumentEvent e) { checkPasswords(); }
        };
        passwordField.getDocument().addDocumentListener(passwordListener);
        confirmField.getDocument().addDocumentListener(passwordListener);
        
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptRegistration();
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack();
            }
        });
        
        // Enter key to register
        confirmField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptRegistration();
            }
        });
    }
    
    private void checkUsername() {
        String username = usernameField.getText().trim();
        
        if (username.length() < 3) {
            usernameStatus.setText("Minimum 3 characters");
            usernameStatus.setForeground(Color.RED);
            return;
        }
        
        if (username.length() > 20) {
            usernameStatus.setText("Maximum 20 characters");
            usernameStatus.setForeground(Color.RED);
            return;
        }
        
        if (userDao.checkUsernameExists(username)) {
            usernameStatus.setText("Username taken");
            usernameStatus.setForeground(Color.RED);
        } else {
            usernameStatus.setText("Username available ");
            usernameStatus.setForeground(new Color(0, 150, 0));
        }
    }
    
    private void checkPasswords() {
        String password = new String(passwordField.getPassword());
        String confirm = new String(confirmField.getPassword());
        
        if (password.length() < 6) {
            passwordStatus.setText("Minimum 6 characters");
            passwordStatus.setForeground(Color.RED);
            return;
        }
        
        if (!password.equals(confirm)) {
            passwordStatus.setText("Passwords don't match");
            passwordStatus.setForeground(Color.RED);
        } else {
            // Check password strength
            boolean hasUpper = !password.equals(password.toLowerCase());
            boolean hasLower = !password.equals(password.toUpperCase());
            boolean hasDigit = password.matches(".*\\d.*");
            
            if (password.length() >= 8 && hasUpper && hasLower && hasDigit) {
                passwordStatus.setText("Strong password ");
                passwordStatus.setForeground(new Color(0, 150, 0));
            } else if (password.length() >= 6) {
                passwordStatus.setText("Passwords match ");
                passwordStatus.setForeground(new Color(0, 150, 0));
            }
        }
    }
    
    private void attemptRegistration() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirm = new String(confirmField.getPassword());
        
        // Validation
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required", 
                                        "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match", 
                                        "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this, "Password must be at least 6 characters", 
                                        "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address", 
                                        "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (userDao.checkUsernameExists(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists", 
                                        "Registration Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        boolean success = userDao.register(username, password, email);
        
        if (success) {
            JOptionPane.showMessageDialog(this, "Account created successfully!\nLogging you in...", 
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Auto-login and open dashboard
            int userId = userDao.login(username, password);
            if (userId > 0) {
                dashboard dashboardScreen = new dashboard(userId);
                dashboardScreen.setVisible(true);
                this.dispose();
                if (parent != null) {
                    parent.dispose();
                }
            } else {
                goBack();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed. Please try again.", 
                                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void goBack() {
        if (parent != null) {
            parent.setVisible(true);
        }
        this.dispose();
    }
}