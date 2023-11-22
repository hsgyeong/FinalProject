<%@page import="sist.last.dto.MemberDto"%>
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
    .m {
    font-size: 12px;
    color: gray;
    }
    
    .x { 
    text-decoration:none;
    color: gray;
    cursor: pointer;
    }
    
    table {
		margin: auto;
	}
	
	.img {
	margin-left: 100px;
	}
	
	.id, .nickn, .hp, .email, .birth{
	font-size: 24px;
	}
	
	.in {
	text-align: center;
	}
	
	.update {
	text-decoration: none;
	font-weight: bold;
	color:black;
	}
	
	.out {
	text-decoration: none;
	color:black;
	}
    </style>
</head>
<body>
<div class="in"><b>내 정보</b></div>
<br>

<table>
	<tr>
		<td>
			<img alt="" src="../loginsave/s7.png"  
			class="img" width="150px;" height="150px;"> 
		</td>
	</tr>
	<tr class="id">	
		<td>
		<br><br>
		아이디
		&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;
		 ${memberDto.info_id }
		<br><br>
		</td>
	</tr>
	<tr class="nickn">
		<td>
		닉네임
		&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;
		${memberDto.info_nickname }
		<br><br>
		</td>
	</tr>
	<tr class="hp">
		<td>
		연락처
		&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;
		 ${memberDto.info_hp }
		<br><br>
		</td>
	</tr>
	<tr class="email">
		<td>
		이메일
		&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;
		 ${memberDto.info_email }
		<br><br>
		</td>
	</tr>
	<tr class="birth">
		<td>
		생년월일
		&nbsp;&nbsp;
		&nbsp;&nbsp;
		 ${memberDto.info_birth }
		<br><br>
		</td>
	</tr>
	<br>
	<tr>
		<td>
		<hr>
		<span class="m">개인정보 보호를 위해 내 정보는 모두 안전하게 암호화됩니다.</span>
		<br>
		<hr>
		</td>
	</tr>
	<tr>	
		<td>
		<a href="/member/member-update?info_id=${memberDto.info_id }" class="update">정보수정</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a class="out" href="/login/logout">로그아웃</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a class="x" id="x" name="x" value="${memberDto.info_id }">회원탈퇴</a>
		</td>
	</tr>
	</table>
	
	<!-- The Modal -->
<div class="modal" id="deleteModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h5 class="modal-title">회원탈퇴</h5>
        <button type="button" class="btn-close cs" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
      정말 탈퇴하시겠습니까?
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
      
      <button type="button" class="btn btn-danger" id="btndelete"
      onclick="location.href=/member/delete-member">회원탈퇴</button>
        <button type="button" class="btn btn-success cc" data-bs-dismiss="modal">취소</button>
      </div>
      
  </div>
 </div>
 </div> 
     
</body>
<script>
$(document).ready(function(){
	
	$(document).on("click",".x",function(){
	
	//info_id = $("#x").attr("value");
	//info_id = "${sessionScope.myid}";
	//alert(info_id);
	
	$("#deleteModal").show();
	
	}); 
	
	/*$.ajax({
		
		type:"get",
		dataType:"html",
		url:"member/delete-member",
		data:{"info_id":info_id},
		success(function(){
				
		})
	})*/


	$(document).on("click","#btndelete",function(){

		info_id = "${sessionScope.myid}";
		
		//alert(info_id);
		
		$.ajax({
			
			type:"get",
			dataType:"html",
			url:"/member/delete-member",
			data:{"info_id":info_id},
			success:function(){
			}
			})
		
		
	$("#deleteModal").hide();	
	
	setTimeout(function(){
	alert("안녕히가세요!");
	
	window.location.href="/login/logout";
		
	},300)	
})

		
	$(document).on("click",".cs",function(){
		
		$("#deleteModal").hide();
		
	})
	
	$(document).on("click",".cc",function(){
		
		$("#deleteModal").hide();
		
	})
});
</script>
</html>