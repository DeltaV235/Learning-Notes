window.onload = function () {
    let name;
    let gender;
    let age;
    let qq;
    let email;
    let address;
    let resetBtn = document.getElementById("reset");
    let parameters = document.getElementsByTagName("input");
    for (let i = 0; i < parameters.length; i++) {
        let paramName = parameters[i].name;
        let paramValue = parameters[i].value;
        // 获取原来的属性值
        switch (paramName) {
            case "name":
                name = paramValue;
                break;
            case "gender":
                if (parameters[i].checked)
                    gender = paramValue;
                break;
            case "age":
                age = paramValue;
                break;
            case "qq":
                qq = paramValue;
                break;
            case "email":
                email = paramValue;
                break;
        }
    }
    let selectElement = document.getElementsByTagName("option");
    for (let j = 0; j < selectElement.length; j++) {
        if (selectElement[j].selected)
            address = selectElement[j].value;
    }

    // 若按下重置键
    resetBtn.onclick = function () {
        for (let i = 0; i < parameters.length; i++) {
            let paramName = parameters[i].name;
            let paramValue = parameters[i].value;
            // 获取原来的属性值
            switch (paramName) {
                case "name":
                    paramValue = name;
                    break;
                case "gender":
                    if (paramValue === gender)
                        parameters[i].checked = true;
                    break;
                case "age":
                    paramValue = age;
                    break;
                case "qq":
                    paramValue = qq;
                    break;
                case "email":
                    paramValue = email;
                    break;
            }

            for (let j = 0; j < selectElement.length; j++) {
                if (address === selectElement[j].value)
                    selectElement[j].selected = true;
            }
        }
    };
};