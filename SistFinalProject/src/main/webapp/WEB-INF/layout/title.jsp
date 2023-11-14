<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=VT323&display=swap" rel="stylesheet">
    <link href="https://webfontworld.github.io/goodchoice/Jalnan.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
    <title>Insert title here</title>
</head>
<style>
    body {
        margin: 0;
        padding: 0;
    }

    header {
        font-family: 'Jalnan';
        width: 70%;
        color: white;
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-right: auto;
        margin-left: auto;
        height: 72px;
        /*border: 1px solid ;*/
    }

    div.titlebox {
        display: flex;
        align-items: center;
    }

    div.select-option {
        display: flex;
        align-items: center;
        justify-content: right;
        width: 80%;
    }
    div.select-option>a{
        width: 30px;
        margin-right: 30px;
    }
    i.bi-search {
        color: white;
        cursor: pointer;
        font-size: 24px;
    }


    div ul {
        list-style: none;
        margin: 0;
        padding: 0;
    }

    div.select-option ul li.select-li {
        display: flex;
        align-items: center;
    }

    div ul li.select-li a {
        margin-right: 30px;
        color: white;
        text-decoration: none;
        font-size: 18px;
        white-space: nowrap;
    }
    .titlebox a {
        color: white;
        text-decoration: none;
    }
    div.search-input-box{
        display: flex;
        align-items: center;
        width: 80%;
    }

    input.search-input{
        background: #f7323f;
        position: relative;
        width: 80%;
        border: none;
    }
    input.search-input:focus{
        border: none;
        outline: none;

    }
    i.bi-x{
        font-size: 30px;
        cursor: pointer;
    }

    div.sub-title{
        position: relative;
        background: white;
        top: -20px;
        width: 150px;
        margin-left: 50%;
        border: 3px solid green;
        border-radius: 10px;
        box-shadow: 2px 2px 2px 2px #777879;
        padding: 10px 10px 10px 10px;
    }
    div.sub-title a{
        color: black;
        text-decoration: none;
    }


</style>

<script>

    $(function () {
        $("div.sub-title").hide();
        $("div.search-input-box").hide();

        $("#see_more").mouseover(function (){
            $("div.sub-title").show();
        });

        $("#see_more").mouseout(function (){
            $("div.sub-title").hide();
        });

        $(".bi-search").click(function (){
            // $(this).animate({"marginLeft":"100%"},500);
            $(".select-ul").hide();
            $("div.search-input-box").show();
        });

        $(".search-input-box").click(function (){

        });

        $("i.bi-x").click(function (){
            $(".select-ul").show();
            $("div.search-input-box").hide();
        });

    });

</script>


<c:set var="root" value="<%=request.getContextPath() %>"/>
<body>

<header>
    <div class="titlebox">
        <a href="/"><h2>TRIVIEW</h2></a>
    </div>
    <div class="select-option">
        <a><i class="bi bi-search"></i></a>
        <ul class="select-ul">
            <li class="select-li">
                <a href="#">내주변</a>
                <a href="#">예약내역</a>
                <a href="#" id="see_more">더보기</a>
                <a href="#">로그인</a>
            </li>
        </ul>
        <div class="search-input-box">
            <input type="text" class="search-input" placeholder="지역,숙소명">
            <i class="bi bi-x"></i>
        </div>
    </div>
</header>

<div class="sub-title">
    <ul>
        <li><a href="#">공지사항</a></li>
        <li><a href="#">이벤트</a></li>
        <li><a href="#">1:1문의</a></li>
        <li><a href="#">약관 및 정책</a></li>
        <li><a href="#">공지사항</a></li>
    </ul>
</div>

</body>
</html>