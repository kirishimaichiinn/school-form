package com.example.service.post;

import com.example.Util.ConstConfig;
import com.example.Util.RestBean;
import com.example.entity.auth.Account;
import com.example.entity.post.PostHead;
import com.example.mapper.auth.AccountMapper;
import com.example.mapper.post.PostHeadMapper;
import com.example.mapper.post.PostReplyMapper;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostService {
    @Resource
    PostHeadMapper postHeadMapper;
    @Resource
    PostReplyMapper postReplyMapper;
    @Resource
    AccountMapper accountMapper;
    @Resource
    ConstConfig constConfig;

    public ResponseEntity<RestBean.RestData<Object>> addPostHead(PostHead postHead){
        Account exist = accountMapper.selectById(postHead.getAuthor_id());
        if(exist == null) return RestBean.failure("用户不存在");

        int insert = postHeadMapper.insert(postHead);
        if(insert <=0) return RestBean.failure(500,"数据添加失败");

        return RestBean.success("","发布成功");
    }

    public ResponseEntity<RestBean.RestData<Object>> getPostHeadList(int page){
        if(page <= 0) return RestBean.failure("参数错误");

        int passRows = (page - 1) * constConfig.getMainViewPageNum();
        int getNum = constConfig.getMainViewPageNum();

        List<PostHead> postHeadList = postHeadMapper.getPostHeadList(passRows, getNum);
        return RestBean.success(Map.of("postHeadList",postHeadList));
    }
}
