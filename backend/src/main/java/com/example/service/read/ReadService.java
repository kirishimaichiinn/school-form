package com.example.service.read;

import com.example.Util.ConstConfig;
import com.example.Util.FailureRestException;
import com.example.Util.RestBean;
import com.example.entity.post.PostHead;
import com.example.entity.post.PostReply;
import com.example.mapper.auth.AccountMapper;
import com.example.mapper.post.PostHeadMapper;
import com.example.mapper.post.PostReplyMapper;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.time.LocalDateTime;
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
        if(postHead.getStatus() == 2)   return RestBean.failure("这是一篇笔记");

        List<PostReply> replyList = postReplyMapper.getAllReply(pid);
        return RestBean.success(Map.of("postHead",postHead,"replyList",replyList));
    }

    public ResponseEntity<RestBean.RestData<Object>> getNote(int nid){
        PostHead postHead = postHeadMapper.selectById(nid);
        if(postHead == null) return RestBean.failure("贴子不存在");
        if(postHead.getStatus() == 1)   return RestBean.failure("这是一篇贴子");

        List<PostReply> replyList = postReplyMapper.getAllReply(nid);
        return RestBean.success(Map.of("postHead",postHead,"replyList",replyList));
    }

    public PostReply getReplyById(int id){
        return postReplyMapper.selectById(id);
    }

    public boolean delReplyById(int id){
        return postReplyMapper.delReply(id);
    }

    public PostHead getPostHeadById(int id){
        return postHeadMapper.selectById(id);
    }

    @Transactional
    public ResponseEntity<RestBean.RestData<Object>> delPostHeadById(int id){
        try {
            boolean delPostHead = postHeadMapper.delPostHead(id);
            boolean delAllReply = postReplyMapper.delAllReplyByHeadId(id);
            if(!delAllReply || !delPostHead) throw new FailureRestException("删除失败");

            return RestBean.success("", "删除成功");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RestBean.failure(500, e.getMessage());
        }
    }
}
