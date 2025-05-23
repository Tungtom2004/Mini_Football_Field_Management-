package view.good;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import dao.ProviderDAO;
import dao.GoodDAO;
import model.Provider;
import model.Good;
import model.User;
import view.good.SearchGoodFrm;

public class AddGoodFrm extends JFrame implements ActionListener {
    private User user;
    private String providerID;
    private String providerName;
    private JTextField txtGoodsID, txtName;
    private JComboBox<String> cbProvider;
    private JButton btnSubmit, btnBack;
    private HashMap<String, String> providerMap; // map tên → ID

    public AddGoodFrm(User user, String providerID, String providerName) {
        super("Add Good");
        this.user = user;
        this.providerID = providerID;
        this.providerName = providerName;
        this.setLayout(new BorderLayout(10, 10));

        Font font = new Font("Segoe UI", Font.PLAIN, 14);
        providerMap = new HashMap<>();

        // === Fields ===
        txtGoodsID = new JTextField(20);
        txtName = new JTextField(20);
        cbProvider = new JComboBox<>();
        txtGoodsID.setFont(font);
        txtName.setFont(font);
        cbProvider.setFont(font);
        btnSubmit = new JButton("Submit");
        btnSubmit.setFont(font);
        btnBack = new JButton("Back");
        btnBack.setFont(font);
        btnSubmit.addActionListener(this);
        btnBack.addActionListener(this);

        // Load providers
        ProviderDAO dao = new ProviderDAO();
        Provider[] providers = dao.SearchProvider("");
        for (Provider p : providers) {
            cbProvider.addItem(p.getName());
            providerMap.put(p.getName(), p.getProviderID()); // name -> id
        }

        cbProvider.setSelectedItem(providerName);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.anchor = GridBagConstraints.WEST;
        int row = 0;

        // Row 1 - Goods ID
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("Goods ID:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(txtGoodsID, gbc);

        // Row 2 - Name
        gbc.gridx = 0; gbc.gridy = ++row;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(txtName, gbc);

        // Row 3 - Provider
        gbc.gridx = 0; gbc.gridy = ++row;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Provider:"), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(cbProvider, gbc);

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnSubmit);
        btnPanel.add(btnBack);

        this.add(formPanel, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);

        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            this.dispose();
            new SearchGoodFrm(user, providerID, providerName).setVisible(true);
        } else if (e.getSource() == btnSubmit) {
            String goodsID = txtGoodsID.getText().trim();
            String name = txtName.getText().trim();
            String providerNameSelected = (String) cbProvider.getSelectedItem();
            String providerIDSelected = providerMap.get(providerNameSelected);

            if (goodsID.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đẩy đủ thông tin");
                return;
            }

            Good good = new Good(goodsID, name, providerIDSelected);
            GoodDAO dao = new GoodDAO();
            if (dao.AddGood(good)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                this.dispose();
                new SearchGoodFrm(user, providerIDSelected, providerNameSelected).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại!");
            }
        }
    }
}
