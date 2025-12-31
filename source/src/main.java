

public class main {
    public static void main(String[] args) {
        // Test database connection first
        if (!db_manager.testConnection()) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Database connection failed!\nPlease check your setup.",
                "Database Error", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Start the login screen
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new login().setVisible(true);
            }
        });
    }
}