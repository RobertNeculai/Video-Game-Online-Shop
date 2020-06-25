window.CustomerRegister = {
    API_URL: "http://localhost:8082/customers",
    registration: function () {
        let userId = localStorage.getItem("userId");
        localStorage.clear();
        let firstName = $("#First-Name").val();
        let lastName = $("#Last-Name").val();
        let email = $("#Email").val();
        let address = $("#Address").val();
        let DoB = $("#Date-of-Birth").val();
        let requestBody = {
            address: address,
            dob: DoB,
            email: email,
            firstName: firstName,
            lastName: lastName,
            user_id: userId
        }
        $.ajax({
            type: "POST",
            url: CustomerRegister.API_URL,
            data: JSON.stringify(requestBody),
            contentType: "application/json"
        }).done(function (response) {
            window.location.replace("/login")
        })
    },
    bindEvents: function () {
        $("#nextButton").click(function (event) {
            event.preventDefault();
            CustomerRegister.registration();
        });

    }
}
CustomerRegister.bindEvents();
