<template>
  <div v-show="show" class="fixed top-0 left-0 w-screen h-screen" @click="$emit('close')">
    <div
      class="w-48 fixed flex flex-col px-4 py-3 justify-center items-start gap-6 bg-white shadow-md border border-black"
      :style="position"
    >
      <div
        v-for="menuItem in menuItems"
        :key="menuItem.text"
        @click="menuItem.method"
        class="flex justify-between items-center self-stretch cursor-pointer"
      >
        <span class="text-xl">{{ menuItem.text }}</span>
        <StandardImage :src="menuItem.icon" class="h-10 w-10" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import StandardImage from './StandardImage.vue'
import { computed, ref } from 'vue'
import { useRouter } from 'vue-router'
import { useTodosStore } from '@/stores/todos'

const props = defineProps({
  itemId: {
    type: Number,
    required: true
  },
  left: {
    type: Number,
    required: true
  },
  top: {
    type: Number,
    required: true
  },
  show: {
    type: Boolean,
    required: true
  }
})

const position = computed(() => {
  return 'top: ' + props.top + 'px; ' + 'left: ' + props.left + 'px;'
})

const router = useRouter()

const sendTo = (url: string) => {
  router.push({ path: url })
}

const deleteTodo = (todoId: number) => {
  useTodosStore().deleteTodoItem(todoId)
}

const menuItems = ref([
  {
    text: 'Details',
    icon: 'magnifying-glass.svg',
    method: () => sendTo(`/details/${props.itemId}`)
  },
  { text: 'Send to-do', icon: 'send-icon.svg', method: () => sendTo('/send') },
  { text: 'Delete', icon: 'icon-trash-bin.svg', method: () => deleteTodo(props.itemId) }
])
</script>
