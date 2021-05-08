let totalPrice;
let backLocation = () => {
    let bookUid = new URLSearchParams(location.search).get("bookUid");
    window.location.href="/books/bookdetail?uid="+bookUid;
}