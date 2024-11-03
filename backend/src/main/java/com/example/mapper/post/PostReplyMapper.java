package com.example.mapper.post;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.post.PostHead;
import com.example.entity.post.PostReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface PostReplyMapper extends BaseMapper<PostReply> {
    @Select("select * from post_reply where head_id = #{head_id} and status = '1'")
    List<PostReply> getAllReply(int head_id);

    @Select("select * from post_reply where speaker_id = #{speaker_id} and status = '1' order by id desc limit #{passRows},#{getRows}")
    List<PostReply> getPersonalReply(int speaker_id, int passRows, int getRows);

    @Update("update post_reply set status = '0' where id = #{id}")
    boolean delReply(int id);

    @Update("update post_reply set status = '0' where head_id = #{head_id}")
    boolean delAllReplyByHeadId(int head_id);

    @Update("update post_reply set text = #{text} where head_id = #{head_id} and floor = #{floor}")
    boolean updateReplyByHeadAndFloor(String text,int head_id,int floor);
}
