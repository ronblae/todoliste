export interface Todo {
  todoId?: number
  title: string
  important: boolean
  checked: boolean
  date: Date
  description: string
  fromFriend: boolean
  creatorName?: string
}

export interface Friend {
  userId: number
  name: string
  email: string
}
