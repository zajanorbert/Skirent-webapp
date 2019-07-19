<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <title>Slippery Ski rental</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="style.css" media="all">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <c:url value="/scripts/index.js" var="indexScriptUrl"/>
    <c:url value="/scripts/login.js" var="loginScriptUrl"/>
    <script src="${indexScriptUrl}"></script>
    <script src="${loginScriptUrl}"></script>
</head>
<body>
<div id="mainPage" class="content">
    <div id="topNav" class="topnav">
        <a href="javascript:void(0)" onclick="openNav()">sports</a>
        <a id="signInButton" href="javascript:void(0)">sign in/sign up</a>
        <a>sales</a>
        <a>news</a>
        <a style="padding: 0">
            <button id="shoppingCart"><i class="fa fa-shopping-cart"></i></button>
        </a>
        <a id="logoutButton" class="active">logout</a>
    </div>
    <div id="sideNav" class="sidenav">
        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
        <a>Ski</a>
        <a>Snowboard</a>
        <a>Sled</a>
    </div>
</div>
<div id="signUpContent" class="hidden content modal">
    <form class="modal-content animate" id="signUpForm" action="/action_page.php">
        <div class="imgcontainer"><span style="width: 2%; float: right" id="closeSignUpButton" title="Close PopUp"
                                        class="close">&times;</span>
            <input type="text" name="IDCardNumber" placeholder="ID card number">
            <input type="text" name="forename" placeholder="Forename" required>
            <input type="text" name="lastName" placeholder="LastName" required>
            <input type="text" name="email" placeholder="Email address" required>
            <input type="password" placeholder="Password" name="psw"
                   pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required
                   title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters">
            <input type="text" name="city" placeholder="City" required>
            <input type="text" name="postalCode" placeholder="Postal code" required>
            <input type="text" name="address" placeholder="Address" title="Name,nature,number" required>
            <button id="signUpButton">SignUp</button>
            <p>If u forgot, u already have an account, click and get back to login!</p>
            <button id="backToLoginButton">Back To Login</button>
        </div>
    </form>
</div>
<div id="loginContent" class="hidden content modal">
    <form class="modal-content animate" id="loginForm" action="/action_page.php">
        <div class="imgcontainer"><span style="width: 2%; float: right" id="closeLoginButton" title="Close PopUp"
                                        class="close">&times;</span>
            <input type="text" name="email" placeholder="Email" required>
            <input type="password" name="psw" placeholder="Password" required>
            <button id="loginButton">Login</button>
            <p>Don't have an account, but u want? Sign Up for us!</p>
            <button id="toSignUpButton">SignUp</button>
        </div>
    </form>
</div>
</body>
</html>
