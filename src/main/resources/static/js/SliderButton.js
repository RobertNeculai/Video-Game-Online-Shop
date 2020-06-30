function navigateToSingleProduct(productID) {
    localStorage.setItem("prodId",productID);
    location.replace("/single-product");

}