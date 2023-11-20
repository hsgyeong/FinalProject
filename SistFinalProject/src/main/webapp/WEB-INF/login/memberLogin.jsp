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
</head>
<body>
	<form action="memberLogin">
			<tr>
				<td>
				<br>
				<div class="p">
				<span class="id">아이디
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" name="id" style="width: 200px;"
					class="form-control" required="required"></span>
					<br><br>
				<span class="pass">비밀번호&nbsp;&nbsp;&nbsp;
				<input type="password" name="pass" style="width: 200px;"
					class="form-control" required="required"></span>
					</div>	
				</td>
			</tr>
	</form>		
</body>
</html>