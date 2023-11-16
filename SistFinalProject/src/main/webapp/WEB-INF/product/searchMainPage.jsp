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
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@2.0/nanumsquare.css">

    <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
    <title>Insert title here</title>
</head>
<style type="text/css">
    .date-wrap {
        font-family: 'NanumSquare', serif;
    }

    div.btn-date {
        padding: 10px;
        border: 2px solid gainsboro;
        border-radius: 5px;
        cursor: pointer;
    }

    div.filter-box {
        padding: 20px;
        margin: 30px;
        border: 1px solid gainsboro;
    }

    .calendar {
        width: 300px;
        background-color: #fff;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
        overflow: hidden;
    }

    .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px;
        background-color: #f7323f;
        color: #fff;
    }

    .prev, .next {
        cursor: pointer;
        font-size: 1.1em;
        font-weight: bold;
    }

    .days {
        display: grid;
        grid-template-columns: repeat(7, 1fr);
        padding: 10px;
    }

    .day {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 40px;
        border-radius: 4px;
        cursor: pointer;
    }

    .day:hover {
        background-color: #e0e0e0;
    }

    .selected:hover {
        background-color: #AA323F;
    }

    .day.disabled {
        color: #ccc;
        cursor: not-allowed;
    }

    .selected {
        background-color: #f7323f;
        color: #fff;
    }
</style>
<body>
<script type="text/javascript">
    $(function () {
        $(".calendar").hide();

        $(".btn-date").click(function () {
            $(".calendar").toggle();
        })
    })
