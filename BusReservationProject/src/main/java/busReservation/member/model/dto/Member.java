package busReservation.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Member {
	private String name; // 이름
	private String phoneNum; // 전화번호
	private int busNo; // 버스 번호
	private String departures; // 출발지
	private String arrivals; // 도착지
	private String departureTime; // 출발 시간
	private int reserveSeatNo; // 예약한 좌석
}
