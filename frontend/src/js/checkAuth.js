
export function checkAuth(author_id){
    let userId = localStorage.getItem('userId')
    return userId !== '' && userId == author_id;//str和num
}