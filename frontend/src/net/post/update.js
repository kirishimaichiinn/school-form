import {checkMe} from "@/net/auth/checkMe.js";
import {post} from "@/net/index.js";
import {goBack} from "@/js/goBack.js";

export function update(form){
    let edit = localStorage.getItem('editId')
    if(edit === null || edit === '')    return

    let type = edit.split('_')[0]
    let id = edit.split('_')[1]

    checkMe(true,
        (token)=>{
        let path = '/api/post/'
        if(type === 'postHead') path += 'updatePost'
        else if(type === 'reply') path += 'updateReply'
        else return

        post(path,{
            id:id,
            text:form.text,
            title:form.title,
            token:token
        },()=>{
            localStorage.removeItem('editId')
            goBack()
        })
    })
}