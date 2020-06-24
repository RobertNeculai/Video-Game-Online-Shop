window.shop = {
    API_URL: "http://localhost:8082",


    getUserSession: function () {
        $.ajax({
            url: shop.API_URL +"/user" +"/user",
            method: "GET",
        }).done(function (response) {
            shop.getUserById(response);

        })
    },

    getUserById: function (id) {
        $.ajax({
            url: shop.API_URL +"/user" +"/"+ id,
            method: "GET"
        }).done(function (user) {
            console.log(user);
            $('.user-menu').append(shop.displayUserName(user));

        });
    },

    displayUserName: function (user) {
        console.log(this.name,this.id);
        return `<li  class="fa fa-user" data-id=${user.id}>${user.username}</></li>`
    }

};
shop.getUserSession();