package entities;

import entities.Building;
import java.math.BigInteger;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-05-26T14:11:41", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Elevator.class)
public class Elevators_ { 

    public static volatile SingularAttribute<Elevator, BigInteger> currentFloor;
    public static volatile SingularAttribute<Elevator, Integer> id;
    public static volatile SingularAttribute<Elevator, BigInteger> maxPeople;
    public static volatile SingularAttribute<Elevator, BigInteger> maxWeight;
    public static volatile SingularAttribute<Elevator, BigInteger> highestFloor;
    public static volatile SingularAttribute<Elevator, BigInteger> lowestFloor;
    public static volatile SingularAttribute<Elevator, String> status;
    public static volatile SingularAttribute<Elevator, Building> buildingId;

}