<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Dongle:wght@300&family=Gaegu:wght@300&family=Nanum+Pen+Script&family=Sunflower:wght@300&display=swap" rel="stylesheet">
<link href="https://webfontworld.github.io/goodchoice/Jalnan.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
    <title>Insert title here</title>
    <style type="text/css">
        .mainbox{
            width: 100%;
            /*height: 300vh;*/
            border: 3px solid red;
            /*margin: 6vh 17.5%;*/
        }

        .mainad{
            width: 100%;
            /*height: 65vh;*/
            border: 3px solid orange;
            margin-bottom: 50px;
        }

        .maincategory{
            width: 100%;
            /*height: 20vh;*/
            border: 3px solid blue;
            padding: 10px 10px 10px 10px;
            font-family: 'Jalnan';
            display: flex;
            justify-content: center;


        }
        div.maincategory img{
            width: 100px;
            margin: 0px 70px 0px 70px;
        }
        div.mainnews{
            font-family: Jalnan;
            width: 100%;

        }
    </style>
</head>
<c:set var="root" value="<%=request.getContextPath() %>"/>
<body>
<div class="mainbox" align="center">

    <div class="mainad">
        <img src="${root}/dabang.png" style="width: 100%;">
    </div>

    <div class="maincategory d-inline-flex">
        <a href="#"><figure><img src="${root}/hotel.jpeg"><figcaption>호텔</figcaption></figure></a>
        <a href="#"><figure><img src="${root}/bed.png"><figcaption>모텔</figcaption></figure></a>
        <a href="#"><figure><img src="${root}/pension.jpeg"><figcaption>펜션</figcaption></figure></a>
    </div>



    <div class="mainnews">
        <h3>TREVIEW 소식</h3>

    </div>

    <div></div>
</div>
</body>
</html>