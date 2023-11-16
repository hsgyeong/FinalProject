<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=VT323&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.0.js"></script>
<style type="text/css">
.accom_insert_box {
	width: 100%;
	height: 71vh;
	margin-top: 3.5vh;
}

.accom_insert_box table {
	width: 80vh; 
}

.accom_insert_box table tr td {
	padding: 2.5vh 2.5vh;
}

.accom_insert_box textarea {
	width: 55vh;
	height: 25vh;
}

.accom_insert_btn1 {
	width: 11vh;
	height: 6vh;
	border: none;
	color: white;
	font-weight: bold;
	background-color: rgb(23, 93, 238);
	border-radius: 1vh;
	margin-right: 1vh; 
}

.accom_insert_btn2 {
	width: 11vh;
	height: 6vh;
	border: 1px solid silver;
	color: black;
	font-weight: bold;
	background-color: white;
	border-radius: 1vh;
}

.accom_search_btn1 {
	width: 20vh;
	height: 5vh;
	border: 0.5px solid silver;
	color: black;
	background-color: rgb(246, 247, 249);
	font-size: 2vh;
	border-radius: 1vh;
	margin-right: 1vh; 
}

.accom_search_btn2 {
	width: 18vh;
	height: 5vh;
	border: 0.5px solid silver;
	color: black;
	background-color: rgb(246, 247, 249);
	font-size: 2vh;
	border-radius: 1vh;
}

.accom_search_btn1:hover,
.accom_search_btn2:hover {
	background-color: rgb(228, 231, 235);
}

#accom_category:hover {
	background-color: rgb(228, 231, 235);
}

.modal {
	display: none;
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
}

.modal-content {
	position: absolute;
	top: 50%;
	left: 50%;
	transform: translate(-50%, -50%);
	padding: 20px;
	width: 80%;
    max-width: 300px;
    max-height: 80vh;
	background-color: #fff;
	border-radius: 10px;
	text-align: center;
}

.modal input {
	width: 70%;
	height: 5vh;
}

.modal-inputbox{
	margin-top: 2vh;
	display: flex;
    align-items: center;
    justify-content: center;
}

.detailAddressInputbtn {
	width: 8vh;
	height: 5vh;
	border: 1px solid silver;
	color: black;
	background-color: rgb(246, 247, 249);
	font-size: 2vh;
	border-radius: 1vh;
	margin-left: 1vh;
}

.hashtag_result{
	margin-bottom: 1vh;
	font-size: 0.8em;
}

.hashtagremovebtn{
	width: 0.6vh;
	height: 0.8vh;
	border: none;
	background-color: white;
	align-items: center;
}

