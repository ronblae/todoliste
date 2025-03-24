import axios from 'axios'

import type { Todo } from './types'

const updateTodo = async (updatedTodo: Todo) => {
  console.log('updateTodo')
  console.log(updatedTodo)
  try {
    const response = await axios.put(
      `${import.meta.env.VITE_APP_API_URL}todos/${updatedTodo.todoId}`,
      updatedTodo
    )
    return response.data
  } catch (error) {
    console.error('Error updating todo:', error)
    throw error
  }
}

export default updateTodo
