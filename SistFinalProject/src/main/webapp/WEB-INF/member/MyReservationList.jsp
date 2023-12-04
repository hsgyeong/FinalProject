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
    <script type="text/javascript">
$(document).ready(function(){
	
	$(".room_detail_reserve_btn").click(function(){
		
		var reserve_id = $("#reserve-cancle").val();
		var reserve_status = "예약취소";
		
		var ans = confirm("예약 취소하시겠습니까?");
		//alert(reserve_id);		
		if(ans){
	
	$.ajax({
		
		type:"post",
		url:"update-reserve",
		dataType:"html",
		data:{
			"reserve_id":reserve_id,
			"reserve_status":reserve_status
		},
		success:function(res){
			
			alert("예약 취소되었습니다.");
			history.back();
			
		}
		
	})
		
	}
	
})

})
</script>
    <style type="text/css">
    .reserve_checkin, .reserve_checkout {
    font-weight: bold;
	font-size: 2vh;
    }
    
    .reserve_status {
    font-weight: bold;
    
    }
    
    .detail_room_select {
	border-radius: 1px;
	margin: auto;
    }
    
    .reservelist {

	border-radius: 5px;
	width:800px;
    height:400px;
    
    }
    
    .detail_room_reserve {
    margin: auto;
	border-radius: 5px;
	width:800px;
    height:600px;
    margin-top: 50px;
 
    }
    
    .room_detail_reserve_btn {
    color: white;
	background-color: #f7323f;
	border: none;
	border-radius: 3px;
	width: 400px;
	height: 7vh;
	box-shadow: 2px 2px 2px silver;
    }
    
    .reserve_list {
    width:800px;
    height:300px;
    margin-top: 50px;
    margin-left: 80px;
    float:right;
    }
    
    .cancel_bt {
    text-align: center;
    }
    
    #roomimg {
    
    }
    </style>
</head>
<body>
<h4><b>예약내역</b></h4>
<br><br><br>
<table class="table table-bordered reservelist">
<c:forEach items="${list }" var="reserveDto" varStatus="i">
												<!-- i.count는 몇번쨰인지 알려줌. varStatus는 첫번째가 0번째임  -->
		
		<div class="detail_room_select">
			<div class="detail_room_reserve" style="display: flex;">
			<div class="img" style="float:left">
			<c:forEach items="${photoList }" var="photo" varStatus="j">  <!-- list의 순번과 photoList의 순번이 같을때 일치하는 photo출력  -->
			<c:if test="${i.count==j.count }">
			<img alt="" src="../roomsave/${photo }"
			style="width:400px; height: 380px; margin-top: 50px;" id="roomimg">
			</c:if>
			</c:forEach>
			</div>
			<div class="reserve_list">
				
				 <div class="reserve_accom">예약자&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px;">${reserveDto.reserve_name }</span> </div><br>
			
				<div class="reserve_room">숙소타입&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px;">${reserveDto.room_name }</span> </div><br>
				
				<div class="reserve_checkin" style="margin-bottom: 10px;">체크인</div>
				<span style="font-size: 20px;">${reserveDto.reserve_checkin }</span>
				
				<br><div class="reserve_checkout"  style="margin-bottom: 10px;"><br>체크아웃</div>
				<span style="font-size: 20px;">${reserveDto.reserve_checkout }</span><br>
				<br>			
				<div class="reserve_amount">
				결제금액&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-size: 20px;"><fmt:formatNumber value="${reserveDto.reserve_amount }"/>원</span>
				</div>
				
				<br>
				<div class="status" style="color:green; font-size: 30px; font-weight: bold;">${reserveDto.reserve_status }</div>
				
				<div class="cancel_bt" align="center">
					<br>
						<button type="button" class="room_detail_reserve_btn" id="reserve-cancle"
						value="${reserveDto.reserve_id}">예약취소</button>
				</div>
			</div>
			</div>
			
		</div>
		
	</c:forEach>
</table>
</body>
</html>