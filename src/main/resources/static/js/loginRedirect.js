window.loginRedirect={
    API_URL: "http://localhost:8082",

    getRedirect: function () {
        window.location.replace("/login");

    },
}
loginRedirect.getRedirect();