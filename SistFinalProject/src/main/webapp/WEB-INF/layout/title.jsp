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
    <link href="https://webfontworld.github.io/goodchoice/Jalnan.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
    <title>Insert title here</title>
</head>
<%
    String loginok = (String) session.getAttribute("loginok");
%>
<style>
    body {
        margin: 0;
        padding: 0;
    }

    header {
        font-family: 'Jalnan';
        width: 70%;
        color: white;
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-right: auto;
        margin-left: auto;
        height: 72px;
        /*border: 1px solid ;*/
    }

    div.titlebox {
        display: flex;
        align-items: center;
    }

    div.select-option {
        display: flex;
        align-items: center;
        justify-content: right;
        width: 80%;
        height: 100%;
    }

    div.select-option > a {
        width: 30px;
        margin-right: 30px;
    }

    i.bi-search {
        color: white;
        cursor: pointer;
        font-size: 24px;
    }


    div ul.select-ul {
        list-style: none;
        margin: 0;
        padding: 0;
        position: relative;
        /*display: flex;*/
        /*justify-content: center;*/
        align-items: center;
    }

    div.select-option ul li.select-li {
        display: flex;
        align-items: center;
        justify-content: center;
        /*width: 100px;*/
        height: 72px;

    }

    div ul li.select-li > a {
        margin-right: 30px;
        color: white;
        text-decoration: none;
        font-size: 18px;
        white-space: nowrap;
        height: 100%;
        padding-top: 20px;
    }

    .titlebox a {
        color: white;
        text-decoration: none;
    }

    div.search-input-box {
        display: flex;
        align-items: center;
        width: 80%;
    }

    input.search-input {
        background: #f7323f;
        position: relative;
        width: 80%;
        border: none;
    }

    input.search-input:focus {
        border: none;
        outline: none;

    }

    i.bi-x {
        font-size: 30px;
        cursor: pointer;
    }

    div.sub-title {
        position: absolute;
        background: #fff;
        top: 72px;
        width: 150px;
        /*border: 3px solid green;*/
        border-radius: 10px;
        box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.2);
        padding: 10px 5px 0px 5px;
        margin-right: 60px;
    }

    div.sub-title a {
        color: black;
        text-decoration: none;
    }

    div.info-title {
        position: absolute;
        background: #fff;
        top: 72px;
        width: 150px;
        margin: auto;
        border-radius: 10px;
        box-shadow: 0px 3px 5px 0px rgba(0, 0, 0, 0.2);
        padding: 10px 5px 5px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    div.info-title a {
        text-decoration: none;
        color: black;
    }

    #log {
        cursor: pointer;
    }

    .con {
        align: center;
    }

    .title-name {
        color: #f7323f;
    }

    #my-surroundings {
        cursor: pointer;
    }


</style>

<script>

    $(document).ready(function () {

        $("#logoutBtn").click(function () {

            if ("<%=loginok%>" === "kakao") {

                $.ajax({

                    url: "/logout/kakaounlink",
                    type: "get",
                    success: function () {
                        window.location.href = "/";
                    }
                });
            } else {
                window.location.href = "/login/logout";
            }
        })
    })

    $(function () {
        $("i.search-active").hide();
        $("div.sub-title").hide();
        $("div.search-input-box").hide();


        $(".select-li:eq(2)").mouseover(function () {
            $("div.sub-title").show();
        });
        $("div.sub-title").mouseover(function () {
            $(this).show();
        });

        $(".select-li").mouseout(function () {
            $("div.sub-title").hide();
        });
        $("div.sub-title").mouseout(function () {
            $(this).hide();
        });

        $("i.search-disabled").click(function () {
            $("div.search-input-box").show();
            $("div.select-box").hide();
            $("i.search-active").show().animate({"marginLeft": "70px"}, 500);
            $(this).hide();
        });

        $(".search-input-box").click(function () {

        });

        $("i.bi-x").click(function () {
            $("i.search-active").hide().animate({"marginLeft": "0px"}, 500);
            $("i.search-disabled").show();
            $("div.select-box").show();
            $("div.search-input-box").hide();
        });

        $("i.search-active").click(function () {
            const searchText = $("input.search-input").val();
            if (searchText.trim().length == 0) {
                alert("검색하고 싶은 내용을 입력해주세요.")
                return false;
            }
            searchActive(searchText);
            $("input.search-input").val("");
        })

        $("input.search-input").keydown(function (key) {
            if (key.keyCode === 13) {
                const searchText = $(this).val();
                searchActive(searchText);
            }
        });

        $("div.info-title").hide();
        $(".select-li:eq(3)").on({
            mouseover: function () {
                $("div.info-title").show();
            }
        });

        $("div.info-title").mouseover(function () {
            $(this).show();
        })

        $(".select-li").mouseout(function () {
            $("div.info-title").hide();
        })

        $("div.info-title").mouseout(function () {
            $(this).hide();
        })


    });

    function searchActive(searchText) {
        //alert(searchText);
        location.href = "/product/search-main?keyword=" + searchText;
    }

    navigator.geolocation.getCurrentPosition((position) => {
        console.log(position)
        accessToGeo(position);
    });

    let lat;
    let long;

    function accessToGeo(position) {
        const positionObj = {
            latitude: position.coords.latitude,
            longitude: position.coords.longitude
        }
        console.log(positionObj);

        lat = positionObj.latitude;
        long = positionObj.longitude;
    }


    function settingLocation() {
        if (lat == null) {
            alert("주소록 앞 느낌표를 누른 후 위치 권한 허용으로 변경해주세요.");
            return false;
        }
        location.href = "/product/msr?latitude=" + lat + "&longitude=" + long;
    }

