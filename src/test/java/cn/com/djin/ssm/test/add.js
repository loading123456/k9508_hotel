function add() {
    var add = document.getElementById('add');
    var parentNode = add.parentNode;
    var cloneNode = add.cloneNode();
    content = add.innerHTML;
    cloneNode.removeAttribute("id");
    cloneNode.innerHTML=content;
    parentNode.appendChild(cloneNode);

}
