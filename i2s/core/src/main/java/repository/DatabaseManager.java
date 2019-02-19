package repository;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class DatabaseManager {

    public DatabaseManager() {

    }

    public void deleteDatabase() {
        System.out.println("DatabaseManager: Deleting database...\n");
        try {
            String DATABASE_PATH = "./db";
            FileUtils.deleteDirectory(new File(DATABASE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
