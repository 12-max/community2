package zgz.community2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import zgz.community2.dto.PaginationDTO;
import zgz.community2.mapper.UserMapper;
import zgz.community2.model.User;
import zgz.community2.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;


    @GetMapping("/profile/{action}")
    public String profile(
            @PathVariable(name = "action")String action,
            Model model,
            HttpServletRequest request,
            @RequestParam(name = "page",defaultValue = "1",required = false) Integer page,
            @RequestParam(name = "size",defaultValue = "5",required = false) Integer size){
        String user = (String) request.getSession().getAttribute("user");
        User userName = userMapper.userName(user);
        if (userName==null){
            return "redirect:/";
        }

        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","我的回复");
        }
        PaginationDTO paginationDTO = questionService.listQuestion(userName.getId(), page, size);
        System.out.println(paginationDTO.toString());
        model.addAttribute("pagination",paginationDTO);
        return "profile";
    }
}
