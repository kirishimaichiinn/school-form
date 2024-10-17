import axios from "axios";
import {ElMessage} from "element-plus";

const defaultError = () => ElMessage.error('发生错误')
const defaultFailure = (message) => ElMessage.warning(message)
function post(url, data, success, failure = defaultFailure, error = defaultError) {
    axios.post(url, data, {
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        withCredentials: true
    }).then((response) => {
        const data = response.data
        if (response.status === 200)
            success(data)
        else
            failure(data.msg)
    }).catch((reason)=>{
        if(reason.status === 500)
            ElMessage.error("连接超时")
        else
            error()
    })
}

function get(url,success, failure = defaultFailure, error = defaultError) {
    axios.get(url,{
        withCredentials:true
    }).then((response) => {
        const data = response.data
        if (data.code === 200)
            success(data)
        else
            failure(data.message)
    }).catch(error)
}

export {get,post}