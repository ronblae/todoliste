<template>
  <main v-if="friend != null" class="flex flex-col justify-center items-start gap-12 self-stretch">
    <div class="flex justify-between items-center self-stretch">
      <span class="text-2xl underline">{{ friend.name }}</span>
    </div>
    <div class="flex flex-col justify-center items-start gap-8 self-stretch">
      <div class="flex flex-col items-start gap-3 self-stretch pb-0 border-b border-black">
        <span class="font-bold text-lg">Name</span>
        <span> {{ friend.name }}</span>
      </div>
      <div class="flex flex-col items-start gap-3 self-stretch pb-0 border-b border-black">
        <span class="font-bold text-lg">Description</span>
        <span> {{ friend.email }}</span>
      </div>
    </div>
  </main>
</template>

<script setup lang="ts">
import { useFriendsStore } from '@/stores/friends'
import { useRoute } from 'vue-router'

const friendsStore = useFriendsStore()
const route = useRoute()

const friend = friendsStore.GET_FRIEND_BY_ID(
  Number(route.path.substring(route.path.lastIndexOf('/') + 1))
)
</script>
