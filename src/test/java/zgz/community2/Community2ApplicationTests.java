package zgz.community2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zgz.community2.dto.CommentDTO;
import zgz.community2.dto.QuestionDTO;
import zgz.community2.mapper.CommentMapper;
import zgz.community2.mapper.QuestionMapper;
import zgz.community2.mapper.UserMapper;
import zgz.community2.model.Comment;
import zgz.community2.service.CommentService;
import zgz.community2.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Community2ApplicationTests {

    @Autowired
    QuestionService questionService;
    @Autowired
    CommentService commentService;
    @Test
    public void test(){
        ArrayList<QuestionDTO> questionDTOS = questionService.related(1l, "java,程序员人生,mysql,生活");
        int i=0;
        for (QuestionDTO questionDTO : questionDTOS) {
            i=1+i;
            System.out.println(questionDTO.toString());
        }
        System.out.println(i);
    }
}
