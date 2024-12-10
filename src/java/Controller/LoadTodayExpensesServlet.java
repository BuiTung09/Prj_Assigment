/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.ExpensesDAO;
import Model.Expenses;
import Model.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

public class LoadTodayExpensesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        if (user != null) {
            ExpensesDAO expensesDAO = new ExpensesDAO();
            LocalDate today = LocalDate.now();
            java.sql.Date date = java.sql.Date.valueOf(today);
            List<Expenses> todayExpenses = expensesDAO.getTodayExpenses(user.getUserID(), date);
            request.setAttribute("todayExpenses", todayExpenses);
        }
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
