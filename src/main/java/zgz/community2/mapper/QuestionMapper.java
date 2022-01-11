package zgz.community2.mapper;

import org.apache.ibatis.annotations.*;
import zgz.community2.dto.QuestionDTO;
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

    @Select("select * from question where creator=#{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param("userId") Integer userId,
                                @Param("offset") Integer offset,
                                @Param("size") Integer size);

    @Select("select count(1) from question where creator=#{userid}")
    Integer countByUserId(@Param("userid") Integer userid);

    @Select("select * from question where id = #{id}")
    Question getById(@Param("id") Integer id);

    @Update("update question set title = #{title},description = #{description},gmt_modified = #{gmt_modified} ,tag = #{tag} where id=#{id}")
    void updata(Question question);
}
