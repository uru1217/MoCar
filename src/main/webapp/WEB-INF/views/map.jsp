<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@include file="./header.jsp" %>
<link href="/css/map.css" rel="stylesheet">


<div class="container" style="margin-top: 8%">
    <div class="container-custom">
        <div class="map_wrap">
            <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div>

            <div id="menu_wrap" class="bg_white">
                <div class="option">
                    <div>
                        <form onsubmit="searchPlaces(); return false;">
<%--                        <span id="centerAddr"></span>--%>
                            키워드 : <input type="text" value="카센터" id="keyword" size="15">
                            <button type="submit">검색하기</button>
                        </form>
                    </div>
                </div>
                <hr>
                <ul id="placesList"></ul>
                <div id="pagination"></div>
            </div>
        </div>
    </div>
</div>
<br/>
<script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ff92dbc028b27a4967515e0f52a609ac&libraries=services,clusterer,drawing"></script>
<script src="js/map.js"></script>

<%@include file="./footer.jsp" %>


