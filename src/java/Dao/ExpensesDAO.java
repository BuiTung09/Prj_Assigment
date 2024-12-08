package Dao;

import Model.Categories;
import Model.Expenses;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpensesDAO extends DBContext {

    public void addExpense(Expenses expense) {
        String sql = "INSERT INTO Expenses (ExpenseName, Amount, ExpenseDate, CategoryID, UserID) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, expense.getExpenseName());
            ps.setDouble(2, expense.getAmount());
            ps.setDate(3, expense.getExpenseDate());
            ps.setInt(4, expense.getCategoryID());
            ps.setInt(5, expense.getUserID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateExpense(Expenses expense) {
        String sql = "UPDATE Expenses SET ExpenseName = ?, Amount = ?, ExpenseDate = ?, CategoryID = ? WHERE ExpenseID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, expense.getExpenseName());
            ps.setDouble(2, expense.getAmount());
            ps.setDate(3, expense.getExpenseDate());
            ps.setInt(4, expense.getCategoryID());
            ps.setInt(5, expense.getExpenseID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteExpense(int expenseID) {
        String sql = "DELETE FROM Expenses WHERE ExpenseID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, expenseID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Expenses> getTodayExpenses(int userID, Date date) {
        List<Expenses> expensesList = new ArrayList<>();
        String sql = "SELECT e.*, c.CategoryName FROM Expenses e "
                + "JOIN Categories c ON e.CategoryID = c.CategoryID "
                + "WHERE e.UserID = ? AND e.ExpenseDate = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userID);
            ps.setDate(2, date);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Expenses expense = new Expenses(rs.getInt("ExpenseID"), rs.getString("ExpenseName"), rs.getDouble("Amount"), rs.getDate("ExpenseDate"), rs.getInt("CategoryID"), userID);
                    expense.setCategoryName(rs.getString("CategoryName"));
                    expensesList.add(expense);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return expensesList;
    }

    public List<Categories> getTotalAmountByCategory(int userID) {
        List<Categories> categoriesList = new ArrayList<>();
        String sql = "SELECT c.CategoryID, c.CategoryName, COALESCE(SUM(e.Amount), 0) AS TotalAmount " 
                + "FROM Categories c " 
                + "LEFT JOIN Expenses e ON c.CategoryID = e.CategoryID AND e.UserID = ? " 
                + "GROUP BY c.CategoryID, c.CategoryName";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Categories category = new Categories(rs.getInt("CategoryID"), rs.getString("CategoryName"));
                    category.setTotalAmount(rs.getDouble("TotalAmount"));
                    categoriesList.add(category);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return categoriesList;
    }

}
