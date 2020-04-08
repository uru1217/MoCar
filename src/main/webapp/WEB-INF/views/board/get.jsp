<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="../header.jsp" %>

<div class="container" style="margin-top: 10%">
    <div class="container-custom">
        <div class="col-lg-12">
            <div class="form-group">
                <label>
                    <h2>게시글 조회</h2>
                </label>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">

                    <div class="card-body" style="margin: 3% 3% 3% 3%">
                        <div class="form-group">
                            <label>제&nbsp;&nbsp;목&nbsp;</label>
                            <input class="form-control" type="text" name="title" readonly="readonly" style="background: #fbf3fc" value='${board.title}'>
                        </div>

                        <div class="form-group">
                            <label>내&nbsp;&nbsp;용&nbsp;</label>
                            <textarea name="content" class="form-control" style="height: 25rem; background: #fbf3fc"
                                      readonly="readonly" >${board.content}</textarea>
                        </div>

                        <div class="form-group">
                            <label>댓&nbsp;&nbsp;글&nbsp;</label>
                            <textarea name="reply" class="form-control" style="height: 10rem;">${board.content}</textarea>
                        </div>

                        <div class="form-group mt-2">
                            <button id="move" type="button" class="btn btn-info">수정하기</button>
                            <button type="button" data-oper="list" class="btn btn-default">목록</button>
                        </div>

                        <input hidden="hidden" readonly="readonly" type="number" name="user_userId" value="1"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<form id="operForm" action="/board/modify" method="get">
    <input type="hidden" id="boardId" name="boardId" value='<c:out value="${board.boardId}"/>'>
    <input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum}"/>'>
    <input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'>
    <input type="hidden" name="keyword" value='<c:out value="${cri.keyword}"/>'>
    <input type="hidden" name="type" value='<c:out value="${cri.type}"/>'>
</form>


<%@include file="../footer.jsp" %>

<script src="/js/board/modify.js"></script>
