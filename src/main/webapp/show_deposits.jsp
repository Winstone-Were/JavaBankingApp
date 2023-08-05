<%--
  Created by IntelliJ IDEA.
  User: stonie
  Date: 7/23/23
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Deposit History</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://localhost:8080/demo_war_exploded/w3.css">
    <style>
        /* Same styling as deposit.jsp */
        /* ... (add your custom styles here if needed) ... */
    </style>
</head>
<body>
<div class="w3-top">
    <div class="w3-bar w3-black w3-card">
        <a class="w3-bar-item w3-button w3-padding-large w3-hide-medium w3-hide-large w3-right" href="javascript:void(0)" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
        <a href="index.jsp" class="w3-bar-item w3-button w3-padding-large">HOME</a>
        <a href="deposit.jsp" class="w3-bar-item w3-button w3-padding-large w3-hide-small">DEPOSIT</a>
        <a href="transact.jsp" class="w3-bar-item w3-button w3-padding-large w3-hide-small">SEND MONEY </a>
        <a href="show_transactions" class="w3-bar-item w3-button w3-padding-large w3-hide-small">SHOW TRANSACTIONS</a>
    </div>
</div>
<div class="container">
    <h2>Deposits History</h2>
    <table class="w3-table w3-striped w3-bordered">
        <tr>
            <th>Date</th>
            <th>Amount</th>
        </tr>
        ${depositTable} <!-- This will be replaced with the actual deposit table from the servlet -->
    </table>
</div>
</body>
</html>
