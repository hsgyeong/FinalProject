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
    .reserve_checkin, .reserve_checkout {
    font-weight: bold;
	font-size: 2vh;
    }
    
    .reserve_status {
    font-weight: bold;
    
    }
    </style>
</head>
<body>
<table>
<h4><b>예약내역</b></h4>
<c:forEach items="${list }" var="reserveDto">
		<div class="detail_room_select">
		<!--	<div class="detail_room_select_img">
				<img src="../roomsave/${reserveDto.room_photo.split(',')[0]}" alt="">
			</div> -->
			<div class="detail_room_reserve">
				<div class="reserve_accom">${reserveDto.reserve_name }</span> </div><br><br>
				<div class="reserve_room">${reserveDto.room_name }</span> </div>
				<div class="reserve_checkin">체크인</div><br>
				${reserveDto.reserve_checkin }
				<div class="reserve_checkout">체크아웃</div><br>
				${reserveDto.reserve_checkout }
				<div></div>
				<div class="reserve_amount" align="right">
					<fmt:formatNumber value="${reserveDto.reserve_amount }" />
					원
				</div>
				<br>
				<div class="status">${reserveDto.reserve_status }</div>
				<!--  <div class="detail_room_select_checkin">
					입실시간 <b>${rdto.room_checkin }시부터</b>
				</div>
				<div class="detail_room_select_checkout">
					퇴실시간 <b>익일 ${rdto.room_checkout }시</b>
				</div>-->
				<div align="center">
					<br>
						<button type="button" class="room_detail_reserve_btn"
							onclick="location.href='/reserve/reserve-cancle?reserve_id=${reserveDto.reserve_num}'">예약취소</button>
				</div>
			</div>
		</div>
	</c:forEach>
</table>
</body>
</html>