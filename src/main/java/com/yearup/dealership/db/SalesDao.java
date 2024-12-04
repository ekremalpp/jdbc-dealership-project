package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        String sql = "INSERT INTO sales_contracts (VIN, sale_date, price) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, salesContract.getVin());
            preparedStatement.setDate(2, Date.valueOf(salesContract.getSaleDate()));
            preparedStatement.setDouble(3, salesContract.getPrice());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Sales contract successfully added.");
            } else {
                System.out.println("Failed to add sales contract.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
