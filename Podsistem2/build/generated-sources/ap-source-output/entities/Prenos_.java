package entities;

import entities.Racun;
import entities.Transakcija;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-03T03:31:40")
@StaticMetamodel(Prenos.class)
public class Prenos_ { 

    public static volatile SingularAttribute<Prenos, Transakcija> transakcija;
    public static volatile SingularAttribute<Prenos, Racun> primalacId;
    public static volatile SingularAttribute<Prenos, Integer> transakcijaId;

}