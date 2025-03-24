<template>
  <div class="flex flex-col items-center gap-12">
    <MainMenu />
    <div v-if="loading" class="spinner" />
    <TodoList v-else />
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import TodoList from '@/components/todoList/TodoList.vue'
import MainMenu from '@/components/shared/MainMenu.vue'
import { useTodosStore } from '@/stores/todos'

const loading = ref(true)
const todosStore = useTodosStore()

onMounted(async () => {
  try {
    await todosStore.fetchTodos()
  } catch (error) {
    console.error('Fehler beim Laden der Todos:', error)
  } finally {
    loading.value = false
  }
})
</script>

<style scoped>
.spinner {
  border: 4px solid rgba(0, 0, 0, 0.1);
  border-left-color: #7986cb;
  border-radius: 50%;
  width: 50px;
  height: 50px;
  animation: spin 1s linear infinite;
}

@keyframes spin {
  to {
    transform: rotate(360deg);
  }
}
</style>
