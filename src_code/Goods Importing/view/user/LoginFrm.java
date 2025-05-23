/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.user;  
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import dao.UserDAO;
import model.User;

public class LoginFrm extends JFrame implements ActionListener{
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin; 
    
    public LoginFrm(){
        super("Login");
        
        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);
        txtPassword.setEchoChar('*'); 
        btnLogin = new JButton("Login");
        
        Font font = new Font("Segoe UI",Font.PLAIN,18);
        txtUsername.setFont(font);
        txtPassword.setFont(font);
        btnLogin.setFont(font); 
        
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain,BoxLayout.PAGE_AXIS));
        pnMain.add(Box.createRigidArea(new Dimension(0,10)));
        
        JLabel lblHome = new JLabel("Yard Manager Login");
        lblHome.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblHome.setFont(lblHome.getFont().deriveFont(20.0f));
        pnMain.add(lblHome);
        pnMain.add(Box.createRigidArea(new Dimension(0,20))); 
        
        JPanel pnUsername = new JPanel(new FlowLayout());
        pnUsername.add(new JLabel("Username:"));
        pnUsername.add(txtUsername);
        pnMain.add(pnUsername);
        
        JPanel pnPass = new JPanel(new FlowLayout());
        pnPass.add(new JLabel("Password:")); 
        pnPass.add(txtPassword);
        pnMain.add(pnPass); 
        
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.addActionListener(this);
        pnMain.add(btnLogin); 
        pnMain.add(Box.createRigidArea(new Dimension(0,10))); 
        
        
        this.setSize(650,400);
        this.setLocation(300,150);
        this.setContentPane(pnMain);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if((e.getSource() instanceof JButton) && ((JButton) e.getSource()).equals(btnLogin)){
            
            if(txtUsername.getText().isEmpty() || txtPassword.getPassword().length == 0){
                JOptionPane.showMessageDialog(this,"Vui lòng nhập thông tin và mật khẩu");
                return; 
            }
            
            User user = new User();
            user.setUsername(txtUsername.getText().trim());
            user.setPassword(new String(txtPassword.getPassword()).trim());
            
            UserDAO ud = new UserDAO();
            
            if(ud.checkLogin(user)){
                JOptionPane.showMessageDialog(this,"Đăng nhập thành công");
                new YardManagerHomeFrm(user).setVisible(true);
                this.dispose();
                                
            }
            else{
                JOptionPane.showMessageDialog(this,"Sai tên đăng nhập hoặc mật khẩu");
            }
            
        }
    }
    
    
}
