package zgz.community2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import zgz.community2.mapper.QuestionMapper;
import zgz.community2.model.Question;

@Controller
public class publishController {

    @Autowired
    QuestionMapper questionMapper;

    @GetMapping("/toPublish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(String title,String description,String tag){

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmt_modified(System.currentTimeMillis());
        question.setGmt_create(question.getGmt_create());

        questionMapper.addQuestion(question);
        return "index";
    }


}
