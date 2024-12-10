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
import java.time.format.DateTimeFormatter;

public class AddSpendServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String dateStr = request.getParameter("date");
        LocalDate localDate = LocalDate.parse(dateStr, DateTimeFormatter.ISO_DATE);
        java.sql.Date date = java.sql.Date.valueOf(localDate);
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");
        Expenses expense = new Expenses(0, name, amount, date, categoryID, user.getUserID());
        ExpensesDAO expensesDAO = new ExpensesDAO();
        expensesDAO.addExpense(expense);
        response.sendRedirect("LoadCategoriesWithAmountServlet");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
