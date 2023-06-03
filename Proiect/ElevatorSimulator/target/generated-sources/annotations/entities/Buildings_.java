package entities;

import entities.Accounts;
import entities.Elevators;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-05-26T14:11:41", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Building.class)
public class Buildings_ { 

    public static volatile ListAttribute<Building, Elevators> elevatorsList;
    public static volatile ListAttribute<Building, Accounts> accountsList;
    public static volatile SingularAttribute<Building, String> name;
    public static volatile SingularAttribute<Building, Integer> id;

}