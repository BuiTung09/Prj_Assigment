package Dao;

import Model.Expenses;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    // Các phương thức khác như getExpenses, updateExpense, deleteExpense...
}
