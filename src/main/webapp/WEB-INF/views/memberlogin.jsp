<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <div id="login-row" class="row justify-content-center align-items-center">
        <div id="login-column" class="col-md-4">
            <div id="login-box" class="col-md-12">
                <form id="login-form" class="form" action="/logIn" method="post">
                    <h3 class="text-center text-info" style="margin-top: 3%">로그인</h3>
                    <div class="form-group">
                        <label for="email" class="text-info">e-mail:</label><br>
                        <input type="email" name="email" id="email" class="form-control" placeholder="e-mail을 입력해주세요" required="required" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <label for="password" class="text-info">비밀번호:</label><br>
                        <input type="password" name="password" id="password" class="form-control" placeholder="비밀번호를 입력해주세요" required="required" autocomplete="off">
                    </div>
                    <div class="form-group">
                        <input type="submit" name="submit" class="btn btn-info btn-md" value="로그인">
                        <a id="register-link" href="#" class="text-info pull-right">Register here</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<script src="/js/userModal.js"></script>
