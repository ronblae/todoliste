<template>
  <div class="flex flex-col items-center gap-12">
    <MainMenu />
    <FriendList />
  </div>
</template>

<script setup lang="ts">
import FriendList from '@/components/friendList/FriendList.vue'
import MainMenu from '@/components/shared/MainMenu.vue'
</script>
