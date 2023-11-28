<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=VT323&display=swap"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<title>Insert title here</title>
</head>
<style>
.detail_box {
	width: 100%;
}

.detail_content {
	width: 100%;
	height: 55vh;
}

.detail_info {
	width: 45%;
	position: relative;
	left: 55%;
	top: -93%;
}

.detail_img {
	width: 50%;
	height: 40vh;
}

.detail_img img {
	width: 100%;
	height: 40vh;
	border-radius: 2px;
	box-shadow: 5px 5px 5px silver;
}

.detail_imgs {
	width: 50%;
	margin-top: 1%;
	overflow: hidden;
}

.detail_imgs img {
	width: 15vh;
	max-width: 15vh;
	height: 10vh;
	max-height: 10vh;
}

.detail_imgs ul {
	list-style: none;
	display: flex;
	justify-content: center;
	align-items: center;
	width: 100%;
	margin: 0;
	padding: 0;
}

.detail_imgs ul li:first-child {
	margin-left: 0.6vh;
}

.detail_imgs ul li img {
	max-width: 100%;
	max-height: 110%;
	border-radius: 1px;
	margin: 1vh 0.5vh 1vh 0.5vh;
	box-shadow: 3px 3px 3px silver;
}

.detail_room_name {
	width: 100%;
	bottom: 39vh;
	font-weight: bold;
	font-size: 3vh;
}

.detail_asr {
	width: 100%;
	display: flex;
	margin: 1vh 0px 2vh 0px;
}

.detail_accom_score {
	width: 10%;
	border-radius: 5px;
	color: white;
	background-color: orange;
	text-align: center;
	margin-right: 1vh;
}

.detail_score_result {
	width: 15%;
	color: orange;
	font-size: 2vh;
	font-weight: 650;
	margin-right: 0.5vh;
	text-align: center;
}

.detail_review_count {
	width: 20%;
	color: silver;
	font-size: 2vh;
	font-weight: 500;
	margin-left: 0.5vh;
}

.detail_accom_location {
	width: 100%;
	color: gray;
	font-size: 2vh;
	margin: 1vh 0px 2vh 0px;
}

.detail_accom_hashtag {
	width: 100%;
	color: gray;
	font-size: 2vh;
	margin: 1vh 0px 2vh 0px;
}

.detail_room_info {
	width: 100%;
	color: gray;
	font-size: 2vh;
	background-color: #FAFAFA;
	padding: 2vh 2vh 2vh 2vh;
	border: 1px solid silver;
}

.detail_room_info b {
	color: black;
	font-size: 2vh;
	font-weight: 600;
}

.detail_room_info_coment {
	margin: 2vh 0px 0px 0px;;
}

.detail_select {
	width: 100%;
	margin-top: 5vh;
}

.detail_select ul {
	list-style: none;
	margin-left: -2%;
}

.detail_select ul li {
	display: inline-block;
	color: silver;
	font-size: 2.3vh;
	text-align: center;
	padding-bottom: 1vh;
	cursor: pointer;
	margin-right: 1vh;
}

.detail_room_select {
	border: 1px solid silver;
	border-radius: 1px;
	margin-top: 3vh;
	display: flex;
	box-shadow: 0 0.5px 0 silver;
	
}

.detail_room_select_img {
	width: 60%;
	margin: 2vh 2vh 2vh 2vh;
}

.detail_room_select_img img {
	width: 100%;
	height: 35vh;
	box-shadow: 2px 2px 2px silver;
}

.detail_room_reserve {
	width: 40%;
	height: 30vh;
	margin: 2vh 2vh 2vh 0px;
	padding: 0px 2vh;
}

.detail_room_select_name {
	font-weight: bold;
	font-size: 2.2vh;
	margin-bottom: 1.5vh;
}

.detail_room_select_suk {
	font-weight: bold;
	font-size: 2vh;
}

.detail_room_select_price {
	font-weight: bold;
	font-size: 2.5vh;
	box-shadow: 0 0.5px 0 silver;
	margin-bottom: 1vh;
}

.detail_room_select_checkin>b, .detail_room_select_checkout>b {
	text-align: right;
	display: block;
}

.room_detail_reserve_btn {
	color: white;
	background-color: #f7323f;
	border: none;
	border-radius: 3px;
	width: 100%;
	height: 7vh;
	margin-top: -2vh;
	box-shadow: 2px 2px 2px silver;
}

.detail_suk_info,.detail_review{
	width: 100%;
	border: 1px solid black;
	height: 500px;
	margin-top: 3vh;
}

#map {
    width: 100%;
    height: 50vh;
    border: 1px solid black;
}

