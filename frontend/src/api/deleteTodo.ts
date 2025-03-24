import axios from 'axios'

const deleteTodo = async (deletedTodoId: number) => {
  try {
    const response = await axios.delete(`${import.meta.env.VITE_APP_API_URL}todos/${deletedTodoId}`)
    return response.data
  } catch (error) {
    console.error('Error updating todo:', error)
    throw error
  }
}

export default deleteTodo
