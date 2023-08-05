<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
  <title>TODO supply a title</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="http://localhost:8080/demo_war_exploded/w3.css">

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
    <a href="login.jsp" class="w3-bar-item w3-button w3-padding-large w3-hide-small">LOGIN</a>
    <a href="register.jsp" class="w3-bar-item w3-button w3-padding-large w3-hide-small">REGISTER</a>
  </div>
</div>
<div class="container">
  <form action="servlet1" method="POST">

    <h2>Welcome to Ebank</h2>


    <label>Enter the Account Number</label>
    <input class="w3-input w3-border" type="text" name="accno" class="form-control" placeholder="AccNo">


    <label>Pin No</label>
    <input class="w3-input w3-border" type="password" name="pinno" class="form-control" placeholder="PinNo">

    <input  class="w3-button w3-black w3-margin-bottom" type="submit" class="btn btn-success">Submit</input>

    <br>
    <a href="register.jsp">register</a>

  </form>
</div>
</body>
</html>