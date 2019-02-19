package bootstrap;

import adapters.MatrizIO;
import models.Cobertura;
import models.Envolvente;
import models.FatorRisco;
import models.User;
import repository.*;
import settings.Settings;
import utils.CSVImporter;

import java.util.List;

public class Bootstrap {

    // Importers
    private final CSVImporter csvImporter = new CSVImporter();
    private final MatrizIO matrizIO = new MatrizIO();
    //    private final MatrizCaracterizadaIO matrizCaracterizadaIO = new MatrizCaracterizadaIO();
//    private final MatrizDetalhadaIO matrizDetalhadaIO = new MatrizDetalhadaIO();
    // Model Repositories
    private final CoberturaRepository coberturaRepository = new CoberturaRepository();
    private final EnvolventeRepository envolventeRepository = new EnvolventeRepository();
    private final FatorRiscoRepository fatorRiscoRepository = new FatorRiscoRepository();
    private final UserRepository userRepository = new UserRepository();
    // Matriz Repositories
    private final MatrizRepository matrizBaseRepository = new MatrizRepository();
    //private final MatrizCaracterizadaRepository matrizCaracterizadaRepository = new MatrizCaracterizadaRepository();
    //private final MatrizDetalhadaRepository matrizDetalhadaRepository = new MatrizDetalhadaRepository();


    public void populateDatabase() {
        System.out.println("Bootstrap: Populating database from CSV files...\n");

        List<Cobertura> coberturaList = csvImporter.importCobertura(Settings.PROJECT_PATH + "/main/resources/csv/coberturas.csv");
        List<Envolvente> envolventeList = csvImporter.importEnvolvente(Settings.PROJECT_PATH + "/main/resources/csv/envolventes.csv");
        List<FatorRisco> fatorRiscoList = csvImporter.importFatorRisco(Settings.PROJECT_PATH + "/main/resources/csv/factores-de-risco.csv");
        List<User> userList = csvImporter.importUser(Settings.PROJECT_PATH + "/main/resources/csv/users.csv");

        for (Cobertura cobertura : coberturaList) {
            coberturaRepository.add(cobertura);
        }

        for (Envolvente envolvente : envolventeList) {
            envolventeRepository.add(envolvente);
        }

        for (FatorRisco fatorRisco : fatorRiscoList) {
            fatorRiscoRepository.add(fatorRisco);
        }

        for (User user : userList) {
            userRepository.add(user);
        }

        // Adicionar Matriz Base à Database
        //Matriz matrizBase = matrizIO.importBase(Settings.PROJECT_PATH + "/main/resources/csv/matriz-base.csv");
        //matrizBaseRepository.add(matrizBase);

        // Adicionar Matriz Caracterizada à Database
        //MatrizCaracterizada matrizCaracterizada = matrizCaracterizadaIO.importObject(Settings.PROJECT_PATH + "/main/resources/csv/matriz-caracterizada.csv");
        //matrizCaracterizadaRepository.add(matrizCaracterizada);

        // Adicionar Matriz Detalhada à Database
        //MatrizDetalhada matrizDetalhada = matrizDetalhadaIO.importObject(Settings.PROJECT_PATH + "/main/resources/csv/matriz-detalhada.csv");
        //matrizDetalhadaRepository.add(matrizDetalhada);

    }

}
