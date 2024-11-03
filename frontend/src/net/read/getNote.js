import {post} from "@/net/index.js";
import router from "@/router/index.js";

export function getNote(nid,postHead,replyList){
    post('/api/read/getNote',{
            nid:nid
        },
        (data)=>{
            postHead.value = data.data.postHead
            replyList.value = data.data.replyList
        },
        (msg)=>{
            if(msg === '这是一篇贴子'){
                router.push(`/read?pid=${nid}`)
            }else {
                router.push('/')
            }
        }
    )
}