package entities;

import entities.Buildings;
import java.math.BigInteger;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-05-26T14:11:41", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Elevators.class)
public class Elevators_ { 

    public static volatile SingularAttribute<Elevators, BigInteger> currentFloor;
    public static volatile SingularAttribute<Elevators, Integer> id;
    public static volatile SingularAttribute<Elevators, BigInteger> maxPeople;
    public static volatile SingularAttribute<Elevators, BigInteger> maxWeight;
    public static volatile SingularAttribute<Elevators, BigInteger> highestFloor;
    public static volatile SingularAttribute<Elevators, BigInteger> lowestFloor;
    public static volatile SingularAttribute<Elevators, String> status;
    public static volatile SingularAttribute<Elevators, Buildings> buildingId;

}