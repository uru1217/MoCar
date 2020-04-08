<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="../header.jsp"%>

<div class="container" style="margin-top: 10%">
    <div class="container-custom">

        <div class="col-lg-12">
            <form role="form" action="/board/write" method="post">
                <div class="card-body">
                    <div>
                        <label>
                            <h2><span>글작성</span></h2>
                        </label>
                    </div>

                    <div>
                        <label>
                            <span>제&nbsp;&nbsp;목&nbsp;</span>
                            <input type="text" name="title" class="ml-2" placeholder="게시글 제목을 입력하세요">
                        </label>
                    </div>

                    <div>
                    <label>내용</label>
                    <textarea name="content" style="width: 210px; height: 200px" ></textarea>
                    </div>

                    <input hidden="hidden" readonly="readonly" type="number" name="user_userId" value="1"/>
                    <div>
                        <button type="submit" class="btn btn-default">등록</button>
                        <button type="reset" class="btn btn-default">초기화</button>
                        <a href="/board/list"><button type="button">목록</button></a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="../footer.jsp"%>