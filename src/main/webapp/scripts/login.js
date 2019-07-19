function onLoginResponse() {
    if (this.status === OK) {
        const user = JSON.parse(this.responseText);
        setAuthorization(user);
        if (hasAuthorization()) {
            showContents(['mainPage']);
            //onLoadProfile(getAuthorization());

        }
    }else if(this.status === UNAUTHORIZED){
        alert("Your email address or password was incorrect!");
    }
}

function onLoginButtonClicked(){
    const loginFormEl = document.forms['loginForm'];
    const emailInputEl = loginFormEl.querySelector('input[name="email"]');
    const passwordInputEl = loginFormEl.querySelector('input[name="psw"]');

    const email = emailInputEl.value;
    const password = passwordInputEl.value;

    const params = new URLSearchParams();
    params.append('email', email);
    params.append('password', password);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onLoginResponse);
    xhr.open('POST', 'login');
    xhr.send(params);
}

function onSignUpResponse() {
    if (this.status === OK) {
        showContents(['login-content', 'welcome-content']);
    } else if (this.status === BAD_REQUEST) {
        alert("You've provided invalid data");
    }
}

function onSubmitButtonClicked() {
    const signUpFormEl = document.forms['signUpForm'];
    const emailInputEl = signUpFormEl.querySelector('input[name="email"]');
    const forenameInputEl = signUpFormEl.querySelector('input[name="forename"]');
    const lastNameInputEl = signUpFormEl.querySelector('input[name="lastName"]');
    const passwordInputEl = signUpFormEl.querySelector('input[name="psw"]');
    const cityInputEl = signUpFormEl.querySelector('input[name="city"]');
    const postalCodeInputEl = signUpFormEl.querySelector('input[name="postalCode"]');
    const addressInputEl = signUpFormEl.querySelector('input[name="address"]');
    const IDCardNumberInputEl = signUpFormEl.querySelector('input[name="IDCardNumber"]');

    const email = emailInputEl.value;
    const forename = forenameInputEl.value;
    const lastName = lastNameInputEl.value;
    const password = passwordInputEl.value;
    const city = cityInputEl.value;
    const postalCode = postalCodeInputEl.value;
    const address = addressInputEl.value;
    const IDCardNumber = IDCardNumberInputEl.value;

    const params = new URLSearchParams();
    params.append('email', email);
    params.append('forename', forename);
    params.append('lastName', lastName);
    params.append('password', password);
    params.append('city', city);
    params.append('postalCode', postalCode);
    params.append('address', address);
    params.append('IDCardNumber', IDCardNumber);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onSignUpResponse);
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