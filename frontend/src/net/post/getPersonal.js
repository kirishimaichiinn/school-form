import {checkMe} from "@/net/auth/checkMe.js";
import {post} from "@/net/index.js";
import {ElMessage} from "element-plus";

export function getPersonal(type, page,tableData) {
    checkMe(true,
        (token) => {
            post('/api/post/getPersonal', {
                    type: type,
                    page: page,
                    token: token
                },
                (data) => {
                    switch (type){
                        case 'all':
                            tableData.postList = data.data.postList
                            tableData.noteList = data.data.noteList
                            tableData.replyList = data.data.replyList
                            break
                        case 'post':
                            tableData.value = data.data.postList
                            break
                        case 'note':
                            tableData.value = data.data.noteList
                            break
                        case 'reply':
                            tableData.value = data.data.replyList
                            break
                        default:break
                    }
                },
                (msg) => {
                    ElMessage.warning(msg)
                }
            )
    })
}