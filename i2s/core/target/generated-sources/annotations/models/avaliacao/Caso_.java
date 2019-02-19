package models.avaliacao;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.avaliacao.EstadoCaso;
import models.avaliacao.ObjetoSeguro;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-15T21:54:50")
@StaticMetamodel(Caso.class)
public class Caso_ { 

    public static volatile ListAttribute<Caso, ObjetoSeguro> objetosSegurados;
    public static volatile SingularAttribute<Caso, EstadoCaso> estado;
    public static volatile SingularAttribute<Caso, Long> id;
    public static volatile SingularAttribute<Caso, Long> version;

}