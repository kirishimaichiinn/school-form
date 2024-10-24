package com.example.mapper.post;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.post.PostHead;
import com.example.entity.post.PostReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostReplyMapper extends BaseMapper<PostReply> {
    @Select("select * from post_reply where head_id = #{head_id}")
    List<PostReply> getAllReply(int head_id);

    @Select("select * from post_reply where speaker_id = #{speaker_id} and status = '1' order by id desc limit #{passRows},#{getRows}")
    List<PostReply> getPersonalReply(int speaker_id, int passRows, int getRows);
}
