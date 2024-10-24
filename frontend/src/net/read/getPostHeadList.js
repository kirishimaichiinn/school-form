import {post} from "@/net/index.js";
import {ElMessage} from "element-plus";
import router from "@/router/index.js";

export function getPostHeadList(page,tableData){
    post('/api/read/getPostHeadList', {
            page:page.value
        },
        (data) => {
            tableData.value = data.data.postHeadList
        },
        (msg)=>{
            ElMessage.warning(msg)
        }
    )
}