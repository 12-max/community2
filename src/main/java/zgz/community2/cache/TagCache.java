package zgz.community2.cache;

import zgz.community2.dto.TagDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagCache {
    public List<TagDTO> get(){
        List<TagDTO> tagDTOs = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setList(Arrays.asList("js","java","spring","mysql","ppt","wor d"));
        tagDTOs.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setList(Arrays.asList("linux","nginx","apache","ubuntu","centos"));
        tagDTOs.add(framework);
        return tagDTOs;
    }
}
