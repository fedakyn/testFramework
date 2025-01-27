package com.example.stepdefs.db;

import com.example.base.BaseDbTest;
import com.example.steps.DbSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DbStepDefs extends BaseDbTest {

    private static final DbSteps dbSteps = new DbSteps();
    private int lastInsertedAlbumId;

    @Given("Database connection is established")
    public void db_connection_established() {
        dbSteps.setUp();
    }

    @When("Delete a row from {string} table where {string} is {string}")
    public void deleteRowFromTable(String tableName, String columnName, String value) {
        try {
            dbSteps.deleteRow(tableName, columnName, value);
            logger.info("Row successfully deleted from table " + tableName);
        } catch (Exception e) {
            System.err.println("Failed to delete row from table " + tableName + ": " + e.getMessage());
        }
    }

    @When("Get AlbumId for title {string}")
    public void get_albumId_for_title(String title) {
        dbSteps.getAlbumId(title);
    }

    @When("Update album title with id {int} to {string}")
    public void update_album_title_for_id(int id, String title){
        dbSteps.updateAlbumTitle(id, title);
    }

    @When("Fetch latest AlbumId")
    public void fetch_latest_albumId(){
        dbSteps.fetchLatestAlbumId();
    }

    @When("Insert row in Album table with Title {string} ArtistId {int}")
    public void insert_row_album(String albumTitle, int artistId){
        int lastInsertedAlbumId = dbSteps.getLatestAlbumId() + 1;
        dbSteps.insertAlbum(lastInsertedAlbumId,albumTitle,artistId);
    }

    @When("Delete latest album")
    public void delete_latest_album(){
        dbSteps.fetchLatestAlbumId();
        int albumIdToDelete = dbSteps.getLatestAlbumId();
        dbSteps.deleteRowByLatestAlbumId(albumIdToDelete);
    }

    @Then("Close database connection")
    public void close_db_connection(){
        dbSteps.tearDown();
    }
}
