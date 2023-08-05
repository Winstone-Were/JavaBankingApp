<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Transfer Funds</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://localhost:8080/demo_war_exploded/w3.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f0f0f0;
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .container {
      background-color: #fff;
      padding: 20px;
      box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
      width: 300px;
    }

    h2 {
      text-align: center;
      margin-bottom: 20px;
    }

    label {
      display: block;
      font-weight: bold;
      margin-bottom: 5px;
    }

    input[type="text"],
    input[type="password"] {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      margin-bottom: 15px;
    }

    input[type="submit"] {
      width: 100%;
      padding: 10px;
      background-color: #4CAF50;
      color: white;
      border: none;
      cursor: pointer;
    }

    input[type="submit"]:hover {
      background-color: #45a049;
    }
  </style>
</head>
<body>
  <div class="w3-top">
    <div class="w3-bar w3-black w3-card">
      <a class="w3-bar-item w3-button w3-padding-large w3-hide-medium w3-hide-large w3-right" href="javascript:void(0)" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
      <a href="index.jsp" class="w3-bar-item w3-button w3-padding-large">HOME</a>
      <a href="deposit.jsp" class="w3-bar-item w3-button w3-padding-large w3-hide-small">DEPOSIT</a>
      <a href="show_deposits" class="w3-bar-item w3-button w3-padding-large w3-hide-small">SHOW DEPOSITS</a>
      <a href="show_transactions" class="w3-bar-item w3-button w3-padding-large w3-hide-small">SHOW TRANSACTIONS</a>
    </div>
  </div>
  <div class="container">
    <h2>TRANSFER FUNDS</h2>
    <form action="transactServlet" method="post" >
      <label for="toAcc">To Account Number</label>
      <input class="w3-input w3-border" type="text" id="toAcc" name="toAccNo" required>
      <label for="amount">Amount</label>
      <input class="w3-input w3-border" type="text" id="amount" name="amount" required>
      <input class="w3-button w3-black w3-margin-bottom" type="submit" value="TRANSFER">
    </form>
  </div>
</body>
</html>
