<template>
  <div class="flex flex-col items-center gap-12 w-full h-full">
    <PageHeader />
    <TodoSend />
  </div>
</template>

<script setup lang="ts">
import TodoSend from '@/components/todoList/TodoSend.vue'
import PageHeader from '@/components/shared/PageHeader.vue'
</script>
