function add(customerId,productId) {
    let request={
        customerId:customerId,
        productsIds:[productId]
    }
    $.ajax({
        url: Cart.API_URL + "/carts",
        method: "PUT",
        contentType: "application/json",
        data:JSON.stringify(request)
    }).done(function () {
       location.reload();

    })
}
function remove(customerId,productId) {
    let request={
        customerId:customerId,
        productsIds:[productId]
    }
    $.ajax({
        url: Cart.API_URL + "/carts",
        method: "DELETE",
        contentType: "application/json",
        data:JSON.stringify(request)
    }).done(function () {
        location.reload();

    })
}
