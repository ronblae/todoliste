import { createApp } from 'vue'
import { createPinia } from 'pinia'

import '@/index.css'
import App from './App.vue'
import router from './router'
import VueDatePicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'

createApp(App)
  .use(createPinia())
  .use(router)
  .component('VueDatePicker', VueDatePicker)
  .mount('#app')
