// name : db_manager
// pakage/path : C:\Users\HP\Desktop\java\project_quiz\quiz_app\src\database
// description : responsable for db

import java.sql.*;
import java.io.File;

public class db_manager {
    private static final String DATABASE_NAME = "quizmaster.db";
    private static Connection connection = null;
    
    // Get database connection
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Get OS-specific application data folder
                String userHome = System.getProperty("user.home");
                String appDataDir = "";
                
                String os = System.getProperty("os.name").toLowerCase();
                if (os.contains("win")) {
                    // Windows
                    appDataDir = userHome + "\\AppData\\Local\\QuizMaster";
                } else if (os.contains("mac")) {
                    // Mac
                    appDataDir = userHome + "/Library/Application Support/QuizMaster";
                } else {
                    // Linux/Unix
                    appDataDir = userHome + "/.local/share/QuizMaster";
                }
                
                // Create directory if it doesn't exist
                File dir = new File(appDataDir);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                
                // Database file path
                String dbPath = appDataDir + File.separator + DATABASE_NAME;
                
                // Load SQLite driver
                Class.forName("org.sqlite.JDBC");
                
                // Connect to database
                connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
                
                // Enable foreign keys
                Statement stmt = connection.createStatement();
                stmt.execute("PRAGMA foreign_keys = ON");
                
                // Create tables if they don't exist
                createTables(stmt);
                stmt.close();
                
                System.out.println("Database connected: " + dbPath);
                
            } catch (ClassNotFoundException e) {
                System.out.println("ERROR: SQLite JDBC driver not found");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("ERROR: Database connection failed");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return connection;
    }
    
    // Create necessary tables
    private static void createTables(Statement stmt) throws SQLException {
        // Users table
        String usersTable = 
            "CREATE TABLE IF NOT EXISTS users (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username TEXT UNIQUE NOT NULL," +
            "password_hash TEXT NOT NULL," +
            "email TEXT," +
            "created_at DATETIME DEFAULT CURRENT_TIMESTAMP," +
            "last_login DATETIME" +
            ")";
        
        // Quiz results table
        String scoresTable = 
            "CREATE TABLE IF NOT EXISTS quiz_results (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "user_id INTEGER NOT NULL," +
            "subject TEXT NOT NULL," +
            "score INTEGER NOT NULL," +
            "total_questions INTEGER NOT NULL," +
            "percentage REAL NOT NULL," +
            "time_taken INTEGER," +
            "completed_at DATETIME DEFAULT CURRENT_TIMESTAMP," +
            "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE" +
            ")";

        // difficulty table 
        String difficultyTable =
            "CREATE TABLE IF NOT EXISTS user_difficulty (" +
            "user_id INTEGER NOT NULL," +
            "subject TEXT NOT NULL," +
            "level INTEGER DEFAULT 2," + // 1=easy, 2=medium, 3=hard
            "last_updated DATETIME DEFAULT CURRENT_TIMESTAMP," +
            "PRIMARY KEY (user_id, subject)," +
            "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE" +
            ")";
        
        stmt.execute(difficultyTable);
        stmt.execute(usersTable);
        stmt.execute(scoresTable);

        // Add salt column if it doesn't exist (for existing databases)
        try {
            stmt.execute("ALTER TABLE users ADD COLUMN salt TEXT NOT NULL DEFAULT ''");
        } catch (SQLException e) {
            // Column already exists, ignore
        }

        System.out.println("Database tables created/verified");
    }
    
    // Test database connection
    public static boolean testConnection() {
        try {
            Connection conn = getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("Database connection test: SUCCESS");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Database connection test: FAILED - " + e.getMessage());
        }
        return false;
    }
    
    // Close database connection
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                connection = null;
                System.out.println("Database connection closed");
            }
        } catch (SQLException e) {
            System.out.println("Error closing connection: " + e.getMessage());
        }
    }
}