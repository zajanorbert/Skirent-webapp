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
    <c:url value="/scripts/products.js" var="productsScriptUrl"/>
    <script src="${indexScriptUrl}"></script>
    <script src="${loginScriptUrl}"></script>
    <script src="${productsScriptUrl}"></script>
</head>
<body>
<div id="mainPage" class="content">
    <div id="topNav" class="topnav">
        <a href="javascript:void(0)" onclick="openNav()">sports</a>
        <%--<a id="signInButton" href="javascript:void(0)">sign in/sign up</a>--%>
        <div class="dropdown">
            <button id="signInButton" class="dropbtn" >sign in/sign up
            </button>
            <div class="dropdown-content" id="myDropdown">
                <a id="addItemButton">Add item</a>
                <a id="seeStorageButton">See storage</a>
                <a id="idkButton">Fuck this shit</a>
            </div>
        </div>
        <a id="salesButton">sales</a>
        <a id="newsButton">news</a>
        <a id="cartA" style="padding: 0">
            <button id="shoppingCart"><i class="fa fa-shopping-cart"></i></button>
        </a>
        <a id="logoutButton" class="active" href="javascript:void(0)">logout</a>
    </div>
    <div id="sideNav" class="sidenav">
        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
        <a id="ski">Ski</a>
        <a id="snowboard">Snowboard</a>
        <a id="sled">Sled</a>
    </div>
    <div id="products" class="products">
    </div>
</div>
<div id="signUpContent" class="hidden content modal">
    <form class="modal-content animate" id="signUpForm" action="/action_page.php" onsubmit="return false;">
        <div class="imgcontainer"><span style="width: 2%; float: right" id="closeSignUpButton" title="Close PopUp"
                                        class="close">&times;</span>
            <input type="text" name="IDCardNumber" placeholder="ID card number" maxlength="10">
            <input type="text" name="forename" placeholder="Forename" required>
            <input type="text" name="lastName" placeholder="LastName" required>
            <input type="text" name="email" placeholder="Email address" required>
            <input type="password" placeholder="Password" name="psw"
                   pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" required
                   title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters">
            <input type="text" name="country" placeholder="Country" required>
            <input type="text" name="city" placeholder="City" required>
            <input type="text" name="zipCode" placeholder="Zip code" required>
            <input type="text" name="address" placeholder="Address" title="Name,nature,number" required>
            <br>
            <button id="signUpButton" class="button">SignUp</button>
            <p>If u forgot, u already have an account, click and get back to login!</p>
            <button id="backToLoginButton" class="button">Back To Login</button>
        </div>
    </form>
</div>
<div id="loginContent" class="hidden content modal">
    <form class="modal-content animate" id="loginForm" action="/action_page.php" onsubmit="return false;">
        <div class="imgcontainer"><span style="width: 2%; float: right" id="closeLoginButton" title="Close PopUp"
                                        class="close">&times;</span>
            <p><input type="text" name="email" placeholder="Email" required></p>
            <p><input type="password" name="psw" placeholder="Password" required></p>
            <button id="loginButton" class="button">Login</button>
            <p>Don't have an account, but u want? Sign Up for us!</p>
            <button id="toSignUpButton" class="button">SignUp</button>
        </div>
    </form>
</div>
</body>
</html>
