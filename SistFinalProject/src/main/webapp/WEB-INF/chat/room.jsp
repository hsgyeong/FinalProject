<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <meta charset="UTF-8">
    <title>Room</title>
    <style>
        *{
            margin:0;
            padding:0;
        }
        .container{
            width: 500px;
            margin: 0 auto;
            padding: 25px
        }
        .container h1{
            text-align: left;
            padding: 5px 5px 5px 15px;
            color: #FFBB00;
            border-left: 3px solid #FFBB00;
            margin-bottom: 20px;
        }
        .roomContainer{
            background-color: #F6F6F6;
            width: 500px;
            height: 500px;
            overflow: auto;
        }
        .roomList{
            border: none;
        }
        .roomList th{
            border: 1px solid #FFBB00;
            background-color: #fff;
            color: #FFBB00;
        }
        .roomList td{
            border: 1px solid #FFBB00;
            background-color: #fff;
            text-align: left;
            color: #FFBB00;
        }
        .roomList .num{
            width: 75px;
            text-align: center;
        }
        .roomList .room{
            /*width: 350px;*/
        }
        .roomList .go{
            width: 71px;
            text-align: center;
        }
        button{
            background-color: #FFBB00;
            font-size: 14px;
            color: #000;
            border: 1px solid #000;
            border-radius: 5px;
            padding: 3px;
            margin: 3px;
        }
        .inputTable th{
            padding: 5px;
        }
        .inputTable input{
            width: 330px;
            height: 25px;
        }
    </style>
</head>

<script type="text/javascript">


</script>
<body>
<div class="container">
    <h1>채팅방</h1>
    <div id="roomContainer" class="roomContainer">
        <table id="roomList" class="roomList">
            <tr>
                <th class="num">번호</th>
                <th class="room">고객 ID</th>
                <th>chatting 생성 날짜</th>
                <th class="go"></th>
            </tr>
            <c:if test="${sessionScope.admin_id!=null}">
                <c:forEach var="chatRoomDto" items="${chatRoomDtoList}" varStatus="i">
                    <tr>
                        <td>
                            ${chatRoomDto.roomnumber}
                        </td>
                        <td>
                            ${chatRoomDto.senderid}
                        </td>
                        <td>
                            ${chatRoomDto.roomcreateday}
                        </td>
                    </tr>
                </c:forEach>
            </c:if>

        </table> <%-- 채팅방목록은 여기에 표시됩니다. 방은 서버 응답을 기반으로 JavaScript를 사용하여 동적으로 채워집니다. --%>
    </div>
    <div>
        <table class="inputTable">
            <tr>
                <th>방 제목</th>
                <th><input type="text" name="roomName" id="roomName"></th>
                <th><button id="createRoom">방 만들기</button></th>
            </tr>
        </table>
    </div>
</div>


</body>
</html>