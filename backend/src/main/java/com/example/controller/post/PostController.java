package com.example.controller.post;

import com.example.Util.FailureRestException;
import com.example.Util.RestBean;
import com.example.entity.auth.Account;
import com.example.entity.post.PostHead;
import com.example.entity.post.PostReply;
import com.example.service.post.PostService;
import com.example.service.read.ReadService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Resource
    PostService postService;
    @Resource
    ReadService readService;

    @PostMapping("/addPost")
    public ResponseEntity<RestBean.RestData<Object>> addPost(@RequestAttribute("account") Account account, @RequestParam("title") String title, @RequestParam("text") String text) {
        PostHead postHead = new PostHead();

        postHead.setTitle(title);
        postHead.setText(text);
        postHead.setAuthor_id(account.getId());
        postHead.setAuthor_name(account.getNickname());
        postHead.setLast_reply(LocalDateTime.now());

        return postService.addPostHead(postHead);
    }

    @PostMapping("/addReply")
    public ResponseEntity<RestBean.RestData<Object>> addReply(@RequestAttribute("account") Account account, @RequestParam("pid") int pid, @RequestParam("text") String text){
        PostReply reply = new PostReply();

        reply.setHead_id(pid);
        reply.setSpeaker_id(account.getId());
        reply.setSpeaker_name(account.getNickname());
        reply.setText(text);

        return postService.addReply(reply);
    }

    @PostMapping("/getPersonal")
    public ResponseEntity<RestBean.RestData<Object>> getPersonal(@RequestAttribute("account") Account account, @RequestParam("type") String type,@RequestParam("page")int page) throws FailureRestException {
        List<PostHead> personalPost;
        List<PostHead> personalNote;
        List<PostReply> personalReply;
        switch (type){
            case "all":
                personalPost = postService.getPersonalPost(account, 1);
                personalNote = postService.getPersonalNote(account, 1);
                personalReply = postService.getPersonalReply(account, 1);
                return RestBean.success(Map.of("postList",personalPost,"noteList",personalNote,"replyList",personalReply));
            case "post":
                personalPost = postService.getPersonalPost(account, page);
                return RestBean.success(Map.of("postList",personalPost));
            case "note":
                personalNote = postService.getPersonalNote(account, page);
                return RestBean.success(Map.of("noteList",personalNote));
            case "reply":
                personalReply = postService.getPersonalReply(account, page);
                return RestBean.success(Map.of("replyList",personalReply));
            default:return RestBean.failure("参数错误");
        }

    }

    @PostMapping("/delReply")
    public ResponseEntity<RestBean.RestData<Object>> delReply(@RequestAttribute("account") Account account, @RequestParam("id") int id){
        PostReply reply = readService.getReplyById(id);
        if(reply == null)   return RestBean.failure("该回复不存在");
        if(reply.getStatus() != 1 || !reply.getSpeaker_id().equals(account.getId())) return RestBean.failure("删除失败");

        boolean del = readService.delReplyById(id);
        if(!del) return RestBean.failure("删除失败");

        return RestBean.success("");
    }

    @PostMapping("/delPost")
    public ResponseEntity<RestBean.RestData<Object>> delPost(@RequestAttribute("account") Account account, @RequestParam("id") int id){
        PostHead postHead = readService.getPostHeadById(id);
        if(postHead == null)   return RestBean.failure("该主题不存在");
        if(postHead.getStatus() != 1 || !postHead.getAuthor_id().equals(account.getId())) return RestBean.failure("删除失败");

        return readService.delPostHeadById(id);
    }

    @PostMapping("/updatePost")
    public ResponseEntity<RestBean.RestData<Object>> updatePost(@RequestAttribute("account") Account account, @RequestParam("id") int id,@RequestParam("title") String title, @RequestParam("text") String text) {
        PostHead postHead = readService.getPostHeadById(id);
        if(postHead == null)   return RestBean.failure("该主题不存在");
        if(postHead.getStatus() != 1 || !postHead.getAuthor_id().equals(account.getId())) return RestBean.failure("更新失败");

        postHead.setTitle(title);
        postHead.setText(text);
        postHead.setLast_reply(LocalDateTime.now());

        return postService.updatePost(postHead);
    }

    @PostMapping("/updateReply")
    public ResponseEntity<RestBean.RestData<Object>> updateReply(@RequestAttribute("account") Account account, @RequestParam("id") int id, @RequestParam("text") String text){
        PostReply reply = readService.getReplyById(id);
        if(reply == null)   return RestBean.failure("该回复不存在");
        if(reply.getStatus() != 1 || !reply.getSpeaker_id().equals(account.getId())) return RestBean.failure("更新失败");

        reply.setText(text);

        return postService.updateReply(reply);
    }

    @PostMapping("/addNote")
    public ResponseEntity<RestBean.RestData<Object>> addNote(@RequestAttribute("account") Account account, @RequestParam("title") String title, @RequestParam("text")List<String> list){
        PostHead postHead = new PostHead();

        postHead.setTitle(title);
        postHead.setText(list.get(0));
        postHead.setAuthor_id(account.getId());
        postHead.setAuthor_name(account.getNickname());
        postHead.setLast_reply(LocalDateTime.now());
        postHead.setStatus(2);

        return postService.addNote(postHead,list);
    }

    @PostMapping("/updateNote")
    public ResponseEntity<RestBean.RestData<Object>> updateNote(@RequestAttribute("account") Account account, @RequestParam("nid") int nid, @RequestParam("title") String title, @RequestParam("text")List<String> list){
        PostHead postHead = new PostHead();

        postHead.setId(nid);
        postHead.setTitle(title);
        postHead.setText(list.get(0));
        postHead.setAuthor_id(account.getId());
        postHead.setAuthor_name(account.getNickname());
        postHead.setLast_reply(LocalDateTime.now());
        postHead.setStatus(2);

        return postService.updateNote(postHead,list);
    }

    @PostMapping("/delNote")
    public ResponseEntity<RestBean.RestData<Object>> delNote(@RequestAttribute("account") Account account, @RequestParam("id") int id){
        PostHead postHead = readService.getPostHeadById(id);
        if(postHead == null)   return RestBean.failure("该主题不存在");
        if(postHead.getStatus() != 2 || !postHead.getAuthor_id().equals(account.getId())) return RestBean.failure("删除失败");

        return readService.delPostHeadById(id);
    }




    /*@PostMapping("/checkMe")
    public ResponseEntity<RestBean.RestData<String>> checkMe(@RequestAttribute("account") Account account) {
        return RestBean.success("OK");
    }*/
}
