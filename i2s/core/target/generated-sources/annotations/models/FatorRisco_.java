package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import services.FatorRiscoService;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-15T21:54:50")
@StaticMetamodel(FatorRisco.class)
public class FatorRisco_ { 

    public static volatile SingularAttribute<FatorRisco, String> titulo;
    public static volatile SingularAttribute<FatorRisco, Long> id;
    public static volatile SingularAttribute<FatorRisco, FatorRiscoService> fs;
    public static volatile SingularAttribute<FatorRisco, Long> version;
    public static volatile SingularAttribute<FatorRisco, String> descricao;

}