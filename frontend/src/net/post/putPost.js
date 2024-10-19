import {ElMessage} from "element-plus";
import {post} from "@/net/index.js";
import router from "@/router/index.js";
import {checkMe} from "@/net/auth/checkMe.js";

export function putPost(form) {
    if (!form.title) {
        ElMessage.warning('请填写标题')
    } else if (!form.text) {
        ElMessage.warning('请填写内容')
    } else {
        post('/api/post/addPost', {
                title:form.title,
                text:form.text,
                token:localStorage.getItem('userToken')
            },
            (data) => {
                ElMessage.success("发布成功")
                router.push('/')
                setTimeout(()=>{location.reload()},100)
            },
            (msg)=>{
                if(msg === '缺少参数') {
                    ElMessage.warning("请先登录")
                    router.push('/login')
                    setTimeout(()=>{location.reload()},100)
                    return
                }
                ElMessage.warning(msg)
            }
        )
    }
}