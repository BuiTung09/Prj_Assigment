package Dao;

import Model.Categories;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDAO extends DBContext {

    public List<Categories> getAllCategories() {
        List<Categories> categoriesList = new ArrayList<>();
        String sql = "SELECT * FROM Categories";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Categories category = new Categories(rs.getInt("CategoryID"), rs.getString("CategoryName"));
                categoriesList.add(category);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return categoriesList;
    }
}
