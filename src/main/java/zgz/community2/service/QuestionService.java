package zgz.community2.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zgz.community2.dto.PaginationDTO;
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


    public PaginationDTO listQuestion(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalPage;
        Integer totalCount = questionMapper.count();

        if (totalCount% size==0){
            totalPage=totalCount/size;
        }else {
            totalPage=totalCount/size+1;
        }
        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }

        paginationDTO.setPagination(totalPage,page);

        //size*(page-1)
        Integer offset = size * (page - 1);
        List<Question> listQuestion = questionMapper.listQuestion(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : listQuestion) {
            User byId = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(byId);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO listQuestion(Integer userid, Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userid);

        if (totalCount% size==0){
            totalPage=totalCount/size;
        }else {
            totalPage=totalCount/size+1;
        }
        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }

        paginationDTO.setPagination(totalPage,page);

        //size*(page-1)
        Integer offset = size * (page - 1);
        List<Question> listQuestion = questionMapper.listByUserId(userid,offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : listQuestion) {
            User byId = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(byId);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId()==null){
            question.setGmt_create(System.currentTimeMillis());
            question.setGmt_modified(System.currentTimeMillis());
            questionMapper.addQuestion(question);
        }else {
            question.setGmt_modified(System.currentTimeMillis());
            questionMapper.updata(question);
        }
    }
}
