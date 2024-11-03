import router from "@/router/index.js";

export function goBack() {
    if (window.history.length > 1) {
        router.go(-1);
    } else {
        router.push({path: '/'});
    }
}