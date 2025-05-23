    package view.provider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.Provider;
import dao.ProviderDAO;
import model.User;

public class AddProviderFrm extends JFrame implements ActionListener {
    private User user;
    private JTextField txtID, txtName, txtAddress, txtEmail, txtPhone;
    private JTextArea txtDescription;
    private JButton btnSubmit, btnBack;

    public AddProviderFrm(User user) {
        super("Add Provider");
        this.user = user;

        
        Font font = new Font("Segoe UI", Font.PLAIN, 14);

        
        txtID = new JTextField(20);
        txtName = new JTextField(20);
        txtAddress = new JTextField(20);
        txtEmail = new JTextField(20);
        txtPhone = new JTextField(20);
        txtDescription = new JTextArea(3, 20);
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);

        
        txtID.setFont(font);
        txtName.setFont(font);
        txtAddress.setFont(font);
        txtEmail.setFont(font);
        txtPhone.setFont(font);
        txtDescription.setFont(font);

        
        btnSubmit = new JButton("Submit");
        btnBack = new JButton("Back");
        btnSubmit.setFont(font);
        btnBack.setFont(font);
        btnSubmit.addActionListener(this);
        btnBack.addActionListener(this);

        
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;

        formPanel.add(new JLabel("Provider ID:"), gbc);
        gbc.gridx = 1; formPanel.add(txtID, gbc);
        gbc.gridx = 0; gbc.gridy = ++row;

        formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; formPanel.add(txtName, gbc);
        gbc.gridx = 0; gbc.gridy = ++row;

        formPanel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1; formPanel.add(txtAddress, gbc);
        gbc.gridx = 0; gbc.gridy = ++row;

        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; formPanel.add(txtEmail, gbc);
        gbc.gridx = 0; gbc.gridy = ++row;

        formPanel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1; formPanel.add(txtPhone, gbc);
        gbc.gridx = 0; gbc.gridy = ++row;

        formPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        JScrollPane scroll = new JScrollPane(txtDescription);
        formPanel.add(scroll, gbc);

        // === Button panel ===
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnSubmit);
        btnPanel.add(btnBack);

        
        this.setLayout(new BorderLayout(10, 10));
        this.add(formPanel, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);
        this.setBorderForPanel(formPanel);

        this.setSize(450, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void setBorderForPanel(JPanel panel) {
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            this.dispose();
            new SearchProviderFrm(user).setVisible(true);
        } 
        else if (e.getSource() == btnSubmit) {
            String providerID = txtID.getText().trim();
            String name = txtName.getText().trim();
            if(providerID.isEmpty() || name.isEmpty()){
                JOptionPane.showMessageDialog(this,"Vui lòng nhập đẩy đủ thông tin");
                return; 
            }
            Provider provider = new Provider(
                txtID.getText().trim(),
                txtName.getText().trim(),
                txtAddress.getText().trim(),
                txtEmail.getText().trim(),
                txtPhone.getText().trim(),
                txtDescription.getText().trim()
            );

            ProviderDAO dao = new ProviderDAO();
            if (dao.AddProvider(provider)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                this.dispose();
                new SearchProviderFrm(user).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại!");
            }
        }
    }
}
