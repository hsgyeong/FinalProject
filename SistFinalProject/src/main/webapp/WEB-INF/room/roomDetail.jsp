<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=VT323&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>Insert title here</title>
</head>
<body>
${dto.room_num }<br>
${dto.accom_num }<br>
${dto.room_name }<br>
${dto.room_price }<br>
${dto.room_minpeople }<br>
${dto.room_maxpeople }<br>
${dto.room_location }<br>
${dto.room_photo }<br>
${dto.room_info }<br>
${dto.room_count }<br>
${dto.room_checkin }<br>
${dto.room_checkout }<br>

<button type="button" onclick="location.href='/reserve/reserve-form?room_num=${dto.room_num}'">예약</button>
</body>
</html>