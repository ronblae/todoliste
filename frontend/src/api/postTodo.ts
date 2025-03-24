import axios from 'axios'

import type { Todo } from './types'

const postTodo = async (postedTodo: Todo) => {
  try {
    const response = await axios.post(`${import.meta.env.VITE_APP_API_URL}todos`, postedTodo)
    return response.data
  } catch (error) {
    console.error('Error updating todo:', error)
    throw error
  }
}

export default postTodo