</script>
<caption><b style="font-size: 40px; font-family: 'NanumSquare', serif;">검색 : '${keyword}'</b></caption>
<br><br>
<table style="width: 100%; border: 2px solid gray;">
    <tr>
        <td style="width: 35%" colspan="5">
            <div class="filter-box">
                <section class="date-wrap">
                    <h4><b>날짜</b></h4>
                    <div class="btn-date d-inline-flex">
                        <i class="bi bi-calendar-check" style="margin-right: 10px;"></i>
                        <span class="date-view">
                            <b>11.15 ~ 11.16</b>
                            <em>&nbsp;·&nbsp;1박</em>
                        </span>
                    </div>
                    <div class="calendar">
                        <div class="header">
                            <div class="prev" onclick="prevMonth()">&#9665;</div>
                            <div class="current-month"></div>
                            <div class="next" onclick="nextMonth()">&#9655;</div>
                        </div>
                        <div class="days" id="calendar-days"></div>
                        <button class="calendar-setting btn btn-outline-danger">선택 완료</button>
                    </div>

                    <script>
                        const today = new Date();
                        let count = 1;
                        let firstClickDay = today.getDate();
                        let lastClickDay = firstClickDay + 1;
                        let firstMonth = today.getMonth();
                        let lastMonth = today.getMonth();
                        let currentMonth = today.getMonth();
                        let firstYear = today.getFullYear();
                        let lastYear = today.getFullYear();
                        let currentYear = today.getFullYear();
                        let start = false;
                        let flag = false;

                        function updateCalendar() {
                            const currentDay = new Date(currentYear, currentMonth + 1, 0);
                            const lastDay = new Date(lastYear, lastMonth + 1, 0);
                            const firstDay = new Date(firstYear, firstMonth + 1, 0);
                            //alert(count);
                            //alert(firstMonth + "," + currentMonth);
                            //alert(firstClickDay + "," + lastClickDay);
                            if (count == 0) {
                                lastClickDay = null;
                            }
                            //alert(flag && start);
                            if (flag && start) {
                                if (count == 1) {
                                    //alert(firstDay.getDate() - firstClickDay + lastClickDay);
                                    if (firstYear < lastYear) {
                                        if (lastMonth > 0) {
                                            alert("7일까지 예약 가능합니다.");
                                            lastClickDay = null;
                                            lastMonth = null;
                                            count--;
                                            return;
                                        }
                                        if (firstDay.getDate() - firstClickDay + lastClickDay > 6) {
                                            alert("7일까지 예약 가능합니다.");
                                            lastClickDay = null;
                                            lastMonth = null;
                                            count--;
                                            return;
                                        }
                                    }
                                    if (firstYear > lastYear) {
                                        if (lastMonth < 11) {
                                            alert("7일까지 예약 가능합니다.");
                                            lastClickDay = null;
                                            lastMonth = null;
                                            count--;
                                            return;
                                        }
                                        if (lastDay.getDate() - lastClickDay + firstClickDay > 6) {
                                            alert("7일까지 예약 가능합니다.");
                                            lastClickDay = null;
                                            lastMonth = null;
                                            count--;
                                            return;
                                        }
                                    }
                                    if (firstYear == lastYear) {
                                        if (firstMonth < lastMonth) {
                                            if (firstMonth + 1 < lastMonth) {
                                                alert("7일까지 예약 가능합니다.");
                                                lastClickDay = null;
                                                lastMonth = null;
                                                count--;
                                                return;
                                            }
                                            if (firstDay.getDate() - firstClickDay + lastClickDay > 6) {
                                                //alert("7일이상");
                                                alert("7일까지 예약 가능합니다");
                                                lastClickDay = null;
                                                lastMonth = null;
                                                count--;
                                                return;
                                            }
                                        }
                                        if (firstMonth > lastMonth) {
                                            if (firstMonth > lastMonth + 1) {
                                                alert("7일까지 예약 가능합니다.");
                                                lastClickDay = null;
                                                lastMonth = null;
                                                count--;
                                                return;
                                            }
                                        }
                                        //alert(lastClickDay + "," + firstClickDay);
                                        if (firstMonth == lastMonth) {
                                            if (lastClickDay > firstClickDay) {
                                                if (lastClickDay - firstClickDay > 6) {
                                                    alert("7일까지 예약 가능합니다.");
                                                    lastClickDay = null;
                                                    lastMonth = null;
                                                    count--;
                                                    return;
                                                }
                                            }
                                            if (lastClickDay < firstClickDay) {
                                                if (firstClickDay - lastClickDay > 6) {
                                                    alert("7일까지 예약 가능합니다.");
                                                    lastClickDay = null;
                                                    lastMonth = null;
                                                    count--;
                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            //alert(currentMonth + "," + today.getMonth());
                            $(".prev").attr('onclick', "prevMonth()").css({
                                "color": "white",
                                "cursor": "pointer"
                            });
                            if (currentMonth == today.getMonth()) {
                                $(".prev").attr("onclick", "").css({
                                    "color": "#ccc",
                                    "cursor": "none"
                                });
                            }
                            /*const firstDay = new Date(currentYear, currentMonth, 1);
                            const lastDay = new Date(currentYear, currentMonth + 1, 0);*/
                            const daysContainer = document.getElementById('calendar-days');
                            /*const currentMonthContainer = document.querySelector('.current-month');*/
                            $(".current-month").html(currentYear + "년 " + (currentMonth + 1) + "월");
                            daysContainer.innerHTML = '';
                            //alert(currentMonth + "," + firstMonth);

                            for (let i = 1; i <= currentDay.getDate(); i++) {
                                const dayElement = document.createElement('div');
                                dayElement.textContent = i;
                                dayElement.classList.add('day');

                                if (i < today.getDate() && currentMonth === today.getMonth() && currentYear === today.getFullYear()) {
                                    dayElement.classList.add('disabled');
                                    dayElement.style.pointerEvents = 'none'; // Disable click event
                                    dayElement.style.opacity = '0.5'; // Apply visual effect for disabled date
                                }

                                /*if (i < firstDay.getDay() + 1 || i > lastDay.getDate()) {
                                    dayElement.classList.add('disabled');
                                    dayElement.style.pointerEvents = 'none'; // Disable click event
                                    dayElement.style.opacity = '0.5'; // Apply visual effect for disabled date
                                }*/
                                if (currentMonth == firstMonth) {
                                    if (lastClickDay == null) {
                                        if (firstClickDay == i) {
                                            dayElement.classList.add('selected');
                                        }
                                    }
                                    if (lastClickDay != null) {
                                        //alert(firstYear + "," + lastYear);
                                        if (firstYear == lastYear) {
                                            if (firstMonth == lastMonth) {
                                                if (firstClickDay < lastClickDay) {
                                                    if (i >= firstClickDay && i <= lastClickDay) {
                                                        dayElement.classList.add('selected');
                                                    }
                                                }
                                                if (firstClickDay > lastClickDay) {
                                                    if (i >= lastClickDay && i <= firstClickDay) {
                                                        dayElement.classList.add('selected');
                                                    }
                                                }
                                            }
                                            if (firstMonth < lastMonth) {
                                                if (i >= firstClickDay && i <= lastDay.getDate()) {
                                                    dayElement.classList.add('selected');
                                                }

                                            }
                                            if (firstMonth > lastMonth) {
                                                if (i <= firstClickDay && i >= 1) {
                                                    dayElement.classList.add('selected');
                                                }

                                            }
                                        }
                                        if (firstYear < lastYear) {
                                            //alert("첫번째<나중")
                                            if (firstYear == currentYear) {
                                                if (i >= firstClickDay && i <= firstDay.getDate()) {
                                                    dayElement.classList.add('selected');
                                                }
                                            }
                                        }
                                        if (firstYear > lastYear) {
                                            //alert("첫번째>나중")
                                            if (firstYear == currentYear) {
                                                if (i <= firstClickDay && i >= 1) {
                                                    dayElement.classList.add('selected');
                                                }
                                            }
                                            if (lastYear == currentYear) {
                                                if (i >= lastClickDay && i <= lastDay.getDate()) {
                                                    dayElement.classList.add('selected');
                                                }
                                            }
                                        }
                                    }
                                }
                                if (currentMonth == lastMonth) {
                                    if (lastClickDay != null) {
                                        if (firstYear == lastYear) {
                                            if (firstMonth > lastMonth) {
                                                if (i >= lastClickDay && i <= lastDay.getDate()) {
                                                    dayElement.classList.add('selected');
                                                }

                                            }
                                            if (firstMonth < lastMonth) {
                                                if (i <= lastClickDay && i >= 1) {
                                                    dayElement.classList.add('selected');
                                                }

                                            }
                                        }
                                        if (firstYear < lastYear) {
                                            if (lastYear == currentYear) {
                                                if (i <= lastClickDay && i >= 1) {
                                                    dayElement.classList.add('selected');
                                                }
                                            }
                                        }
                                        if (firstYear > lastYear) {
                                            //alert("첫번째>나중")
                                            if (lastYear == currentYear) {
                                                if (i >= lastClickDay && i <= lastDay.getDate()) {
                                                    dayElement.classList.add('selected');
                                                }
                                            }
                                        }

                                    }
                                }

                                daysContainer.appendChild(dayElement);

                                dayElement.addEventListener('click', () => selectDate(i));
                            }
                        }

                        function prevMonth() {
                            if (currentMonth > 0) {
                                currentMonth--;
                            } else {
                                currentMonth = 11;
                                currentYear--;
                            }
                            updateCalendar();
                        }

                        function nextMonth() {
                            if (currentMonth < 11) {
                                currentMonth++;
                            } else {
                                currentMonth = 0;
                                currentYear++;
                            }
                            updateCalendar();
                        }

                        function selectDate(day) {
                            //alert(day + "," + firstClickDay)
                            if (day == firstClickDay && firstMonth == currentMonth) {
                                return false;
                            }
                            //alert('Selected Date:' + day);
                            count++;
                            if (count == 2) {
                                firstClickDay = day;
                                firstMonth = currentMonth;
                                firstYear = currentYear;
                                flag = false;
                                start = true;

                                count = 0;
                            }
                            if (count == 1) {
                                lastClickDay = day;
                                lastMonth = currentMonth;
                                lastYear = currentYear;
                                flag = true;
                            }
                            //alert(count);
                            //alert(firstClickDay + "," + lastClickDay);
                            updateCalendar();
                        }

                        updateCalendar();
                    </script>
                </section>

            </div>
        </td>
        <td>

        </td>
    </tr>

</table>
</body>
</html>