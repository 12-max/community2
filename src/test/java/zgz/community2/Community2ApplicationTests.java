package zgz.community2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zgz.community2.dto.QuestionDTO;
import zgz.community2.mapper.QuestionMapper;
import zgz.community2.mapper.UserMapper;
import zgz.community2.model.Question;
import zgz.community2.model.User;
import zgz.community2.service.QuestionService;

import java.util.List;

@SpringBootTest
class Community2ApplicationTests {
    @Autowired
    UserMapper userMapper;
    QuestionService questionService=new QuestionService();
    @Autowired
    QuestionService questionMapper;
    @Test
    public void add(){
        List<QuestionDTO> questionDTOS = questionMapper.listQuestion();
        for (QuestionDTO question : questionDTOS) {
            System.out.println(question.toString());
        }
    }
}
