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
<style type="text/css">
.accom_insert_box{
	width: 100%;
	height: 60vh;
	margin-top: 3.5vh;
}

.accom_insert_box table{
	width: 80vh;
}

.accom_insert_box table tr td{
	padding: 2.5vh 2.5vh;
}

.accom_insert_box textarea{
	width: 55vh;
	height: 25vh;
}

.accom_insert_btn1{
	width: 11vh;
	height: 6vh;
	border: none;
	color: white;
	font-weight: bold;
	background-color: rgb(23,93,238);
	border-radius: 1vh;
}

.accom_insert_btn2{
	width: 11vh;
	height: 6vh;
	border: 0.5px solid silver;
	color: black;
	font-weight: bold;
	background-color: #e9e9e9;
	border-radius: 1vh;
	margin-left: 1vh;
}
</style>
<title>Insert title here</title>
</head>
<body>
<form action="insert">
<div class="accom_insert_box" align="center">
<input type="hidden" name="business_id" value="test">
<table class="table table-bordered">

	<tr>
		<td align="center" valign="middle" colspan="2" style="font-size: 1.5em;"><b>숙소정보등록</b></td>
	</tr>
	<tr>
		<td align="center" valign="middle"><b>숙소이름</b></td>
		<td valign="middle">
			<input type="text" class="form-control" name="accom_name" required="required"
			placeholder="ex)신라호텔, 조선호텔" style="width: 30vh; height: 5vh;">
		</td>
	</tr>
	
	<tr>
		<td align="center" valign="middle"><b>카테고리</b></td>
		<td valign="middle">
			<select class="form-control" name="accom_category" required="required"
			style="width: 8vh; height: 5vh;">
				<option value="호텔">호텔</option>
				<option value="모텔">모텔</option>
				<option value="펜션">펜션</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<td align="center" valign="middle"><b>숙소사진</b></td>
		<td valign="middle">
			<input type="file" class="form-control" name="photo" required="required"
			style="width: 40vh; height: 5vh;">
		</td>
	</tr>

	<tr>
		<td align="center" valign="middle"><b>숙소위치</b></td>
		<td valign="middle">
			<input type="text" class="form-control" name="accom_location" required="required"
			placeholder="ex)경기도 부천시, 부산시 사하구" style="width: 50vh; height: 5vh;">
		</td>
	</tr>
	
	<tr>
		<td align="center" colspan="2">
			<button type="submit" class="accom_insert_btn1">등록</button>
			<button type="submit" class="accom_insert_btn2"onclick="location.href='history.back()'">취소</button>
		</td>
	</tr>
</table>
</div>
</form>
</body>
</html>