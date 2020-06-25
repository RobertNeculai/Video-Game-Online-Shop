window.Register = {
    API_URL: "http://localhost:8082/user",
    matchingPassword: function () {
        $('#password, #confirmPassword').on('keyup', function () {
            if ($("#password").val() === $("#confirmPassword").val()) {
                $('#message').html('Password is matching').css('color', 'green');
            } else {
                $('#message').html('Password not matching').css('color' ,'red');
            }
        });
    },
    AvailableUsername: function(){
        let ChosenUsername=$("#username").val();
        $.ajax({
            type: "GET",
            url:Register.API_URL+"/username"+"?username="+ChosenUsername,
            contentType: "application/json"
        }).done(function (response) {
            if(response===true) {
                Register.registration();
            }
            else {
                alert("Username Already in User")
            }

        })
    },
    registration: function () {
        if ($("#password").val() === $("#confirmPassword").val()) {
            {
                let username = $("#username").val();
                let password = $("#password").val();
                let requestBody = {
                    password: password,
                    username: username
                }
                $.ajax({
                    type: "POST",
                    url: Register.API_URL,
                    data: JSON.stringify(requestBody),
                    contentType: "application/json"
                }).done(function (response) {
                    localStorage.setItem("userId",response.id.toString());
                    window.location.replace("/CustomerRegister")
                })
            }
        }
    },

    bindEvents: function () {
        $("#nextButton").click(function (event) {
            event.preventDefault();
            Register.AvailableUsername();
        });

    }
};
Register.matchingPassword();
Register.bindEvents();




