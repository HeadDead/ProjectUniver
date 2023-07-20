var req = new XMLHttpRequest();
$(function () {
    $('#login').click(function () {
        let username = $("#loginUserName").val();
        let password = $("#password").val();
        let body = {};
        body.username = username;
        body.password = password;
        console.log(body)
        req.open('POST', 'login', false);
        req.setRequestHeader("Content-type", "application/json;charset=UTF-8");
        req.send(JSON.stringify(body));
        let token = JSON.parse(req.responseText);
        let jwt = token.token;
        document.cookie = 'jwtToken=' + jwt + ';path=/';
        console.log(req.status);
        if (req.status === 200) {
            window.location.href = "/submitapplication";
        } else {
            /*req.open('GET',)*/
        }
    });
})