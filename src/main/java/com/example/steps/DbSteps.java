package com.example.steps;

import com.example.base.BaseDbTest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbSteps extends BaseDbTest {

    public ResultSet getAlbumId(String title) {
        String query = "SELECT * FROM ALBUM WHERE Title = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Error executing getAlbumId query: " + e.getMessage(), e);
        }
    }

    public int updateAlbumTitle(String title, int id) {
        String query = "UPDATE Album SET Title = ? WHERE AlbumId = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error executing updateAlbumTitle query: " + e.getMessage(), e);
        }
    }

    public int insertAlbum(int albumId, String title, int artistId) {
        String query = "INSERT INTO Album (AlbumId, Title, ArtistId) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, albumId);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, artistId);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error executing insertAlbum query: " + e.getMessage(), e);
        }
    }

    public int getLatestAlbumId() {
        String query = "SELECT MAX(AlbumId) AS LatestAlbumId from Album";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("LatestAlbumId");
            } else {
                throw new RuntimeException("No albums found in the database.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing getLatestAlbumId query: " + e.getMessage(), e);
        }
    }

    public void deleteAlbumId(int albumId) {
        String query = "DELETE FROM Album WHERE AlbumId = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, albumId);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0){
                System.out.println("Album with ID " + albumId + " was deleted!");
            } else{
                System.out.println("No album found with ID " + albumId + ". Nothing was deleted.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error executing deleteAlbumId query: " + e.getMessage(), e);
        }
    }

}

