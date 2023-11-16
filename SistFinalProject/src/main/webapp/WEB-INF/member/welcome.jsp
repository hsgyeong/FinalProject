<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Dongle:wght@300&family=Gaegu:wght@300&family=Nanum+Pen+Script&family=Sunflower:wght@300&display=swap"
          rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
    <title>Insert title here</title>
    <style type="text/css">
    .t{
	font-family: 'Jalnan';
	color:#f7323f;
	text-align: center;
}

.bt{
	font-family: 'Jalnan';
	color: #f7323f;
	float: right;
}
    </style>
</head>
<body>
<h3><div class="t">TRIVIEW</div></h3><br>
${member.name } 님 환영합니다.<br>
<button type="button" class="btn" onclick="location.href='/'">메인으로</button>
</body>
</html>