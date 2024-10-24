import {ElMessage} from "element-plus";
import {post} from "@/net/index.js";
import router from "@/router/index.js";
import {checkMe} from "@/net/auth/checkMe.js";

export function putReply(form){
    if (!form.text) {
        ElMessage.warning('请填写内容')
        return
    }
    checkMe(true,
        (token)=>{
        post('/api/post/addReply', {
                pid:form.pid,
                text:form.text,
                token:token
            },
            (data) => {
                ElMessage.success("回复成功")
                window.location.reload()
            },
            (msg)=>{
                ElMessage.warning(msg)
            }
        )
    })

}