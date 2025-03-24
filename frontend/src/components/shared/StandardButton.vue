<template>
  <button
    @click.prevent="pushTo"
    class="flex px-4 py-3 justify-center items-center gap-3 w-full border-2 border-black bg-white shadow-custom"
  >
    <span class="text-2xl">{{ text }}</span>
    <StandardImage
      v-if="typeof icon === 'string'"
      :src="icon"
      alt="button icon"
      class="w-10 h-8"
      :type="type"
    />
  </button>
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router'
import StandardImage from './StandardImage.vue'

const router = useRouter()

const props = defineProps({
  text: {
    type: String,
    required: true
  },
  type: {
    type: String,
    required: false,
    default: 'button'
  },
  icon: {
    type: String,
    required: false
  },
  to: {
    type: String,
    required: false,
    default: 'previousPage'
  }
})

const pushTo = () => {
  if (props.to === 'previousPage') {
    router.back()
  } else {
    router.push({ name: props.to })
  }
}
</script>
