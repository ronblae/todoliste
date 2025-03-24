<template>
  <div class="flex flex-col items-center gap-12 w-full h-full">
    <PageHeader />
    <TodoBuilder :todo="todoToEdit" mode="edit" />
  </div>
</template>

<script setup lang="ts">
import type { Todo } from '@/api/types'
import PageHeader from '@/components/shared/PageHeader.vue'
import TodoBuilder from '@/components/todoList/TodoBuilder.vue'
import { useTodosStore } from '@/stores/todos'
import { onMounted, ref } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const todosStore = useTodosStore()

const todoToEdit = ref<Todo | undefined>(undefined)

onMounted(async () => {
  await todosStore.fetchTodos()
  const id = Number(route.path.substring(route.path.lastIndexOf('/') + 1))
  todoToEdit.value = todosStore.getTodoById(id)
})
</script>
