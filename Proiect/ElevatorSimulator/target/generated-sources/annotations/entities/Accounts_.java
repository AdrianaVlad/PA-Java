package entities;

import entities.Buildings;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-05-26T14:11:41", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Account.class)
public class Accounts_ { 

    public static volatile SingularAttribute<Account, String> password;
    public static volatile SingularAttribute<Account, String> name;
    public static volatile SingularAttribute<Account, Integer> id;
    public static volatile SingularAttribute<Account, String> type;
    public static volatile ListAttribute<Account, Buildings> buildingsList;

}