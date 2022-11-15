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

@Controller // 사용자의 요청을 처리 한 후 지정된 뷰에 모델 객체를 넘겨주는 역할을 한다.
public class BusController {

    @Autowired
    private BusService busService;
    @Autowired
    private ReserveService reserveService;
    @GetMapping("/bus/write") // 어떤 url로 접근할 것인가? localhost:8080/bus/write
    public String busWriteForm() {

        return "/buswrite"; // 어떤 html view 파일로 리턴해줄것인가 => buswrite.html
    }

    @PostMapping("/bus/write/create")
    public String busWritePro(Bus bus, Model model) { // 매개변수를 다 적을 필요 없이 Bus bus 엔티티 형식 그대로 받는다

        busService.write(bus);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/bus/list");

        return "message";
    }

    @GetMapping("/bushome") // 어떤 url로 접근할 것인가? localhost:8080/bus/write
    public String busHome() {

        return "/bushome"; // 어떤 html view 파일로 리턴해줄것인가 => buswrite.html
    }

    @GetMapping("/bussearch")
    // page: default 페이지, size: 한 페이지 보여줄 리스트 수, id로 내림차순
    public String busList(Model model,
                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
                            , String searchKeyword) { // Model: 데이터를 담아 우리가 보는 페이지로 넘겨준다
        Page<Bus> list = null;

        if(searchKeyword == null)
        {
            list = busService.busList(pageable);
        }
        else{ // 검색할 단어가 입력되면
            list = busService.busSearchList(searchKeyword, pageable); // 검색 단어가 포함된 리스트를 반환
        }

        int nowPage = list.getPageable().getPageNumber() + 1; // getPageable 현재 페이지를 가져온다, 1페이지부터 보여주기 위해 +1
        int startPage = Math.max(nowPage - 4, 1); // 현재페이지 - 4, 음수페이지가 발생할 수 있으므로 Math.max를 이용한 시작 페이지 반환
        int endPage = Math.min(nowPage + 5, list.getTotalPages()); // End페이지를 넘는 페이지 수가 발생 할 수 있으므로 Math.min을 이용한 엔드 페이지 반환

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "bussearch";
    }

    @GetMapping("/bus/view") // localhost8080/bus/view?id=1 Get 방식 파라미터
    public String busview(Model model, Integer id) { // 게시글 번호를 매개변수로 넘겨준다
        model.addAttribute("bus", busService.busView(id));
        return "busview";
    }

    @GetMapping("/bus/delete")
    public String busDelete(Integer id){

        busService.busDelete(id);

        return "redirect:/bus/list";
    }

    @GetMapping("/bus/modify/{id}")
    public String busModify(@PathVariable("id") Integer id,
                            Model model) {
        model.addAttribute("bus", busService.busView(id));
        return "busmodify";
    }

    @PostMapping("/bus/update/{id}")
    public String busUpdate(@PathVariable("id") Integer id, Bus bus){
        Bus busTemp = busService.busView(id);
        busTemp.setBusnum(bus.getBusnum());
        busTemp.setRoute(bus.getRoute());

        busService.write(busTemp);

        return "redirect:/";
    }




}