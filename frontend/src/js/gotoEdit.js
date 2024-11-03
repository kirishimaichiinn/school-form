import router from "@/router/index.js";

export function gotoEdit(type,data){
    if(type === 'postHead'){
        localStorage.setItem('editId','postHead_'+data.id)
        router.push({
            path:'/edit',
            query:{
                text:data.text,
                title:data.title
            }
        })
    }
    else if (type === 'reply'){
        localStorage.setItem('editId','reply_'+data.id)
        router.push({
            path:'/edit',
            query:{
                text:data.text
            }
        })
    }
}