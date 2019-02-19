package models.celula;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.caracteristica.Contribuicao;
import models.caracteristica.Necessidade;
import models.caracteristica.Peso;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-15T21:54:52")
@StaticMetamodel(CelulaCaracterizada.class)
public class CelulaCaracterizada_ { 

    public static volatile SingularAttribute<CelulaCaracterizada, Necessidade> necessidade;
    public static volatile SingularAttribute<CelulaCaracterizada, Peso> peso;
    public static volatile SingularAttribute<CelulaCaracterizada, Contribuicao> contribuicao;
    public static volatile SingularAttribute<CelulaCaracterizada, Long> id;
    public static volatile SingularAttribute<CelulaCaracterizada, Long> version;

}