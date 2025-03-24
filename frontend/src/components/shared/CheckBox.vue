<template>
  <div
    class="bg-white block relative cursor-pointer border border-black h-8 w-9 shadow-md"
    @click="flipChecked"
  >
    <StandardImage v-if="isChecked" src="icon-checkmark.svg" />
  </div>
</template>

<script setup lang="ts">
import StandardImage from './StandardImage.vue'
import { ref } from 'vue'

const emit = defineEmits(['update:modelValue'])

const props = defineProps({
  checked: {
    type: Boolean,
    required: false,
    default: false
  }
})

const isChecked = ref<boolean>(props.checked)

const flipChecked = () => {
  isChecked.value = !isChecked.value
  emit('update:modelValue', isChecked.value)
}
</script>
