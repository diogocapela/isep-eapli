package ui;

import controllers.ListEnvolventesController;
import models.Envolvente;

import java.util.List;

public class ListEnvolventesUI {
    private final ListEnvolventesController controller = new ListEnvolventesController();

    public void listEnvolventes() {
        StringBuilder stringEnvolventes = new StringBuilder();
        List<Envolvente> envolventes = controller.listEnvolventes();

        stringEnvolventes.append("\n==============================================================").
            append("\n            Listing Envolventes...").
            append("\n==============================================================");
        for (Envolvente e : envolventes) {
            stringEnvolventes.append("\n    ").append(e.toDTO().getTitulo()).
                append("\n--------------------------------------------------------------");
        }
        stringEnvolventes.append("\n            Envolventes listed successfully!").
            append("\n==============================================================");

        System.out.println(stringEnvolventes);
    }
}
