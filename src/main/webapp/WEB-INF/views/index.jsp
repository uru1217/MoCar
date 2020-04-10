<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="header.jsp" %>

<!-- Start Slider Area -->
<div id="home" class="slider-area">
    <div class="bend niceties preview-2">
        <div id="ensign-nivoslider" class="slides">
            <img src="img/slider/1.jpg" alt="" title="#slider-direction-1"/>
            <%--            <img src="img/slider/slider2.jpg" alt="" title="#slider-direction-2" />--%>
            <%--            <img src="img/slider/slider3.jpg" alt="" title="#slider-direction-3" />--%>
        </div>

        <div id="slider-direction-1" class="slider-direction slider-one">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="slider-content">
                            <!-- layer 1 -->
                            <div class="layer-1-1 hidden-xs">
                                <h2 class="title1">모두를 만족할 모카</h2>
                            </div>
                            <!-- layer 2 -->
                            <div class="layer-1-2">
                                <h1 class="title2">카센터의 필요한 모든 것을 제공합니다.</h1>
                            </div>
                            <!-- layer 3 -->
                            <div class="layer-1-3 hidden-xs" >
                                <a class="ready-btn right-btn page-scroll" href="/map">내 주변 모카</a>
                                <a class="ready-btn page-scroll" href="/board/list">게시판</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%@include file="footer.jsp" %>