<template>
  <div class="flex w-full px-3 py-2 flex-col justify-center items-start border-b">
    <div class="flex justify-between items-center self-stretch gap-3">
      <StandardImage src="friend-icon.svg" alt="Friend Icon" />
      <span class="w-full text-xl">{{ friend.name }}</span>
      <div
        class="flex justify-center w-4 -ml-4 cursor-pointer"
        @click="$emit('openContextMenu', $event, props.friend.userId)"
      >
        <StandardImage src="button-options.svg" class="h-full" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import StandardImage from '../shared/StandardImage.vue'
import { type PropType } from 'vue'

import type { Friend } from '@/api/types'

const props = defineProps({
  friend: {
    type: Object as PropType<Friend>,
    required: true
  }
})
</script>
