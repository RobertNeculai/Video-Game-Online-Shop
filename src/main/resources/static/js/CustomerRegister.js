window.CustomerRegister = {
    API_URL: "http://localhost:8082/customers",
    registration: function () {
        let userId = localStorage.getItem("userRegistrationId");
        let firstName = $("#First-Name").val();
        let lastName = $("#Last-Name").val();
        let address = $("#Address").val();
        let DoB = $("#Date-of-Birth").val();
        let email = $("#Email").val();
        let requestBody = {
            address: address,
            dob: DoB,
            email: email,
            firstName: firstName,
            lastName: lastName,
            user_id: userId
        }
        console.log(requestBody);
        $.ajax({
            type: "POST",
            url: CustomerRegister.API_URL,
            data: JSON.stringify(requestBody),
            contentType: "application/json"
        }).done(function (response) {
            console.log(response);
            window.location.replace("/login")
        })

    },
    validEmail: function () {
        $('#Email').on('keyup', function () {
            let filter = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i;
            let email = $('#Email').val();
            if (filter.test(email))
                $('#message').html('Email is valid').css('color', 'green');
            else {
                $('#message').html('Email is not valid').css('color', 'red');
            }
        });
    },
    bindEvents: function () {
        $("#nextButton").click(function (event) {
            event.preventDefault();
            CustomerRegister.registration();
        });

    }
}
CustomerRegister.validEmail();
CustomerRegister.bindEvents();

