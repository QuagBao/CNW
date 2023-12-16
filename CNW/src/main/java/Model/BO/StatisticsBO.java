package Model.BO;

import Model.BEAN.Statistics;
import Model.DAO.StatisticsDAO;

public class StatisticsBO {
    private StatisticsDAO statisticsDAO;

    public StatisticsBO() {
        statisticsDAO = new StatisticsDAO();
    }

    // Phương thức lấy thống kê tổng quan
    public Statistics getOverallStatistics() {
        return statisticsDAO.getOverallStatistics();
    }

    // Phương thức cập nhật thông tin thống kê
    public void updateStatistics(Statistics statistics) {
        statisticsDAO.updateStatistics(statistics);
    }
}
