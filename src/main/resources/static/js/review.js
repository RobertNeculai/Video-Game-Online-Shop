window.Review={
    API_URL: "http://localhost:8082",

    getReviews: function () {
        let productId = localStorage.getItem("prodId");
        $.ajax({
            url: singleProduct.API_URL + "/reviews/" + productId,
            method: "GET"
        }).done(function (response) {
            console.log(response);
            Review.displayReviews(response.content);
        })
    },
    displayReviews: function (reviews) {
        let reviewHtml = '<h style="border: #2a6496 solid; font-size: 30px; align-content: center;background-color: dodgerblue">Reviews</h>';
        reviews.forEach(review => reviewHtml += Review.getHtmlForOneReview(review));

        $('.product-images').append(reviewHtml);
    },
        getHtmlForOneReview(review) {
    return `<p></p>
<div class="clear" ></div>
<div class="help-block" <p></p><label for="review" >${review.content}</label> <p>
                                                    Rating: ${review.rating}/5</p> </div>`
}
};
Review.getReviews();