package entities;

import entities.Prenos;
import entities.Racun;
import entities.UplataIsplata;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-03T03:31:40")
@StaticMetamodel(Transakcija.class)
public class Transakcija_ { 

    public static volatile SingularAttribute<Transakcija, Double> iznos;
    public static volatile SingularAttribute<Transakcija, Date> datumVreme;
    public static volatile SingularAttribute<Transakcija, String> svrha;
    public static volatile SingularAttribute<Transakcija, UplataIsplata> uplataIsplata;
    public static volatile SingularAttribute<Transakcija, Integer> redniBr;
    public static volatile SingularAttribute<Transakcija, Prenos> prenos;
    public static volatile SingularAttribute<Transakcija, Integer> transakcijaId;
    public static volatile SingularAttribute<Transakcija, Racun> racunId;

}