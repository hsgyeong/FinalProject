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
<style type="text/css">
	div.right-side{
		width: 30%;
		height: 80%;
		float: right;
		background-color: #FAFAFA;
	}
	div.right-inside{
		padding: 20px 10px 0px 10px;
	}
	div.right-category{
		color: #CFCFCF;
	}
	span.vat{
		color: #333333;
		font-size: 0.9em;
	}
	b.totalpay{
		color: #DB0F56;
		font-size: 1.4em;
	}
	p.explain{
		color: #9C9C9C;
		font-size: 0.8em;
	}
	button.btnpay{
		background-color: #EE0057;
		width: 100%;
		height: 50px;
		color: white;
		border: none;
		font-size: 0.8em;
	}
	
	div.left-side{
		float: left;
	}
	
	div.left-side input::placeholder {
 		color: #BDBDBD;
	}
	
	.left-category{
		color: #707070;
	}
	
	.btn-discount, .btn-point, .btn-giftcard, .payment{
		border: 1px solid lightgray;
		padding: 10px 10px 10px 10px;
		border-radius: 5px;
		background-color: #FAFAFA;
		color: #707070;
	}
	
	span.count {
		margin-left: 5px;
	}
	
	input.point{
		border: 1px solid lightgray;
		padding: 10px 10px 10px 10px;
		color: #707070;
		width: 150px;
	}
	
	.agreement-chkbox a{
		color: #707070;
	}
</style>
</head>
<body>
	<form action="#" method="post">
		<div class="left-side">
			<div>
				<b>예약자 정보</b>
			</div>
			<br>

			<div class="left-category">예약자 이름</div>
			<input type="text" class="form-control" placeholder="체크인시 필요한 정보입니다" style="width: 500px; height: 50px;">
			<br><br>
			
			<div class="left-category">
				휴대폰 번호<br>
				<div style="font-size: 0.8em;">개인 정보 보호를 위해 안심번호로 숙소에 전송됩니다.</div>
			</div>
			<br>
			
			<input type="text" class="form-control" value="010-9773-7503">
			<br>
			<hr>
			<br>
			
			<b>할인 수단 선택</b>
			<br><br>
			
			<div>
				<span class="left-category">구매 총액</span>
				<span style="float: right;"><b>199,100원</b></span>
			</div>
			<br>
			
			<div>
				<button type="button" class="btn-discount">사용 가능 쿠폰<span class="count">0장</span></button>
				<span style="float: right;"><b>-</b></span>
			</div>
			<br>
			
			<div class="left-category">
				<span style="font-size: 15px;">일반쿠폰</span>
				<span style="float: right;">-</span>
			</div>
			<br>
			
			<div class="left-category">
				<span style="font-size: 15px;">더하기 쿠폰</span>
				<span style="float: right;">-</span>
			</div>
			<br>
			
			<div>
				<button type="button" class="btn-point">포인트 사용<span class="count">0장</span></button>
				<span style="float: right;">
					<input type="text" value="0" style="text-align: right;" class="point">&nbsp;<b>P</b>
				</span>
			</div>
			<br>
			
			<div>
				<button type="button" class="btn-giftcard">상품권 사용</button>
				<span style="float: right;"><b>-</b></span>
			</div>
			<br>
			<hr>
			<br>
			
			<b>결제수단 선택</b>
			<br><br>
			
			<div>
				<select class="payment">
					<option value="kakaopay">카카오페이</option>
					<option value="tosspay">토스페이</option>
					<option value="card">신용/체크 카드</option>
					<option value="account">간편계좌이체</option>
					<option value="naverpay">네이버페이</option>
					<option value="payco">페이코</option>
					<option value="phonepay">휴대폰결제</option>
				</select>
			</div>
			<br>
			
			<div class="agreement-chkbox">
				<input type="checkbox">&nbsp;<b>전체동의</b> <br><br>
				<input type="checkbox">&nbsp;<a href="#">숙소이용규칙 및 취소/환불규정 동의</a> <span style="color: #EE0057;">(필수)</span><br><br>
				<input type="checkbox">&nbsp;<a href="#">개인정보 수집 및 이용 동의</a> <span style="color: #EE0057;">(필수)</span><br><br>
				<input type="checkbox">&nbsp;<a href="#">개인정보 제 3자 제공 동의</a> <span style="color: #EE0057;">(필수)</span>
			</div>
		</div>

		<div class="right-side">
			<div class="right-inside">
				<div class="right-category">숙소이름</div>
				<div>숙소이름 넣을 곳</div>
				<br>

				<div class="right-category">객실타입/기간</div>
				<div>숙소타입 넣을 곳 / 기간</div>
				<br>

				<div class="right-category">체크인</div>
				<div>체크인시간</div>
				<br>

				<div class="right-category">체크아웃</div>
				<div>체크아웃시간</div>
				<br>

				<hr style="width: 95%; margin-left: 5px;">
				<br> <b>총 결제 금액</b><span class="vat">(VAT포함)</span> <br> <b
					class="totalpay">198,000원</b> <br> <br>

				<p class="explain">
					• 해당 객실가는 세금, 봉사료가 포함된 금액입니다 <br> • 결제완료 후 예약자 이름으로 바로 체크인 하시면
					됩니다
				</p>
				<br>
			</div>

			<button type="submit" class="btnpay">결제하기</button>
		</div>
	</form>
</body>
</html>