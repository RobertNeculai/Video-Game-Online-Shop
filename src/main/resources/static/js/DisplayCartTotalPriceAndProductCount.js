window.DisplayCartAll={
    API_URL: "http://localhost:8082",
    getCartContent: function (customerId) {
        $.ajax({
            url: DisplayCartAll.API_URL + "/carts/" + customerId,
        }).done(function (response) {
            DisplayCartAll.displayCartContent(response.products);

        })

    },
    getCustomerID: function () {
        let userId = localStorage.getItem("userId");
        $.ajax({
            url: DisplayCartAll.API_URL + "/customers/userid/" + userId,
            method: "GET",
            contentType: "application/json"
        }).done(function (response) {
            DisplayCartAll.getCartContent(response.id)

        })
    },
    displayCartContent: function (products) {
        let htmlContent = "";
        let TotalPrice = 0;
        let ProductCount=0;
        products.forEach(product => TotalPrice += product.price * product.quantity);
        products.forEach(product => ProductCount+=1);
        htmlContent=this.displayCartTotalandNumberofProducts(TotalPrice,ProductCount);
        $('.shopping-item').html(htmlContent);


    },
    displayCartTotalandNumberofProducts:function(TotalPrice,ProductCount){

        return `<div class="shopping-item">
                        <a href="/CartPage">My Cart - <span class="cart-amunt">$ ${TotalPrice}</span> <i class="fa fa-shopping-cart"></i> <span class="product-count">${ProductCount}</span></a>
                    </div>`
    }
}
DisplayCartAll.getCustomerID();