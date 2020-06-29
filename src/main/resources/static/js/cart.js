window.Cart = {
    API_URL: "http://localhost:8082",

    getCartContent: function (customerId) {
        $.ajax({
            url: Cart.API_URL + "/carts/" + customerId,
        }).done(function (response) {
            Cart.displayCartContent(response.products);

        })

    },
    getCustomerID: function () {
        let userId = localStorage.getItem("userId");
        $.ajax({
            url: Cart.API_URL + "/customers/userid/" + userId,
            method: "GET",
            contentType: "application/json"
        }).done(function (response) {
            Cart.getCartContent(response.id)

        })
    },
    displayCartContent: function (products) {
        let htmlContent = "";
        let htmlPriceContent = "";
        let TotalPrice = 0;
        products.forEach(product => TotalPrice += product.price * product.quantity);
        products.forEach(product => htmlContent += Cart.getHtmlForOneProduct(product));
        htmlPriceContent+=Cart.getTotalOrder(TotalPrice);
        $('table.shop_table.cart').html(htmlContent);
        $('table.TotalPrice').html(htmlPriceContent);


    },
    getHtmlForOneProduct: function (product) {
        let pricePerType = product.price * product.quantity;
        return `<tr class="cart_item">
                <td class="product-remove">
                    <a title="Remove this item" class="remove" href="#">×</a>   
                <td class="product-name">
                    <a href="single-product.html">${product.name}</a> 
                      
                <td class="product-quantity">
                    <div class="quantity buttons_added">
                        <input type="button" class="minus" value="-">
                        <input type="number" size="4" class="input-text qty text" title="Qty" value="${product.quantity}" min="0" step="1">
                        <input type="button" class="plus" value="+">
                    </div>
                    <td class="product-price">
                    <span class="amount">$ ${pricePerType}</span> 
            
                                            </td>
                    </tr>`

    },
    getTotalOrder: function (TotalPrice) {
    return `<tr class="order-total">
            <th>Order Total: </th>
        <td><strong><span class="amount">$ ${TotalPrice}</span></strong> </td>
        </tr>`
    },
};
Cart.getCustomerID();