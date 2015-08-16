package ejb;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2015-08-16T10:07:33")
@StaticMetamodel(CustomerOrderLine.class)
public class CustomerOrderLine_ { 

    public static volatile SingularAttribute<CustomerOrderLine, Long> id;
    public static volatile SingularAttribute<CustomerOrderLine, String> orderNo;
    public static volatile SingularAttribute<CustomerOrderLine, Double> Price;
    public static volatile SingularAttribute<CustomerOrderLine, String> PartNo;
    public static volatile SingularAttribute<CustomerOrderLine, String> LineNo;

}