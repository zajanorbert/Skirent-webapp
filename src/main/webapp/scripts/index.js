const OK = 200;
const BAD_REQUEST = 400;
const UNAUTHORIZED = 401;
const NOT_FOUND = 404;
const INTERNAL_SERVER_ERROR = 500;

let mainPageDivEl;
let profileContentDivEl;
let loginContentDivEl;

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

function onCloseProfileButtonClicked() {
    showContents(['mainPage']);
}

function onLoginButtonClicked() {
    showContents(['mainPage', 'loginContent']);
}

function onLoad() {
    mainPageDivEl = document.getElementById('mainPage');
    profileContentDivEl = document.getElementById('profileContent');
    loginContentDivEl = document.getElementById('loginContent');

    const closeProfileButtonEl = document.getElementById('closeLoginButton');
    closeProfileButtonEl.addEventListener('click', onCloseProfileButtonClicked);

    const loginButtonEl = document.getElementById('loginButton');
    loginButtonEl.addEventListener('click', onLoginButtonClicked);
}

document.addEventListener('DOMContentLoaded', onLoad);