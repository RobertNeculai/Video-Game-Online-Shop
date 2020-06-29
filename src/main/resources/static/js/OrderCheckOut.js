window.OrderCheckout={
    API_URL: "http://localhost:8082",

    getCartContent: function (customerId) {
        $.ajax({
            url: OrderCheckout.API_URL + "/carts/" + customerId,
        }).done(function (response) {
            OrderCheckout.displayCartContent(response.products);

        })

    },
    getCustomerID: function () {
        let userId = localStorage.getItem("userId");
        $.ajax({
            url: OrderCheckout.API_URL + "/customers/userid/" + userId,
            method: "GET",
            contentType: "application/json"
        }).done(function (response) {
            OrderCheckout.getCartContent(response.id)

        })
    },
    displayCartContent: function (products) {
        let htmlContent = "";
        let htmlPriceContent = "";
        let TotalPrice = 0;
        products.forEach(product => TotalPrice += product.price * product.quantity);
        products.forEach(product => htmlContent += OrderCheckout.getHtmlForOneProduct(product));
        htmlPriceContent=OrderCheckout.displayCheckOutOrder(TotalPrice);
        $('#order_review_heading').html(htmlPriceContent);
        $('.shop_table').append(htmlContent);


    },
    getHtmlForOneProduct: function (product) {
        let pricePerType = product.price * product.quantity;
        return `<tbody>
                                            <tr class="cart_item">
                                                <td class="product-name">${product.name}
                                                     <strong class="product-quantity">× ${product.quantity}</strong> </td>
                                                <td class="product-total">
                                                    <span class="amount">$ ${pricePerType}</span> </td>
                                            </tr>
                                        </tbody>`

    },
    displayCheckOutOrder:function (TotalPrice) {
        return`<div id="order_review" style="position: relative;">
                                    <table class="shop_table">
                                        <thead>
                                            <tr>
                                                <th class="product-name">Products</th>
                                                <th class="product-total">Total</th>
                                            </tr>
                                        </thead>
                                        <tfoot>

                                            <tr class="cart-subtotal">
                                                <th>Cart Subtotal</th>
                                                <td><span class="amount">$ ${TotalPrice}</span>
                                                </td>
                                            </tr>

                                            <tr class="shipping">
                                                <th>Shipping and Handling</th>
                                                <td>

                                                    Free Shipping
                                                    <input type="hidden" class="shipping_method" value="free_shipping" id="shipping_method_0" data-index="0" name="shipping_method[0]">
                                                </td>
                                            </tr>


                                            <tr class="order-total">
                                                <th>Order Total</th>
                                                <td><strong><span class="amount">$ ${TotalPrice}</span></strong> </td>
                                            </tr>

                                        </tfoot>
                                    </table> `

    }
};
OrderCheckout.getCustomerID();