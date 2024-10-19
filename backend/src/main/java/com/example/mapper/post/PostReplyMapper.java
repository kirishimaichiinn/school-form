package com.example.mapper.post;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.post.PostReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostReplyMapper extends BaseMapper<PostReply> {
    @Select("select * from post_body where head_id = #{head_id}")
    List<PostReply> getAllReply(int head_id);
}
