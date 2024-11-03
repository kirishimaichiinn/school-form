import {checkMe} from "@/net/auth/checkMe.js";
import {post} from "@/net/index.js";

export function delReply(reply, success,failure) {
    checkMe(true,
        (token) => {
        post('/api/post/delReply',{
            id:reply.id,
            token:token
        },
            (data)=>{
                success(data)
            },
            (msg)=>{
                failure(msg)
            })
        }
    )
}