</script>


<c:set var="root" value="<%=request.getContextPath() %>"/>
<body>

<header>
    <div class="titlebox">
        <a href="/"><h2>TRIVIEW</h2></a>
    </div>
    <div class="select-option">
        <a><i class="bi bi-search search-active"></i></a>
        <a><i class="bi bi-search search-disabled"></i></a>
        <div class="select-box">
            <ul class="select-ul d-inline-flex">
                <li class="select-li"><a onclick="settingLocation()" id="my-surroundings">내주변</a></li>
                <li class="select-li"><a href="#">예약내역</a></li>
                <li class="select-li"><a href="#" id="see_more">더보기</a></li>
                <c:if test="${sessionScope.loginok==null}">
                    <li class="select-li"><a href="/login/loginmain">로그인</a></li>
                </c:if>
                <c:if test="${sessionScope.loginok!=null }">
                    <li class="select-li" id="log">
                        <img alt="" src="../loginsave/s1.png" style="width:35px; height:35px;">
                        &nbsp;&nbsp;&nbsp;&nbsp;<img alt="" src="../loginsave/t1.png" style="width:8px; height:8px;">
                    </li>
                </c:if>

            </ul>
        </div>
        <div class="search-input-box">
            <input type="text" class="search-input" placeholder="지역,숙소명">
            <i class="bi bi-x"></i>

        </div>
        <div class="sub-title">
            <ul>
                <li><a href="#">공지사항</a></li>
                <li><a href="/notice/event">이벤트</a></li>
                <li><a href="/chat/room1">1:1문의</a></li>
                <li><a href="#">약관 및 정책</a></li>
                <li><a href="#">공지사항</a></li>
            </ul>
        </div>
        <c:if test="${sessionScope.loginok !=null }">
            <div class="info-title">
                <ul>
                    <div class="con">
                        <div class="title-name">
                            <c:choose>
                                <c:when test="${sessionScope.loginok != null && sessionScope.loginok == 'member'}">
                                    <li><a class="nick">${memberDto.info_nickname }</a></li>
                                </c:when>
                                <c:when test="${sessionScope.loginok != null && sessionScope.loginok == 'kakaomember'}">
                                    <li><a class="nick">${KakaoMemberDto.kakao_nickname }</a></li>
                                </c:when>
                                <c:when test="${sessionScope.loginok != null && sessionScope.loginok == 'business'}">
                                    <li><a class="com">${businessDto.business_company }</a></li>
                                </c:when>
                            </c:choose>
                        </div>
                        <c:choose>
                            <c:when test="${sessionScope.loginok != null && sessionScope.loginok == 'member' || sessionScope.loginok == 'kakaomember'}">
                                <li><a href="/member/member-mytriview">내정보</a></li>
                            </c:when>
                            <c:when test="${sessionScope.loginok != null && sessionScope.loginok == 'business'}">
                                <li><a href="/accom/non-book">예약불가날짜</a></li>
                                <li><a href="/business/business-mytriview">내정보</a></li>
                            </c:when>
                        </c:choose>


                        <li><a href="" id="logoutBtn">로그아웃</a></li>

                    </div>
                </ul>
            </div>
        </c:if>
    </div>

</header>


</body>
</html>