package com.project.bus.controller;

import com.project.bus.entity.Bus;
import com.project.bus.entity.Reserve;
import com.project.bus.service.BusService;
import com.project.bus.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReserveController {
    @Autowired
    private BusService busService;
    @Autowired
    private ReserveService reserveService;


    // 승하차 예약 버튼 컨트롤러
    @PostMapping("/reserve")
    public String reserve(Reserve reserve, Model model) {

        reserveService.ReserveCheck(reserve);
        model.addAttribute("message", "승하차 예약 등록이 완료되었습니다.");
        model.addAttribute("searchUrl", "/reservecheck");
        return "message";
    }

    @PostMapping("http://localhost:8090/board/reserve")
    public String boardreserve(Reserve reserve, Model model) {

        reserveService.ReserveCheck(reserve);
        model.addAttribute("message", "승하차 예약 전송이 완료 되었습니다.");
        model.addAttribute("searchUrl", "http://localhost:8080/reserve/list");
        return "message";
    }

    // 승하차 예약 정보 확인
    @GetMapping("/reserve/list")
    public String ReserveList(Model model,@PageableDefault(page = 0, size = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        model.addAttribute("list", reserveService.reserveList(pageable));

        return "reservelist";
    }

    // 승하차 예약 삭제
    @GetMapping("/reserve/delete")
    public String reserveDelete(Integer id, Model model){

        reserveService.reserveDelete(id);
        model.addAttribute("message", "예약 전송이 취소되었습니다.");
        model.addAttribute("searchUrl", "http://localhost:8080/reservecheck");
        return "message";
    }

    @GetMapping("/reservecheck")
    public String ReserveCheck(Model model,@PageableDefault(page = 0, size = 1, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        model.addAttribute("list", reserveService.reserveList(pageable));

        return "reservecheck";
    }


}
