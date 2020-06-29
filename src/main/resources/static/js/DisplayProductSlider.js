window.ProductSlider = {
    API_URL: "http://localhost:8082",

    getProducts: function () {
        $.ajax({
            url: ProductSlider.API_URL + "/products",
            method: "GET"
        }).done(function (response) {
            ProductSlider.displayProducts(response.content);
        })

    },
    displayProducts: function (products) {
        let productsHtml = '';
        products.forEach(product => productsHtml += ProductSlider.displayProductsOnSlider(product));
        $('#bxslider-home4').html(productsHtml);
    },
    displayProductsOnSlider: function (product) {

        return `<li>
                <img src=${product.imageUrl}  alt="Slide">
                <div class="caption-group">
                    <h2 class="caption title">
                        ${product.name} <span class="primary">${product.genre}</span>
                    </h2>
                    <h4 class="caption subtitle">Dual SIM</h4>
                    <a class="caption button-radius" href="#"><span class="icon"></span>Shop now</a>
                </div>
                </li>`

    },
    getLastestProducts: function () {
        $.ajax({
            url: ProductSlider.API_URL + "/products",
            method: "GET"
        }).done(function (response) {
            ProductSlider.displayLatestProducts(response.content);
        })
    },
    displayLatestProducts: function (products) {
        let productsHtml = '';
        products.forEach(product => productsHtml += ProductSlider.LastestProductsView(product));

        $('.product-carousel').html(productsHtml);
    },
    LastestProductsView: function (product) {
        return `<div class="single-product">
                            <div class="product-f-image">
                                <img src=${product.imageUrl} width="1100px" height="950px" alt="">
                                <div class="product-hover">
                                    <a href="#" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> Add to cart</a>
                                    <a href="single-product.html" class="view-details-link"><i class="fa fa-link"></i> See details</a>
                                </div>
                            </div>

                            <h2><a href="single-product.html">${product.name}</a>
                            <div>Genre: ${product.genre}</div>
          

                            <div class="product-carousel-price">
                                <ins>$ ${product.salesPrice}</ins>
                            </div>
                            </h2>
                        </div>`

    }
};

ProductSlider.getProducts();
ProductSlider.getLastestProducts();