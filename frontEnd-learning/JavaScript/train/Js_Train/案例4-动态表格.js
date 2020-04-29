var btn = document.getElementById("btn").onclick = addData;

function addData() {
    var table = document.getElementById("table");
    var id = document.getElementById("id").value;
    var name = document.getElementById("name").value;
    var sex = document.getElementById("sex").value;

    var tr = document.createElement("tr");
    var td_id = document.createElement("td");
    var td_name = document.createElement("td");
    var td_sex = document.createElement("td");
    var td_del_btn = document.createElement("td");
    var del_btn = document.createElement("a");
    td_id.innerHTML = id;
    td_name.innerHTML = name;
    td_sex.innerHTML = sex;
    del_btn.setAttribute("href", "javascript:void(0);");
    del_btn.innerHTML = "删除";
    del_btn.setAttribute("onclick", "delData(this)");
    td_del_btn.appendChild(del_btn);

    tr.appendChild(td_id);
    tr.appendChild(td_name);
    tr.appendChild(td_sex);
    tr.appendChild(td_del_btn);
    table.appendChild(tr);
}

function delData(obj) {
    var del_tr = obj.parentNode.parentNode;
    var table = del_tr.parentNode;
    table.removeChild(del_tr);
}
