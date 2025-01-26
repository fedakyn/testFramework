package com.example.steps;

import com.example.base.BaseDbTest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbSteps extends BaseDbTest {

    private int latestAlbumId = 0;

    public void deleteRow(String tableName, String columnName, Object value) {
        if (tableName == null || columnName == null || value == null) {
            throw new IllegalArgumentException("Table name, column name, and value cannot be null.");
        }
        String query = "DELETE FROM " + tableName + " WHERE " + columnName + " = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setObject(1, value);
            int rowsDeleted = preparedStatement.executeUpdate();
            System.out.println(rowsDeleted > 0
                    ? "Deleted " + rowsDeleted + " row(s) from " + tableName
                    : "No rows found in " + tableName + " where " + columnName + " = " + value);
        } catch (SQLException e) {
            throw new RuntimeException("Error executing deleteRow: " + e.getMessage(), e);
        }
    }

    public void getAlbumId(String title) {
        String query = "SELECT * FROM ALBUM WHERE Title = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int albumId = resultSet.getInt("AlbumId");
                System.out.println("Album ID: " + albumId + ", Title: " + title);
            } else {
                System.out.println("No album found with Ttile: " + title);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error executing getAlbumId query: " + e.getMessage(), e);
        }
    }

    public void updateAlbumTitle(int id, String title) {
        String query = "UPDATE Album SET Title = ? WHERE AlbumId = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, id);
            int resultSet = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error executing updateAlbumTitle query: " + e.getMessage(), e);
        }
    }

    public void insertAlbum(int albumId, String title, int artistId) {
        String query = "INSERT INTO Album (AlbumId, Title, ArtistId) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, albumId);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, artistId);
            preparedStatement.executeUpdate();
            System.out.println("Inserted album: AlbumId = " + albumId + ", Title = " + title + ", ArtistId = " + artistId);
        } catch (SQLException e) {
            throw new RuntimeException("Error executing insertAlbum query: " + e.getMessage(), e);
        }
    }

    public void fetchLatestAlbumId() {
        String query = "SELECT MAX(AlbumId) AS LatestAlbumId from Album";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                this.latestAlbumId = resultSet.getInt("LatestAlbumId");
                System.out.println("Album with id " + latestAlbumId + " extracted!");
            } else {
                throw new RuntimeException("No albums found in the database.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing getLatestAlbumId query: " + e.getMessage(), e);
        }
    }

    public int getLatestAlbumId() {
        return latestAlbumId;
    }

    public void deleteRowByLatestAlbumId(int albumId) {
        String query = "DELETE FROM Album WHERE AlbumId = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, albumId);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Album with ID " + albumId + " was deleted!");
            } else {
                System.out.println("No album found with ID " + albumId + ". Nothing was deleted.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing deleteAlbumId query: " + e.getMessage(), e);
        }
    }

}

