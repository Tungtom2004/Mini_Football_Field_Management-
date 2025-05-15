/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.User;
import view.user.YardImportingFrm;

public class SearchFrm extends JFrame implements ActionListener{
    private User user;
    private JButton btnBack;
    private JButton btnSearchProvider;
    private JButton btnSearchGood;
    
    public SearchFrm(User user){
        super("Search");
        this.user = user;
        
        // top bar: Username + Back
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel lblUsername = new JLabel(user.getUsername());
        lblUsername.setFont(new Font("Segoe UI",Font.PLAIN,14));
        
        btnBack = new JButton("Back");
        btnBack.addActionListener(this);
        
        topPanel.add(lblUsername,BorderLayout.WEST);
        topPanel.add(btnBack,BorderLayout.EAST);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        
        JLabel lblTitle = new JLabel("Search");
        lblTitle.setFont(new Font("Segoe UI",Font.BOLD,26));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER); 
        
        btnSearchProvider = new JButton("Search Provider");
        btnSearchGood = new JButton("Search Good");
        
        btnSearchProvider.setFont(new Font("Segoe UI",Font.PLAIN,16));
        btnSearchGood.setFont(new Font("Segoe UI",Font.PLAIN,16));
        
        btnSearchProvider.setPreferredSize(new Dimension(180,50));
        btnSearchGood.setPreferredSize(new Dimension(180,50));
        
        btnSearchProvider.addActionListener(this);
        btnSearchGood.addActionListener(this);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,40,10));
        buttonPanel.add(btnSearchProvider);
        buttonPanel.add(btnSearchGood);
        
        //main layout
        this.setLayout(new BorderLayout());
        this.add(topPanel,BorderLayout.NORTH);
        this.add(lblTitle,BorderLayout.CENTER);
        this.add(buttonPanel,BorderLayout.SOUTH);
        
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == btnBack){
            this.dispose();
            new YardImportingFrm(user).setVisible(true);
        }
        
        else if(e.getSource() == btnSearchProvider){
            JOptionPane.showMessageDialog(this,"Search Provider clicked");
            this.dispose();
        }
        
        else if(e.getSource() == btnSearchGood){
            JOptionPane.showMessageDialog(this,"Search Good clicked");
        }
        
    }
}
