import router from "@/router/index.js";

export function tableRowClick(row){
    if(row.head_id) router.push(`/read?pid=${row.head_id}`)
    else router.push(`/read?pid=${row.id}`)
}