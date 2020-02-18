window.onload = function () {
    let del_btns = document.getElementsByClassName("update-del");
    for (let i = 0; i < del_btns.length; i++) {
        del_btns.item(i).href += del_btns.item(i).parentNode.parentNode.firstElementChild.innerHTML;
    }
};