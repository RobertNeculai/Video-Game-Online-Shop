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
        $('#slider').html(productsHtml);

        $('#slider').bxSlider();
    },
    displayProductsOnSlider: function (product) {
        return `<li>
                <img src=${product.imageUrl}  alt="Slide">
                <div class="caption-group">
                    <h2 class="caption title">
                        ${product.name} <span class="primary">${product.genre}</span>
                    </h2>
                    <h4 class="price">Just $${product.salesPrice}</h4>
                    <button class="add_to_cart_button" rel="nofollow"  id="button1" onclick="navigateToSingleProduct(${product.id});"
                   ><span class="icon"></span>Shop now</button>
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
                                </div>
                            </div>

                            <h2><a href="" class="product" data-id=${product.id}>${product.name}</a>
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

