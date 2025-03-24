<template>
  <main class="flex flex-col justify-center items-start gap-12 self-stretch">
    <div class="flex justify-between items-center self-stretch">
      <div class="flex items-center gap-2 self-stretch">
        <span class="underline text-3xl">Friends</span>
        <RouterLink :to="{ name: 'AddFriend' }">
          <button>
            <StandardImage src="button-new-item.svg" class="h-8" />
          </button>
        </RouterLink>
      </div>
      <SearchBar @updateSearch="(searchTerm: string) => updateList(searchTerm)" />
    </div>
    <div class="flex flex-col justify-center items-start gap-3 self-stretch w-full">
      <FriendListItem
        v-for="friend in friends"
        :key="friend.email"
        :friend="friend"
        @openContextMenu="(event: MouseEvent, id: number) => openContextMenu(event, id)"
      />
    </div>
    <div class="flex flex-col justify-center items-center gap-5 self-stretch">
      <span v-if="friends.length === 0" class="text-xl">You have no friends yet! Add one?</span>
      <span v-else class="text-xl">Add new friend</span>
      <RouterLink :to="{ name: 'AddFriend' }"
        ><StandardImage src="button-new-item.svg" class="h-20 drop-shadow-lg"
      /></RouterLink>
    </div>
  </main>

  <ContextMenu :item-id="friendId" :top="top" :left="left" :show="show" @close="closeContextMenu" />
</template>

<script setup lang="ts">
import { computed, ref } from 'vue'
import FriendListItem from './FriendListItem.vue'
import { useFriendsStore } from '@/stores/friends'
import ContextMenu from '../shared/ContextMenu.vue'
import StandardImage from '../shared/StandardImage.vue'
import SearchBar from '../shared/SearchBar.vue'

const friendsStore = useFriendsStore()
const search = ref('')

const friends = computed(() => {
  return friendsStore.GET_FRIENDS(search.value)
})

const friendId = ref(0)
const top = ref(0)
const left = ref(0)
const show = ref(false)

const openContextMenu = (event: MouseEvent, id: number) => {
  friendId.value = id
  show.value = true
  left.value = event.clientX - 192
  top.value = event.clientY
}

const closeContextMenu = () => (show.value = false)

const updateList = (searchTerm: string) => {
  search.value = searchTerm
}
</script>
