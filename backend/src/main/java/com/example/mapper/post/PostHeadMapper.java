package com.example.mapper.post;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.post.PostHead;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostHeadMapper extends BaseMapper<PostHead> {
    @Select("select * from post_head order by last_reply desc limit #{passRows},#{getRows}")
    List<PostHead> getPostHeadList(int passRows,int getRows);
}
