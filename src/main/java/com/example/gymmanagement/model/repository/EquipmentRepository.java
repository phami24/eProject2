package com.example.gymmanagement.model.repository;

import com.example.gymmanagement.database.JDBCConnect;
import com.example.gymmanagement.model.entity.Equipment;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EquipmentRepository {
    private final JDBCConnect jdbcConnect;

    public EquipmentRepository() {
        this.jdbcConnect = new JDBCConnect();
    }

    // Phương thức để thêm một thiết bị vào cơ sở dữ liệu
    public void addEquipment(Equipment equipment) {
        String query = "INSERT INTO equipment (equipment_name, category, purchase_date, price, status, notes) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        executeEquipmentQuery(query, equipment);
    }

    // Phương thức để cập nhật thông tin thiết bị trong cơ sở dữ liệu
    public void updateEquipment(Equipment equipment) {
        String query = "UPDATE equipment " +
                "SET equipment_name = ?, category = ?, purchase_date = ?, price = ?, status = ?, notes = ? " +
                "WHERE equipment_id = ?";
        executeEquipmentQuery(query, equipment);
    }

    // Phương thức để xóa thiết bị khỏi cơ sở dữ liệu
    public void deleteEquipment(int equipmentId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = jdbcConnect.getJDBCConnection();
            String query = "DELETE FROM equipment WHERE equipment_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, equipmentId);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            jdbcConnect.closeConnection(connection);
            jdbcConnect.closePreparedStatement(statement);
        }
    }

    // Phương thức để lấy thông tin thiết bị dựa trên ID
    public Equipment getEquipmentById(int equipmentId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Equipment equipment = null;

        try {
            connection = jdbcConnect.getJDBCConnection();
            String query = "SELECT * FROM equipment WHERE equipment_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, equipmentId);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                equipment = fromResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            jdbcConnect.closeConnection(connection);
            jdbcConnect.closeResultSet(resultSet);
            jdbcConnect.closePreparedStatement(statement);
        }

        return equipment;
    }

    // Phương thức để lấy danh sách tất cả các thiết bị
    public List<Equipment> getAllEquipment() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<Equipment> equipmentList = new ArrayList<>();

        try {
            connection = jdbcConnect.getJDBCConnection();
            String query = "SELECT * FROM equipment";
            statement = connection.prepareStatement(query);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Equipment equipment = fromResultSet(resultSet);
                equipmentList.add(equipment);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            jdbcConnect.closeConnection(connection);
            jdbcConnect.closeResultSet(resultSet);
            jdbcConnect.closePreparedStatement(statement);
        }

        return equipmentList;
    }

    public Equipment fromResultSet(ResultSet resultSet) throws SQLException {
        Equipment equipment = new Equipment();
        equipment.setEquipmentId(resultSet.getInt("equipment_id"));
        equipment.setEquipmentName(resultSet.getString("equipment_name"));
        equipment.setCategory(resultSet.getString("category"));
        equipment.setPurchaseDate(resultSet.getString("purchase_date"));
        equipment.setPrice(resultSet.getBigDecimal("price"));
        equipment.setStatus(resultSet.getString("status"));
        equipment.setNotes(resultSet.getString("notes"));
        return equipment;
    }

    private void executeEquipmentQuery(String query, Equipment equipment) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = jdbcConnect.getJDBCConnection();
            statement = connection.prepareStatement(query);

            statement.setString(1, equipment.getEquipmentName());
            statement.setString(2, equipment.getCategory());
            statement.setString(3, equipment.getPurchaseDate());
            statement.setBigDecimal(4, equipment.getPrice());
            statement.setString(5, equipment.getStatus());
            statement.setString(6, equipment.getNotes());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            jdbcConnect.closeConnection(connection);
            jdbcConnect.closePreparedStatement(statement);
        }
    }

    public void updateEquipmentName(int equipmentId, String newEquipmentName) {
        String query = "UPDATE equipment SET equipment_name = ? WHERE equipment_id = ?";
        updateField(query, newEquipmentName, equipmentId);
    }

    public void updateCategory(int equipmentId, String newCategory) {
        String query = "UPDATE equipment SET category = ? WHERE equipment_id = ?";
        updateField(query, newCategory, equipmentId);
    }

    public void updatePurchaseDate(int equipmentId, String newPurchaseDate) {
        String query = "UPDATE equipment SET purchase_date = ? WHERE equipment_id = ?";
        updateField(query, newPurchaseDate, equipmentId);
    }

    public void updatePrice(int equipmentId, BigDecimal newPrice) {
        String query = "UPDATE equipment SET price = ? WHERE equipment_id = ?";
        updateField(query, newPrice, equipmentId);
    }

    public void updateStatus(int equipmentId, String newStatus) {
        String query = "UPDATE equipment SET status = ? WHERE equipment_id = ?";
        updateField(query, newStatus, equipmentId);
    }

    public void updateNotes(int equipmentId, String newNotes) {
        String query = "UPDATE equipment SET notes = ? WHERE equipment_id = ?";
        updateField(query, newNotes, equipmentId);
    }

    private void updateField(String query, Object newValue, int equipmentId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = jdbcConnect.getJDBCConnection();
            statement = connection.prepareStatement(query);

            if (newValue instanceof String) {
                statement.setString(1, (String) newValue);
            } else if (newValue instanceof BigDecimal) {
                statement.setBigDecimal(1, (BigDecimal) newValue);
            }

            statement.setInt(2, equipmentId);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            jdbcConnect.closeConnection(connection);
            jdbcConnect.closePreparedStatement(statement);
        }
    }
}



