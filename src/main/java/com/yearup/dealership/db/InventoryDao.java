package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {
        String sql = "INSERT INTO inventory (dealership_id, VIN) VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, dealershipId);
            preparedStatement.setString(2, vin);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void removeVehicleFromInventory(String vin) {
        String sql = "DELETE FROM inventory WHERE VIN = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, vin);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No vehicle found with VIN: " + vin);
            } else {
                System.out.println("Vehicle with VIN " + vin + " has been removed from the inventory.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
