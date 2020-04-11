<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="container">
    <div id="login-row" class="row justify-content-center align-items-center">
        <div id="login-column" class="col-md-4">
            <div id="login-box" class="col-md-12">
                <form id="login-form" class="form" action="/register" method="post" onsubmit="return checkEmail()">
                    <h3 class="text-center text-info" style="margin-top: 3%">회원가입</h3>
                    <div class="form-group">
                        <label for="email" class="text-info">e-mail:</label><br>
                        <input type="email" name="email" id="email" class="form-control" placeholder="e-mail을 입력해주세요" required="required" autocomplete="off">
                        <div id="failId"></div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="text-info">닉네임:</label><br>
                        <input type="text" name="name" id="name" class="form-control" placeholder="사용할 닉네임을 입력해주세요" required="required" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label for="password" class="text-info">비밀번호:</label><br>
                        <input type="password" name="password" id="password" class="form-control" placeholder="비밀번호를 입력해주세요" required="required" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label for="passwordCheck" class="text-info">비밀번호 확인:</label><br>
                        <input type="password" name="passwordCheck" id="passwordCheck" class="form-control" placeholder="비밀번호를 다시 입력해주세요" autocomplete="off">
                        <div id="failPassword"></div>
                    </div>
                    <div class="form-group">
                        <input id="regBtn" type="submit" name="submit" class="btn btn-info btn-md" value="회원가입">
                        <button type="button" id="closeBtn" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="/js/regModal.js"></script>
