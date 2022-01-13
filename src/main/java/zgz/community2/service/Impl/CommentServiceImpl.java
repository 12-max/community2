package zgz.community2.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zgz.community2.dto.CommentDTO;
import zgz.community2.mapper.CommentMapper;
import zgz.community2.model.Comment;
import zgz.community2.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    @Override
    public int insert(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setParent_id(commentDTO.getParent_id());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmt_modified(System.currentTimeMillis());
        comment.setGmt_create(System.currentTimeMillis());
        comment.setCommentator(1);
        return commentMapper.inset(comment);

    }
}
