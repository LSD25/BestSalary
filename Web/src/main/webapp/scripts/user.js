/**
 * Created Victor Zagnitko on 08.07.2014.
 */
var url = '/register/user/save';
$(document).ready(function () {
    $("#save-user").click(function () {
        var items = JSON.stringify({
            "username": $("#login").val(),
            "password": $("#pass").val(),
            "confirmPassword": $("#confPass").val(),
            "firstName": $("#fname").val(),
            "lastName": $("#lname").val(),
            "companyName": $("#comp").val(),
            "phoneNumber": $("#phone").val()
        });
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: url,
            type: 'POST',
            dataType: "json",
            data: items,
            success: function (data) {
                console.log(data);
                if (data.success) {  // If there is an error, show the error messages
                    alert("Success register")
                } else {
                    if (data.username) {
                        $("#username").append('<td>' + data.username + '</td>');
                    }
                    if (data.password) {
                        $("#password").append('<td>' + data.password + '</td>');
                    }
                    if (data.confirmPassword) {
                        $("#confirmPassword").append('<td>' + data.confirmPassword + '</td>');
                    }
                    if (data.firstName) {
                        $("#firstName").append('<td>' + data.firstName + '</td>');
                    }
                    if (data.lastName) {
                        $("#lastName").append('<td>' + data.lastName + '</td>');
                    }
                    if (data.companyName) {
                        $("#companyName").append('<td>' + data.companyName + '</td>');
                    }
                    if (data.phoneNumber) {
                        $("#phoneNumber").append('<td>' + data.phoneNumber + '</td>');
                    }
                }
            }
        });
    });
});