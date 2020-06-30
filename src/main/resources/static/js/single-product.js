window.singleProduct= {
    API_URL: "http://localhost:8082",

    getProduct: function () {
        let productId = localStorage.getItem("prodId");
        $.ajax({
            url: singleProduct.API_URL + "/products/" + productId,
            method: "GET"
        }).done(function (response) {
            console.log(response);
            singleProduct.displayProducts(response);
        })
    },
    displayProducts: function (product) {
        let productsHtml = '';
        productsHtml = singleProduct.getHtmlForOneProduct(product);

        $('.single-product-area .row .col-md-8').replaceWith(productsHtml);
    },
    getHtmlForOneProduct: function (product) {
        let price;
        if (product.salesPrice !== product.price)
            price = product.price;
        return ` <div class="col-md-8">
                    <div class="product-content-right">
                        <div class="product-breadcroumb">
                            <a href="/index">Home</a>
                            <a >${product.name}</a>
                        </div>
                        
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="product-images">
                                    <div class="product-main-img">
                                        <img src="${product.imageUrl}" alt="">
                                        <a>Rating: ${product.averageRating}/10</a>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-sm-6">
                                <div class="product-inner">
                                    <h2 class="product-name">${product.name}</h2>
                                    <div class="product-inner-price">
                                        <ins>$${product.salesPrice}</ins> <del ${isNaN(price) ? "hidden" : ""}>${price}</del>
                                    </div>    
                                    
                                    <form action="" class="cart">
                                        <div class="quantity">
                                            <input type="number" size="4" class="input-text qty text" title="Qty" value="1" name="quantity" min="1" step="1">
                                        </div>
                                        <button class="add_to_cart_button" type="submit">Add to cart</button>
                                    </form>   
                                    
                                    <div class="product-inner-category">
                                        <p>Genre: <a href="">${product.genre}</a>. Tags: <a href="">awesome</a></p>
                                    </div> 
                                    
                                    <div role="tabpanel">
                                        <ul class="product-tab" role="tablist">
                                            <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Description</a></li>
                                        </ul>
                                        <div class="tab-content">
                                            <div role="tabpanel" class="tab-pane fade in active" id="home">
                                                <h2>Product Description</h2>  
                                                <p>${product.description}</p>

                                                <p></p>
                                            </div>
                                                <div class="submit-review">
                                                    <div class="rating-chooser">
                                                        <p>Your rating</p>

                                                        <div class="rating-wrap-post">
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                            <i class="fa fa-star"></i>
                                                        </div>
                                                    </div>
                                                    <p><label for="review">Your review</label> <textarea name="review" id="" cols="30" rows="10"></textarea></p>
                                                    <p><input type="submit" value="Submit"></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                        `;
    },
};
singleProduct.getProduct();