#address {
    width: 10%;
    margin-top: -50px; /* 마커 위로 조절 */
    padding: 1vh;
    border: 1px solid black;
    border-radius: 5px;
    background-color: white;
    text-align: center;
    font-size: 2vh;
    position: absolute;
    top: 49%;
    left: 45%;
    z-index: 1;
}

.detail_room_count{
	color:red;
	font-size: 0.8em;
	float: right;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		// 페이지 로딩 시 실행되는 함수
		(function() {
			$(".detail_suk_info").hide();
			$(".detail_review").hide();
			$(".detail_select_1").css({
				
				"color" : "#f7323f",
				"border-bottom" : "5px solid #f7323f",
				"font-weight" : "bold"
			});
		})();

		// 경고 창 띄우는 함수
		$("#detail_alert").click(function() {
			alert('로그인 후 이용해주세요');
		});

		// 탭 클릭 시 이벤트 처리
		$(".detail_select_1").click(function() {

			$(this).css({

				"color" : "#f7323f",
				"border-bottom" : "5px solid #f7323f",
				"font-weight" : "bold"
			})

			$(".detail_select_2, .detail_select_3").css({
				"color" : "",
				"border-bottom" : "",
				"font-weight" : ""
			});

			$(".detail_room_select").show();
			$(".detail_suk_info").hide();
			$(".detail_review").hide();
		});

		$(".detail_select_2").click(function() {

			$(this).css({

				"color" : "#f7323f",
				"border-bottom" : "5px solid #f7323f",
				"font-weight" : "bold"
			})

			$(".detail_select_1, .detail_select_3").css({
				"color" : "",
				"border-bottom" : "",
				"font-weight" : ""
			});
			$(".detail_room_select").hide();
			$(".detail_suk_info").show();
			$(".detail_review").hide();
		});

		$(".detail_select_3").click(function() {

			$(this).css({

				"color" : "#f7323f",
				"border-bottom" : "5px solid #f7323f",
				"font-weight" : "bold"
			})

			$(".detail_select_1, .detail_select_2").css({
				"color" : "",
				"border-bottom" : "",
				"font-weight" : ""
			});

			$(".detail_room_select").hide();
			$(".detail_suk_info").hide();
			$(".detail_review").show();
		});
	});