.hashtagremovebtn:hover{
	color: red;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<form action="Insert" method="post" enctype="multipart/form-data">
		<div class="accom_insert_box" align="center">
			<input type="hidden" name="business_id" value="test">
			<table class="table table-bordered">

				<tr>
					<td align="center" valign="middle" colspan="2"
						style="font-size: 1.5em;"><b>숙소정보등록</b></td>
				</tr>
				<tr>
					<td align="center" valign="middle"><b>숙소이름</b></td>
					<td valign="middle"><input type="text" class="form-control"
						name="accom_name" required="required" placeholder="ex)신라호텔, 조선호텔"
						style="width: 30vh; height: 5vh;"></td>
				</tr>

				<tr>
					<td align="center" valign="middle"><b>카테고리</b></td>
					<td valign="middle"><select class="form-control"
						name="accom_category" id="accom_category" required="required"
						style="width: 8vh; height: 5vh;">
							<option value="호텔">호텔</option>
							<option value="모텔">모텔</option>
							<option value="펜션">펜션</option>
					</select></td>
				</tr>

				<tr>
					<td align="center" valign="middle"><b>숙소사진</b></td>
					<td valign="middle"><input type="file" class="form-control"
						name="photo" required="required" style="width: 40vh; height: 5vh;">
					</td>
				</tr>

				<tr>
					<td align="center" valign="middle"><b>숙소위치</b></td>
					<td valign="middle">

						<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
						<script>
					    window.onload = function () {
					        document.getElementById("address_kakao").addEventListener("click", function () {
					            new daum.Postcode({
					                oncomplete: function (data) {
					                	var detailAddressInput = document.getElementById("detailAddressInput").value;
					                    var searchResultDiv = document.getElementById("search_result");
					                    var searchDetailResultDiv = document.getElementById("search_detailresult");
					                    var accomLocationInput = document.getElementsByName("accom_location")[0];
					                    var accomInsertBox = document.querySelector(".accom_insert_box");
					
					                    // 주소 넣기
					                    searchResultDiv.innerHTML = data.address;
					
					                    // accom_location input에 주소 값 넣기
					                    if (accomLocationInput) {
					                        accomLocationInput.value = data.address;
					
					                        // 주소가 있을 때 스타일 설정
					                        searchResultDiv.style.width = '60vh';
					                        searchResultDiv.style.height = '5vh';
					                        searchResultDiv.style.marginBottom = '0.5vh';
					                        searchResultDiv.style.marginLeft = '0.6vh';
					                        accomInsertBox.style.height = '77vh';
					                        
					                    }
					                }
					            }).open();
					        });
					    }
						</script>
						
						<!-- 도로명주소 출력 -->
						<div id="search_result"></div>
						<!-- 상세주소 출력 -->
						<div id="search_detailresult"></div>

						<!-- 도로명 주소와 상세 주소 값을 넘겨주기 위해 -->
						<input type="hidden" name="accom_location" id="accom_location">
						
						<button type="button" class="accom_search_btn1" id="address_kakao">도로명주소 검색</button>
						<button type="button" class="accom_search_btn2" id="address_detail">상세주소 입력</button>
						
						<!-- 모달창 -->
						<div class="modal" id="myModal">
						    <div class="modal-content">
						        <h5>상세주소 입력</h5>
						        <div class="modal-inputbox">
							        <input type="text" id="detailAddressInput" class="form-control">
							        <button type="button" class="detailAddressInputbtn" onclick="saveDetailAddress()">확인</button>
						        </div>
						    </div>
						</div>
						<!-- 모달창 끝 -->
						
						<script>
						    // 모달창 보이기
						    $("#address_detail").click(function () {
						        // 검색 결과가 없으면 모달창을 열지 않고 알림창을 띄웁니다.
						        var searchDetailResult = document.getElementById("search_result").innerHTML.trim();
						        if (searchDetailResult === "") {
						            alert("도로명 주소를 먼저 검색해주세요.");
						        } else {
						            $("#myModal").show();
						        }
						    });
						
						    // 모달창 숨기기
						    function closeModal() {
						        $("#myModal").hide();
						    }
						
						 // 입력 확인 시 동작
						    function saveDetailAddress() {
						        var detailAddressInput = document.getElementById("detailAddressInput").value;
						        var searchDetailResultDiv = document.getElementById("search_detailresult");
						        var accomLocationInput = document.getElementById("accom_location");
						        var accomInsertBox = document.querySelector(".accom_insert_box");

						        // 도로명 주소 가져오기
						        var roadAddress = document.getElementById("search_result").innerHTML.trim();

						        // 상세 주소가 있을 때 스타일 설정
						        if (detailAddressInput) {
						            // accom_location에 도로명 주소와 새로운 상세 주소를 추가
						            accomLocationInput.value = roadAddress + ' ' + detailAddressInput;

						            // search_detailresult를 비우고, 새로운 상세 주소를 추가
						            searchDetailResultDiv.innerHTML = '';
						            var newDetailElement = document.createElement("div");
						            newDetailElement.innerHTML = detailAddressInput;
						            newDetailElement.style.width = '60vh';
						            newDetailElement.style.height = '5vh';
						            newDetailElement.style.marginBottom = '0.5vh';
						            newDetailElement.style.marginLeft = '0.6vh';
						            searchDetailResultDiv.appendChild(newDetailElement);
						        }

						        closeModal(); // 모달창 닫기
						        accomInsertBox.style.height = '83vh'; //닫고 나서 동적으로 크기 증가
						    }
						</script>

					</td>
				</tr>
				
				<tr>
					<td align="center" valign="middle"><b>해쉬태그</b></td>
					<td>
					<!-- 해쉬태그 출력 -->
					<div id="hashtag_result"></div>
					<input type="hidden" name="accom_hashtag" id="accom_hashtag">
					<input type="text" class="form-control" id="hashtag_input" placeholder="키워드 입력"
        				style="width: 40vh; height: 5vh;" onkeypress="handleHashtagInput(event)">
    
				    <script>

				    function handleHashtagInput(event) {
				        if (event.key === "Enter") {
				            event.preventDefault();
				            var hashtagInput = document.getElementById("hashtag_input");
				            var hashtagResultDiv = document.getElementById("hashtag_result");
				            var accomHashtagInput = document.getElementById("accom_hashtag");
				            var accomInsertBox = document.querySelector(".accom_insert_box");

				            var newHashtag = hashtagInput.value.trim();

				            if (newHashtag !== "") {
				                if (!newHashtag.startsWith("#")) {
				                    newHashtag = "#" + newHashtag;
				                }

				                if (accomHashtagInput.value === "") {
				                    accomHashtagInput.value = newHashtag;
				                } else {
				                    accomHashtagInput.value += ', ' + newHashtag;
				                }

				                var newHashtagDiv = document.createElement("div");
				                newHashtagDiv.innerHTML = newHashtag + ("<button type='button' class='hashtagremovebtn' onclick='removeHashtag(this)'>x</button>");
				                newHashtagDiv.style.display = "inline-block";
				                newHashtagDiv.style.padding = "0.3vh 2vh";
				                newHashtagDiv.style.marginRight = "4px";
				                newHashtagDiv.style.border = "1px solid #ccc";
				                newHashtagDiv.style.borderRadius = "4px";
				                newHashtagDiv.style.marginBottom = "2vh";

				                // 추가된 부분: 해시태그 결과를 갱신하고, 입력란 비우기
				                hashtagResultDiv.appendChild(newHashtagDiv);
				                hashtagInput.value = "";

				                // 추가된 부분: 입력란이 업데이트되었을 때 accomInsertBox의 높이 조절
				                accomInsertBox.style.height = '90vh';
				            }
				        }
				    }

				    function removeHashtag(button) {
				        var hashtagResultDiv = document.getElementById("hashtag_result");
				        var accomHashtagInput = document.getElementById("accom_hashtag");
				        var accomInsertBox = document.querySelector(".accom_insert_box");

				        // 부모 엘리먼트를 찾아 삭제
				        var hashtagDiv = button.parentNode;
				        hashtagResultDiv.removeChild(hashtagDiv);

				        // 추가된 부분: 해시태그 결과를 갱신하고, 입력란이 업데이트되었을 때 accomInsertBox의 높이 조절
				        accomHashtagInput.value = Array.from(hashtagResultDiv.children)
				            .map(tagDiv => tagDiv.textContent.replace('x', '').trim())
				            .join(', ');
				        accomInsertBox.style.height = '90vh';
				    }
					</script>
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<button type="button" class="accom_insert_btn1" onclick="submitForm()">등록</button>
						<button type="button" class="accom_insert_btn2"
							onclick="location.href='history.back()'">취소</button>
							<script>
							    function submitForm() {
							        // 폼을 선택하고 submit 메서드를 호출하여 폼을 서브밋합니다.
							        document.forms[0].submit();
							    }
							</script>
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>