const actionForm = $("#actionForm");
// alert('dd')
$(".paginate_button a").on("click", function (e) {
    e.preventDefault();
    console.log('click');
    actionForm.find("input[name='pageNum']").val($(this).attr("href"));
    actionForm.submit();
});

$(".move").on("click", function (e) {
    e.preventDefault();
    actionForm.append("<input name='boardId' value='"+$(this).attr("href")+"'>");
    actionForm.attr("action","/board/get");
    actionForm.submit();
});

const searchForm = $("#searchForm");
$("#searchForm button").on("click", function (e) {
    if (!searchForm.find("option:selected").val()) {
        alert("검색종류를 선택하세요");
        return false;
    }
    if (!searchForm.find("input[name='keyword']").val()) {
        alert("키워드를 입력하세요");
        return  false;
    }

    searchForm.find("input[name='pageNum']").val("1");
    e.preventDefault();

    searchForm.submit();
});
