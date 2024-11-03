import {ElMessage} from "element-plus";
import {checkMe} from "@/net/auth/checkMe.js";
import {post} from "@/net/index.js";
import router from "@/router/index.js";

export function updateNote(nid,form){
    if (!form.title) {
        ElMessage.warning('请填写标题')
        return
    }
    checkMe(true,
        (token)=>{
            post('/api/post/updateNote', {
                    nid:nid,
                    title:form.title,
                    text:form.text,
                    token:token
                },
                (data) => {
                    ElMessage.success("保存成功")
                    router.push(`/readNote?nid=${data.data.nid}`)
                },
                (msg)=>{
                    ElMessage.warning(msg)
                }
            )
        })
}