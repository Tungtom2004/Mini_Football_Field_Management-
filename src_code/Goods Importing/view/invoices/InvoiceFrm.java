package view.invoices;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.*;
import dao.InvoicesDAO;
import java.util.List;
import view.provider.SearchProviderFrm; 

public class InvoiceFrm extends JFrame implements ActionListener {
    private Invoices invoice;
    private User user;
    private JTable tblInvoiceDetails;
    private DefaultTableModel tableModel;
    private JButton btnSubmit;

    public InvoiceFrm(Invoices invoice,User user) {
        super("Invoice Details");
        this.invoice = invoice;
        this.user = user;

        // Labels for invoice info
        JLabel lblInvoiceID = new JLabel("Invoice ID: " + invoice.getInvoicesID());
        JLabel lblUserID = new JLabel("Created by: " + invoice.getUsersID());
        
        String providerID = invoice.getProviderID() != null ? invoice.getProviderID() : "N/A";
        JLabel lblProviderID = new JLabel("Provider ID: " + providerID);

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.add(lblInvoiceID);
        infoPanel.add(lblUserID);
        infoPanel.add(lblProviderID);

        // Table setup
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Goods ID");
        tableModel.addColumn("Unit Price");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Total");

        List<InvoicesGood> goodsList = invoice.getListGood();
        for (InvoicesGood g : goodsList) {
            long total = g.getUnitPrice() * g.getQuantity();
            tableModel.addRow(new Object[]{g.getGoodsID(), g.getUnitPrice(), g.getQuantity(), total});
        }

        tblInvoiceDetails = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblInvoiceDetails);

        // Submit button
        btnSubmit = new JButton("Submit Invoice");
        btnSubmit.addActionListener(this);
        JPanel submitPanel = new JPanel();
        submitPanel.add(btnSubmit);

        // Layout setup
        this.setLayout(new BorderLayout());
        this.add(infoPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(submitPanel, BorderLayout.SOUTH);

        this.setSize(700,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            InvoicesDAO dao = new InvoicesDAO();
            if (dao.updatePaymentStatus(invoice.getInvoicesID())) {
                JOptionPane.showMessageDialog(this, "Nhập vào hóa đơn thành công!");
                this.dispose();
                new SearchProviderFrm(user).setVisible(true);
                
            } else {
                JOptionPane.showMessageDialog(this, "Nhập vào hóa đơn thất bại!");
            }
        }
    }
}
