<template>
  <div class="flex w-full justify-between items-center">
    <StandardImage src="icon-cancel.svg" class="h-6 cursor-pointer" @click="$router.back" />
    <span class="text-lg">{{ date }}</span>
  </div>
</template>

<script setup lang="ts">
import { computed } from 'vue'
import StandardImage from './StandardImage.vue'
import { format } from 'date-fns'

const date = computed(() => {
  const currentDate = new Date()
  return format(currentDate, 'dd.MM.yyyy')
})
</script>
