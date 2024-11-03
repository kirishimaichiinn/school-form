package com.example.entity.post;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class PostReply {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;
    Integer head_id;
    String head_title;
    Integer floor;
    Integer speaker_id;
    String speaker_name;
    String text;
    Integer status = 1;
}
