//STOP FORGETTING YOU RETARD : C:\Users\aghos\Documents\NetBeansProjects\SwingLogin

package swinglogin;

/****arrange imports PLS BOOSSS***/

//layouts

//components

//misc

//threading/thread safety

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

/***TODO***/ 
//- use JCOMPONENT and create methods[ ]
//MOVE ALL VARIABLES TO TOP OF CLASS[ ]
//RESET FOCUS[ ]
//LIMIT TRIES - lock out of system.[ ]
//FIX OTHER UI - alignment and scaling[x] 

public class SwingLogin extends JFrame 
        implements ActionListener{

    private static JPasswordField passwordTextField;
    private static TextField userTextField;
    private File accountsFile;
    Scanner accountScanner;
    
    public SwingLogin(String title){
        //CONFIGURE MAIN WINDOW
        super(title);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FILE_CHOOSER_DIALOG);
        this.setSize(400,400);
        this.setLocation(1920/4, 1080/4);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //CONFIGURE CONTENT PANE
        Container mainContainer = this.getContentPane();
        mainContainer.setLayout(new BorderLayout(8,6));
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.WHITE));
        
        //CENTER PANEL
        JPanel centerPanel = new JPanel();
        centerPanel.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
        centerPanel.setLayout(new BorderLayout());//check flow layout constructor for info
        centerPanel.setBackground(Color.LIGHT_GRAY);
        add(centerPanel, BorderLayout.CENTER);
        
        //USERNAME
        JLabel userLabel = new JLabel("USERNAME: ");
        userLabel.setFont(new java.awt.Font(Font.MONOSPACED, Font.BOLD, 15));
        userTextField = new TextField();
        userLabel.setLabelFor(userTextField);
        
        //PASSWORD
        JLabel passwordLabel = new JLabel("PASSWORD: ");
        passwordLabel.setFont(new java.awt.Font(Font.MONOSPACED, Font.BOLD, 15));
        passwordTextField = new JPasswordField(10);
        passwordLabel.setLabelFor(passwordTextField);
        
        //CREDENTIALS PANE - user and pass
        JPanel credentialsPane = new JPanel();
        credentialsPane.setLayout(new GridLayout(8,6,10,10));
        credentialsPane.setBackground(Color.LIGHT_GRAY);
        

        //alternate: setBounds()
        credentialsPane.add(userLabel);
        credentialsPane.add(userTextField);
        

        credentialsPane.add(passwordLabel);
        credentialsPane.add(passwordTextField);
    

        //ADD TO CENTER
        centerPanel.add(credentialsPane, BorderLayout.SOUTH);
        
        //LOGIN PANEL --REGISTER AND OR FORGOT ACCOUNT
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Color.LIGHT_GRAY);
        add(loginPanel, BorderLayout.SOUTH);
        
        JButton loginButton = new JButton("LOG IN");
        loginButton.setFont(new java.awt.Font(Font.MONOSPACED, Font.BOLD, 15));
        loginButton.setActionCommand("login");
        loginButton.addActionListener(this);
        loginPanel.add(loginButton);
        
        JButton registerButton = new JButton("REGISTER - inactive");
        registerButton.setFont(new java.awt.Font(Font.MONOSPACED, Font.BOLD, 15));
        loginPanel.add(registerButton);
   
        
    }   
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if("login".equals(e.getActionCommand())){
            if(isValidPassword()){
                JOptionPane.showMessageDialog(this,
                    "WELCOME BACK ADMIN");
                
                //OPEN ADMIN WINDOW
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {
                        SwingManager sm = new SwingManager("ADMIN WINDOW");
                    }
                });
            
                //CLOSE LOGIN WINDOW - invokeLater??
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(this,
                    "Invalid password or username. Try again.",
                    "Error Message",
                    JOptionPane.ERROR_MESSAGE);
            }
            
            }
        
    }
    
    
    //READ TEXT FILE PASSWORD AND USER NAMES AND COMPARE WITH INPUT
    public boolean isValidPassword(){//THROWING FOR NOW --ADDING EXCEPTION HANDLING LATER
        boolean valid = true;//true for testing
        String userName = userTextField.getText();
        char[] passInput = passwordTextField.getPassword();

        //FILE DATA
        String fileUserName;
        String filePassword;
        
        accountsFile = new File("acc.txt");
        
        //ATTEMPT TO READ FROM FILE
        try {
            accountScanner = new Scanner(accountsFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SwingLogin.class.getName()).log(Level.SEVERE, null, ex);
            
            //CREATE FILE.
            createFile("acc.txt");
        }
        
        while(accountScanner.hasNextLine()){
            fileUserName = accountScanner.nextLine();//retrieve file data
            filePassword = accountScanner.nextLine();
            
            if(fileUserName.equals(userName) && Arrays.equals(filePassword.toCharArray(), passInput)){
                valid = true;
                break;//no need to keep iterating over file
            }
        }
   
        Arrays.fill(passInput, '0');//set each elem to zero for security purposes
        
        return valid;
    }
      
    
    public void createFile(String fileName){
        try{
            accountsFile = new File(fileName);
            if (accountsFile.createNewFile()) {
                System.out.println("Succes: File created: " + accountsFile.getName());
            }        
            else {
                System.out.println("Failed: File already exists");
            }
        }
        catch(IOException e){
            System.out.println("FAILED TO CREATE FILE via createFile(): " + e + ":(");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SwingLogin sw = new SwingLogin("LOGIN PAGE");
            }
        });
    }
}
