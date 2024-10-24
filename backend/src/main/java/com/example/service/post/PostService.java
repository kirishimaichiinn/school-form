package com.example.service.post;

import com.example.Util.ConstConfig;
import com.example.Util.FailureRestException;
import com.example.Util.RestBean;
import com.example.entity.auth.Account;
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

    public ResponseEntity<RestBean.RestData<Object>> addPostHead(PostHead postHead) {
        int insert = postHeadMapper.insert(postHead);
        if (insert <= 0) return RestBean.failure(500, "数据添加失败");

        return RestBean.success("", "发布成功");
    }

    @Transactional
    public ResponseEntity<RestBean.RestData<Object>> addReply(PostReply reply) {
        try {
            int pid = reply.getHead_id();
            PostHead postHead = postHeadMapper.selectById(pid);
            if (postHead == null) {
                throw new FailureRestException("贴子不存在");
            }

            reply.setFloor(postHead.getMax_floor() + 1);
            reply.setHead_title(postHead.getTitle());
            int insert = postReplyMapper.insert(reply);
            if (insert <= 0) {
                throw new FailureRestException("数据添加失败");
            }

            boolean updatedFloor = postHeadMapper.updateMaxFloor(pid, postHead.getMax_floor() + 1);
            if (!updatedFloor) {
                throw new FailureRestException("楼层更新失败");
            }

            boolean updatedReply = postHeadMapper.updateLastReply(pid, LocalDateTime.now());
            if (!updatedReply) {
                throw new FailureRestException("回复时间更新失败");
            }

            return RestBean.success("", "发布成功");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RestBean.failure(500, e.getMessage());
        }
    }

    public List<PostHead> getPersonalPost(Account account, int page) throws FailureRestException {
        if (page <= 0) throw new FailureRestException("参数错误");

        int passRows = (page - 1) * constConfig.getMainViewPageNum();
        int getNum = constConfig.getMainViewPageNum();

        return postHeadMapper.getPersonalPost(account.getId(), passRows, getNum);
    }

    public List<PostHead> getPersonalNote(Account account, int page) throws FailureRestException {
        if (page <= 0) throw new FailureRestException("参数错误");

        int passRows = (page - 1) * constConfig.getMainViewPageNum();
        int getNum = constConfig.getMainViewPageNum();

        return postHeadMapper.getPersonalNote(account.getId(), passRows, getNum);
    }

    public List<PostReply> getPersonalReply(Account account, int page) throws FailureRestException {
        if (page <= 0) throw new FailureRestException("参数错误");

        int passRows = (page - 1) * constConfig.getMainViewPageNum();
        int getNum = constConfig.getMainViewPageNum();

        return postReplyMapper.getPersonalReply(account.getId(), passRows, getNum);

    }
}
