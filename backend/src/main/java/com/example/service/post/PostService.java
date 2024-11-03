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

    public ResponseEntity<RestBean.RestData<Object>> addPostHead(PostHead postHead) {
        int insert = postHeadMapper.insert(postHead);
        if (insert <= 0) return RestBean.failure(500, "数据添加失败");

        return RestBean.success(Map.of("pid",postHead.getId()), "发布成功");
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

    public ResponseEntity<RestBean.RestData<Object>> updatePost(PostHead postHead) {
        int insert = postHeadMapper.updateById(postHead);
        if (insert <= 0) return RestBean.failure(500, "数据更新失败");

        return RestBean.success("", "更新成功");
    }

    @Transactional
    public ResponseEntity<RestBean.RestData<Object>> updateReply(PostReply reply) {
        try {
            int insert = postReplyMapper.updateById(reply);
            if (insert <= 0) return RestBean.failure(500, "数据更新失败");

            boolean updateLastReply = postHeadMapper.updateLastReply(reply.getHead_id(), LocalDateTime.now());
            if (!updateLastReply) {
                throw new FailureRestException("回复时间更新失败");
            }

            return RestBean.success("", "更新成功");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RestBean.failure(500, e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<RestBean.RestData<Object>> addNote(PostHead postHead,List<String> list) {
        try {
            int insert = postHeadMapper.insert(postHead);
            if(insert <= 0)  throw new FailureRestException("数据插入失败");

            for(int i = 1;i < list.size();i++){
                PostReply reply = new PostReply();
                reply.setHead_id(postHead.getId());
                reply.setSpeaker_id(postHead.getAuthor_id());
                reply.setSpeaker_name(postHead.getAuthor_name());
                reply.setText(list.get(i));
                reply.setFloor(i);
                reply.setHead_title(postHead.getTitle());

                int inserted = postReplyMapper.insert(reply);
                if(inserted <= 0)  throw new FailureRestException("数据插入失败");
            }

            return RestBean.success(Map.of("nid",postHead.getId()), "发布成功");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RestBean.failure(500, e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<RestBean.RestData<Object>> updateNote(PostHead postHead,List<String> list) {
        try {
            int update = postHeadMapper.updateById(postHead);
            if(update <= 0)  throw new FailureRestException("数据更新失败");

            for(int i = 1;i < list.size();i++){
                boolean updateReply = postReplyMapper.updateReplyByHeadAndFloor(list.get(i), postHead.getId(), i);
                if(!updateReply)  throw new FailureRestException("数据更新失败");
            }

            return RestBean.success(Map.of("nid",postHead.getId()), "更新成功");
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return RestBean.failure(500, e.getMessage());
        }
    }
}
