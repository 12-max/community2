package zgz.community2.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunMisc;
import org.springframework.stereotype.Service;
import zgz.community2.dto.QuestionDTO;
import zgz.community2.mapper.QuestionMapper;
import zgz.community2.mapper.UserMapper;
import zgz.community2.model.Question;
import zgz.community2.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

   /* public List<QuestionDTO> listQuestion() {
        List<Question> listQuestion = questionMapper.listQuestion();
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : listQuestion) {
            User byId = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(byId);
            questionDTOList.add(questionDTO);
        }
        return null;
    }*/

    public List<QuestionDTO> listQuestion() {
        List<Question> listQuestion = questionMapper.listQuestion();
        List<QuestionDTO> questionDTOList=new ArrayList<>();
        for (Question question : listQuestion) {
            User byId = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(byId);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
