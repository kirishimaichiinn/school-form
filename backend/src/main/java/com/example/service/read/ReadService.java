package com.example.service.read;

import com.example.Util.ConstConfig;
import com.example.Util.RestBean;
import com.example.entity.post.PostHead;
import com.example.entity.post.PostReply;
import com.example.mapper.auth.AccountMapper;
import com.example.mapper.post.PostHeadMapper;
import com.example.mapper.post.PostReplyMapper;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReadService {
    @Resource
    PostHeadMapper postHeadMapper;
    @Resource
    PostReplyMapper postReplyMapper;
    @Resource
    AccountMapper accountMapper;
    @Resource
    ConstConfig constConfig;

    public ResponseEntity<RestBean.RestData<Object>> getPostHeadList(int page){
        if(page <= 0) return RestBean.failure("参数错误");

        int passRows = (page - 1) * constConfig.getMainViewPageNum();
        int getNum = constConfig.getMainViewPageNum();

        List<PostHead> postHeadList = postHeadMapper.getPostHeadList(passRows, getNum);
        return RestBean.success(Map.of("postHeadList",postHeadList));
    }

    public ResponseEntity<RestBean.RestData<Object>> getPostAndReply(int pid){
        PostHead postHead = postHeadMapper.selectById(pid);
        if(postHead == null) return RestBean.failure("贴子不存在");

        List<PostReply> replyList = postReplyMapper.getAllReply(pid);
        return RestBean.success(Map.of("postHead",postHead,"replyList",replyList));
    }
}
