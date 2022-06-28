package entities;

import entities.Komitent;
import entities.Prenos;
import entities.Transakcija;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-03T03:31:40")
@StaticMetamodel(Racun.class)
public class Racun_ { 

    public static volatile SingularAttribute<Racun, Date> datumVreme;
    public static volatile SingularAttribute<Racun, Komitent> komitentId;
    public static volatile SingularAttribute<Racun, Double> stanje;
    public static volatile SingularAttribute<Racun, Double> dozvoljeniminus;
    public static volatile SingularAttribute<Racun, Integer> brTransakcija;
    public static volatile ListAttribute<Racun, Transakcija> transakcijaList;
    public static volatile SingularAttribute<Racun, Integer> mestoId;
    public static volatile ListAttribute<Racun, Prenos> prenosList;
    public static volatile SingularAttribute<Racun, Integer> racunId;
    public static volatile SingularAttribute<Racun, Short> status;

}