package com.project.bus.controller;

import com.project.bus.entity.Bus;
import com.project.bus.service.BusService;
import com.project.bus.service.ReserveService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class BtsController {
    @Autowired
    private BusService busService;
    @Autowired
    private ReserveService reserveService;
    @RequestMapping(value = "/html1", method = RequestMethod.GET)
    public String test_html1(){
        return "404";
    }

    //@RequestMapping(value = "/html2", method = RequestMethod.GET)
    //public String test_html2(){
        //return "index";
    //}
    @GetMapping("/")
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

        return "bushome";
    }

}
