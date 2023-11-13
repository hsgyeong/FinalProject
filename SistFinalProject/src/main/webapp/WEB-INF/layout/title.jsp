<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    header{
        font-family: 'Jalnan';
        width: 70%;
        color: white;
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-right: auto;
        margin-left: auto;
        /*border: 1px solid ;*/
    }
    div.titlebox{
        /*position: absolute;*/
        margin-left: 0px;
    }
    div.select_option{
        /*float: right;*/
        margin-right: 0;
    }
    div ul li a{
        margin-right: 40px;
        color: white;
        text-decoration: none;
        display: inline-block;
        vertical-align: middle;
        font-size: 18px;
    }

    /*    margin-right: 30px;*/
    /*    color: white;*/
    /*    text-decoration: none;*/
    /*    display: inline-block;*/
    /*    vertical-align: middle;*/
    /*    !*float: right;*!*/
    /*    margin-right: 30px;*/

    /*}*/

    /*section{*/
    /*    font-family: 'Jalnan';*/
    /*    width: 80%;*/
    /*    color: white;*/
    /*    display: flex;*/
    /*    align-items: center;*/
    /*    justify-content: center;*/
    /*    margin-right: auto;*/
    /*    margin-left: auto;*/
    /*}*/

    /*div.select_option{*/
    /*    !*width: 60%;*!*/
    /*    !*float: right;*!*/
    /*    !*margin-right: auto;*!*/
    /*}*/

</style>
<c:set var="root" value="<%=request.getContextPath() %>"/>
<body>

<header>
<%--    <section class="d-inline-flex">--%>
        <div class="titlebox">
            <img src="${root}/triview.png" style="height: 70px;">
        </div>
        <div class="select_option">
            <ul>
                <li>
                    <a href="#"><i class="bi bi-search"></i></a>
                    <a href="#">내주변</a>
                    <a href="#">예약내역</a>
                    <a href="#">더보기</a>
                    <a href="#">로그인</a>
                </li>
            </ul>
        </div>
<%--    </section>--%>
</header>
</body>
</html>