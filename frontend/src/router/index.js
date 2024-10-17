import { createRouter, createWebHistory } from 'vue-router'


const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path:'/',
            name:'main',
            component:()=>import('@/views/MainView.vue'),
            //redirect:'login',
            children:[

            ]
        },
        {
            path:'/test',
            name:'test',
            component:()=>import('@/views/TestView.vue')
        },
        {
            path: '/:pathMatch(.*)*',
            redirect: '/'
        }

    ]
})

export default router