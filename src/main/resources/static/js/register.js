window.Register = {
    API_URL: "http://localhost:8082/user",

    matchingPassword: function () {
        $('#password, #confirmPassword').on('keyup', function () {
            if ($("#password").val() === $("#confirmPassword").val()) {
                $('#message').html('Password is matching').css('color', 'green');
            } else
                $('#message').html('Password is not matching').css('color', 'red');
        });
    },
    registration: function () {
        if ($("#password").val() === $("#confirmPassword").val()) {
            let username = $("#sign-name").val();
            let password = $("#sign-password").val();
            let requestBody={
                password:password,
                username:username
            }
            console.log(requestBody);
            $.ajax({
                type: "POST",
                url: Register.API_URL,
                data: JSON.stringify(requestBody),
                contentType : "application/json"
            }).done(function (requestBody) {
                localStorage.setItem("user", requestBody.id.toString());
                window.location.replace("/shop")
            })
        }
    },

    bindEvents: function () {
        $("#nextButton").submit(function (event) {
            event.preventDefault();
            alert("merge");
            Register.registration();
            return false;
        });

    }
};
Register.bindEvents();
Register.matchingPassword();



