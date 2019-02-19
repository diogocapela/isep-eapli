import bootstrap.Bootstrap;
import repository.DatabaseManager;

public class Main {

    public static void main(String[] args) {

        DatabaseManager databaseManager = new DatabaseManager();
        Bootstrap bootstrap = new Bootstrap();

        databaseManager.deleteDatabase();
        bootstrap.populateDatabase();

        /*
        ⚠️ Just to test JSON Importer, do not touch this! ☢️

        JSONImporter jsonImporter = new JSONImporter();

        Caso caso;
        try {
            caso = jsonImporter.importCaso("./core/src/main/resources/json/caso-example.json");
            caso = jsonImporter.importCaso(".\src\main\resources\json\caso-example.json");
            System.out.println(caso);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        */

        Menu.loop();

    }

}
