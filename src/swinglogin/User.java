
package swinglogin;

import java.io.Serializable;

/** BASE CLASS FOR WHICH ALL SUBCLASSES 
 *  SUCH AS MANAGER, DELIVERY STAFF AND CUSTOMER
 *  INHERIT FROM
 **/

//TODO - missing lots of functionality

public class User implements Serializable{
    String name;
    int phoneNo;
    int dobDate, dobMonth, dobYear;
}
