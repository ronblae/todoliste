import axios from 'axios'

import type { Todo } from './types'

const getTodos = async () => {
  const response = await axios.get<Todo[]>(`${import.meta.env.VITE_APP_API_URL}todos`)
  return response.data
}

export default getTodos
