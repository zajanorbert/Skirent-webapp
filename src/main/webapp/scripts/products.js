let productsList;

function adminMenu() {
    signInButtonEl.addEventListener('click', showDropdown);
}

function loadProducts() {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', showProducts);
    xhr.open('GET', 'products');
    xhr.send();
}



function onElementPress(id) {

    const params = new URLSearchParams();
    params.append('id', id);

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', showProducts);
    xhr.open('POST', 'Sport');
    xhr.send(params);
}

function showProducts() {
    productsList = JSON.parse(this.responseText);
    console.log(productsList);
    while (productsDivEl.hasChildNodes()){
        productsDivEl.removeChild(productsDivEl.firstChild);
    }
    for(let i = 0; i < productsList.length; i++){
        const product = productsList[i];
        const productDivEl = document.createElement('div');
        productDivEl.classList.add('product');
        productDivEl.setAttribute('id', product.id);
        const pic = document.createElement('img');
        pic.setAttribute('src', product.pic);
        const brand = document.createElement('p');
        brand.textContent = product.brand;
        const NR = document.createElement('p');
        NR.textContent = product.nr;

        productDivEl.appendChild(pic);
        productDivEl.appendChild(brand);
        productDivEl.appendChild(NR);
        productsDivEl.appendChild(productDivEl);
    }
}