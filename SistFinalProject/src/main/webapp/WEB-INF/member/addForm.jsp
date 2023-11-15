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
    <script type="text/javascript">
$(function(){
	
$("#idchk").click(function(){
	
	var id = $("#id").val();
	//alert(id);
	
	$.ajax({
		
		type:"get",
		dataType:"json",
		url:"idcheck",
		data:{"id":id}, 
		success:function(res){
			alert(id);
			
		}
			/*if(res.count==0){
		
				alert("사용 가능한 아이디입니다.");
		}else{
			
				alert("사용중인 아이디입니다. 다시 입력해주세요");			
		}*/
	}) 
});
	
});
</script>
</head>
<body>
<form action="member-insert" method="post">
<table class="table table-bordered">
	<caption align="top"><h4>회원가입</h4></caption>
	<tr>
	<td>
	아이디<br>
	<input type="text" name="id" id="id" class="form-control"  required="required"
	style="width:320px;"  placeholder="아이디를 입력해주세요">
	<button type="button" class="btn btn-info" id="idchk">중복체크</button>
	비밀번호<br>
	<input type="password" name="pass" class="form-control"  required="required"
	style="width:320px;" placeholder="비밀번호를 입력해주세요">
	닉네임<br>
	<input type="text" name="nickname" class="form-control" required="required"
	style="width:320px;" placeholder="닉네임을 입력해주세요">
	이름<br>
	<input type="text" name="name" class="form-control" required="required"
	style="width:320px;" placeholder="이름을 입력해주세요">
	휴대폰<br>
	<select style="width: 80px; height:31px;" name="hp">
	<option value="010" selected="selected">010</option>
	<option value="011">011</option>
	<option value="017">017</option>
	<option value="018">018</option>
	<option value="019">019</option>
	</select>
	-
	<input type="text" name="hp2"  class="form-conrol" required="required" style="width:80px;">
	-
	<input type="text" name="hp3"  class="form-conrol" required="required" style="width:80px;">
	생년월일 
	<inpyt type="date" name="birth">
	</td>
	</tr>
</table>
<button type="submit" class="btn">회원가입</button>
</form>
</body>
</html>