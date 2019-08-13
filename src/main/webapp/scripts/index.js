const OK = 200;
const BAD_REQUEST = 400;
const UNAUTHORIZED = 401;
const NOT_FOUND = 404;
const INTERNAL_SERVER_ERROR = 500;

let mainPageDivEl;
let profileContentDivEl;
let loginContentDivEl;
let signUpContentDivEl;
let signInButtonEl;
let logoutButtonEl;


function hasAuthorization() {
    return localStorage.getItem('user') !== null;
}

function setAuthorization(user) {
    return localStorage.setItem('user', JSON.stringify(user));
}

function getAuthorization() {
    return JSON.parse(localStorage.getItem('user'));
}

function setUnauthorized() {
    return localStorage.removeItem('user');
}

function newInfo(targetEl, message) {
    newMessage(targetEl, 'info', message);
}

function newError(targetEl, message) {
    newMessage(targetEl, 'error', message);
}

function newMessage(targetEl, cssClass, message) {
    clearMessages();

    const pEl = document.createElement('p');
    pEl.classList.add('message');
    pEl.classList.add(cssClass);
    pEl.textContent = message;

    targetEl.appendChild(pEl);
}

function clearMessages() {
    const messageEls = document.getElementsByClassName('message');
    for (let i = 0; i < messageEls.length; i++) {
        const messageEl = messageEls[i];
        messageEl.remove();
    }
}

function showContents(ids) {
    const contentEls = document.getElementsByClassName('content');
    for (let i = 0; i < contentEls.length; i++) {
        const contentEl = contentEls[i];
        if (ids.includes(contentEl.id)) {
            contentEl.classList.remove('hidden');
        } else {
            contentEl.classList.add('hidden');
        }
    }
}

function onNetworkError(response) {
    document.body.remove();
    const bodyEl = document.createElement('body');
    document.appendChild(bodyEl);
    newError(bodyEl, 'Network error, please try reloading the page');
}

function onOtherResponse(targetEl, xhr) {
    if (xhr.status === NOT_FOUND) {
        newError(targetEl, 'Not found');
        console.error(xhr);
    } else {
        const json = JSON.parse(xhr.responseText);
        if (xhr.status === INTERNAL_SERVER_ERROR) {
            newError(targetEl, `Server error: ${json.message}`);
        } else if (xhr.status === UNAUTHORIZED || xhr.status === BAD_REQUEST) {
            newError(targetEl, json.message);
        } else {
            newError(targetEl, `Unknown error: ${json.message}`);
        }
    }
}

function openNav() {
    document.getElementById("sideNav").style.width = "200px";
}

function closeNav() {
    document.getElementById("sideNav").style.width = "0";
}
function onCloseButtonClicked() {
    showContents(['mainPage']);
}

function onSignInButtonClicked() {
    showContents(['mainPage', 'loginContent']);
}

function addLogout(user) {
    signInButtonEl.textContent = user.forename;
    signInButtonEl.removeEventListener('click', onSignInButtonClicked);
    signInButtonEl.removeAttribute('href');
    logoutButtonEl.style.display= "block";
}

function logout() {
    signInButtonEl.textContent ="sign in/sign up";
    signInButtonEl.addEventListener('click', onSignInButtonClicked);
    signInButtonEl.setAttribute('href', 'javascript:void(0)');
    logoutButtonEl.style.display= "none";
}

function onLoad() {
    mainPageDivEl = document.getElementById('mainPage');
    profileContentDivEl = document.getElementById('profileContent');
    loginContentDivEl = document.getElementById('loginContent');
    signUpContentDivEl = document.getElementById('signUpContent');
    logoutButtonEl = document.getElementById('logoutButton');

    const closeLoginButtonEl = document.getElementById('closeLoginButton');
    closeLoginButtonEl.addEventListener('click', onCloseButtonClicked);

    const closeSignUpButtonEl = document.getElementById('closeSignUpButton');
    closeSignUpButtonEl.addEventListener('click', onCloseButtonClicked);

    const toSignUpButtonEl = document.getElementById('toSignUpButton');
    toSignUpButtonEl.addEventListener('click', onToSignUpButtonClicked);

    signInButtonEl = document.getElementById('signInButton');
    signInButtonEl.addEventListener('click', onSignInButtonClicked);

    const loginButtonEl = document.getElementById('loginButton');
    loginButtonEl.addEventListener('click', onLoginButtonClicked);


    logoutButtonEl.addEventListener('click', onLogoutButtonClicked);
}

document.addEventListener('DOMContentLoaded', onLoad);