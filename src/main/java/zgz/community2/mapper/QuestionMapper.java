package zgz.community2.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import zgz.community2.model.Question;

import java.util.List;

@Mapper
public interface QuestionMapper {


    @Insert("insert into question (title, description,gmt_create,gmt_modified,creator,tag) VALUES (#{title}, #{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    public void addQuestion(Question question);

    @Select("select * from question limit #{offset},#{size}")
    public List<Question> listQuestion(@Param("offset") Integer offset,
                                       @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();
}
