package sist.last.chat;
public class Room {
    int roomNumber;
    String roomName;

    public int getRoomNumber() {
        return roomNumber;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "Room [roomNumber=" + roomNumber + ", roomName=" + roomName + "]";
    } // toString() 메서드는 객체의 문자열 표현을 반환합니다. 이 메서드를 재정의하여 객체를 문자열로 표현할 때 사용됩니다.

    // Room 클래스는 방의 번호와 이름을 나타내는 데이터 모델로 사용될 수 있습니다.
    //멤버 변수는 private으로 선언되어 외부에서 직접 접근되지 않고 게터와 세터를 통해 접근됩니다.
    //toString 메서드를 재정의함으로써 객체를 문자열로 표현할 때 사용자가 읽기 쉬운 형태로 출력됩니다.
    //이 클래스는 주로 방 정보를 담는 데이터 모델로 활용될 수 있습니다.
}