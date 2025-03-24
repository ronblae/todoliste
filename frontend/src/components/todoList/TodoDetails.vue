<template>
  <main v-if="todo != null" class="flex flex-col justify-center items-start gap-12 self-stretch">
    <div class="flex justify-between items-center self-stretch">
      <span class="text-2xl underline">Details</span>
      <RouterLink :to="editUrl">
        <div class="flex justify-center items-center gap-1">
          <span>Edit</span>
          <StandardImage src="icon-edit-pen.svg" /></div
      ></RouterLink>
    </div>
    <div class="flex flex-col justify-center items-start gap-8 self-stretch">
      <div class="flex flex-col items-start gap-3 self-stretch pb-0 border-b border-black">
        <span class="font-bold text-lg">Title</span>
        <span> {{ todo.title }}</span>
      </div>
      <div
        v-if="todo.fromFriend"
        class="flex flex-col items-start gap-3 self-stretch pb-0 border-b border-black"
      >
        <span class="font-bold text-lg">From</span>
        <span> {{ props.todo.creatorName }}</span>
      </div>
      <div class="flex flex-col items-start gap-3 self-stretch pb-0 border-b border-black">
        <span class="font-bold text-lg">Description</span>
        <span> {{ todo.description }}</span>
      </div>
      <div class="flex flex-col items-start gap-3 self-stretch pb-0 border-b border-black">
        <span class="font-bold text-lg">To do until</span>
        <span> {{ todo.date }}</span>
      </div>
      <div class="flex items-center justify-between self-stretch pb-0 border-b border-black">
        <span class="font-bold text-lg text-custom-red">Important</span>
        <StandardImage v-if="todo.important" src="icon-checkmark.svg" class="h-8 mb-1" />
        <StandardImage v-else src="icon-cancel.svg" class="h-6 mb-1" />
      </div>
    </div>
  </main>
</template>

<script setup lang="ts">
import { computed, type PropType } from 'vue'
import StandardImage from '../shared/StandardImage.vue'
import type { Todo } from '@/api/types'

const props = defineProps({
  todo: {
    type: Object as PropType<Todo>,
    required: true
  }
})

const editUrl = computed(() => {
  return '/edit/' + props.todo.todoId
})
</script>
