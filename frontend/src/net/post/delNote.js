import {checkMe} from "@/net/auth/checkMe.js";
import {post} from "@/net/index.js";

export function delNote(id,success,failure){
    checkMe(true,
        (token) => {
            post('/api/post/delNote',{
                    id:id,
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