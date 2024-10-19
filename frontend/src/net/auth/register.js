import {ElMessage} from "element-plus";
import {post} from "@/net/index.js";
import router from "@/router/index.js";

export function register(form) {
    if (!form.nickname) {
        ElMessage.warning('请填写昵称')
    } else if (!form.account) {
        ElMessage.warning('请填写账号')
    } else if (!form.password) {
        ElMessage.warning('请填写密码')
    } else if (!form.reputPsw) {
        ElMessage.warning('请确认密码')
    } else if (form.password !== form.reputPsw) {
        ElMessage.warning('两次密码输入不同')
    } else {
        post('/api/auth/register', {
                nickname: form.nickname,
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