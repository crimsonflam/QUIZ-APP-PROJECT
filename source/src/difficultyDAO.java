import java.sql.*;

public class difficultyDAO {
    private Connection connection;
    
    public difficultyDAO() {
        this.connection = db_manager.getConnection();
    }
    
    // Get user's level for a subject
    public int getUserLevel(int userId, String subject) {
        String sql = "SELECT level FROM user_difficulty WHERE user_id = ? AND subject = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, subject);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("level");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 2; // Default to medium if no record
    }
    
    // Update user's level
    public boolean updateUserLevel(int userId, String subject, int newLevel) {
        // Clamp level between 1 and 3
        newLevel = Math.max(1, Math.min(3, newLevel));
        
        String sql = "INSERT OR REPLACE INTO user_difficulty (user_id, subject, level) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, subject);
            pstmt.setInt(3, newLevel);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Calculate new level based on score
    public int calculateNewLevel(int currentLevel, double scorePercentage) {
        if (scorePercentage >= 80 && currentLevel < 3) {
            return currentLevel + 1; // Level up
        } else if (scorePercentage < 50 && currentLevel > 1) {
            return currentLevel - 1; // Level down
        }
        return currentLevel; // Stay same
    }
    
    // Get all subjects levels for a user
    public java.util.Map<String, Integer> getAllLevels(int userId) {
        java.util.Map<String, Integer> levels = new java.util.HashMap<>();
        String sql = "SELECT subject, level FROM user_difficulty WHERE user_id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                levels.put(rs.getString("subject"), rs.getInt("level"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return levels;
    }
}