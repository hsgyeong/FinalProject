package sist.last.controller;

public class Ssung2sinController {
    /*@ResponseBody
    @GetMapping("/room/duplicate-date")
    public Map<String, String> duplicateDate(@RequestParam("checkin") String[] checkin,
                                             @RequestParam("checkout") String[] checkout,
                                             @RequestParam("idx") int[] idx) {
        Map<String, String> map = new HashMap<>();
        // 필요한 로직을 수행하고 응답을 반환합니다.
        StringBuilder sb = new StringBuilder();
        int flag = 0;
        for (int i = 0; i < checkin.length; i++) {
            LocalDate checkinDate = LocalDate.parse(checkin[i]);
            LocalDate checkoutDate = LocalDate.parse(checkout[i]);

            // 중복 검사
            if (isDateRangeOverlap(checkinDate, checkoutDate, checkin, checkout, i)) {
                sb.append(i).append(",");
                flag = 1;
            }
            if (flag == 1) {
                sb.delete(sb.length() - 1, sb.length());
            }
        }
        map.put("index", sb + "가 중복됨");
        map.put("flag", flag + "");
        return map;
    }

    private boolean isDateRangeOverlap(LocalDate newCheckin, LocalDate newCheckout, String[] checkinList,
                                       String[] checkoutList, int currentIndex) {
        for (int i = 0; i < checkinList.length; i++) {
            if (i != currentIndex) {
                LocalDate existingCheckin = LocalDate.parse(checkinList[i]);
                LocalDate existingCheckout = LocalDate.parse(checkoutList[i]);

                // 새로운 체크인 날짜가 기존 기간에 포함되거나
                // 새로운 체크아웃 날짜가 기존 기간에 포함되는 경우 중복으로 처리
                if ((newCheckin.isEqual(existingCheckin) || newCheckin.isAfter(existingCheckin)) &&
                        (newCheckin.isBefore(existingCheckout) || newCheckin.isEqual(existingCheckout)) ||
                        (newCheckout.isEqual(existingCheckin) || newCheckout.isAfter(existingCheckin)) &&
                                (newCheckout.isBefore(existingCheckout) || newCheckout.isEqual(existingCheckout))) {
                    return true; // 중복이 발생했음
                }
            }
        }
        return false; // 중복이 없음
    }*/
}
