import {ElMessage} from "element-plus";
import {post} from "@/net/index.js";
import router from "@/router/index.js";

export function checkMe(mustLogin,success,failure) {
    let token =localStorage.getItem('userToken')
    if(token === null || token === '') {
        if(mustLogin){
            ElMessage.warning("请先登录")
            router.push('/login')
            setTimeout(()=>{location.reload()},50)
        }
        return
    }
    post('/api/auth/checkMe', {
            token: token
        },
        (data) => {
            localStorage.setItem('userToken',data.data.token);
            localStorage.setItem('nickname',data.data.nickname);
            localStorage.setItem('userId',data.data.id);
            if(success) success(data.data.token)
        },
        (msg)=>{
            if(failure) failure(msg)
            else {
                localStorage.setItem('userToken','');
                ElMessage.warning("请先登录")
                router.push('/login')
                setTimeout(()=>{location.reload()},50)
            }
        }
    )
}