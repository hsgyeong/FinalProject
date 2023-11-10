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
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
    <title>Insert title here</title>
    <style type="text/css">
        .mainbox{
            width: 65%;
            height: 300vh;
            border: 3px solid red;
            margin: 6vh 17.5%;
        }

        .mainad{
            width: 100%;
            height: 65vh;
            border: 3px solid orange;
        }

        .maincategory{
            width: 100%;
            height: 20vh;
            border: 3px solid blue;
        }
    </style>
</head>
<c:set var="root" value="<%=request.getContextPath() %>"/>
<body>
<div class="mainbox" align="center">

    <div class="mainad">광고</div>

    <div class="maincategory">카테고리</div>

    <div></div>
</div>
</body>
</html>