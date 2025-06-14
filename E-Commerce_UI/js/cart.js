let cart = JSON.parse(localStorage.getItem("cart")) || [];
function loadCart() {
    let cart = JSON.parse(localStorage.getItem("cart")) || [];
    let cartItems = document.getElementById("cart-items");
    let totalAmount = 0;
    cartItems.innerHTML = "";

    cart.forEach((item, index) => {
        let itemTotal = item.price * item.quantity;
        totalAmount += itemTotal;

        cartItems.innerHTML += `
            <tr>
                <td> <img src="${item.imageUrl}" width="50"></td>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td>
                     <button class="btn btn-sm btn-secondary" onclick="changeQuantity(${index},-1)">-</button>
                     ${item.quantity}
                    <button class="btn btn-sm btn-secondary" onclick="changeQuantity(${index},1)">+</button>
                </td>
                <td>₹ ${itemTotal}</td>
                <td>
                    <button class="btn btn-danger btn-sm" onclick="removeFromCart(${index})">
                        <svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="20" height="20" viewBox="0 0 30 30">
                        <path d="M 14.984375 2.4863281 A 1.0001 1.0001 0 0 0 14 3.5 L 14 4 L 8.5 4 A 1.0001 1.0001 0 0 0 7.4863281 5 L 6 5 A 1.0001 1.0001 0 1 0 6 7 L 24 7 A 1.0001 1.0001 0 1 0 24 5 L 22.513672 5 A 1.0001 1.0001 0 0 0 21.5 4 L 16 4 L 16 3.5 A 1.0001 1.0001 0 0 0 14.984375 2.4863281 z M 6 9 L 7.7929688 24.234375 C 7.9109687 25.241375 8.7633438 26 9.7773438 26 L 20.222656 26 C 21.236656 26 22.088031 25.241375 22.207031 24.234375 L 24 9 L 6 9 z"></path>
                       </svg>
                    </button>
                 </td>
            </tr>
        `;
    });
    document.getElementById("total-amount").innerText = totalAmount;

    // Save totalAmount to localStorage
    localStorage.setItem("totalAmount", totalAmount);

}

function addToCart(id, name, price, imageUrl) {
    console.log("Adding product to cart :", id, name, price, imageUrl)

    price = parseFloat(price);
    let itemIndex = cart.findIndex((item) => item.id === id);
    if (itemIndex !== -1) {
        cart[itemIndex].quantity += 1;
    }
    else {
        cart.push({
            id: id,
            name: name,
            price: price,
            imageUrl: imageUrl,
            quantity: 1
        });
    }
    localStorage.setItem("cart", JSON.stringify(cart))
    updateCartCounter();
}

function updateCartCounter() {
    document.querySelector(".cart-badge").innerText = cart.length;
}

function changeQuantity(index, change) {
    let cart = JSON.parse(localStorage.getItem("cart")) || [];
    cart[index].quantity += change;
    if (cart[index].quantity <= 0) cart.splice(index, 1)
    localStorage.setItem("cart", JSON.stringify(cart));
    loadCart();
    updateCartCounter();
}

function removeFromCart(index) {
    let cart = JSON.parse(localStorage.getItem("cart")) || [];
    cart.splice(index, 1);
    localStorage.setItem("cart", JSON.stringify(cart));
    loadCart();
    updateCartCounter();
}

document.addEventListener("DOMContentLoaded", loadCart)
document.addEventListener("DOMContentLoaded",updateCartCounter)