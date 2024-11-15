import {post} from "@/net/index.js";
import router from "@/router/index.js";

export function getPostAndReply(pid,postHead,replyList){
    post('/api/read/getPostAndReply',{
            pid:pid
        },
        (data)=>{
            postHead.value = data.data.postHead
            replyList.value = data.data.replyList
        },
        (msg)=>{
            if(msg === '这是一篇笔记'){
                router.push(`/readNote?nid=${pid}`)
            }else {
                router.push('/')
            }
        }
    )
}