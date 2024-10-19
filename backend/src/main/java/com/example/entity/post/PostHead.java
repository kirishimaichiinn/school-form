package com.example.entity.post;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostHead {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;
    String title;
    String text;
    Integer author_id;
    String author_name;
    LocalDateTime last_reply;
    Integer max_floor = 0;
    Integer status = 1;
}
