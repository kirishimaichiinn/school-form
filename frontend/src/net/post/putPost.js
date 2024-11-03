import {ElMessage} from "element-plus";
import {post} from "@/net/index.js";
import router from "@/router/index.js";
import {checkMe} from "@/net/auth/checkMe.js";

export function putPost(form) {
    if (!form.title) {
        ElMessage.warning('请填写标题')
        return
    }
    if (!form.text) {
        ElMessage.warning('请填写内容')
        return;
    }
    checkMe(true,
        (token)=>{
        post('/api/post/addPost', {
                title:form.title,
                text:form.text,
                token:token
            },
            (data) => {
                ElMessage.success("发布成功")
                router.push(`/read?pid=${data.data.pid}`)
            },
            (msg)=>{
                ElMessage.warning(msg)
            }
        )
    })

}