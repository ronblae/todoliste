<template>
  <div class="flex w-full flex-col items-start gap-3">
    <span class="font-bold text-lg w-full">Send to...</span>
    <select class="bg-white w-full h-8 text-xl pl-1 border shadow-custom">
      <option v-for="friend in friends" :key="friend.email" :value="friend.name" class="text-xl">
        {{ friend.name }}
      </option>
    </select>
  </div>
  <label class="flex w-full flex-col items-start gap-3 font-bold text-lg"
    >Message
    <textarea class="w-full h-full border bg-white shadow-custom p-1" cols="40" rows="5" />
  </label>
  <div class="flex items-center justify-between w-full gap-5 self-stretch">
    <StandardButton text="Send" icon="icon-checkmark.svg" />
    <StandardButton text="Cancel" icon="icon-cancel.svg" />
  </div>
</template>

<script setup lang="ts">
import { useFriendsStore } from '@/stores/friends'
import StandardButton from '../shared/StandardButton.vue'

const friendsStore = useFriendsStore()

const friends = friendsStore.GET_FRIENDS('')
</script>
