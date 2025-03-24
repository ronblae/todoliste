<template>
  <div class="flex flex-col items-center gap-12 w-full h-full">
    <PageHeader />
    <TodoBuilder mode="add" />
  </div>
</template>

<script setup lang="ts">
import PageHeader from '@/components/shared/PageHeader.vue'
import TodoBuilder from '@/components/todoList/TodoBuilder.vue'
</script>
