package com.example.controller.post;

import com.example.Util.RestBean;
import com.example.entity.auth.Account;
import com.example.entity.post.PostHead;
import com.example.service.auth.AccountService;
import com.example.service.post.PostService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Resource
    PostService postService;

    @PostMapping("/addPost")
    public ResponseEntity<RestBean.RestData<String>> addPost(@RequestAttribute("account") Account account, @RequestParam("title") String title, @RequestParam("text") String text) {
        PostHead postHead = new PostHead();

        postHead.setTitle(title);
        postHead.setText(text);
        postHead.setAuthor_id(account.getId());
        postHead.setAuthor_name(account.getNickname());
        postHead.setLast_reply(LocalDateTime.now());

        return postService.addPostHead(postHead);
    }

    @PostMapping("/checkMe")
    public ResponseEntity<RestBean.RestData<String>> checkMe(@RequestAttribute("account") Account account) {
        return RestBean.success("OK");
    }
}
