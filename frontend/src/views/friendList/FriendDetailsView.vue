<template>
  <div class="flex flex-col items-center gap-12 w-full h-full">
    <PageHeader />
    <FriendDetails />
  </div>
</template>

<script setup lang="ts">
import FriendDetails from '@/components/friendList/FriendDetails.vue'
import PageHeader from '@/components/shared/PageHeader.vue'
</script>
