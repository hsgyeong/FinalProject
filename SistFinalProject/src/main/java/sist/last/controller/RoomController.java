package sist.last.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoomController {

	@GetMapping("/room/roominsert")
	public String roominsertform()
	{
		return "/room/roominsert";
	}
}
