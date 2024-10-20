
export function formatLast_reply(inputData){
    let parts = inputData.last_reply.split(/[-T:]/); // 分解日期字符串
    let date = new Date(parts[0], parts[1] - 1, parts[2], parts[3], parts[4], parts[5]).getTime();

    let now = new Date().getTime();
    let diffInSeconds = Math.floor((now - date) / 1000);

    if (diffInSeconds < 60) {
        return `${diffInSeconds} 秒前`;
    } else if (diffInSeconds < 3600) {
        let minutes = Math.floor(diffInSeconds / 60);
        return `${minutes} 分钟前`;
    } else if (diffInSeconds < 86400) {
        let hours = Math.floor(diffInSeconds / 3600);
        return `${hours} 小时前`;
    } else {
        let days = Math.floor(diffInSeconds / 86400);
        return `${days} 天前`;
    }
}