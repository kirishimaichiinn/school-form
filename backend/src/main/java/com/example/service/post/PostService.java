package com.example.service.post;

import com.example.Util.RestBean;
import com.example.entity.auth.Account;
import com.example.entity.post.PostHead;
import com.example.mapper.auth.AccountMapper;
import com.example.mapper.post.PostHeadMapper;
import com.example.mapper.post.PostReplyMapper;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Resource
    PostHeadMapper postHeadMapper;
    @Resource
    PostReplyMapper postReplyMapper;
    @Resource
    AccountMapper accountMapper;

    public ResponseEntity<RestBean.RestData<String>> addPostHead(PostHead postHead){
        Account exist = accountMapper.selectById(postHead.getAuthor_id());
        if(exist != null){
            int insert = postHeadMapper.insert(postHead);
            if(insert >0) return RestBean.success("","发布成功");
            else return RestBean.failure(500,"数据添加失败");
        }else return RestBean.failure("用户不存在");
    }
}
