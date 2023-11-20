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
    <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
    <style type="text/css">
        .room_insert_box {
            width: 100%;
            height: 113vh;
            margin-top: 3.5vh;
        }

        .room_insert_box table {
            width: 80vh;
        }

        .room_insert_box table tr td {
            padding: 2.5vh 2.5vh;
        }

        .room_insert_box textarea {
            width: 55vh;
            height: 25vh;
        }

        .room_insert_btn1 {
            width: 11vh;
            height: 6vh;
            border: none;
            color: white;
            font-weight: bold;
            background-color: rgb(23, 93, 238);
            border-radius: 1vh;
        }

        .room_insert_btn2 {
            width: 11vh;
            height: 6vh;
            border: 1px solid silver;
            color: black;
            font-weight: bold;
            background-color: white;
            border-radius: 1vh;
            margin-left: 1vh;
        }
    </style>
    <script>
        //이벤트 핸들러
        $(document).ready(function () {

            $("select[name='room_minpeople']").change(function () {
                var minPeople = parseInt($(this).val());
                var maxPeople = parseInt($("select[name='room_maxpeople']").val());

                if (minPeople > maxPeople) {
                    $("select[name='room_maxpeople']").val(minPeople);
                }
            });

            $("select[name='room_maxpeople']").change(function () {
                var maxPeople = parseInt($(this).val());
                var minPeople = parseInt($("select[name='room_minpeople']").val());

                if (maxPeople < minPeople) {
                    $("select[name='room_minpeople']").val(maxPeople);
                }
            });
        });
    </script>
    <title>Insert title here</title>
</head>
<body>
<form action="update" method="post" enctype="multipart/form-data">
<input type="hidden" name="room_num" value="${dto.room_num }">
<div class="room_insert_box" align="center">
<table class="table table-bordered">
	<tr>
		<td align="center" valign="middle" colspan="2" style="font-size: 1.5em;"><b>방정보등록</b></td>
	</tr>
	<tr>
		<td align="center" valign="middle"><b>이름</b></td>
		<td valign="middle">
			<input type="text" class="form-control" name="room_name" required="required"
			placeholder="ex)디럭스, 스탠다드" style="width: 30vh; height: 5vh;" value="${dto.room_name }">
		</td>
	</tr>
	
	<tr>
		<td align="center" valign="middle"><b>가격</b></td>
		<td valign="middle">
			<input type="text" class="form-control" name="room_price" required="required"
			style="width: 25vh; height: 5vh;" value="${dto.room_price }">
		</td>
	</tr>
	
	<tr>
		<td align="center" valign="middle"><b>인원</b></td>
		<td valign="middle" style="display: flex; border-left: none;">
			<select class="form-control" name="room_minpeople" required="required"
			style="width: 5vh; height: 5vh;">
				<option value="1" ${dto.room_minpeople eq '1' ? 'selected' : ''}>1</option>
				<option value="2" ${dto.room_minpeople eq '2' ? 'selected' : ''}>2</option>
				<option value="3" ${dto.room_minpeople eq '3' ? 'selected' : ''}>3</option>
				<option value="4" ${dto.room_minpeople eq '4' ? 'selected' : ''}>4</option>
				<option value="5" ${dto.room_minpeople eq '5' ? 'selected' : ''}>5</option>
				<option value="6" ${dto.room_minpeople eq '6' ? 'selected' : ''}>6</option>
				<option value="7" ${dto.room_minpeople eq '7' ? 'selected' : ''}>7</option>
				<option value="8" ${dto.room_minpeople eq '8' ? 'selected' : ''}>8</option>
			</select>
			
			<b style="padding-top: 0.7vh;">&nbsp;~&nbsp;</b>
			
			<select class="form-control" name="room_maxpeople" required="required"
			style="width: 5vh; height: 5vh;">
				<option value="1" ${dto.room_maxpeople eq '1' ? 'selected' : ''}>1</option>
				<option value="2" ${dto.room_maxpeople eq '2' ? 'selected' : ''}>2</option>
				<option value="3" ${dto.room_maxpeople eq '3' ? 'selected' : ''}>3</option>
				<option value="4" ${dto.room_maxpeople eq '4' ? 'selected' : ''}>4</option>
				<option value="5" ${dto.room_maxpeople eq '5' ? 'selected' : ''}>5</option>
				<option value="6" ${dto.room_maxpeople eq '6' ? 'selected' : ''}>6</option>
				<option value="7" ${dto.room_maxpeople eq '7' ? 'selected' : ''}>7</option>
				<option value="8" ${dto.room_maxpeople eq '8' ? 'selected' : ''}>8</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<td align="center" valign="middle"><b>방갯수</b></td>
		<td valign="middle">
			<select class="form-control" name="room_count" required="required"
			style="width: 5vh; height: 5vh;">
				<option value="1" ${dto.room_count eq '1' ? 'selected' : ''}>1</option>
				<option value="2" ${dto.room_count eq '2' ? 'selected' : ''}>2</option>
				<option value="3" ${dto.room_count eq '3' ? 'selected' : ''}>3</option>
				<option value="4" ${dto.room_count eq '4' ? 'selected' : ''}>4</option>
				<option value="5" ${dto.room_count eq '5' ? 'selected' : ''}>5</option>
			</select>
		</td>
	</tr>
	
	<tr>
		<td align="center" valign="middle"><b>사진</b></td>
		<td valign="middle">
			<input type="file" class="form-control" name="photo" required="required"
			style="width: 40vh; height: 5vh;">
		</td>
	</tr>

            <tr>
                <td align="center" valign="middle"><b>가격</b></td>
                <td valign="middle">
                    <input type="text" class="form-control" name="room_price" required="required"
                           style="width: 25vh; height: 5vh;" value="${dto.room_price }">
                </td>
            </tr>

	
	<tr>
		<td align="center" valign="middle"><b>방정보소개</b></td>
		<td valign="middle">
			<textarea class="form-control" name="room_info" required="required">${dto.room_info }</textarea>
		</td>
	</tr>
	
	<tr>
		<td align="center" colspan="2">
			<button type="submit" class="room_insert_btn1">등록</button>
			<button type="button" class="room_insert_btn2"onclick="location.href='history.back()'">취소</button>
		</td>
	</tr>
</table>
</div>
</form>
</body>
</html>