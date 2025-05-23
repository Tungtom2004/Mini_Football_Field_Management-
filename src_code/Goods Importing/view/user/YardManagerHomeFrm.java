/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.user;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.User;
public class YardManagerHomeFrm extends JFrame implements ActionListener{
    private User user;
    private JButton btnLogOut;
    private JButton btnImporting;
    
    public YardManagerHomeFrm(User user){
        super("Yard Manager Home");
        this.user = user;
        
        // top left: Username, + Logout: Right
        JPanel topPanel = new JPanel(new BorderLayout()); 
        JLabel lblUsername = new JLabel(user.getUsername());
        lblUsername.setFont(new Font("Segoe UI",Font.PLAIN,14));
        btnLogOut = new JButton("Log Out"); 
        btnLogOut.addActionListener(this);
        topPanel.add(lblUsername,BorderLayout.WEST);
        topPanel.add(btnLogOut,BorderLayout.EAST); 
        topPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        
        // Center title
        JLabel lblTitle = new JLabel("Yard Manager Home");
        lblTitle.setFont(new Font("Segoe UI",Font.BOLD,24));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER); 
        
        
        //Bottom: Manage Importing button
        btnImporting = new JButton("Manage Importing");
        btnImporting.setFont(new Font("Segoe UI",Font.PLAIN,16));
        btnImporting.setPreferredSize(new Dimension(250,50));
        btnImporting.addActionListener(this);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(btnImporting);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        
        
        //Main layout
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
        if(e.getSource() == btnLogOut){
            btnLogOutClick();
        }
        else if(e.getSource() == btnImporting){
            btnImportingClick(); 
        }
    }
    
    public void btnLogOutClick(){
        int confirm = JOptionPane.showConfirmDialog(this,"Bạn có chắc muốn đăng xuất không?","Xác nhận",JOptionPane.YES_NO_OPTION); 
        if(confirm == JOptionPane.YES_OPTION){
            this.dispose();
            new LoginFrm().setVisible(true);
        }
        
    }
    
    public void btnImportingClick(){
        new YardImportingFrm(user).setVisible(true);
        this.dispose();
    }
    
}
