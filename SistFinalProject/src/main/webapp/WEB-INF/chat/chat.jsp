<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

    <script src="https://code.jquery.com/jquery-3.6.3.js"></script>
    <script type="text/javascript" src="/messagejscss/emoji_jk.js"></script>
    <script src="https://kit.fontawesome.com/16085d762f.js"
            crossorigin="anonymous"></script>
    <link href="/messagejscss/emoji_jk.css" type="text/css" rel="stylesheet">

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
            href="https://fonts.googleapis.com/css2?family=Jua&family=Stylish&family=Sunflower&display=swap"
            rel="stylesheet">
    <meta charset="UTF-8">
    <title>Chating</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        .container {
            width: 500px;
            margin: 0 auto;
            padding: 25px
        }

        .container h1 {
            text-align: left;
            padding: 5px 5px 5px 15px;
            color: #FFBB00;
            border-left: 3px solid #FFBB00;
            margin-bottom: 20px;
        }

        .chating {
            background-color: #000;
            width: 500px;
            height: 500px;
            overflow: auto;
        }

        .chating .me {
            color: #F6F6F6;
            text-align: right;
        }

        .chating .others {
            color: #FFE400;
            text-align: left;
        }

        input {
            width: 330px;
            height: 25px;
        }

        #yourMsg {
            display: none;
        }

        .msgImg {
            width: 200px;
            height: 125px;
        }

        .clearBoth {
            clear: both;
        }

        .img {
            float: right;
        }
    </style>
</head>

