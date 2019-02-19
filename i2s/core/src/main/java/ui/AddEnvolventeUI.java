package ui;

import controllers.AddEnvolventeController;

import java.util.Scanner;

public class AddEnvolventeUI {
    private final Scanner scanner = new Scanner(System.in);
    private final AddEnvolventeController controller = new AddEnvolventeController();

    public void addEnvolvente() {
        System.out.println("Add Envolvente:");
        System.out.println("=============================");
        System.out.println("Titulo do Envolvente:");
        String tituloEnvolvente = scanner.nextLine();
        System.out.println("Descricao:");
        String descricao = scanner.nextLine();
        System.out.println("Adding envolvente...");
        controller.addEnvolvente(tituloEnvolvente, descricao);
        System.out.println("Envolvente added successfully!\n");
    }
}
