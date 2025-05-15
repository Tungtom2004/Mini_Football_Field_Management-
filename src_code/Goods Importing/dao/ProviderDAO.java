package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Provider; 

public class ProviderDAO extends DAO {

    public ProviderDAO() {
        super();
    }

    public Provider[] SearchProvider(String key) {
        ArrayList<Provider> result = new ArrayList<>();
        String sql = "SELECT * FROM tblProviders WHERE name LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Provider p = new Provider(
                    rs.getString("providerID"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("description")
                );
                result.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toArray(new Provider[0]);
    }

    public boolean AddProvider(Provider provider) {
        String sql = "INSERT INTO tblProviders(providerID, name, address, email, phone, description) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, provider.getProviderID());
            ps.setString(2, provider.getName());
            ps.setString(3, provider.getAddress());
            ps.setString(4, provider.getEmail());
            ps.setString(5, provider.getPhone());
            ps.setString(6, provider.getDescription());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
