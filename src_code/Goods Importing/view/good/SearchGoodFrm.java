package view.good;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import model.User;
import dao.GoodDAO;
import dao.InvoicesDAO;
import java.util.ArrayList;
import model.InvoicesGood;
import model.Good;
import model.Invoices;
import view.invoices.InvoiceFrm;
import view.provider.SearchProviderFrm;

public class SearchGoodFrm extends JFrame implements ActionListener {
    private User user;
    private String providerID;
    private String providerName;
    private JTextField txtSearch, txtUnitPrice, txtQuantity;
    private JButton btnSearch, btnAdd, btnBack, btnAddToInvoice, btnSubmitandFinish;
    private JTable tblListSearchGood, tblTempInvoice;
    private ArrayList<InvoicesGood>goodList;
    private DefaultTableModel tblModel, tempModel;

    public SearchGoodFrm(User user, String providerID, String providerName) {
        super("Search Good");
        this.user = user;
        this.providerID = providerID;
        this.providerName = providerName;

        // === Top panel: Username + Back ===
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel lblUsername = new JLabel(user.getUsername());
        btnBack = new JButton("Back");
        btnBack.addActionListener(this);
        topPanel.add(lblUsername, BorderLayout.WEST);
        topPanel.add(btnBack, BorderLayout.EAST);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // === Search input panel ===
        JPanel searchPanel = new JPanel();
        txtSearch = new JTextField(20);
        btnSearch = new JButton("Search");
        btnSearch.addActionListener(this);
        searchPanel.add(new JLabel("Good name:"));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        // === Table and model ===
        tblModel = new DefaultTableModel();
        tblModel.addColumn("Goods ID");
        tblModel.addColumn("Name");
        tblModel.addColumn("Provider ID");

        tblListSearchGood = new JTable(tblModel);
        JScrollPane scrollPane = new JScrollPane(tblListSearchGood);
        scrollPane.setPreferredSize(new Dimension(700, 200));

        // === Input for price and quantity ===
        JPanel inputPanel = new JPanel();
        txtUnitPrice = new JTextField(6);
        txtQuantity = new JTextField(6);
        btnAddToInvoice = new JButton("AddToInvoice");
        btnAddToInvoice.addActionListener(this);
        inputPanel.add(new JLabel("Unit Price:"));
        inputPanel.add(txtUnitPrice);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(txtQuantity);
        inputPanel.add(btnAddToInvoice);

        // === Table for temporary invoice ===
        tempModel = new DefaultTableModel();
        tempModel.addColumn("Goods ID");
        tempModel.addColumn("Name");
        tempModel.addColumn("Unit Price");
        tempModel.addColumn("Quantity");
        tempModel.addColumn("Total");

        tblTempInvoice = new JTable(tempModel);
        JScrollPane tempScrollPane = new JScrollPane(tblTempInvoice);
        tempScrollPane.setPreferredSize(new Dimension(700, 150));

        // === Add Button ===
        btnAdd = new JButton("Add");
        btnAdd.addActionListener(this);
        btnSubmitandFinish = new JButton("Finish");
        btnSubmitandFinish.addActionListener(this);
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnAdd);
        btnPanel.add(btnSubmitandFinish);

        // === Main content layout (vertical stacking) ===
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(searchPanel);
        mainPanel.add(scrollPane);
        mainPanel.add(inputPanel);
        mainPanel.add(tempScrollPane);
        mainPanel.add(btnPanel);

        // === Main layout ===
        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(mainPanel, BorderLayout.CENTER);

        this.setSize(800, 650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            this.dispose();
            new SearchProviderFrm(user).setVisible(true);
        } else if (e.getSource() == btnSearch) {
            btnSearchClick();
        } else if (e.getSource() == btnAdd) {
            btnAddClick();
        } else if (e.getSource() == btnAddToInvoice) {
            int row = tblListSearchGood.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng hóa!");
                return;
            }
            try {
                String goodsID = tblListSearchGood.getValueAt(row, 0).toString();
                String name = tblListSearchGood.getValueAt(row, 1).toString();
                int price = Integer.parseInt(txtUnitPrice.getText().trim());
                int quantity = Integer.parseInt(txtQuantity.getText().trim());
                long total = price * quantity;

                tempModel.addRow(new Object[]{goodsID, name, price, quantity, total});

                txtUnitPrice.setText("");
                txtQuantity.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Đơn giá và số lượng phải là số hợp lệ.");
            }
        }
        else if(e.getSource() == btnSubmitandFinish){
            System.out.println(this.user.getUsersID());
            if(tempModel.getRowCount() == 0){
                JOptionPane.showMessageDialog(this,"Chưa có mặt hàng được thêm vào hóa đơn");
            }
            int confirm = JOptionPane.showConfirmDialog(this,"Bạn có chắc đã thêm đầy đủ hàng hóa","Xác nhận",JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION){
                ArrayList<InvoicesGood> goodList = new ArrayList<>();
                for(int i = 0;i < tempModel.getRowCount();i++){
                    String id = tempModel.getValueAt(i,0).toString();
                    String name = tempModel.getValueAt(i,1).toString();
                    int price = Integer.parseInt(tempModel.getValueAt(i,2).toString());
                    int quantity = Integer.parseInt(tempModel.getValueAt(i,3).toString());
                    goodList.add(new InvoicesGood(id,quantity,price));
                }
               
                InvoicesDAO dao = new InvoicesDAO();
                String invoiceID = dao.generateInvoiceID();
                Invoices invoice = new Invoices(invoiceID,this.user.getUsersID(),goodList,providerID);
                
                if(dao.addInvoice(invoice)){
                    JOptionPane.showMessageDialog(this,"Thêm hóa đơn thành công");
                    this.dispose();
                    new InvoiceFrm(invoice,this.user).setVisible(true);
                }
                
                else{
                    JOptionPane.showMessageDialog(this,"Thêm hóa đơn thất bại");
                }
            }
        }
    }

    public void btnSearchClick() {
        String key = txtSearch.getText().trim();
        if (key.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin");
            return;
        }
        tblModel.setRowCount(0);

        GoodDAO dao = new GoodDAO();
        Good[] list = dao.SearchGood(key);

        if (list != null && list.length > 0) {
            boolean found = false;
            for (Good g : list) {
                if (g.getProviderID().equals(providerID)) {
                    Vector<String> row = new Vector<>();
                    row.add(g.getGoodsID());
                    row.add(g.getName());
                    row.add(g.getProviderID());
                    tblModel.addRow(row);
                    found = true;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(this, "Không có hàng hóa nào thuộc nhà cung cấp này.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy hàng hóa");
        }
    }

    public void btnAddClick() {
        this.dispose();
        new AddGoodFrm(user, providerName, providerID).setVisible(true);
    }
}
