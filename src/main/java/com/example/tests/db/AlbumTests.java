package com.example.tests.db;

import com.example.steps.DbSteps;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumTests {

    public static void main(String[] args) {
        DbSteps dbSteps = new DbSteps();

        dbSteps.setUp();

        String albumTitle = "Big Ones";

        try {
            ResultSet resultSet = dbSteps.getAlbumId(albumTitle);
            while (resultSet.next()) {
                int albumId = resultSet.getInt("AlbumId");
                String title = resultSet.getString("Title");
                System.out.println("Album ID: " + albumId + ", Title: " + title);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.err.println("Error during query execution: " + e.getMessage());
            e.printStackTrace();
        }

        int rowsUpdated = dbSteps.updateAlbumTitle("Balls to the wall 1", 2);
        if (rowsUpdated > 0) {
            System.out.println("Album title updated successfully!");
        } else {
            System.out.println("No album found with the given ID.");
        }

        int latestAlbumId = dbSteps.getLatestAlbumId();
        System.out.println("Latest AlbumId: " + latestAlbumId);

        int rowsInserted = dbSteps.insertAlbum(latestAlbumId + 1, "Gestu", 276); // Insert the album
        if (rowsInserted > 0) {
            System.out.println("Album inserted successfully!");
        } else {
            System.out.println("No album was inserted.");
        }

        dbSteps.deleteAlbumId(latestAlbumId + 1);

        dbSteps.tearDown();

    }
}
