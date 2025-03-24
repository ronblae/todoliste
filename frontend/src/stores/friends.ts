import { defineStore } from 'pinia'
import type { Friend } from '@/api/types'
import { getFriends } from '@/api/getFriends'

export const FETCH_FRIENDS = 'FETCH_FRIENDS'
export const GET_FRIENDS = 'GET_FRIENDS'
export const GET_FRIEND_BY_ID = 'GET_FRIEND_BY_ID'

export interface FriendsState {
  friends: Friend[]
}

export const useFriendsStore = defineStore('friends', {
  state: (): FriendsState => ({
    friends: []
  }),
  actions: {
    async [FETCH_FRIENDS]() {
      const friends = await getFriends()
      this.friends = friends
    }
  },
  getters: {
    [GET_FRIENDS]:
      (state) =>
      (searchTerm: string): Friend[] => {
        if (searchTerm.length != 0) {
          return state.friends.filter((friend) =>
            friend.name.toLowerCase().includes(searchTerm.toLowerCase())
          )
        }
        return state.friends
      },
    [GET_FRIEND_BY_ID]:
      (state) =>
      (id: number): Friend | undefined => {
        const result = state.friends.find((friend) => friend.id === id)
        if (result !== undefined) {
          return result
        }
      }
  }
})
