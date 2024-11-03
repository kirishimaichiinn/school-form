import {ElMessage} from "element-plus";
import {post} from "@/net/index.js";
import router from "@/router/index.js";
import {checkMe} from "@/net/auth/checkMe.js";

export function putNote(form) {
    if (!form.title) {
        ElMessage.warning('请填写标题')
        return
    }
    checkMe(true,
        (token)=>{
            post('/api/post/addNote', {
                    title:form.title,
                    text:form.text,
                    token:token
                },
                (data) => {
                    ElMessage.success("发布成功")
                    router.push(`/readNote?nid=${data.data.nid}`)
                },
                (msg)=>{
                    ElMessage.warning(msg)
                }
            )
        })

}