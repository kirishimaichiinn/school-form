package com.example.mapper.post;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.post.PostHead;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface PostHeadMapper extends BaseMapper<PostHead> {
    @Select("select * from post_head where status = '1' order by last_reply desc limit #{passRows},#{getRows}")
    List<PostHead> getPostHeadList(int passRows,int getRows);

    @Update("update post_head set max_floor = #{maxFloor} where id = #{pid}")
    boolean updateMaxFloor(int pid, int maxFloor);

    @Update("update post_head set last_reply = #{last_reply} where id = #{pid}")
    boolean updateLastReply(int pid, LocalDateTime last_reply);

    @Select("select * from post_head where author_id = #{author_id} and status = '1' order by last_reply desc limit #{passRows},#{getRows}")
    List<PostHead> getPersonalPost(int author_id,int passRows,int getRows);

    @Select("select * from post_head where author_id = #{author_id} and status = '2' order by last_reply desc limit #{passRows},#{getRows}")
    List<PostHead> getPersonalNote(int author_id,int passRows,int getRows);
}
