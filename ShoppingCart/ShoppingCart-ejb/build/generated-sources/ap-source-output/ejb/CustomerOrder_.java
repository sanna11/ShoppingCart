package ejb;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2015-08-16T10:07:33")
@StaticMetamodel(CustomerOrder.class)
public class CustomerOrder_ { 

    public static volatile SingularAttribute<CustomerOrder, Long> id;
    public static volatile SingularAttribute<CustomerOrder, Double> amount;
    public static volatile SingularAttribute<CustomerOrder, String> orderNo;
    public static volatile SingularAttribute<CustomerOrder, String> customerId;
    public static volatile SingularAttribute<CustomerOrder, Date> dueDate;
    public static volatile SingularAttribute<CustomerOrder, String> comments;

}