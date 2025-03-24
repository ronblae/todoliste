import axios from 'axios'

import type { Friend } from './types'

const getFriends = async () => {
  const response = await axios.get<Friend[]>(`${import.meta.env.VITE_APP_API_URL}friendships`)

  return response.data
}

export { getFriends }
