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
    <link rel="stylesheet" type="text/css" href="https://cdn.rawgit.com/innks/NanumSquareRound/master/nanumsquareround.min.css">

    <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
    <title>Insert title here</title>
</head>
<style type="text/css">
    div.filter-box{
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

    .day.disabled {
        color: #ccc;
        cursor: not-allowed;
    }

    .selected {
        background-color: #4285f4;
        color: #fff;
    }
</style>
<body>
<caption><b style="font-size: 40px; right: 50%">검색 : '${keyword}'</b></caption>
<br><br>
<table style="width: 100%; border: 2px solid gray;">
    <tr>
        <td style="width: 35%" colspan="5">
            <div class="filter-box">
                <section class="date-wrap">
                    <h3>날짜</h3>
                    <span class="date-view">
                        <b>11.15 ~ 11.16</b>
                        <em>&nbsp;·&nbsp;1박</em>
                    </span>
                    <div class="calendar">
                        <div class="header">
                            <div class="prev" onclick="prevMonth()">&#9665;</div>
                            <div class="current-month"></div>
                            <div class="next" onclick="nextMonth()">&#9655;</div>
                        </div>
                        <div class="days" id="calendar-days"></div>
                    </div>

                    <script>
                        const today = new Date();
                        let currentMonth = today.getMonth();
                        let currentYear = today.getFullYear();
                        const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

                        function updateCalendar() {
                            const firstDay = new Date(currentYear, currentMonth, 1);
                            const lastDay = new Date(currentYear, currentMonth + 1, 0);
                            const daysContainer = document.getElementById('calendar-days');
                            const currentMonthContainer = document.querySelector('.current-month');
                            daysContainer.innerHTML = '';
                            currentMonthContainer.innerHTML = `${months[currentMonth]} ${currentYear}`;

                            for (let i = 1; i <= lastDay.getDate(); i++) {
                                const dayElement = document.createElement('div');
                                dayElement.textContent = i;
                                dayElement.classList.add('day');
                                dayElement.addEventListener('click', () => selectDate(i));
                                daysContainer.appendChild(dayElement);

                                if (i === today.getDate() && currentMonth === today.getMonth() && currentYear === today.getFullYear()) {
                                    dayElement.classList.add('selected');
                                }

                                if (i < firstDay.getDay() + 1 || i > lastDay.getDate()) {
                                    dayElement.classList.add('disabled');
                                }
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
                            alert(`Selected Date: ${months[currentMonth]} ${day}, ${currentYear}`);
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