$(function(){
    $(document).ready(function(){
        var bookCount = $('#count').val();
        var price = $('#book_Price').text();
        var total_price = price * bookCount;

        var h4 = document.createElement("h4");
        h4.setAttribute("id","bookPrice");
        h4.innerHTML = comma(total_price);
        $('#total').append(h4);
    }),
    $("#count").change(function(){
        var bookCount = $('#count').val();
        var price = $('#book_Price').text();
        var total_price = price * bookCount;

        $('#bookPrice').text(comma(total_price));
    })
})

let comma = (num) => {
    let len, point, str;

    num = num + "";
    point = num.length % 3 ;
    len = num.length;

    str = num.substring(0, point);
    while (point < len) {
        if (str != "") str += ",";
        str += num.substring(point, point + 3);
        point += 3;
    }

    return str;

}

let orderBtn = () => {
    let countValue = $("#count").val();
    let bookUid = new URLSearchParams(location.search).get("uid");

    window.location.href="/orders/addOrder?bookUid="+bookUid+"&count="+countValue;
}