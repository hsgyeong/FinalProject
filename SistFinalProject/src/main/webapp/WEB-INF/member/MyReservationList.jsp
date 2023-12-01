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
    
    .detail_room_select {
     border: 1px solid silver;
	border-radius: 1px;
	margin: auto;
    }
    
    .list {
    border: 1px solid silver;
	border-radius: 1px;
    }
    
    .detail_room_reserve {
    margin-top: 30px;
    margin-left: 50px;
    
    }
    
    .room_detail_reserve_btn {
    color: white;
	background-color: #f7323f;
	border: none;
	border-radius: 3px;
	width: 100%;
	height: 7vh;
	margin-top: -2vh;
	box-shadow: 2px 2px 2px silver;
    }
    
    .reserve_list {
    width:800px;
    float: right;
    
    }
    </style>
</head>
<script type="text/javascript">
$(document).ready(function(){
	
	$(".room_detail_reserve_btn").click(function(){
		
		var reserve_id = $("#reserve-cancle").val();
		
		var ans = confirm("예약 취소하시겠습니까?");
		//alert(reserve_id);		
		if(ans){
	
	$.ajax({
		
		type:"get",
		url:"update-reserve",
		dataType:"html",
		data:{"reserve_id":reserve_id},
		success:function(res){
			
			alert("예약 취소되었습니다.");
			history.back();
			
		}
		
	})
		
	});
	
})

</script>
<body>

<h4><b>예약내역</b></h4>
<br><br><br>
<c:forEach items="${list }" var="reserveDto">
		<table class="table table-bordered list">
		
		<div class="detail_room_select">
		
			<div class="detail_room_reserve">
			<div class="reserve_list">
				
				 <div class="reserve_accom">예약자&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${reserveDto.reserve_name }</span> </div><br>
			
				<div class="reserve_room">숙소타입&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${reserveDto.room_name }</span> </div><br>
				
				<div class="reserve_checkin">체크인</div>
				${reserveDto.reserve_checkin }
				
				<br><div class="reserve_checkout"><br>체크아웃</div>
				${reserveDto.reserve_checkout }<br>
			
				<div class="reserve_amount">
					<br><fmt:formatNumber value="${reserveDto.reserve_amount }" />
					원
				</div>
				
				<br>
				<div class="status" style="color:green">${reserveDto.reserve_status }</div>
				<!--  <div class="detail_room_select_checkin">
					입실시간 <b>${rdto.room_checkin }시부터</b>
				</div>
				<div class="detail_room_select_checkout">
					퇴실시간 <b>익일 ${rdto.room_checkout }시</b>
				</div>-->
				<div align="center">
					<br>
						<button type="button" class="room_detail_reserve_btn" id="reserve-cancle"
						value="${reserveDto.reserve_id}">예약취소</button>
				</div>
				
			</div>
			</div>
			
		</div>
		</table>
	</c:forEach>

</body>
</html>