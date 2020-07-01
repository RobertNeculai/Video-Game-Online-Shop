function submitReview(productId,content,rating) {
    let requestBody={
        content:content,
        rating:rating
    }
    $.ajax({
        url: "http://localhost:8082" + "/reviews/" + productId,
        method: "POST",
        contentType: "application/json",
        data:JSON.stringify(requestBody)
    }).done(function () {
        location.reload();
    })

}