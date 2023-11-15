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
    <link href="https://webfontworld.github.io/goodchoice/Jalnan.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
    <title>Insert title here</title>
<style>
.login {
	display: flex;
	margin-left:140px;
	justify-content: center;
	margin-top: 100px;
}
.id{
	display:inline-flex;
}
.pass{
	display: inline-flex;	
}

.btn.loginbt{
	width:290px;
	height:40px;
	border-radius: 5px;
	background-color: #f7323f;
	color:white;
}
.a{
	color: black;
	text-decoration: none;
}
.s{
	color: black;
	text-decoration: none;
}
.logtitle{
	font-family: 'Jalnan';
	text-align: center;
	margin-top: 20px;
	color: #f7323f;
}
.tb{
	display: flex;
	flex-direction: column;
	align-items:center;
	margin:auto;
	border: 0;
}

.tb td{
	padding: 10px;
	border-bottom: none;
}
.bts{
	display: flex;
	flex-direction: column;
	align-items:center;
	margin:auto;
}
.btnbt{
	display: inline-flex;
}
</style>
</head>
<% 
String id = (String)session.getAttribute("id");
String saveok = (String) session.getAttribute("saveok");

boolean save = true;

if(saveok==null)
{
	id="";
	save=false;
}else{
	save=true;
	
}

%>
<body>
	<div class="login">
		<form action="login-insert" method="post">
			<table class="table .table-borderless tb" style="width: 700px;">
			<tr>
				<td>
				<div class="logtitle">
				<h2>TRIVIEW</h2>
				</div>
				<br><br><br>
				<span class="id">아이디
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" name="id" style="width: 200px;"
					class="form-control" required="required"></span>
					</td>
				</tr>
			<tr>
				<td>
				<span class="pass">비밀번호&nbsp;&nbsp;&nbsp;
				<input type="password" name="pass" style="width: 200px;"
					class="form-control" required="required"></span>
					</td>
			</tr>		
		</table>
			<br>
			<div class="bts">
			<button type="submit" class="btn loginbt">
			<i class="bi bi-door-open"></i>&nbsp;&nbsp;로그인</button><br><br>
			<div class="btnbt">
			<a class="s" href="location.href='search'">아이디/비밀번호 찾기</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="a" href="/member/joinform"><b>회원가입</b></a>
			</div>
			</div>
		</form>
	</div>
</body>
</html>