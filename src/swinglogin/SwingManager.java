
package swinglogin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


//ALL STAFF EDIT OPTIONS WILL CALL THIS WINDOW
//PASS AN INT TO DETERMINE WHAT ACTION TO EXECUTE -- CURRENT IDEA.

public class SwingManager extends JFrame 
        implements ActionListener{
    
    private static Container mainContainer;
    
    public SwingManager(String title){
        super(title);
        this.setSize(700,500);
        this.setLocation(1920/4, 1080/4);
        this.setVisible(true);
       
       // setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //CONFIGURE CONTENT PANE
        mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(8,6));
        mainContainer.setBackground(Color.gray);
     
         //CENTER PANEL
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        add(centerPanel, BorderLayout.CENTER);
        
        
        //STAFF PANEL
        JPanel staffPanel = new JPanel();
        staffPanel.setLayout(new GridLayout(20,1));
        staffPanel.setBackground(Color.WHITE);
        staffPanel.setBorder(BorderFactory.createTitledBorder("MANAGE STAFFS"));
        
        JButton editStaffButton = new JButton("editStaffButton");
        
        //BUTTON TEST
        JButton addStaffButton = new JButton("addStaffButton");
        addStaffButton.setActionCommand("addStaff");
        addStaffButton.addActionListener(this);
        
        JButton deleteStaffButton = new JButton("deleteStaffButton");
        JButton viewStaffButton = new JButton("viewStaffButton");
        
        staffPanel.add(editStaffButton);
        staffPanel.add(addStaffButton);
        staffPanel.add(deleteStaffButton);
        staffPanel.add(viewStaffButton);
        
        //CUSTOMER PANEL
        JPanel customerPanel = new JPanel();
        customerPanel.setLayout(new GridLayout(20,1));
        customerPanel.setBackground(Color.WHITE);
        customerPanel.setBorder(BorderFactory.createTitledBorder("MANAGE CUSTOMERS"));
       
        
        JButton editCustomerButton = new JButton("editCustomerButton");
        JButton addCustomerButton = new JButton("addCustomerButton");
        JButton deleteCustomerButton = new JButton("deleteCustomerButton");
        JButton viewCustomerButton = new JButton("viewCustomerButton");
        
        customerPanel.add(editCustomerButton);
        customerPanel.add(addCustomerButton);
        customerPanel.add(deleteCustomerButton);
        customerPanel.add(viewCustomerButton);
        
        //ORDER PANEL
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new GridLayout(20,1));
        orderPanel.setBackground(Color.WHITE);
        orderPanel.setBorder(BorderFactory.createTitledBorder("MANAGE ORDERS"));
        
        JButton createOrderButton = new JButton("createOrderButton");
        JButton assignStaffButton = new JButton("assignStaffButton");
        JButton deleteOrderButton = new JButton("deleteOrderButton");
        JButton viewOrdersButton = new JButton("viewOrdersButton");
        
        orderPanel.add(createOrderButton);
        orderPanel.add(assignStaffButton);
        orderPanel.add(deleteOrderButton);
        orderPanel.add(viewOrdersButton);
        
        //ADD COMPONENTS TO CENTER PANEL via GridBagConstraints
        GridBagConstraints c = new GridBagConstraints();
        
        c.weightx = 1;//always resize full size - to occupy whole display
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = (500 - 11);
        c.ipadx = (500/3 - 11);
        c.fill = GridBagConstraints.BOTH;
        centerPanel.add(staffPanel, c);
        
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        c.ipady = (500 - 11);
        c.ipadx = (500/3 - 11);
        c.fill = GridBagConstraints.BOTH;
        centerPanel.add(customerPanel, c);
        
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        c.ipady = (500 - 11);
        c.ipadx = (500/3 - 11);
        c.fill = GridBagConstraints.BOTH;
        centerPanel.add(orderPanel, c);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if("addStaff".equals(e.getActionCommand())){
            System.out.println("OPENING ADD STAFF WINDOW...");
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run(){
                   // setVisible(false);
                    DeliveryStaffProfile devProf = new DeliveryStaffProfile("ADD STAFF");
                }
            });
        }
    } 
}
