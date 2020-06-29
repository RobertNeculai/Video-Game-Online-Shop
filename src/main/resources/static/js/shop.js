window.shop = {
    API_URL: "http://localhost:8082",

    getProducts: function () {
        $.ajax({
            url: shop.API_URL + "/products",
            method: "GET"
        }).done(function (response) {
            console.log(response);
            shop.displayProducts(response.content);
        })

    },
    getCustomerID: function (productId) {
        let userId = localStorage.getItem("userId");
        $.ajax({
            url: shop.API_URL + "/customers/userid/" + userId,
            method: "GET",
            contentType: "application/json"
        }).done(function (response) {
            shop.addProductToCart(response.id, productId)

        })
    },
    addProductToCart: function (customerId, productId) {
        let request = {
            customerId: customerId,
            productsIds: [productId]
        };
        $.ajax({
            url: shop.API_URL + "/carts",
            method: "PUT",
            contentType: "application/json",
            data: JSON.stringify(request)
        }).done(function () {
            location.reload();
        })
    },
    displayProducts: function (products) {
        let productsHtml = '';
        products.forEach(product => productsHtml += shop.getHtmlForOneProduct(product));

        $('.single-product-area .row:first-child').html(productsHtml);
    },
    getHtmlForOneProduct: function (product) {

        return ` <div class="col-md-3 col-sm-6">
                    <div class="single-shop-product">
                        <div class="product-upper">
                        </div>
                        <h1><img src="${product.imageUrl}" width="250px" height="150px" alt=""></a>
                        <h2><a href="">${product.name}</a></h2>
                        <span>Genre: ${product.genre}</span>
                        <p>
                        </p>
                        <label>Description: ${product.description}</label>
                       <div class="fa-star" style="color: orange;" 
                        <div class="product-carousel-price">
                            <ins>Price: $${product.salesPrice}</ins> <del></del>
                        </div>  
                        
                        <div class="product-option-shop">
                            <button  class="add_to_cart_button " data-quantity="1" data-product_sku="" data-product_id=${product.id} rel="nofollow" href="#">Add to cart</button>
                        </div>                       
                    </div>
                </div>`;
    },
    bindEvents: function () {
        $('.single-product-area').delegate('.add_to_cart_button', 'click', function (event) {
            event.preventDefault();
            let productId = $(this).data('product_id');
            shop.getCustomerID(productId);
        })
    },
};
shop.getProducts();
shop.bindEvents();