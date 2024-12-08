<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Expense Tracker</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f9f9f9;
            }

            header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px 20px;
                background-color: #444;
                color: white;
            }

            header .auth {
                display: flex;
                gap: 10px;
            }

            header .auth a {
                color: white;
                text-decoration: none;
                padding: 5px 10px;
                background-color: #555;
                border-radius: 5px;
            }

            .container {
                padding: 20px;
            }

            .quick-actions, .categories {
                margin-bottom: 20px;
            }

            h1 {
                margin-bottom: 20px;
            }

            .flex {
                display: flex;
                gap: 20px;
            }

            .quick-actions button {
                display: block;
                width: 200px;
                padding: 10px;
                margin-bottom: 10px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .quick-actions button:hover {
                background-color: #0056b3;
            }

            .categories {
                display: flex;
                flex-wrap: wrap;
                gap: 10px;
            }

            .category {
                flex: 1;
                max-width: 150px;
                padding: 10px;
                text-align: center;
                border: 1px solid #ddd;
                border-radius: 5px;
                background-color: white;
            }

            .table {
                width: 100%;
                border-collapse: collapse;
                background-color: white;
            }

            .table th, .table td {
                border: 1px solid #ddd;
                padding: 10px;
                text-align: left;
            }

            .table th {
                background-color: #f2f2f2;
            }

            .table tr:hover {
                background-color: #f9f9f9;
            }

        </style>
    </head>
    <body>
        <header>
            <div>Welcome To Expense Management</div>
            <div class="auth">
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <a href="LogoutServlet">Logout</a>
                    </c:when>
                    <c:otherwise>
                        <a href="login.jsp">Login</a>
                        <a href="signup.jsp">Signup</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </header>
        <div class="container">
            <h1>Expense Management</h1>
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <h2>Welcome, ${sessionScope.user.username}!</h2>
                </c:when>
            </c:choose>
            <div class="flex">
                <div class="quick-actions">
                    <h2>Quick Actions</h2>
                    <hr>
                    <button onclick="location.href = 'LoadCategoriesServlet?type=shopping'">Add new Shopping spend</button>
                    <button onclick="location.href = 'LoadCategoriesServlet?type=food'">Add new Food spend</button>
                    <button onclick="location.href = 'LoadCategoriesServlet?type=education'">Add new Education spend</button>
                    <button onclick="location.href = 'LoadCategoriesServlet?type=bills'">Add new Bills & Utilities spend</button>
                    <button onclick="location.href = 'LoadCategoriesServlet?type=health'">Add new Health & Wellness spend</button>
                    <button onclick="location.href = 'LoadCategoriesServlet?type=entertainment'">Add new Entertainment spend</button>
                </div>
                <div class="categories">
                    <hr>
                    <h2>Categories :</h2>
                    <div class="category-columns">
                        <div class="column">
                            <h3>Shopping</h3>
                            <p>$<fmt:formatNumber value="${category[0].totalAmount}" type="currency"/></p>
                        </div>
                        <div class="column">
                            <h3>Food</h3>
                            <p>$<fmt:formatNumber value="${category[1].totalAmount}" type="currency"/></p>
                        </div>
                        <div class="column">
                            <h3>Education</h3>
                            <p>$<fmt:formatNumber value="${category[2].totalAmount}" type="currency"/></p>
                        </div>
                        <div class="column">
                            <h3>Bills & Utilities</h3>
                            <p>$<fmt:formatNumber value="${category[3].totalAmount}" type="currency"/></p>
                        </div>
                        <div class="column">
                            <h3>Health & Wellness</h3>
                            <p>$<fmt:formatNumber value="${category[4].totalAmount}" type="currency"/></p>
                        </div>
                        <div class="column">
                            <h3>Entertainment</h3>
                            <p>$<fmt:formatNumber value="${category[5].totalAmount}" type="currency"/></p>
                        </div>
                    </div>
                </div>
            </div>
            <div>
                <h2>Today</h2>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Amount</th>
                            <th>Date</th>
                            <th>Category</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="expense" items="${todayExpenses}">
                            <tr>
                                <td>${expense.getExpenseName()}</td>
                                <td>$<fmt:formatNumber value="${expense.getAmount()}" type="currency"/></td>
                                <td><fmt:formatDate value="${expense.getExpenseDate()}" pattern="MMMM d, yyyy"/></td>
                                <td>${expense.getCategoryName()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
