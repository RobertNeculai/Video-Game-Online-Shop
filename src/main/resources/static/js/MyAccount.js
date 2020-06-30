window.MyAccount = {
    API_URL: "http://localhost:8082",
    getCustomerID: function () {
        let userId = localStorage.getItem("userId");
        $.ajax({
            url: MyAccount.API_URL + "/customers/userid/" + userId,
            method: "GET",
            contentType: "application/json"
        }).done(function (response) {
            localStorage.setItem("CusID",response.id);
            MyAccount.getCustomer(response.id)

        })
    },
    getCustomer: function (customerId) {
        $.ajax({
            url: MyAccount.API_URL + "/customers/" + customerId,
            method: "GET",
            contentType: "application/json"
        }).done(function (response) {
            $('#billing_first_name_field').append(MyAccount.displayDetailsforCustomer(response));
        })
    },
    displayDetailsforCustomer: function (customer) {
        return `
                                                <label class=""  for="billing_first_name">First Name <abbr title="required" class="required">*</abbr>
                                                </label>
                                                <input type="text" readonly="readonly" value="${customer.firstName}" placeholder="" id="billing_first_name" name="billing_first_name" class="input-text ">
                                                <input class="customerId" hidden="hidden" value="${customer.id}" ">
                                            <p id="billing_last_name_field" class="form-row form-row-last validate-required">
                                                <label class="" for="billing_last_name">Last Name <abbr title="required" class="required">*</abbr>
                                                </label>
                                                <input type="text" readonly="readonly" value="${customer.lastName}" placeholder="" id="billing_last_name" name="billing_last_name" class="input-text ">
                                            </p>
                                            <div class="clear"></div>


                                            <p id="billing_address_1_field" class="form-row form-row-wide address-field validate-required">
                                                <label class="" for="billing_address_1">Address <abbr title="required" class="required">*</abbr>
                                                </label>
                                                <input type="text" value="${customer.address}" placeholder="Street address" id="customer_address" name="billing_address_1" class="input-text ">
                                            </p>

                                            <p id="billing_address_2_field" class="form-row form-row-wide address-field">
                                                <input type="text" value="" placeholder="Apartment, suite, unit etc. (optional)" id="billing_address_2" name="billing_address_2" class="input-text ">
                                            </p>

             
                                            <div class="clear"></div>

                                            <p id="billing_email_field" class="form-row form-row-first validate-required validate-email">
                                                <label class=""  for="billing_email">Email Address <abbr title="required" class="required">*</abbr>
                                                </label>
                                                <input type="text" value="${customer.email}" placeholder="Email" id="customer_email" name="billing_email" class="input-text ">
                                            </p>
                                            <div class="clear"></div>
                                            <label for="Date-of-Birth" readonly="readonly" class="sr-only">Date of Birth</label>
                                            <input type="date" id="Date-of-Birth" readonly="readonly" value="${customer.dob}" name="Date-of-Birth" class="form-control" placeholder="Date-of-Birth"  autofocus="">
`

    },
    getUser: function () {
        let userId = localStorage.getItem("userId");
        $.ajax({
            url: MyAccount.API_URL + "/user/" + userId,
            method: "GET",
            contentType: "application/json"
        }).done(function (response) {
            $('#billing_first_name_field').html(MyAccount.displayDetailsforUser(response));
        })
    },
    displayDetailsforUser: function (user) {
        return `
<div class="clear"></div>
                                                <label class="username" for="username" readonly="readonly">Username <abbr title="required" class="required">*</abbr>
                                                </label>
                                                <input type="text"  readonly="readonly"  value="${user.username}" placeholder="username" id="Username" name="username" class="input-text ">

                                            <p id="password" class="form-row form-row-last validate-required">
                                                <label class="password-field" for="password">Password <abbr title="required" class="required">*</abbr>
                                                </label>
                                                <input type="password" value="${user.password}" placeholder="" id="UserPassword" name="password" >
                                            </p>
                        
                                            <p id="password2" class="form-row form-row-last validate-required">
                                                <label class="password-field2" for="password2">Confirm Password <abbr title="required" class="required">*</abbr>
                                                </label>
                                                <input type="password" value="${user.password}" placeholder="" id="UserPassword2" name="password" >
                                            </p>
                                            <div class="clear"></div>
                                            
`

    },
    matchingPassword: function () {
        $('#password, #confirmPassword').on('keyup', function () {
            if (document.getElementById("UserPassword").value === document.getElementById("UserPassword2").value) {
                $('#message1').html('Password is matching').css('color', 'green');
            } else {
                $('#message1').html('Password not matching').css('color' ,'red');
            }
        });
    },
    updateUserDetails: function(username,password){
        let requestBody={
            password:password
        }
        $.ajax({
            url: MyAccount.API_URL + "/user/" + username,
            method: "PUT",
            contentType: "application/json",
            data: JSON.stringify(requestBody)
        }).done(function () {
        });
    },
    validEmail: function () {
        $('#billing_email').on('keyup', function () {
            let filter = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i;
            let email = $('#billing_email1').val();
            if (filter.test(email))
                $('#message2').html('Email is valid').css('color', 'green');
            else {
                $('#message2').html('Email is not valid').css('color', 'red');
            }
        });
    },
    updateCustomerDetails: function(email,address,customerId){
        let requestBody={
            address: address,
            email:email
        }
        $.ajax({
            url: MyAccount.API_URL + "/customers/" + customerId,
            method: "PUT",
            contentType: "application/json",
            data: JSON.stringify(requestBody)
        }).done(function () {
            location.reload();
        });
    },
    bindEvents:function () {
        $('#Submit-Changes').click(function (event) {
            event.preventDefault();
            let password = document.getElementById("UserPassword").value;
            let username = document.getElementById("Username").value;
            if (document.getElementById("UserPassword").value === document.getElementById("UserPassword2").value) {
                MyAccount.updateUserDetails(username,password);
            }
            let email=document.getElementById("customer_email").value;
            let address=document.getElementById("customer_address").value;
            let customerId=localStorage.getItem("CusID");

            MyAccount.updateCustomerDetails(email,address,customerId);

        })

    }
};
MyAccount.getCustomerID();
MyAccount.getUser();
MyAccount.matchingPassword();
MyAccount.validEmail();
MyAccount.bindEvents();