import {ElMessage, ElMessageBox} from "element-plus";
import router from "@/router/index.js";

export function confirmDel (tips,todo,topush){
    ElMessageBox.confirm(
        tips,
        '请确认',
        {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
        }
    )
        .then(() => {
            todo(()=>{
                ElMessage({
                    type: 'success',
                    message: '删除成功',
                })
                if(topush){
                    router.push(topush)
                }else {
                    window.location.reload()
                }
            },()=>{
                ElMessage({
                    type: 'error',
                    message: '删除失败',
                })
            })
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '取消删除',
            })
        })
}