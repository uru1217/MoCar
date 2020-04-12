<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="../header.jsp" %>

<div class="container" style="margin-top: 10%">
    <div class="container-custom">

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">

                    <div class="card-body" style="margin: 3% 3% 3% 3%">
                        <div class="form-group">
                            <label>제&nbsp;&nbsp;목&nbsp;</label>
                            <input class="form-control" type="text" name="title" readonly="readonly"
                                   style="background: #F8F8FF" value='${board.title}'>
                        </div>

                        <div class="form-group">
                            <label>내&nbsp;&nbsp;용&nbsp;</label>
                            <textarea name="content" class="form-control" style="height: 20rem; background: #F8F8FF"
                                      readonly="readonly">${board.content}</textarea>
                        </div>

                        <div class="form-group row" style="margin-right: 1%">
                            <button type="submit" id="go" class="btn btn-default pull-right" style="margin-left: 1%">
                                목록
                            </button>
                            <button id="move" type="button" class="btn btn-info pull-right">수정하기</button>
                        </div>

                        <input readonly="readonly" type="hidden" name="userId" id="userId" value='${board.userId}'/>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading m-0">
                        <label><i class="fa fa-comments fa-fw"></i>댓글</label>
                        <div id="btnArea">
                            <button id="addBtn" class="btn btn-info pull-right" style="margin-top: -40px">등록하기</button>
                        </div>
                        <div class="">
                            <textarea id="content" name="content" class="form-control"></textarea>
                        </div>
                    </div>

                    <div class="panel-body" style="padding-bottom: 15px">
                        <ul class="chat">
                        </ul>
                        <div id="modifyArea"></div>
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

<script src="/js/board/get.js"></script>
