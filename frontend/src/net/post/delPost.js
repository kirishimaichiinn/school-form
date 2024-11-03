import {checkMe} from "@/net/auth/checkMe.js";
import {post} from "@/net/index.js";

export function delPost(postHead, success,failure) {
    checkMe(true,
        (token) => {
            post('/api/post/delPost',{
                    id:postHead.id,
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