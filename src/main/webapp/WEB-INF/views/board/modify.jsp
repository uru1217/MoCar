<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="../header.jsp" %>

<div class="container" style="margin-top: 10%; height: auto">
    <div class="container-custom">
        <div class="col-lg-12">
            <div class="form-group">
                <label>
                    <h2>게시글 수정</h2>
                </label>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">

                    <form role="form" action="/board/modify" method="post">
                        <input type="hidden" name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
                        <input type="hidden" name='amount' value='<c:out value="${cri.amount}"/>'>
                        <input type="hidden" name="type" value='<c:out value="${cri.type}"/>'>
                        <input type="hidden" name="keyword" value='<c:out value="${cri.keyword}"/>'>

                        <div class="card-body" style="margin: 3% 3% 3% 3%">
                            <div class="form-group">
                                <label>제&nbsp;&nbsp;목&nbsp;</label>
                                <input class="form-control" type="text" name="title" value='${board.title}'>
                            </div>

                            <div class="form-group">
                                <label>내&nbsp;&nbsp;용&nbsp;</label>
                                <textarea name="content" class="form-control" style="height: 35rem">${board.content}</textarea>
                            </div>
                            <input hidden="hidden" readonly="readonly" type="number" name="boardId"
                                   value='${board.boardId}'/>
                            <input hidden="hidden" readonly="readonly" type="number" name="userId" value="${board.userId}"/>

                            <div class="form-group mt-2">
                                <button type="submit" data-oper="modify" class="btn btn-info">수정완료</button>
                                <button type="button" data-oper="remove" class="btn btn-danger">삭제</button>
                                <button type="button" data-oper="list" class="btn btn-default">목록</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../footer.jsp" %>

<script src="/js/board/modify.js"></script>