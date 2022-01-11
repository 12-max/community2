package zgz.community2.controller;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QDecoderStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zgz.community2.dto.QuestionDTO;
import zgz.community2.mapper.QuestionMapper;
import zgz.community2.mapper.UserMapper;
import zgz.community2.model.Question;
import zgz.community2.model.User;
import zgz.community2.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class publishController {


    @Autowired
    private QuestionService questionService;


    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model){
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());

        return "publish";
    }


    @GetMapping("/toPublish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title",required = false) String title,
                            @RequestParam(value = "description") String description,
                            @RequestParam(value = "tag",required = false) String tag,
                            @RequestParam(value = "id",required = false) Integer id,
                            HttpServletRequest request,
                            Model model){

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        model.addAttribute("id",id);

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setId(id);

        questionService.createOrUpdate(question);
        return "redirect:/";
    }


}
