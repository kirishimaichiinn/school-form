import {ref, reactive} from 'vue'
import {defineStore} from 'pinia'
export const useStrore = defineStore('store',()=>{
    const auth = reactive({
        token: null
    })

})