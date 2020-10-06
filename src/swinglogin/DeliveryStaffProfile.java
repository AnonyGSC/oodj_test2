
package swinglogin;

//misc
import java.awt.Container;
import java.awt.Color;

//layouts - styling
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;

//swing components
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Events
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//thread safety
import javax.swing.SwingUtilities;

//date
import java.util.Date;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;


//TODO: 
//
//
//

public class DeliveryStaffProfile extends JFrame 
        implements ActionListener{
    
    //will move others later
    //only necessary variables declared - to increase its scope.
    private static JTextField staffNameField, staffAgeField, staffStatusField, staffPhoneNoField, staffCurrentOrderField;
    private JButton submitButton, cancelButton;
    
    protected Calendar calendar;
    SpinnerListModel monthModel;
    
    private JPanel labelPane, textFieldPane;
    
    String[] spinnerLabels = {"Month: ", "Year: ", "Date: "};
    int numPairs;
    
    public DeliveryStaffProfile(String title){
        super(title);
        this.setSize(700,500);
        this.setLocation(1920/4, 1080/4);
        this.setVisible(true);

        // setResizable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        //CONFIGURE CONTENT PANE
        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(8,6));
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.WHITE));
        
        //NORTH CONTENT --heading/title
        JLabel pageTitle = new JLabel();
        pageTitle.setFont(new java.awt.Font(Font.MONOSPACED, Font.BOLD, 30));
        pageTitle.setText(title);
        add(pageTitle, BorderLayout.NORTH);
        
        //SOUTH PANEL
        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout(4,4,4));
        
        submitButton = new JButton("SUBMIT");
        submitButton.addActionListener(this);
        submitButton.setActionCommand("submit");
        
        cancelButton = new JButton("CANCEL");
        cancelButton.addActionListener(this);
        cancelButton.setActionCommand("cancel");
        
        submitPanel.add(submitButton);
        submitPanel.add(cancelButton);
        add(submitPanel, BorderLayout.SOUTH);
        
        //Label and TextField PANE
        labelPane = new JPanel();
        labelPane.setLayout(new GridLayout(6,0));
        textFieldPane = new JPanel();
        textFieldPane.setLayout(new GridLayout(6,0));
        
        //ADD TO CONTENT PANE
        add(labelPane, BorderLayout.WEST);
        add(textFieldPane, BorderLayout.CENTER);
        
        //name
        JLabel staffNameLabel = new JLabel("FULL NAME: ");
        staffNameLabel.setFont(new java.awt.Font(Font.MONOSPACED, Font.BOLD, 15));
        labelPane.add(staffNameLabel);
        staffNameField = new JTextField();
        textFieldPane.add(staffNameField);
         
        //dob
        JPanel dobSpinnerPanel = new JPanel(new GridLayout(0,3));
        textFieldPane.add(dobSpinnerPanel);

        JLabel staffDOBLabel = new JLabel("DOB:");
        staffDOBLabel.setFont(new java.awt.Font(Font.MONOSPACED, Font.BOLD, 15));
        labelPane.add(staffDOBLabel);
        
        JSpinner monthSpinner = createMonthSpinner();
        JSpinner yearSpinner = createYearSpinner();
        JSpinner dateSpinner = createDateSpinner();
        
        dobSpinnerPanel.add(monthSpinner);
        dobSpinnerPanel.add(dateSpinner);
        dobSpinnerPanel.add(yearSpinner);
 
        //age
        JLabel staffAgeLabel = new JLabel("AGE: ");
        staffAgeLabel.setFont(new java.awt.Font(Font.MONOSPACED, Font.BOLD, 15));
        labelPane.add(staffAgeLabel);
        staffAgeField = new JTextField();
        textFieldPane.add(staffAgeField);

        //phone no
        JLabel staffPhoneNoLabel = new JLabel("PHONE NO:");
        staffPhoneNoLabel.setFont(new java.awt.Font(Font.MONOSPACED, Font.BOLD, 15));
        labelPane.add(staffPhoneNoLabel);
        staffPhoneNoField = new JTextField();
        textFieldPane.add(staffPhoneNoField);

        //current order - drop down list
        JLabel staffCurrentOrderLabel = new JLabel("CURRENT ORDER: ");
        staffCurrentOrderLabel.setFont(new java.awt.Font(Font.MONOSPACED, Font.BOLD, 15));
        labelPane.add(staffCurrentOrderLabel);
        staffCurrentOrderField = new JTextField();
        textFieldPane.add(staffCurrentOrderField);
        
        //status - radio buttons
        JLabel staffStatusLabel = new JLabel("STATUS: ");
        staffStatusLabel.setFont(new java.awt.Font(Font.MONOSPACED, Font.BOLD, 15));
        labelPane.add(staffStatusLabel);
        staffStatusField = new JTextField();
        textFieldPane.add(staffStatusField);
    }
    
    
    //CREATE SPINNER METHODS
    private JSpinner createMonthSpinner(){
       numPairs = spinnerLabels.length;
       calendar = Calendar.getInstance();
       
       String[] monthStrings = getMonths();
       
       monthModel = new SpinnerListModel(monthStrings);//creates a spinner using given list.
       JSpinner monthSpinner = new JSpinner(monthModel);

       return monthSpinner;
    }
    
    private JSpinner createDateSpinner(){
        Date initDate = calendar.getTime();

        calendar.add(Calendar.YEAR,  -100);
        Date minDate = calendar.getTime();
        
        calendar.add(Calendar.YEAR, 200);
        Date maxDate = calendar.getTime();
        
        SpinnerDateModel dateModel = new SpinnerDateModel(initDate,
        minDate,
        maxDate,
        Calendar.YEAR);
        
        JSpinner dateSpinner = new JSpinner(dateModel);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "MM/yyyy"));
        
        return dateSpinner;
    }
    
    private JSpinner createYearSpinner(){
        int currentYear = calendar.get(Calendar.YEAR);
        
        SpinnerModel yearModel = new SpinnerNumberModel(currentYear,//
                currentYear - 50, //min
                currentYear + 50, //max
                1//step
        );
        
        JSpinner yearSpinner = new JSpinner(yearModel);
        yearSpinner.setEditor(new JSpinner.NumberEditor(yearSpinner, "#")); //Make the year be formatted without a thousands separator.
        
        return yearSpinner;
    }
    
     //DateFormatSymbols returns an extra empty value at the  end of the array of months
     //is removed within getMonths through checking and moving the correct values.
    static protected String[] getMonths(){
        String months[] = new java.text.DateFormatSymbols().getMonths();//retrieve months
        int lastIndex = months.length - 1;
        
        if(months[lastIndex] == null
                || months[lastIndex].length() <= 0){//last item empty
            String[] monthStrings = new String[lastIndex];
            System.arraycopy(months, 0, monthStrings, 0, lastIndex);//copy content of month array to monthStrings from - to lastIndex.
            return monthStrings;
        }else{//last item not empty value. -- correct months array.
            return months;
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent e){
        if("submit".equals(e.getActionCommand())){
            /**CREATE DELIVERY STAFF OBJECT
             * USING DATA IN COMPONENTS - getText()
             * SAVE TO FILE
            **/
            // NOT ALL DATA IMPLEMENTED YET
            
            DeliveryStaff newStaff = new DeliveryStaff(staffNameField.getText(), 
                    staffPhoneNoField.getText()
            );
            
            try {
                DeliveryStaff.saveStaff(newStaff);
                
                JOptionPane.showMessageDialog(this,
                    "New Staff Saved Successfully",
                    "Success!!!",
                    JOptionPane.INFORMATION_MESSAGE);
                
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                         dispose();
                    }
                });
               
            } catch (IOException ex) {
                Logger.getLogger(DeliveryStaffProfile.class.getName()).log(Level.SEVERE, null, ex);//CONFIGURE EXCEPTION LATER
            }
            
        }
        else if("cancel".equals(e.getActionCommand())){
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run(){
                    dispose();//closes window
                }
            });
        }
        else{
            System.out.println("no action defined");
        }
    }
}
