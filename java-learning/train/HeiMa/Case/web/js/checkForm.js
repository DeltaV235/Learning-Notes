window.onload = function () {
    let submitBtn = document.getElementById("submit");
    let name = document.getElementById("name");
    let age = document.getElementById("age");
    let qq = document.getElementById("qq");
    let email = document.getElementById("email");

    let isName = false;
    let isAge = false;
    let isQq = false;
    let isEmail = false;

    name.onblur = function () {
        if (!checkName(name.value)) {
            name.parentElement.setAttribute("class", "form-group has-error");
            isName = false;
        } else {
            name.parentElement.setAttribute("class", "form-group has-success");
            isName = true;
        }
    };

    age.onblur = function () {
        if (!checkAge(age.value)) {
            age.parentElement.setAttribute("class", "form-group has-error");
            isAge = false;
        } else {
            age.parentElement.setAttribute("class", "form-group has-success");
            isAge = true;
        }
    };

    qq.onblur = function () {
        if (!checkQq(qq.value)) {
            qq.parentElement.setAttribute("class", "form-group has-error");
            isQq = false;
        } else {
            qq.parentElement.setAttribute("class", "form-group has-success");
            isQq = true;
        }
    };

    email.onblur = function () {
        if (!checkEmail(email.value)) {
            email.parentElement.setAttribute("class", "form-group has-error");
            isEmail = false;
        } else {
            email.parentElement.setAttribute("class", "form-group has-success");
            isEmail = true;
        }
    };

    setInterval(check, 1000);

    function check() {
        if (isName && isAge && isQq && isEmail) {
            submitBtn.setAttribute("class", "btn btn-primary");
        } else {
            submitBtn.setAttribute("class", "btn btn-primary disabled");
        }
    }

    function checkName(name) {
        let reg = /\w{1,32}/;
        return reg.test(name);
    }

    function checkAge(age) {
        let reg = /\d{1,3}/;
        return reg.test(age);
    }

    function checkQq(qq) {
        let reg = /\d{4,20}/;
        return reg.test(qq);
    }

    function checkEmail(email) {
        let reg = /\w{1,32}@\w{1,32}/;
        return reg.test(email);
    }
};