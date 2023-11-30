<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Dongle:wght@300&family=Gaegu:wght@300&family=Nanum+Pen+Script&family=Sunflower:wght@300&display=swap"
          rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
    <title>Insert title here</title>
</head>
<body>
<c:if test="${accomList==null}">
    <h1>등록된 숙소가 없습니다</h1>
</c:if>
<c:if test="${accomList!=null}">
    <table style="width: 600px; border: 1px solid black">
        <tr>
            <td align="center" valign="top"><b>예약 불가능 날짜 선택</b>
                <br><br>
                <h4><b>선택할 숙소</b></h4>
                <select id="accomSelect" class="form-control" style="width: 120px;">
                    <option value="nonSelect">선택해주세요</option>
                    <c:forEach var="list" items="${accomList}" varStatus="i">
                        <option value="${list}">${list}</option>
                    </c:forEach>
                </select>
                <br>
                <button type="button" class="btn btn-outline-danger addDate">날짜 추가</button>
            </td>
            <td>
                <h4><b>불가능 날짜</b></h4>
                <div id="div-nonDate" style="width: 400px;">
                </div>
                <input type="hidden" id="count">
                <input type="hidden" id="totCount">
                <div style="margin-left: 15%" id="setting-btn">
                    <button type="button" class="btn btn-outline-danger"
                            onclick="checkDate()">중복 체크
                    </button>
                    <button type="button" class="btn btn-danger"
                            onclick="resetDate()">다시 설정
                    </button>
                </div>
                <input type="hidden" id="checkDuplicate" value="0">
            </td>
        </tr>
        <tr>
            <td align="center" colspan="2">
                <button type="button" class="btn btn-outline-danger" id="saveNonBook">등록하기</button>
            </td>
        </tr>
    </table>
</c:if>

