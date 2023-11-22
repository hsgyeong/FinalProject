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
    <title>chat</title>
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
        .chating{
            background-color: #000;
            width: 500px;
            height: 500px;
            overflow: auto;
        }
        /*.chating p{*/
        /*    color: #fff;*/
        /*    text-align: left;*/
        /*}*/
        .chating .me{
            color: #F6F6F6;
            text-align: right;
        }
        .chating .others{
            color: #FFE400;
            text-align: left;
        }
        input{
            width: 330px;
            height: 25px;
        }
        #yourMsg{
            display: none;
        }
    </style>
</head>
<script>
    var ws;

    function wsOpen(){
        ws=new WebSocket("ws://"+location.host+"/chating");
        wsEvt();
    }
    function wsEvt(){
        ws.onopen=function (data){
            // 소켓이 열리면 동작
        }
        ws.onmessage=function (data){
            // 메시지를 받으면 동작
            var msg= data.data;
            if(msg!=null&&msg.trim()!=''){

                // json 적용을 위해 추가 ->
                var d=JSON.parse(msg);
                if (d.type=="getId"){
                    var si=d.sessionId!=null?d.sessionId:"";
                    if (si!=''){
                        $("#sessionId").val(si);
                    }
                } else if (d.type=="message"){
                    if (d.sessionId==$("#sessionId").val()){
                        $("#chating").append("<p class='me'>나: "+d.msg+"</p>" );
                    } else {
                        $("#chating").append("<p class='others'>"+d.userName+":"+d.msg+"</p>" );
                    }
                } else {
                    console.warn("unknown type!")
                }
                // <- json 적용을 위해 추가
            }
        }


        document.addEventListener("keypress",function (e){
           if(e.keyCode==13){ // enter press
               send();
           }
        });
    }
    function chatName(){
        var userName=$("#userName").val();
        if(userName==null||userName.trim()==""){
            alert("사용자 이름을 입력 해주세요.");
            $("#userName").focus();
        } else {
            wsOpen();
            $("#yourName").hide();
            $("#yourMsg").show();
        }
    }
    function send(){
        // var uN=$("#userName").val();
        // var msg=$("#chatting").val();
        // ws.send(uN+" : "+msg);
        // $("#chatting").val("");

        // json 적용을 위해 추가 ->
        var option ={
            type: "message",
            sessionId : $("#sessionId").val(),
            userName : $("#userName").val(),
            msg : $("#chatting").val()
        }
        ws.send(JSON.stringify(option))
        $("#chatting").val("");
        // <- json 적용을 위해 추가
    }


</script>
<body>
<div id="container" class="container">
    <h1>채팅</h1>
    <input type="hidden" id="sessionId" value="">
    <div id="chating" class="chating">
    </div>

    <div id="yourName">
        <table class="inputTable">
            <tr>
                <th>사용자명</th>
                <th><input type="text" name="userName" id="userName" placeholder="이름을 입력하세요"></th>
                <th><button onclick="chatName()" id="startBtn" class="btn btn-danger">이름 등록</button></th>
            </tr>
        </table>
    </div>
    <div id="yourMsg">
        <table class="inputTable">
            <tr>
                <th>메시지</th>
                <th><input id="chatting" placeholder="보내실 메시지를 입력하세요."></th>
                <th><button onclick="send()" id="sendBtn">보내기</button></th>
            </tr>
        </table>
    </div>
</div>

</body>
</html>