<script type="text/javascript">
    $(function () {
        wsOpen();
        listWidthChange();
        memberListOut();
        getChatting('${recentgruop}');
        $(".messagefilepreview").hide();
        $(".ttimsg").hide();
        $(".msgalarmcircle").hide();
        $(".allalarmcircle").css("margin-right", "95px");

        // 상단의 채팅중인 사람 num 변경

        // 상단의 채팅중인 사람 이름 변경

        // 상단의 채팅중인 사람 아이디 변경


        $(window).resize(function () {
            //창크기 변화 감지
            listWidthChange();
        });


    });


    var ws;
    function wsOpen() { // 웹 소켓을 연결하고, 연결이 열릴 때와 메시지를 수신할 때의 이벤트 핸들러를 정의합니다.
        //웹소켓 전송시 현재 방의 번호를 넘겨서 보낸다.
        // 웹 소켓 연결 URL을 생성합니다. 현재 방의 번호를 포함하여 서버에 연결합니다.
        // ws = new WebSocket("ws://" + location.host + "/chating/" + $("#roomNumber").val()); // 현재 호스트와 채팅 방 번호를 포함한 웹 소켓 연결 URL 을 나타냅니다.
        ws = new WebSocket("ws://" + location.host + "/chating"); // 현재 호스트와 채팅 방 번호를 포함한 웹 소켓 연결 URL 을 나타냅니다.

        // 웹 소켓 이벤트 핸들러를 설정하는 함수를 호출합니다.
        wsEvt(); // 함수 내부에서는 서버로부터 받은 메시지를 파싱하여 메시지 유형에 따라 다른 동작을 수행합니다.
    }

    function wsEvt() {
        ws.onopen = function (data) { // 웹 소켓 연결이 열릴 때 동작하는 부분
            // 예시: 연결이 열리면 어떤 작업을 수행하거나 초기화를 진행합니다.
        }

        ws.onmessage = function (data) {
            // 웹 소켓으로 메시지를 수신했을 때 동작하는 부분
            // 예시 : 서버로부터 받은 메시지에 따라 다양한 동작을 수행합니다.
            var msg = data.data;
            var msgJson = JSON.parse(msg);

            var nowGroup = $("#chatgroup").val(); // 현재 선택된 채팅방 그룹

            if (msgJson.upload != null && msgJson.upload != '' && '${user_num}' == msgJson.receiver) {
                if (msgJson.group == nowGroup) {
                    getChatting(nowGroup);
                }
                memeberListOut();
            }

            // if(msg != null && msg.trim() != ''){
            //     // 메시지가 비어있지 않으면 처리합니다.
            //
            //     // 서버에서 받은 메시지를 JSON 형태로 파싱합니다.
            //     var d = JSON.parse(msg);
            //     if(d.type == "getId"){
            //         // 서버로부터 받은 메시지의 타입이 "getId"인 경우
            //         // 세션ID를 가져와서 #sessionId 필드에 설정합니다.
            //         var si = d.sessionId != null ? d.sessionId : "";
            //         if(si != ''){
            //             $("#sessionId").val(si);
            //         }
            //     }else if(d.type == "message"){
            //         // 서버로부터 받은 메시지의 타입이 "message"인 경우
            //         // 해당 메시지를 채팅 창에 추가합니다.
            //         if(d.sessionId == $("#sessionId").val()){
            //             // 만약 메시지를 보낸 사용자의 세션 ID가 현재 세션 ID와 일치하면
            //             // "나" 로 표시하여 채팅창에 추가합니다.
            //             $("#chating").append("<p class='me'>나 :" + d.msg + "</p>");
            //         }else{
            //             // 다른 사용자의 메시지인 경우 해당 사용자 이름과 함께 채팅창에 추가합니다.
            //             $("#chating").append("<p class='others'>" + d.userName + " :" + d.msg + "</p>");
            //         }
            //     }else{
            //         // 알수없는 메시지 타입인 경우 콘솔에 경고 메시지를 출력합니다.
            //         console.warn("unknown type!")
            //     }
            // }else {
            //     // 파일 업로드한 경우 업로드한 파일을 채팅방에 추가합니다.
            //     var url=URL.createObjectURL(new Blob([msg]));
            //     $("#chating").append("<div class='img'><img class='msgImg' src="+url+"></div><div class='clearBoth'></div>");
            // }
        }

        // enter 키를 눌렀을 때 send 함수를 호출합니다.
        document.addEventListener("keypress", function (e) {
            if (e.keyCode == 13) { //enter 키 코드
                send();
            }
        });
    }

    // function chatName(){ // 사용자 이름을 입력하고, 웹 소켓을 열어 채팅에 참가합니다.
    //     var userName = $("#userName").val(); // 사용자가 입력한 사용자 이름을 가져옵니다.
    //     if(userName == null || userName.trim() == ""){ // 입력된 사용자 이름이 없거나 공백으로만 이루어져 있으면 경고를 표시하고 함수를 종료합니다.
    //         alert("사용자 이름을 입력해주세요.");
    //         $("#userName").focus(); // 포커스를 사용자 이름 입력 필드로 이동합니다.
    //     }else{ // 사용자 이름이 유효하면 웹 소켓을 연결하고, UI를 업데이트합니다.
    //         wsOpen(); // 웹 소켓을 열고 연결 이벤트 및 메시지 수신 이벤트를 설정하는 함수
    //         $("#yourName").hide(); // 사용자 이름 입력창을 숨깁니다.
    //         $("#yourMsg").show(); // 메시지 입력 창을 보이게 합니다.
    //     }
    // }

    function send() { // 채팅 메시지를 서버로 전송합니다.
        var info_id = '${sessionScope.info_id}';
        var msg = $("#chatting").val();
        var group = $("#chatgroup").val();

        // 만약 사진을 선택하지 않았다면
        if (!$("#msgfileupload").val()){
            ws.send("{\"mynum\":\"" + mynum + "\",\"upload\":\"" + msg + "\",\"receiver\":\"" + $("#receivernum").val()
                + "\",\"group\":\"" + group + "\",\"type\":\"chat\"}");
        } else {
            // 사진부터 업로드
            var form=new FormData();
            form.append("upload",$("#msgfileupload")[0].file[0]); // 선택한 1개만 추가

            $.ajax({
                type: "post",
                dataType: "json",
                url: "fileupload",
                processData: false,
                contentType: false,
                data: form,
                success: function (res) {
                    ws.send("{\"mynum\":\"" + mynum + "\",\"upload\":\"" + res.upload + "\",\"receiver\":\"" + $("#receivernum").val()
                        + "\",\"group\":\"" + group + "\",\"type\":\"img\"}");

                    /* ws.send(myid + " : " + res.upload + " : " + $("#receivernum").val() + " : "
                            + group + " : " + "img"); */
                    $(".messagefilepreview").hide();
                    $("#msgfileupload").val(null);
                }
            });
            //메시지도 적었다면 한 번 더 전송
            if (msg != "") {
                ws.send("{\"mynum\":\"" + mynum + "\",\"upload\":\"" + msg + "\",\"receiver\":\"" + $("#receivernum").val()
                    + "\",\"group\":\"" + group + "\",\"type\":\"chat\"}");
            }
        }

        // 알림전송
        $.ajax({
            type: "get",
            dataType: "text",
            data: {"other": $("#receivernum").val(), "group": group},
            url: "../messagealaramadd",
            success: function () {

            }
        })
        $('#chatting').val("");

        getChatting(group);

        memberListOut(); //멤버 리스트 다시 불러오기

        // var option ={
        //     type: "message", // 메시지의 유형을 나타내는 속성
        //     roomNumber: $("#roomNumber").val(), // 현재 채팅 방의 번호
        //     sessionId : $("#sessionId").val(), // 사용자의 세션 ID
        //     userName : $("#userName").val(), // 사용자의 이름
        //     msg : $("#chatting").val() // 사용자가 입력한 채팅 메시지
        // }
        // ws.send(JSON.stringify(option)) // 수집한 정보를 JSON 형태로 문자열화하고 웹 소켓을 통해 서버로 전송합니다.
        // $('#chatting').val(""); // 채팅 입력 창을 비워 바로 입력할 수 있도록 함.
    }

    // function fileSend() { // 파일업로드 입력 필드에서 선택한 파일을 변수 'file'에 저장합니다.
    //     var file = document.querySelector("#fileUpload").files[0]; // 파일 업로드 입력 필드에서 선택한 파일을 가져옵니다.
    //     var fileReader = new FileReader(); // FileReader 객체를 생성하여 파일을 비동기적으로 읽어들이는 작업을 수행합니다.
    //     fileReader.onload = function () { // 파일이 로드될 때 실행되는 이벤트 핸들러를 정의합니다.
    //         var param = { // 파일과 관련된 정보를 수집하여 객체 param에 저장합니다.
    //             type: "fileUpload", // 메시지 유형을 나타내는 속성
    //             file: file, // 업로드할 파일 객체
    //             roomNumber: $("#roomNumber").val(), // 현재 채팅방의 번호
    //             sessionId: $("#sessionId").val(), // 사용자의 세션 ID
    //             msg: $("#chatting").val(), // 사용자가 입력한 채팅 메시지
    //             userName: $("#userName").val() // 사용자 이름
    //         }
    //         ws.send(JSON.stringify(param)); // 파일을 전송하기 전에 파일 관련 정보를 포함한 메시지를 먼저 보냅니다.
    //         arrayBuffer = this.result; // 파일 데이터를 ArrayBuffer로 변환하여 웹 소켓을 통해 서버로 전송합니다.
    //         ws.send(arrayBuffer); // 파일 소켓 전용
    //     }
    //     fileReader.readAsArrayBuffer(file); // 파일을 ArrayBuffer로 일어들입니다.
    // }
