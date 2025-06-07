Feature: db scenarios


  Scenario: Insert row and delete it
    Given Database connection is established
    When Fetch latest AlbumId
    And Insert row in Album table with Title "Kankun" ArtistId 399
    And Delete latest album
    Then Close database connection

  Scenario: Delete row
    Given Database connection is established
    When Delete a row from "Album" table where "AlbumId" is "365"
    Then Close database connection
