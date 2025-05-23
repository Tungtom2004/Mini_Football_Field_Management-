/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.user;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.User;
import view.user.YardManagerHomeFrm;
import view.provider.SearchProviderFrm; 

public class YardImportingFrm extends JFrame implements ActionListener{
    private User user;
    private JButton btnBack;
    private JButton btnSearch;

    public YardImportingFrm(User user) {
       super("Manage Importing");
       this.user = user;
       
       JPanel topPanel = new JPanel(new BorderLayout());
       JLabel lblUsername = new JLabel(user.getUsername());
       lblUsername.setFont(new Font("Segoe UI",Font.PLAIN,14));
       
       btnBack = new JButton("Back");
       btnBack.addActionListener(this);
       
       topPanel.add(lblUsername,BorderLayout.WEST);
       topPanel.add(btnBack,BorderLayout.EAST);
       topPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
       
       //Title
       JLabel lblTitle = new JLabel("Manage Importing");
       lblTitle.setFont(new Font("Segoe UI",Font.BOLD,24));
       lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
       
       //Bottom button
       btnSearch = new JButton("Search");
       btnSearch.setFont(new Font("Segoe UI",Font.PLAIN,18));
       btnSearch.setPreferredSize(new Dimension(250,50));
       btnSearch.addActionListener(this);
       
       JPanel bottomPanel = new JPanel();
       bottomPanel.add(btnSearch);
       bottomPanel.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
       
       //main layout
       this.setLayout(new BorderLayout());
       this.add(topPanel,BorderLayout.NORTH);
       this.add(lblTitle,BorderLayout.CENTER);
       this.add(bottomPanel,BorderLayout.SOUTH);
       
       this.setSize(600,400);
       this.setLocationRelativeTo(null);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnBack){
            this.dispose();
            new YardManagerHomeFrm(user).setVisible(true);
        }
        else if(e.getSource() == btnSearch){
            this.dispose();
            new SearchProviderFrm(user).setVisible(true);
        }
    }
    
    
    
    
}
