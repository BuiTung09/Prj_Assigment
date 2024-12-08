/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.*;

public class Expenses {
    private int ExpenseID;
    private String ExpenseName;
    private Double Amount;
    private Date ExpenseDate;

    public Expenses() {
    }

    public Expenses(int ExpenseID, String ExpenseName, Double Amount, Date ExpenseDate) {
        this.ExpenseID = ExpenseID;
        this.ExpenseName = ExpenseName;
        this.Amount = Amount;
        this.ExpenseDate = ExpenseDate;
    }

    public int getExpenseID() {
        return ExpenseID;
    }

    public void setExpenseID(int ExpenseID) {
        this.ExpenseID = ExpenseID;
    }

    public String getExpenseName() {
        return ExpenseName;
    }

    public void setExpenseName(String ExpenseName) {
        this.ExpenseName = ExpenseName;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double Amount) {
        this.Amount = Amount;
    }

    public Date getExpenseDate() {
        return ExpenseDate;
    }

    public void setExpenseDate(Date ExpenseDate) {
        this.ExpenseDate = ExpenseDate;
    }
    
}
