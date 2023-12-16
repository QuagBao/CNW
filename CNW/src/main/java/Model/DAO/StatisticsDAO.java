package Model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.BEAN.Statistics;

public class StatisticsDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hotel";
    private static final String USER = "root";
    private static final String PASSWORD = "07062020";

    private static final String SELECT_OVERALL_STATISTICS = "SELECT * FROM statistics";
    private static final String UPDATE_STATISTICS = "UPDATE statistics SET total_room=?, booked_room=?, revenue=?";

    public Statistics getOverallStatistics() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_OVERALL_STATISTICS);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return mapResultSetToStatistics(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateStatistics(Statistics statistics) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATISTICS)) {
            preparedStatement.setInt(1, statistics.getTotalRoom());
            preparedStatement.setInt(2, statistics.getBookedRoom());
            preparedStatement.setDouble(3, statistics.getRevenue());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to map ResultSet to Statistics object
    private Statistics mapResultSetToStatistics(ResultSet resultSet) throws SQLException {
        Statistics statistics = new Statistics();
        statistics.setTotalRoom(resultSet.getInt("total_room"));
        statistics.setBookedRoom(resultSet.getInt("booked_room"));
        statistics.setRevenue(resultSet.getDouble("revenue"));
        return statistics;
    }

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found.", e);
        }
    }
}
