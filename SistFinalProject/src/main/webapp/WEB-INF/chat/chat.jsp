<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css2?family=Bagel+Fat+One&family=Dongle:wght@300&family=East+Sea+Dokdo&family=Gamja+Flower&family=Gowun+Dodum&family=Nanum+Gothic+Coding&family=Nanum+Pen+Script&family=Orbit&display=swap"
          rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
    <title>Insert title here</title>
</head>
<body>
    <div id="chat">
        <h1>WebSocket Chatting</h1>
        <input type="text" id="mid" value="홍길동">
        <input type="hidden" value="로그인" id="btn-login">
        <br>
        <div id="talk"></div>
        <div id="send-zone">
            <textarea id="msg" value="gdgd"></textarea>
            <input type="button" value="전송" id="btn-send">
        </div>
        <script src="./js/chat.js"></script>
    </div>





    <h1>채팅 하고 싶어요 ㅠㅠ 제발 하게 해주세요 ㅠㅠ</h1>
</body>
</html>