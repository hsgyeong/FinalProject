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
            /*height: 113vh;*/
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
<form action="insert" method="post" enctype="multipart/form-data">
<div class="room_insert_box" align="center">
<!-- 숙소 번호 넘기기 fk -->
<input type="hidden" name="accom_num" value="1234">
<table class="table table-bordered">

            <tr>
                <td align="center" valign="middle" colspan="2" style="font-size: 1.5em;"><b>방정보등록</b></td>
            </tr>
            <tr>
                <td align="center" valign="middle"><b>이름</b></td>
                <td valign="middle">
                    <input type="text" class="form-control" name="room_name" required="required"
                           placeholder="ex)디럭스, 스탠다드" style="width: 30vh; height: 5vh;">
                </td>
            </tr>

            <tr>
                <td align="center" valign="middle"><b>가격</b></td>
                <td valign="middle">
                    <input type="text" class="form-control" name="room_price" required="required"
                           style="width: 25vh; height: 5vh;">
                </td>
            </tr>

            <tr>
                <td align="center" valign="middle"><b>인원</b></td>
                <td valign="middle" style="display: flex; border-left: none;">
                    <select class="form-control" name="room_minpeople" required="required"
                            style="width: 5vh; height: 5vh;">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                    </select>

                    <b style="padding-top: 0.7vh;">&nbsp;~&nbsp;</b>

                    <select class="form-control" name="room_maxpeople" required="required"
                            style="width: 5vh; height: 5vh;">
                        <option value="1">1</option>
                        <option value="2" selected="selected">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td align="center" valign="middle"><b>방갯수</b></td>
                <td valign="middle">
                    <select class="form-control" name="room_count" required="required"
                            style="width: 5vh; height: 5vh;">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
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
                <td align="center" valign="middle"><b>위치</b></td>
                <td valign="middle">
                    <input type="text" class="form-control" name="room_location" required="required"
                           placeholder="ex)1층, 2층" style="width: 50vh; height: 5vh;">
                </td>
            </tr>


            <tr>
                <td align="center" valign="middle"><b>방정보소개</b></td>
                <td valign="middle">
                    <textarea class="form-control" name="room_info" required="required"></textarea>
                </td>
            </tr>
            <%-- 성신 수정부분 --%>
            <tr>
                <td align="center" valign="middle"><b>예약 불가능&nbsp; 날짜 선택</b>
                    <br><br>
                    <button type="button" class="btn btn-outline-danger addDate">날짜 추가</button>
                </td>
                <td>
                    <h4><b>불가능 날짜</b></h4>
                    <div id="div-nonDate" style="width: 400px;">
                    <span class="d-inline-flex" id="nonDate1">
                    <input type="date" id="non_checkin1" name="checkin[]" min=""
                           class="form-control" style="width: 150px; margin-right: 20px;"> ~
                    <input type="date" id="non_checkout1" name="checkout[]" min=""
                           class="form-control" style="width: 150px; margin-left: 20px;">
					</span><br><br>
                    </div>
                    <input type="hidden" name="checkin" id="checkin">
                    <input type="hidden" name="checkout">
                </td>
            </tr>

            <script type="text/javascript">
                /*성신 추가 기능*/

                let today = new Date();
                let getYear = today.getFullYear();
                let getMonth = today.getMonth() + 1;
                let getDay = today.getDate();
                let date = getYear + "-" + getMonth + "-" + getDay;
                let totCount = 1;
                let count = 1;
                $("#non_checkin1").attr("min", date);
                $("#non_checkin2").attr("min", date);

                $(".addDate").click(function () {
                    if (totCount > 4) {
                        alert("5개 이상 등록할 수 없습니다.");
                        return;
                    }
                    totCount++;
                    count++;
                    var s = "";
                    s += "<span class='d-inline-flex' id='nonDate" + count + "'>";
                    s += "<input type='date' id='non_checkin" + count + "' name='checkin[]' min=''";
                    s += "class='form-control' style='width: 150px; margin-right: 20px;'> ~"
                    s += "<input type='date' id='non_checkout" + count + "' name='checkout[]' min=''";
                    s += "class='form-control' style='width: 150px; margin-left: 20px;'>";
                    s += "<button type='button' class='delDate btn btn-danger sm' style='margin-left: 10px;' "
                    s += "idx='" + count + "'>x</button>"
                    s += "</span><span class='br" + count + "'><br><br>";
                    $("#div-nonDate").append(s);
                })

                $(document).on("click", ".delDate", function () {
                    var idx = $(this).attr("idx");
                    $("#nonDate" + idx).remove();
                    $(".br" + idx).remove();
                    totCount--;
                })

                function checkDate() {
                    var count = 0;
                    var checkin = [];
                    var checkout = [];
                    for (var index = 0; index < count; index++) {
                        if ($("#non_checkin" + index).val() != null) {
                            checkin[count] = $("#non_checkin" + index).val();
                            checkout[count] = $("#non_checkout" + index).val();
                            count++;
                        }
                    }
                    $.ajax({
                        type: "get",
                        url: "duplicateDate",
                        dataType: "json",
                        data: {"checkin": checkin, "checkout": checkout},
                        sunccess: function (data) {
                            alert("hi");


                        }
                    })
                    return false;
                }
            </script>


            <tr>
                <td align="center" colspan="2">
                    <button type="submit" class="room_insert_btn1">등록</button>
                    <button type="submit" class="room_insert_btn2" onclick="location.href='history.back()'">취소</button>
                </td>
            </tr>
        </table>
    </div>
</form>
</body>
</html>