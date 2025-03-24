import { defineStore } from 'pinia'
import getTodos from '@/api/getTodos'
import updateTodo from '@/api/updateTodo'
import type { Todo } from '@/api/types'
import deleteTodo from '@/api/deleteTodo'
import postTodo from '@/api/postTodo'
import { ref } from 'vue'

export const useTodosStore = defineStore('todos', () => {
  const todos = ref<Todo[]>([])

  const fetchTodos = async () => {
    const fetchedTodos = await getTodos()
    todos.value = fetchedTodos
    sort()
  }

  const postTodoItem = async (postedTodo: Todo) => {
    const newTodo = await postTodo(postedTodo)
    todos.value.push(newTodo)
    sort()
  }

  const updateTodoItem = async (updatedTodo: Todo) => {
    console.log('updateTodoItem')
    console.log(updatedTodo)
    const index = todos.value.findIndex((todo) => todo.todoId === updatedTodo.todoId)
    if (index !== -1) {
      todos.value[index] = await updateTodo(updatedTodo)
    }
  }

  const deleteTodoItem = async (deletedTodoId: number) => {
    const index = todos.value.findIndex((todo) => todo.todoId === deletedTodoId)
    if (index !== -1) {
      todos.value.splice(index, 1)
      await deleteTodo(deletedTodoId)
    }
  }

  const getTodoItems = (searchTerm: string) => {
    return todos.value.filter((todo) => todo.title.toLowerCase().includes(searchTerm.toLowerCase()))
  }

  const getTodoById = (id: number): Todo | undefined => {
    console.log('id')
    return todos.value.find((todo) => todo.todoId === id)
  }

  const sort = () => {
    todos.value.sort((a, b) => {
      if (a.checked && !b.checked) return 1
      if (!a.checked && b.checked) return -1

      if (!a.important && b.important) return 1
      if (a.important && !b.important) return -1

      if (a.date < b.date) return 1
      if (a.date > b.date) return -1

      return a.title.localeCompare(b.title)
    })
  }

  return {
    fetchTodos,
    getTodoItems,
    postTodoItem,
    updateTodoItem,
    deleteTodoItem,
    getTodoById
  }
})
