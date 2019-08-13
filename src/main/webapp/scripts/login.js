function onLoginResponse() {
    if (this.status === OK) {
        const user = JSON.parse(this.responseText);
        setAuthorization(user);
        if (hasAuthorization()) {
            showContents(['mainPage']);
            addLogout(user);
        }
    }else if(this.status === UNAUTHORIZED){
        alert("Your email address or password was incorrect!");
    }
}

function onLoginButtonClicked(){
    const loginFormEl = document.forms['loginForm'];
    const email = loginFormEl.querySelector('input[name="email"]').value;
    const password = loginFormEl.querySelector('input[name="psw"]').value;

    const params = new URLSearchParams();
    params.append('email', email);
    params.append('password', password);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onLoginResponse);
    xhr.open('POST', 'login');
    xhr.send(params);
}


function onSubmitButtonClicked() {
    const signUpFormEl = document.forms['signUpForm'];
    const IDCardNumber = signUpFormEl.querySelector('input[name="IDCardNumber"]');
    const forename = signUpFormEl.querySelector('input[name="forename"]').value;
    const lastName = signUpFormEl.querySelector('input[name="lastName"]').value;
    const email = signUpFormEl.querySelector('input[name="email"]').value;
    const password = signUpFormEl.querySelector('input[name="psw"]').value;
    const country = signUpFormEl.querySelector('input[name="country"]').value;
    const city = signUpFormEl.querySelector('input[name="city"]').value;
    const zip = signUpFormEl.querySelector('input[name="zipCode"]').value;
    const address = signUpFormEl.querySelector('input[name="address"]').value;

    const params = new URLSearchParams();
    params.append('IDCardNumber', IDCardNumber);
    params.append('forename', forename);
    params.append('lastName', lastName);
    params.append('email', email);
    params.append('password', password);
    params.append('country', country);
    params.append('city', city);
    params.append('zipCode', zip);
    params.append('address', address);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onLoginResponse);
    xhr.open('POST', 'signup');
    xhr.send(params);

}

function backToLoginButtonClicked() {
    showContents(['loginContent', 'mainPage']);
}

function onToSignUpButtonClicked() {
    showContents(['signUpContent', 'mainPage']);

    const backToLoginButtonEl = document.getElementById('backToLoginButton');
    backToLoginButtonEl.addEventListener('click', backToLoginButtonClicked);

    const submitButtonEl = document.getElementById('signUpButton');
    submitButtonEl.addEventListener('click', onSubmitButtonClicked);
}

function onLogoutResponse() {
    if (this.status === OK) {
        setUnauthorized();
        clearMessages();
        logout();
    } else {
        onOtherResponse(loginContentDivEl, this)
    }
}

function onLogoutButtonClicked() {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onLogoutResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'logout');
    xhr.send();

}