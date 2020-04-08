<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="../header.jsp" %>

<%--main--%>

<div class="container" style="margin-top: 10%; margin-bottom: 10%">
    <div class="container-custom">

        <div class="col-lg-12">
            <label>
                <h2>누카게시판</h2>
            </label>
        </div>

        <div class="card-body" style="border: #ededed">
            <table class="table table-bordered table-hover">
                <thead style="background: #ededed;" class="text-center">
                <tr class="tr-center">
                    <th width="10%">게시글 번호</th>
                    <th width="60%">게시글 제목</th>
                    <th width="20%">작성자</th>
                    <th width="10%">작성날짜</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${board}" var="list">
                    <tr>
                        <td><c:out value="${list.boardId}"/></td>
                        <td><a class='move' href='<c:out value="${list.boardId}" />'><c:out
                                value="${list.title}"/></a></td>
                        <td></td>
                        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${list.regiDate}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <div class='row col-lg-12'>
                <div class="col-lg-4">
                    <form id='searchForm' action="/board/list" method="get">
                        <select name="type">
                            <option value="" <c:out value="${pageMaker.cri.type == null? 'selected':''}"/>>--</option>
                            <option value="t" <c:out value="${pageMaker.cri.type eq 't' ? 'selected':''}"/>>제목</option>
                            <option value="c" <c:out value="${pageMaker.cri.type eq 'c' ? 'selected':''}"/>>내용</option>
                            <option value="w" <c:out value="${pageMaker.cri.type eq 'w' ? 'selected':''}"/>>작성자</option>
                        </select>
                        <input type="text" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"/>'/>
                        <input type="number" hidden="hidden" name="pageNum"
                               value='<c:out value="${pageMaker.cri.pageNum}"/>'/>
                        <input type="number" hidden="hidden" name="amount"
                               value='<c:out value="${pageMaker.cri.amount}"/>'/>
                        <button type="button" class="btn btn-default">검색</button>
                    </form>
                </div>


                <div class="col-lg-6">
                    <div class="pagination" style="margin-top: 0%">
                        <%--                    ${pageMaker}--%>
                        <c:if test="${pageMaker.prev}">
                            <li class="paginate_button previous">
                                <a href="${pageMaker.startPage -1}">Previous</a>
                            </li>
                        </c:if>

                        <c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
                            <li class="paginate_button"><a href="${num}">${num}</a></li>
                        </c:forEach>

                        <c:if test="${pageMaker.next}">
                            <li class="paginate_button next">
                                <a href="${pageMaker.endPage + 1}">Next</a>
                            </li>
                        </c:if>
                    </div>
                </div>
                <div class="col-lg-2">
                    <button class="btn btn-info pull-right" onclick="location.href='/board/write'">글등록</button>
                </div>
            </div>
        </div>
    </div>
</div>


<form id="actionForm" action="/board/list" method="get">
    <input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
    <input type="hidden" name="amount" value="${pageMaker.cri.amount}">
    <input type="hidden" name="type" value='<c:out value="${pageMaker.cri.type}"/>'>
    <input type="hidden" name="keyword" value='<c:out value="${pageMaker.cri.keyword}"/>'>
</form>

<%@include file="../footer.jsp" %>

<script src="/js/board/list.js"></script>