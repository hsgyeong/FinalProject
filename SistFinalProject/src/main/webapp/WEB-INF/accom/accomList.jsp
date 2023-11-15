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
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>Insert title here</title>
<style type="text/css">
.scrolling-text-container {
    width: 100%;
    overflow: hidden;
    position: relative;
    background-color: none;
    height: 12vh;
    font-size: 1.5em;
}

.scrolling-text {
    white-space: nowrap;
    animation: scroll 30s linear infinite;
    color: black;
}

@keyframes scroll {
    0% {
        transform: translateX(0%);
    }
    100% {
        transform: translateX(100%);
    }
}

.accom_list_box {
    
}

.accom_list_box table {
    width: 100%;
}
</style>
</head>
<body>
<div class="scrolling-text-container">
    <div class="scrolling-text">총 ${totalCount }개의 숙소가 등록되어 있어요!</div>
</div>

<div class="accom_list_box" align="center">
    <table class="table table-bordered">
        <c:if test="${totalCount > 0 }">
            <tr>
                <td align="center" style="width: 10%; height: 5vh;">카운트</td>
                <td align="center" style="width: 20%; height: 5vh;">숙소이름</td>
                <td align="center" style="width: 15%; height: 5vh;">숙소종류</td>
                <td align="center" style="width: 25%; height: 5vh;">숙소사진</td>
                <td align="center" style="width: 30%; height: 5vh;">숙소위치</td>
            </tr>
            <c:forEach var="dto" items="${list}" varStatus="i">
                <tr>
                    <td align="center" valign="middle" style="width: 10%; height: 15vh;">${i.count }</td>
                    <td align="center" valign="middle" style="width: 20%; height: 15vh;"></td>
                    <td align="center" valign="middle" style="width: 15%; height: 15vh;"></td>
                    <td align="center" valign="middle" style="width: 25%; height: 15vh;">
                        <img src="${dto.accom_photo}" alt="">
                    </td>
                    <td align="center" valign="middle" style="width: 30%; height: 15vh;"></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
</body>
</html>
