<template>
  <div class="flex flex-col items-center gap-12 w-full h-full">
    <PageHeader />
    <TodoDetails v-if="todo != undefined" :todo="todo" />
  </div>
</template>

<script setup lang="ts">
import TodoDetails from '@/components/todoList/TodoDetails.vue'
import PageHeader from '@/components/shared/PageHeader.vue'
import { useRoute } from 'vue-router'
import { useTodosStore } from '@/stores/todos'
import type { Todo } from '@/api/types'
import { onMounted, ref } from 'vue'

const todosStore = useTodosStore()
const route = useRoute()

const todo = ref<Todo | undefined>(undefined)

onMounted(async () => {
  await todosStore.fetchTodos()
  const id = Number(route.path.substring(route.path.lastIndexOf('/') + 1))
  todo.value = todosStore.getTodoById(id)
})
</script>