<script type="text/javascript">
    let today = new Date();
    let getYear = today.getFullYear();
    let getMonth = today.getMonth() + 1;
    let getDay = today.getDate();
    let date = getYear + "-" + getMonth + "-" + getDay;
    $("#non_checkin1").attr("min", date);
    $("#non_checkout1").attr("min", date);
    $("#setting-btn").hide();
    $("#saveNonBook").hide();
    $(".addDate").hide();

    $(".addDate").click(function () {
        var totCount = $("#totCount").val();
        var count = $("#count").val();
        //alert("totCount" + totCount);
        //alert("count" + count);
        if (totCount > 4) {
            alert("5개 이상 등록할 수 없습니다.");
            return;
        }
        totCount++;
        count++;
        var s = "";
        s += "<span class='d-inline-flex nonDate newNonDate' id='nonDate" + count + "'>";
        s += "<input type='date' id='non_checkin" + count + "' name='checkin[]' min='" + date + "'";
        s += "class='form-control' style='width: 150px; margin-right: 20px;'> ~"
        s += "<input type='date' id='non_checkout" + count + "' name='checkout[]' min='" + date + "'";
        s += "class='form-control' style='width: 150px; margin-left: 20px;'>";
        s += "<button type='button' class='delDate btn btn-danger sm' style='margin-left: 10px;' "
        s += "idx='" + count + "'>x</button>"
        s += "<input type='hidden' id='count" + count + "' value='true'>"
        s += "</span><span class='br br" + count + "'><br><br>";
        $("#div-nonDate").append(s);

        $("#totCount").val(totCount);
        $("#count").val(count);
    })

    $(document).on("click", ".delDate", function () {
        var totCount = $("#totCount").val();
        var idx = $(this).attr("idx");
        $("#nonDate" + idx).remove();
        $(".br" + idx).remove();
        totCount--;
        $("#totCount").val(totCount);
    })

    function checkDate() {
        //alert("누름");
        var cnt = $("#count").val();
        var checkin = [];
        var checkout = [];
        var idx = [];
        for (var index = 1; index <= cnt; index++) {
            var checkinValue = $("#non_checkin" + index).val();
            var checkoutValue = $("#non_checkout" + index).val();
            var countIdx = $("#count" + index).val();
            //alert("[" + checkinValue + "," + checkoutValue + "]");
            //alert(index + " : " + countIdx);
            if (countIdx == 'true') {
                if (checkinValue != "" && checkoutValue != "") {
                    checkin.push(checkinValue);
                    checkout.push(checkoutValue);
                }
                if (checkinValue == "" && checkoutValue != "") {
                    alert("날짜를 입력하지 않았습니다.");
                    return false;
                }
                if (checkinValue != "" && checkoutValue == "") {
                    alert("날짜를 입력하지 않았습니다.");
                    return false;
                }
                if (checkinValue == "" && checkoutValue == "") {
                    alert("날짜를 입력하지 않았습니다.");
                    return false;
                }
            }
        }

        $.ajax({
            type: "get",
            url: "duplicate-date",
            traditional: true,
            dataType: "json",
            data: {"checkin": checkin, "checkout": checkout, "idx": idx},
            success: function (data) {
                if (data.flag == 0) {
                    alert("중복된 날짜가 있습니다 수정해주세요.");
                }
                if (data.flag == 1) {
                    alert("중복된 날짜가 없습니다.");
                    $("span.nonDate").children().prop('disabled', true);
                    $("#checkDuplicate").val(1);
                }
            }
        });
    }

    function resetDate() {
        var flag = $("#checkDuplicate").val();
        if (flag == 0) {
            alert("날짜를 입력하지 않았습니다.");
        }
        if (flag == 1) {
            $("#checkDuplicate").val(0);
            $("span.nonDate").children().prop('disabled', false);
            $("span.newNonDate").remove();
            $("span.br").remove();
            $("#non_checkin1").val("");
            $("#non_checkout1").val("");

        }
    }

    function checkDuplicateDate() {
        var flag = $("#checkDuplicate").val();
        if (flag == 0) {
            alert("중복 날짜를 체크해주세요.");
            return false;
        }
        return true;
    }

    $("#saveNonBook").click(function () {
        var flag = $("#checkDuplicate").val();
        alert(flag);
        if (flag == 0) {
            alert("날짜를 입력 후 중복체크 눌러주세요.");
        }
        if (flag == 1) {
            var cnt = $("#count").val();
            var checkin = [];
            var checkout = [];
            for (var index = 1; index <= cnt; index++) {
                var checkinValue = $("#non_checkin" + index).val();
                var checkoutValue = $("#non_checkout" + index).val();
                var countIdx = $("#count" + index).val();
                var accom_name = $("#accomSelect").val();
                /*alert("[" + checkinValue + "," + checkoutValue + "]");
                alert(index + " : " + countIdx);*/
                if (countIdx == 'true') {
                    checkin.push(checkinValue);
                    checkout.push(checkoutValue);
                    alert(checkinValue + "," + checkoutValue);
                }
            }
            for (var i = 0; i < checkin.length; i++) {
                alert(checkin[i]);
            }
            $.ajax({
                type: "get",
                traditional: true,
                url: "add-book",
                dataType: "html",
                data: {"checkin": checkin, "checkout": checkout, "accom_name": accom_name},
                success: function () {
                    alert("등록되었습니다.");
                    location.reload();
                }
            })
        }
    })

    $("#accomSelect").change(function () {
        $("span.newNonDate").remove();
        $("span.br").remove();
        var accom_name = $("#accomSelect").val();
        if (accom_name == 'nonSelect') {
            $("#setting-btn").hide();
            $("#saveNonBook").hide();
            $(".addDate").hide();
            return false;
        }
        $.ajax({
            type: "get",
            url: "select-accom",
            dataType: "json",
            data: {"accom_name": accom_name},
            success: function (data) {
                alert(data.length)
                if (data.length == 0) {
                    $("#totCount").val(1);
                    $("#count").val(1);
                    var s = "";
                    s += "<span class='d-inline-flex nonDate newNonDate' id='nonDate1'>";
                    s += "<input type='date' id='non_checkin1' name='checkin[]' min='" + date + "'";
                    s += "class='form-control' style='width: 150px; margin-right: 20px;'> ~";
                    s += "<input type='date' id='non_checkout1' name='checkout[]' min='" + date + "'";
                    s += "class='form-control' style='width: 150px; margin-left: 20px;'>";
                    s += "<button type='button' class='delDate btn btn-danger sm' style='margin-left: 10px;' ";
                    s += "idx='1'>x</button>";
                    s += "<input type='hidden' id='count1' value='true'>"
                    s += "</span><span class='br br1'><br><br>";
                    $("#div-nonDate").append(s);
                    $("#setting-btn").show();
                    $("#saveNonBook").show();
                    $(".addDate").show();
                }
                if (data.length > 0) {
                    $("#totCount").val(data.length);
                    $("#count").val(data.length);
                    $.each(data, function (i, ele) {
                        var s = "";
                        s += "<span class='d-inline-flex nonDate newNonDate' id='nonDate" + (i + 1) + "'>";
                        s += "<input type='date' id='non_checkin" + (i + 1) + "' name='checkin[]' min='" + date + "'";
                        s += "class='form-control' style='width: 150px; margin-right: 20px;'";
                        s += "value='" + ele.non_checkin + "'> ~"
                        s += "<input type='date' id='non_checkout" + (i + 1) + "' name='checkout[]' min='" + date + "'";
                        s += "class='form-control' style='width: 150px; margin-left: 20px;'";
                        s += "value='" + ele.non_checkout + "'>"
                        s += "<button type='button' class='delDate btn btn-danger sm' style='margin-left: 10px;' "
                        s += "idx='" + (i + 1) + "'>x</button>"
                        s += "<input type='hidden' id='count" + (i + 1) + "' value='true'>"
                        s += "</span><span class='br br" + (i + 1) + "'><br><br>";
                        $("#div-nonDate").append(s);
                        $("#setting-btn").show();
                        $("#saveNonBook").show();
                        $(".addDate").show();
                    })
                }
            }
        })

    })

</script>
</body>
</html>