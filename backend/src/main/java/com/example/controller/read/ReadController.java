package com.example.controller.read;

import com.example.Util.RestBean;
import com.example.service.read.ReadService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/read")
public class ReadController {
    @Resource
    ReadService readService;

    @PostMapping("/getPostHeadList")
    public ResponseEntity<RestBean.RestData<Object>> getPostHeadList(@RequestParam("page") int page){
        return readService.getPostHeadList(page);
    }

    @PostMapping("/getPostAndReply")
    public ResponseEntity<RestBean.RestData<Object>> getPostAndReply(@RequestParam("pid") int pid){
        return readService.getPostAndReply(pid);
    }

    @PostMapping("/getNote")
    public ResponseEntity<RestBean.RestData<Object>> getNote(@RequestParam("nid") int nid){
        return readService.getNote(nid);
    }
}
