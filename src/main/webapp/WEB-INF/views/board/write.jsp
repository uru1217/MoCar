<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="../header.jsp" %>

<div class="container" style="margin-top: 10%">
    <div class="container-custom">

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <form role="form" action="/board/write" method="post">
                        <div class="card-body" style="margin: 3% 3% 3% 3%">
                            <div class="form-group">
                                <label>제&nbsp;&nbsp;목&nbsp;</label>
                                <input class="form-control" name="title" placeholder="게시글 제목을 입력하세요" required="required">
                            </div>

                            <div class="form-group">
                                <label>내&nbsp;&nbsp;용&nbsp;</label>
                                <textarea name="content" class="form-control" style="height: 30rem"></textarea>
                            </div>

                            <sec:authorize access="isAuthenticated()">
                                <sec:authentication property="principal.userId" var="userId"/>
                                <input id="userId" name="userId" readonly type="hidden" value="${userId}">
                                <sec:authentication property="principal.name" var="name"/>
                                <input id="writer" name="writer" readonly type="hidden" value="${name}">
                            </sec:authorize>
                            <div>
                                <button type="submit" class="btn btn-info">등록</button>
                                <button type="reset" class="btn btn-warning">초기화</button>
                                <a href="/board/list">
                                    <button type="button" class="btn btn-default">목록</button>
                                </a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../footer.jsp" %>