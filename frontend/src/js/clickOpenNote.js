import router from "@/router/index.js";

export function clickOpenNote(id){
    const route = router.resolve({ path: `readNote`, query: { nid: id } });
    window.open(route.href, '_blank');
}