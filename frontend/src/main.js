import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import index from './router/index.js'
import 'element-plus/dist/index.css'

const pinia = createPinia()
const app = createApp(App)


app.use(pinia)
app.use(index)

app.mount('#app')
