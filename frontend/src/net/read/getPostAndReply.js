import {post} from "@/net/index.js";

export function getPostAndReply(pid,postHead,replyList){
    post('/api/read/getPostAndReply',{
            pid:pid
        },
        (data)=>{
            postHead.value = data.data.postHead
            replyList.value = data.data.replyList
        }
    )
}