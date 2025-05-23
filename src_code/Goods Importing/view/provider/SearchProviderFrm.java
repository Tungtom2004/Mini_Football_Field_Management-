package view.provider;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;
import model.User;
import dao.ProviderDAO;
import model.Provider;
import view.user.YardImportingFrm;
import view.good.SearchGoodFrm;

public class SearchProviderFrm extends JFrame implements ActionListener {
    private User user;
    private JTextField txtSearch;
    private JButton btnSearch, btnAdd, btnBack;
    private JTable tblListSearchProvider;
    private DefaultTableModel tblModel;

    public SearchProviderFrm(User user) {
        super("Search Provider");
        this.user = user;

        // Top: username + back button
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel lblUsername = new JLabel(user.getUsername());
        btnBack = new JButton("Back");
        btnBack.addActionListener(this);
        topPanel.add(lblUsername, BorderLayout.WEST);
        topPanel.add(btnBack, BorderLayout.EAST);

        // Search bar
        JPanel searchPanel = new JPanel();
        txtSearch = new JTextField(20);
        btnSearch = new JButton("Search");
        btnSearch.addActionListener(this);
        searchPanel.add(new JLabel("Provider name:"));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        // Table
        tblModel = new DefaultTableModel();
        tblModel.addColumn("providerID");
        tblModel.addColumn("providerName");
        tblModel.addColumn("address");
        tblModel.addColumn("email");
        tblModel.addColumn("phone");
        tblModel.addColumn("description");
        tblModel.addColumn("Action");

        tblListSearchProvider = new JTable(tblModel);
        JScrollPane scrollPane = new JScrollPane(tblListSearchProvider);

        // Add button
        btnAdd = new JButton("Add");
        btnAdd.addActionListener(this);
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnAdd);

        // Layout
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);

        this.setSize(800, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tblListSearchProvider.getColumn("Action").setCellRenderer(new ButtonRenderer());
        tblListSearchProvider.getColumn("Action").setCellEditor(new ButtonEditor(new JCheckBox(), tblListSearchProvider, user, this));

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            this.dispose();
            new YardImportingFrm(user).setVisible(true);
        } else if (e.getSource() == btnSearch) {
            btnSearchClick();
        } else if (e.getSource() == btnAdd) {
            this.dispose();
            new AddProviderFrm(user).setVisible(true);
        }
    }

    public void btnSearchClick() {
        String key = txtSearch.getText().trim();
        if (key.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Yêu cầu nhập đầy đủ thông tin!");
            return;
        }
        tblModel.setRowCount(0);

        ProviderDAO dao = new ProviderDAO();
        Provider[] list = dao.SearchProvider(key);

        if (list != null && list.length > 0) {
            for (Provider p : list) {
                Vector<Object> row = new Vector<>();
                row.add(p.getProviderID());
                row.add(p.getName());
                row.add(p.getAddress());
                row.add(p.getEmail());
                row.add(p.getPhone());
                row.add(p.getDescription());
                row.add("View Good");
                tblModel.addRow(row);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy nhà cung cấp!");
        }
    }
}


class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "View Good" : value.toString());
        return this;
    }
}


class ButtonEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean clicked;
    private JTable table;
    private User user;
    private JFrame frame;

    public ButtonEditor(JCheckBox checkBox, JTable table, User user, JFrame frame) {
        super(checkBox);
        this.table = table;
        this.user = user;
        this.frame = frame;

        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
        boolean isSelected, int row, int column) {
        label = (value == null) ? "View Good" : value.toString();
        button.setText(label);
        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            int selectedRow = table.getSelectedRow();
            String providerID = table.getValueAt(selectedRow, 0).toString();
            String providerName = table.getValueAt(selectedRow, 1).toString();

            new SearchGoodFrm(user, providerID, providerName).setVisible(true);
            frame.dispose();
        }
        clicked = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }
}
