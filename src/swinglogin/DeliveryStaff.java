
package swinglogin;

//Exception handlind
import java.io.FileNotFoundException;
import java.io.IOException;

//For storing objects in file - more convinient to store as whole object
import java.io.Serializable;

//File handling 
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

//TODO 
/** 
VALIDATION - Ensure all fields are filled before allowing to submit
*          - Validate data given.
* 
* ADD TO STAFF FILE --CURRENTLY OVERWRITING
* STATIC FUNCTION - READ STAFF DATA - RETURN LIST OF STAFFS
 **/

public class DeliveryStaff extends User //throws FileNotFoundException, IOException, ClassNotFoundException //throw for now -- add exception handling later
        implements Serializable{
    
    private static File staffFile;
    
    public static enum staffStatus{//can be accessed from profile
        ON_DELIVERY, IDLE, ON_LEAVE;//possible status of delivery staffs
    }
    
    public DeliveryStaff(String n, String p){//Data recieved from DeliveryStaffProfile
        this.name = n;
        this.phoneNo = Integer.parseInt(p);//convert text recieved 
    }
    
    public DeliveryStaff(){}//default constructor.
    
    //static - can be used without ref to staff obj
    //void - no return
    //public - accessible outside of class
    public static void saveStaff(DeliveryStaff staff) throws FileNotFoundException, IOException{
        staffFile = new File("staffFile.txt");
                
        //Write Save object in file
        FileOutputStream fileOutStrm = new FileOutputStream(staffFile);//fileStrm to file
        ObjectOutputStream objectOutStrm = new ObjectOutputStream(fileOutStrm);//objstrm to filestrm
        objectOutStrm.writeObject(staff);//write object to objectStrm which writes to fileStrm --this refers to staff object being saved.
        
        System.out.println("STAFF SAVED TO " + staffFile.getName());
    }
    
}
