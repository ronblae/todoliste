<template>
  <main class="flex flex-col justify-center items-start gap-12 self-stretch">
    <div class="flex justify-between items-center self-stretch">
      <div class="flex items-center gap-2 self-stretch">
        <span class="underline text-3xl">To-Dos</span>
        <RouterLink :to="{ name: 'AddTodo' }">
          <button>
            <StandardImage src="button-new-item.svg" class="h-8 mt-1" />
          </button>
        </RouterLink>
      </div>
      <SearchBar v-model="search" />
    </div>
    <div class="flex flex-col justify-center items-start gap-3 self-stretch w-full">
      <TodoListItem
        v-for="todo in todos"
        :key="todo.todoId"
        :todo="todo"
        @openContextMenu="(event: MouseEvent, id: number) => openContextMenu(event, id)"
      />
    </div>
    <div class="flex flex-col justify-center items-center gap-5 self-stretch">
      <span v-if="todos.length === 0" class="text-xl">No To-Dos left! Add a new one?</span>
      <span v-else class="text-xl">Add new To-Do</span>
      <RouterLink :to="{ name: 'AddTodo' }"
        ><StandardImage src="button-new-item.svg" class="h-20 drop-shadow-lg"
      /></RouterLink>
    </div>
  </main>

  <ContextMenu :item-id="todoId" :top="top" :left="left" :show="show" @close="closeContextMenu" />
</template>

<script setup lang="ts">
import { computed, ref } from 'vue'
import TodoListItem from './TodoListItem.vue'
import { useTodosStore } from '@/stores/todos'
import ContextMenu from '../shared/ContextMenu.vue'
import StandardImage from '../shared/StandardImage.vue'
import SearchBar from '../shared/SearchBar.vue'

const todosStore = useTodosStore()
const search = ref<string>('')

const todos = computed(() => {
  return todosStore.getTodoItems(search.value)
})

const todoId = ref<number>(0)
const top = ref<number>(0)
const left = ref<number>(0)
const show = ref<boolean>(false)

const openContextMenu = (event: MouseEvent, id: number) => {
  todoId.value = id
  show.value = true
  left.value = event.clientX - 192
  top.value = event.clientY
}

const closeContextMenu = () => (show.value = false)
</script>
