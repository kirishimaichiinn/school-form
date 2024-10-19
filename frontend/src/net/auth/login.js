import {ElMessage} from "element-plus";
import {post} from "@/net/index.js";
import router from "@/router/index.js";

export function login(form) {
    if (!form.account) {
        ElMessage.warning('请填写账号')
    } else if (!form.password) {
        ElMessage.warning('请填写密码')
    } else {
        post('/api/auth/login', {
                account: form.account,
                password: form.password
            },
            (data) => {
                ElMessage.success(data.msg)
                localStorage.setItem('userToken',data.data.token);
                localStorage.setItem('nickname',data.data.nickname);
                router.push('/')
                setTimeout(()=>{location.reload()},100)
            },
            (msg)=>{
                ElMessage.warning(msg)
            }
        )
    }

}