</script>
<body>
<div class="messagecontainer">
    <div class="messagememberlist">
        <div class="mmllisttop">
            <div class="mmltitle">
                <span style="font-size: 20pt"><b>채팅</b></span>
                <div class="mmlbtnbox">
                    <!-- <div class="mmlbtn">
                        <span class="glyphicon glyphicon-option-horizontal"></span>
                    </div> -->
                    <div class="mmlbtn msgadd">
                        <span class="glyphicon glyphicon-edit msgaddicon"></span>
                    </div>
                </div>
            </div>
            <div class="messagesearch">
                <span class="glyphicon glyphicon-search"></span>
                <input type="text" placeholder="Messenger 검색">
            </div>
        </div>
        <div class="messagmember">
        </div>
    </div>

    <div class="messagechatlist">
        <div class="chatinfo">
            <c:if test="${recentgroup!=0}">
                <div class="chatinfophoto" member_num="${otherInfo.user_num}">
                    <c:if test="${otherInfo.user_photo==null}">
                        <img src="/accominage/x.png">
                    </c:if>
                    <c:if test="${otherInfo.user_photo!=null}">
                        <img src="/photo/${otherInfo.user_photo}">
                    </c:if>
                </div>
                <span member_id="${otherInfo.user_id}">${otherInfo.user_id}</span>

            </c:if>
        </div>
        <div class="msgsearchuser"></div>

        <div class="chatlist">
            <div class="chatlistinfo">
                <c:if test="${recentgroup!=0}">
                    <div>
                        <img alt="상대방 사진" src="">
                    </div>
                    <span class="chatlistinfoname"></span>
                    <span class="chatlistinfoid"></span>
                    <span class="chatlistinfofollow"></span>
                </c:if>
            </div>
            <div id="chatShow" class="chatShow">
                <%--                채팅 보이는 구간 --%>
            </div>
        </div>
        <div class="messagefilepreview"></div>
        <div class="messagefotter">
            <%--            이모지 시작 --%>
            <div class="chatemoji">
                <img class="emoji_pickup" id="emoji_pickup_before"
                     src="/messagejscss/img/emoji/1f642.png"> <img
                    class="emoji_pickup" id="emoji_pickup_after"
                    src="/messagejscss/img/emoji/1f600.png">

                <div id="emoji_popup">
                    <!-- emoji popup div start -->
                    <div id="people">
                        <h5>People</h5>
                    </div>
                    <span class="emoji_list" id="&#x1F601;">&#x1F601;</span> <span
                        class="emoji_list" id="&#x1F602;">&#x1F602;</span> <span
                        class="emoji_list" id="&#x1F603;">&#x1F603;</span> <span
                        class="emoji_list" id="&#x1F604;">&#x1F604;</span> <span
                        class="emoji_list" id="&#x1F605;">&#x1F605;</span> <span
                        class="emoji_list" id="&#x1F606;">&#x1F606;</span> <span
                        class="emoji_list" id="&#x1F609;">&#x1F609;</span> <span
                        class="emoji_list" id="&#x1F60A;">&#x1F60A;</span> <span
                        class="emoji_list" id="&#x1F60B;">&#x1F60B;</span> <span
                        class="emoji_list" id="&#x1F60C;">&#x1F60C;</span> <span
                        class="emoji_list" id="&#x1F60D;">&#x1F60D;</span> <span
                        class="emoji_list" id="&#x1F60F;">&#x1F60F;</span> <span
                        class="emoji_list" id="&#x1F612;">&#x1F612;</span> <span
                        class="emoji_list" id="&#x1F613;">&#x1F613;</span> <span
                        class="emoji_list" id="&#x1F614;">&#x1F614;</span> <span
                        class="emoji_list" id="&#x1F616;">&#x1F616;</span> <span
                        class="emoji_list" id="&#x1F618;">&#x1F618;</span> <span
                        class="emoji_list" id="&#x1F61A;">&#x1F61A;</span> <span
                        class="emoji_list" id="&#x1F61C;">&#x1F61C;</span> <span
                        class="emoji_list" id="&#x1F61D;">&#x1F61D;</span> <span
                        class="emoji_list" id="&#x1F61E;">&#x1F61E;</span> <span
                        class="emoji_list" id="&#x1F620;">&#x1F620;</span> <span
                        class="emoji_list" id="&#x1F621;">&#x1F621;</span> <span
                        class="emoji_list" id="&#x1F622;">&#x1F622;</span> <span
                        class="emoji_list" id="&#x1F623;">&#x1F623;</span> <span
                        class="emoji_list" id="&#x1F624;">&#x1F624;</span> <span
                        class="emoji_list" id="&#x1F625;">&#x1F625;</span> <span
                        class="emoji_list" id="&#x1F628;">&#x1F628;</span> <span
                        class="emoji_list" id="&#x1F629;">&#x1F629;</span> <span
                        class="emoji_list" id="&#x1F62A;">&#x1F62A;</span> <span
                        class="emoji_list" id="&#x1F62B;">&#x1F62B;</span> <span
                        class="emoji_list" id="&#x1F62D;">&#x1F62D;</span> <span
                        class="emoji_list" id="&#x1F630;">&#x1F630;</span> <span
                        class="emoji_list" id="&#x1F631;">&#x1F631;</span> <span
                        class="emoji_list" id="&#x1F632;">&#x1F632;</span> <span
                        class="emoji_list" id="&#x1F633;">&#x1F633;</span> <span
                        class="emoji_list" id="&#x1F635;">&#x1F635;</span> <span
                        class="emoji_list" id="&#x1F637;">&#x1F637;</span>
                    <!-- emoji popup div end -->
                </div>
            </div>
            <%--            이모지 끝 --%>
            <%--            사진 올리기 --%>
            <div class="chatupload">
                <input type="file" accept="image/jpeg,.png,.gif,.jpg" id="msgfileupload" style="display: none;">
                <input type="hidden" id="chatgroup" value="${recentgroup}">
            </div>
            <button onclick="send()" id="sendBtn">
                <i class="fa-regular fa-paper-plane"></i>
            </button>
        </div>
    </div>
</div>

</body>
</html>