$(document).ready(function () {
    const formObj = $("form");
    $('button').on("click", function (e) {
        e.preventDefault();

        const operation = $(this).data("oper");

        if (operation === 'remove') {
            formObj.attr("action", "/board/remove");
        } else if (operation === 'list') {
            formObj.attr("action", "/board/list").attr("method", "get");
            var pageNumTag = $("input[name='pageNum']").clone();
            var amountTag = $("input[name='amount']").clone();
            var keywordTag = $("input[name='keyword']").clone();
            var typeTag = $("input[name='type']").clone();

            formObj.empty();
            formObj.append(pageNumTag);
            formObj.append(amountTag);
            formObj.append(typeTag);
            formObj.append(keywordTag);
        }
        formObj.submit();
    });
});