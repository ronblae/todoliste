<template>
  <label class="flex w-full flex-col items-start gap-3 font-bold text-lg">
    {{ text }}
    <input
      :type="type"
      class="w-full h-full border bg-white shadow-custom p-1"
      :value="modelValue"
      @input="$emit('update:modelValue', ($event.target as HTMLInputElement).value)"
    />
  </label>
</template>

<script setup lang="ts">
defineProps({
  text: {
    type: String,
    required: true
  },
  type: {
    type: String,
    required: false,
    default: 'text'
  },
  modelValue: {
    type: String,
    required: false,
    default: ''
  }
})
</script>
