import {ElMessage} from "element-plus";
import {post} from "@/net/index.js";
import router from "@/router/index.js";

export function checkMe(success,failure) {
    let token =localStorage.getItem('userToken')
    post('/api/auth/checkMe', {
            token: token
        },
        (data) => {
            localStorage.setItem('userToken',data.data.token);
            localStorage.setItem('nickname',data.data.nickname);
            if(success) success()
        },
        (msg)=>{
            if(failure) failure(msg)
        }
    )
}