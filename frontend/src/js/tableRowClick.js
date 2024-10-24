import router from "@/router/index.js";

export function tableRowClick(row){
    router.push(`/read?pid=${row.id}`)
}