</script>
<body>
	<div class="detail_box">

		<div class="detail_content">

			<div class="detail_img">
				<img src="../accomsave/${dto.accom_photo }" alt="">
			</div>

			<div class="detail_imgs">
				<ul>

					<c:forEach var="photo" items="${photoList}">
						<li><img src="../roomsave/${photo}" alt=""></li>
					</c:forEach>

				</ul>
			</div>

			<script>
				$(".detail_imgs ul li img").click(function() {
					var selectImg = $(this).attr('src');

					//제거
					$(".detail_imgs ul li img").css({

						"transform" : "none"
					});

					//효과 생성
					$(this).css({

						"transform" : "scale(1.05)"
					});

					$(".detail_img img").fadeOut(300, function() {

						$(this).attr('src', selectImg);

						$(this).fadeIn(300);
					});
				});
			</script>

			<div class="detail_info">

				<div class="detail_room_name">${dto.accom_name }</div>

				<div class="detail_asr">
					<div class="detail_accom_score">${dto.accom_score }</div>
					<div class="detail_score_result"></div>

					<script>
						$(document).ready(function() {
							var accomScore = ${dto.accom_score }

							if (accomScore > 9) {
								$(".detail_score_result").text("최고예요");
							}

							else if (accomScore >= 8 && accomScore <= 9) {
								$(".detail_score_result").text("좋아요");
							}

							else if (accomScore >= 7 && accomScore <= 8) {
								$(".detail_score_result").text("괜찮아요");
							}

							else {
								$(".detail_score_result").text("아쉬워요");
							}
						});
					</script>
					<div class="detail_review_count">리뷰 1000개</div>
				</div>
				<div class="detail_accom_location">${dto.accom_location }</div>
				<div class="detail_accom_hashtag">${dto.accom_hashtag }</div>
				<div class="detail_room_info">
					<b>사장님의 한마디</b> <br>
					<div class="detail_room_info_coment">${dto.accom_info }</div>
				</div>
			</div>
		</div>

		<div class="detail_select">
			<ul>
				<li class="detail_select_1">객실안내/예약</li>
				<li class="detail_select_2">숙소정보</li>
				<li class="detail_select_3">리뷰</li>
			</ul>
		</div>
	</div>

	<c:forEach items="${list }" var="rdto">
		<div class="detail_room_select">

			<div class="detail_room_select_img">
				<img src="../roomsave/${rdto.room_photo.split(',')[0]}" alt="">
			</div>
			<div class="detail_room_reserve">
				<div class="detail_room_select_name">${rdto.room_name } <span class="detail_room_count">남은 객실 ${rdto.room_count }개</span> </div>
				<div class="detail_room_select_suk">기준${rdto.room_minpeople }인 최대${rdto.room_maxpeople }인</div>
				<div class="detail_room_select_price" align="right">
					<fmt:formatNumber value="${rdto.room_price*sleep }" />
					원
				</div>
				<div class="detail_room_select_checkin">
					입실시간 <b>${rdto.room_checkin }시부터</b>
				</div>
				<div class="detail_room_select_checkout">
					퇴실시간 <b>익일 ${rdto.room_checkout }시</b>
				</div>
				<div align="center">
					<br>
					<c:if test="${sessionScope.loginok != null }">
						<button type="button" class="room_detail_reserve_btn"
							onclick="location.href='/reserve/reserve-form?room_num=${rdto.room_num}&checkin=${checkin }&checkout=${checkout }&sleep=${sleep }'">예약</button>
					</c:if>
					<c:if test="${sessionScope.loginok == null }">
						<button type="button" id="detail_alert"
							class="room_detail_reserve_btn"
							onclick="location.href='/login/loginmain'">예약</button>
					</c:if>
				</div>
			</div>
		</div>
	</c:forEach>

	<div class="detail_suk_info">
		
		<div id="map"></div>
		<div id="address"></div>

		<script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=c1b8de800495871b37c96707bbc9b0cb&libraries=services"></script>
		<script src="app.js"></script>

		<script>
		    document.addEventListener("DOMContentLoaded", function () {
		        // 여기에 위도와 경도를 가져오는 로직을 추가하세요.
		        var latitude = ${dto.accom_latitude};
		        var longitude = ${dto.accom_longitude};
		
		        // 카카오 지도 API 초기화
		        kakao.maps.load(function () {
		            var container = document.getElementById('map');
		            var options = {
		                center: new kakao.maps.LatLng(latitude, longitude),
		                draggable: false,
		                level: 1,
		                
		            };
		
		            var map = new kakao.maps.Map(container, options);
		
		            // 마커 추가
		            var markerPosition = new kakao.maps.LatLng(latitude, longitude);
		            var marker = new kakao.maps.Marker({
		                position: markerPosition
		            });
		
		            marker.setMap(map);

		            // 지도의 중심 좌표를 얻어옵니다 
		            var center = map.getCenter(); 
		            // 마커에 표시할 인포윈도우를 생성합니다
		            var infowindow = new kakao.maps.InfoWindow({
		                position : center, 
		                content : '<div id="address">도로명주소: 검색 후 표시됩니다.</div>' 
		            });

		            // 지도에 클릭 이벤트를 등록합니다
		            kakao.maps.event.addListener(map, 'click', function(mouseEvent) { 

		                // 클릭한 위도, 경도 정보를 가져옵니다 
		                var latlng = mouseEvent.latLng;

		                // 지도의 중심 좌표를 변경합니다
		                map.setCenter(latlng);
		                // 마커 위치를 클릭한 위치로 옮깁니다
		                marker.setPosition(latlng);

		                // 클릭한 위치에 인포윈도우를 열어줍니다
		                infowindow.open(map, marker);

		                // 지도를 클릭할 때마다 호출하여 클릭한 위치의 주소를 표시합니다
		                searchAddrFromCoords(map.getCenter(), displayCenterInfo);

		            });

		            // 주소-좌표 변환 객체를 생성합니다
		            var geocoder = new kakao.maps.services.Geocoder();

		            // 지도의 중심 좌표에 대한 주소를 얻어옵니다
		            searchAddrFromCoords(map.getCenter(), displayCenterInfo);

		            function searchAddrFromCoords(coords, callback) {
		                // 좌표로 행정동 주소 정보를 요청합니다
		                geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
		            }

		            // 주소 정보를 실제 지도에 표시하는 함수입니다
		            function displayCenterInfo(result, status) {
		                if (status === kakao.maps.services.Status.OK) {
		                    var infoDiv = document.getElementById('address');

		                    for(var i = 0; i < result.length; i++) {
		                        // 행정동의 region_type 값은 'H' 이므로
		                        if (result[i].region_type === 'H') {
		                            infoDiv.innerHTML = result[i].address_name;
		                            break;
		                        }
		                    }
		                }    
		            }
		        });
		    });
		</script>
    
	</div>
	<div class="detail_review">3</div>
</body>
</html>