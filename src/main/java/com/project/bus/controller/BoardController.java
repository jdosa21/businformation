package com.project.bus.controller;
import com.project.bus.entity.Board;


import com.project.bus.repository.BoardRepository;
import com.project.bus.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class BoardController {
    int hit = 0;
    @Autowired
    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;

    }

//    @GetMapping("/busboard")
//    public String busboard() {
//        return "busboard";
//    }

    @GetMapping("/post")
    public String post() {
        return "post";
    }

    @PostMapping("/post1")
    public String post1(Model model,Board board) {
        boardService.write(board);
        return "redirect:/busboard";
    }

    @GetMapping("/busboard")
    // page: default 페이지, size: 한 페이지 보여줄 리스트 수, id로 내림차순
    public String boardList(Model model,
                          @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
            , String searchKeyword) { // Model: 데이터를 담아 우리가 보는 페이지로 넘겨준다
        Page<Board> list = null;

        list = boardService.boardList(pageable);

        int nowPage = list.getPageable().getPageNumber() + 1; // getPageable 현재 페이지를 가져온다, 1페이지부터 보여주기 위해 +1
        int startPage = Math.max(nowPage - 4, 1); // 현재페이지 - 4, 음수페이지가 발생할 수 있으므로 Math.max를 이용한 시작 페이지 반환
        int endPage = Math.min(nowPage + 5, list.getTotalPages()); // End페이지를 넘는 페이지 수가 발생 할 수 있으므로 Math.min을 이용한 엔드 페이지 반환

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "busboard";
    }

    @GetMapping("/boardview") // localhost8080/bus/view?id=1 Get 방식 파라미터
    public String boardview(Model model, Integer id,Board board) { // 게시글 번호를 매개변수로 넘겨준다
        hit++;

        model.addAttribute("board", boardService.boardView(id));
        model.addAttribute("hit", hit);

        // boardService.updateView(id); // views ++
        return "boardview";
    }

    @GetMapping("/boardview/delete")
    public String boardDelete(Integer id, Model model){

        boardService.boardDelete(id);
        model.addAttribute("message", "게시물이 삭제 되었습니다.");
        model.addAttribute("searchUrl", "http://localhost:8080/busboard");
        return "message";

    }

//    @GetMapping("/update")
//    public String update() {
//
//        return "update";
//    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public  void getModify(@RequestParam("id") int id, Model model) throws Exception {
        model.addAttribute("board", boardService.boardView(id));
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, Model model){
        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp);

        model.addAttribute("message", "게시물이 수정 되었습니다.");
        model.addAttribute("searchUrl", "http://localhost:8080/busboard");
        return "message";
    }

}