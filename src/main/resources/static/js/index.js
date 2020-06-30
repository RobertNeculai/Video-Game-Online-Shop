window.index = {
    API_URL: "http://localhost:8082",


    getUserSession: function () {
        $.ajax({
            url: index.API_URL +"/user" +"/user",
            method: "GET",
        }).done(function (response) {
            index.getUserById(response);

        })
    },

    getUserById: function (id) {
        $.ajax({
            url: index.API_URL +"/user" +"/"+ id,
            method: "GET"
        }).done(function (user) {
            $('.user-menu').append(index.displayUserName(user));

        });
    },

    displayUserName: function (user) {
        localStorage.setItem("userId",user.id);
        return `<li  class="fa fa-user" style="color: black" data-user-id=${user.id}>${user.username}</li>`
    },
    test:function () {
        console.log(123);

    }
};
index.getUserSession();