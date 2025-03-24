<template>
  <input
    v-if="active"
    v-model="searchTerm"
    id="searchbar"
    @focusout="switchActive"
    @input="$emit('update:modelValue', ($event.target as HTMLInputElement).value)"
    class="w-1/2 pl-1"
  />
  <StandardImage v-else src="magnifying-glass.svg" @click="switchActive" />
</template>

<script setup lang="ts">
import StandardImage from './StandardImage.vue'
import { nextTick, ref } from 'vue'

const active = ref(false)

const switchActive = () => {
  active.value = !active.value
  nextTick(() => document.getElementById('searchbar')?.focus())
}

const searchTerm = ref('')
</script>
