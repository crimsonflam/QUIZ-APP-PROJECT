

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class scoreDAO {
    private Connection connection;
    
    public scoreDAO() {
        this.connection = db_manager.getConnection();
    }
    
    // 1. SAVE quiz result
    public int saveScore(int userId, String subject, int score, int total, int timeTaken) {
        double percentage = (double) score / total * 100;
        
        String sql = "INSERT INTO quiz_results (user_id, subject, score, total_questions, percentage, time_taken, completed_at) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, subject);
            pstmt.setInt(3, score);
            pstmt.setInt(4, total);
            pstmt.setDouble(5, percentage);
            pstmt.setInt(6, timeTaken);
            pstmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error saving score: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }
    
    // 2. GET user's quiz history
    public List<QuizResult> getUserHistory(int userId) {
        List<QuizResult> history = new ArrayList<>();
        String sql = "SELECT subject, score, total_questions, percentage, time_taken, completed_at " +
                    "FROM quiz_results WHERE user_id = ? ORDER BY completed_at DESC";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                QuizResult result = new QuizResult();
                result.setSubject(rs.getString("subject"));
                result.setScore(rs.getInt("score"));
                result.setTotal(rs.getInt("total_questions"));
                result.setPercentage(rs.getDouble("percentage"));
                result.setTimeTaken(rs.getInt("time_taken"));
                result.setCompletedAt(rs.getTimestamp("completed_at"));
                history.add(result);
            }
        } catch (SQLException e) {
            System.out.println("Error getting user history: " + e.getMessage());
            e.printStackTrace();
        }
        return history;
    }
    
    // 3. GET user statistics
    public UserStatistics getUserStatistics(int userId) {
        UserStatistics stats = new UserStatistics();
        
        String sql = "SELECT " +
                    "COUNT(*) as total_quizzes, " +
                    "AVG(percentage) as avg_score, " +
                    "MAX(percentage) as best_score " +
                    "FROM quiz_results " +
                    "WHERE user_id = ?";
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                stats.setTotalQuizzes(rs.getInt("total_quizzes"));
                stats.setAverageScore(rs.getDouble("avg_score"));
                stats.setBestScore(rs.getDouble("best_score"));
                
                // Get best subject
                String bestSubjectSql = "SELECT subject FROM quiz_results WHERE user_id = ? ORDER BY percentage DESC LIMIT 1";
                try (PreparedStatement pstmt2 = connection.prepareStatement(bestSubjectSql)) {
                    pstmt2.setInt(1, userId);
                    ResultSet rs2 = pstmt2.executeQuery();
                    if (rs2.next()) {
                        stats.setBestSubject(rs2.getString("subject"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting user statistics: " + e.getMessage());
            e.printStackTrace();
        }
        return stats;
    }
    
    // 4. GET leaderboard (optional)
    public List<LeaderboardEntry> getLeaderboard(String subject) {
        List<LeaderboardEntry> leaderboard = new ArrayList<>();
        
        String sql;
        if ("all".equalsIgnoreCase(subject)) {
            sql = "SELECT u.username, AVG(qr.percentage) as avg_score, COUNT(*) as quiz_count " +
                 "FROM quiz_results qr " +
                 "JOIN users u ON qr.user_id = u.id " +
                 "GROUP BY u.id, u.username " +
                 "HAVING COUNT(*) >= 3 " +  // At least 3 quizzes
                 "ORDER BY avg_score DESC LIMIT 10";
        } else {
            sql = "SELECT u.username, qr.percentage as score, qr.completed_at " +
                 "FROM quiz_results qr " +
                 "JOIN users u ON qr.user_id = u.id " +
                 "WHERE qr.subject = ? " +
                 "ORDER BY qr.percentage DESC, qr.time_taken ASC LIMIT 10";
        }
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            if (!"all".equalsIgnoreCase(subject)) {
                pstmt.setString(1, subject);
            }
            
            ResultSet rs = pstmt.executeQuery();
            int rank = 1;
            while (rs.next()) {
                LeaderboardEntry entry = new LeaderboardEntry();
                entry.setRank(rank++);
                entry.setUsername(rs.getString("username"));
                if ("all".equalsIgnoreCase(subject)) {
                    entry.setScore(rs.getDouble("avg_score"));
                    entry.setQuizCount(rs.getInt("quiz_count"));
                } else {
                    entry.setScore(rs.getDouble("score"));
                    entry.setDate(rs.getTimestamp("completed_at"));
                }
                leaderboard.add(entry);
            }
        } catch (SQLException e) {
            System.out.println("Error getting leaderboard: " + e.getMessage());
            e.printStackTrace();
        }
        return leaderboard;
    }
    
    // Data classes
    public static class QuizResult {
        private String subject;
        private int score;
        private int total;
        private double percentage;
        private int timeTaken;
        private Timestamp completedAt;
        
        // Getters and setters
        public String getSubject() { return subject; }
        public void setSubject(String subject) { this.subject = subject; }
        
        public int getScore() { return score; }
        public void setScore(int score) { this.score = score; }
        
        public int getTotal() { return total; }
        public void setTotal(int total) { this.total = total; }
        
        public double getPercentage() { return percentage; }
        public void setPercentage(double percentage) { this.percentage = percentage; }
        
        public int getTimeTaken() { return timeTaken; }
        public void setTimeTaken(int timeTaken) { this.timeTaken = timeTaken; }
        
        public Timestamp getCompletedAt() { return completedAt; }
        public void setCompletedAt(Timestamp completedAt) { this.completedAt = completedAt; }
    }
    
    public static class UserStatistics {
        private int totalQuizzes;
        private double averageScore;
        private double bestScore;
        private String bestSubject;
        
        // Getters and setters
        public int getTotalQuizzes() { return totalQuizzes; }
        public void setTotalQuizzes(int totalQuizzes) { this.totalQuizzes = totalQuizzes; }
        
        public double getAverageScore() { return averageScore; }
        public void setAverageScore(double averageScore) { this.averageScore = averageScore; }
        
        public double getBestScore() { return bestScore; }
        public void setBestScore(double bestScore) { this.bestScore = bestScore; }
        
        public String getBestSubject() { return bestSubject; }
        public void setBestSubject(String bestSubject) { this.bestSubject = bestSubject; }
    }
    
    public static class LeaderboardEntry {
        private int rank;
        private String username;
        private double score;
        private int quizCount;
        private Timestamp date;
        
        // Getters and setters
        public int getRank() { return rank; }
        public void setRank(int rank) { this.rank = rank; }
        
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        
        public double getScore() { return score; }
        public void setScore(double score) { this.score = score; }
        
        public int getQuizCount() { return quizCount; }
        public void setQuizCount(int quizCount) { this.quizCount = quizCount; }
        
        public Timestamp getDate() { return date; }
        public void setDate(Timestamp date) { this.date = date; }
    }
}