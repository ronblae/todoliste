<template>
  <img :src="imgPath" :alt="alt" />
</template>

<script setup lang="ts">
import { computed } from 'vue'

const props = defineProps({
  src: {
    type: String,
    required: true
  },
  alt: {
    type: String,
    required: false,
    default: ''
  }
})

const imgPath = computed(() => new URL('/assets/' + props.src, import.meta.url).href)
</script>
