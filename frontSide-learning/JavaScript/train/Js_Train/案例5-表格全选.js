var btn = document.getElementById("btn").onclick = addData;

function addData() {
    // 获取input中的值
    var table = document.getElementById("table");
    var id = document.getElementById("id").value;
    var name = document.getElementById("name").value;
    var sex = document.getElementById("sex").value;

    // 创建需要的node对象
    var tr = document.createElement("tr");
    var td_checkbox = document.createElement("td");
    var td_id = document.createElement("td");
    var td_name = document.createElement("td");
    var td_sex = document.createElement("td");
    var td_del_btn = document.createElement("td");
    var del_btn = document.createElement("a");

    // 设置每个td元素的text和属性
    td_checkbox.innerHTML = "<input type = 'checkbox' class='checkbox'>";
    td_id.innerHTML = id;
    td_name.innerHTML = name;
    td_sex.innerHTML = sex;
    del_btn.setAttribute("href", "javascript:void(0);");
    del_btn.innerHTML = "删除";
    del_btn.setAttribute("onclick", "delData(this)");
    td_del_btn.appendChild(del_btn);

    // 将一行的td都添加到tr中
    tr.appendChild(td_checkbox);
    tr.appendChild(td_id);
    tr.appendChild(td_name);
    tr.appendChild(td_sex);
    tr.appendChild(td_del_btn);

    //设置tr属性
    tr.setAttribute("onmouseover", "changeColor(this);");
    tr.setAttribute("onmouseout", "recoverColor(this);");

    table.appendChild(tr);
}

function delData(obj) {
    var del_tr = obj.parentNode.parentNode;
    var table = del_tr.parentNode;
    table.removeChild(del_tr);
}

function changeColor(obj) {
    obj.style.backgroundColor = "pink";
}

function recoverColor(obj) {
    obj.style.backgroundColor = "white";
}

function selectAll() {
    var allCheckBox = document.getElementsByClassName("checkbox");
    for (let i = 0; i < allCheckBox.length; i++) {
        allCheckBox[i].checked = true;
    }
}

function selectNone() {
    var allCheckBox = document.getElementsByClassName("checkbox");
    for (let i = 0; i < allCheckBox.length; i++) {
        allCheckBox[i].checked = false;
    }
}

function selectReverse() {
    var allCheckBox = document.getElementsByClassName("checkbox");
    for (let i = 0; i < allCheckBox.length; i++) {
        allCheckBox[i].checked = !allCheckBox[i].checked;
    }
}

function selectAllToggle(obj) {
    if (obj.checked)
        selectAll();
    else
        selectNone();
}

