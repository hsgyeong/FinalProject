<%--
  Created by IntelliJ IDEA.
  User: imhyeongjun
  Date: 11/29/23
  Time: 6:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>

<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.5.0/kakao.min.js"
        integrity="sha384-kYPsUbBPlktXsY6/oNHSUDZoTX6+YI51f63jCPEIPFP09ttByAdxd2mEjKuhdqn4" crossorigin="anonymous"></script>
<script>
  Kakao.init('c089c8172def97eb00c07217cae17495'); // 사용하려는 앱의 JavaScript 키 입력
</script>

<body>
<a id="chat-channel-button" href="javascript:chatChannel()">
  <img src="/tool/resource/static/img/button/channel/consult/consult_small_yellow_pc.png"
       alt="카카오톡 채널 채팅하기 버튼" />
</a>
</body>

<script>
  function chatChannel() {
    Kakao.Channel.chat({
      channelPublicId: '_ZeUTxl',
    });
  }
</script>
</html>
