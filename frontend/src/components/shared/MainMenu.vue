<template>
  <div class="flex justify-between items-center w-full">
    <StandardImage
      src="hamburger-menu-icon.svg"
      alt="Menu"
      @click="switchSidebarVisibility"
      class="cursor-pointer"
    />
    <span class="text-lg">{{ date }}</span>
  </div>

  <nav
    v-if="sidebarActive"
    class="absolute top-0 left-0 z-10 h-screen w-screen backdrop-blur-sm"
    @click="switchSidebarVisibility"
  >
    <div
      class="flex flex-col items-end gap-12 flex-shrink-0 border-r border-black h-full w-4/6 px-4 py-12"
      style="background: linear-gradient(180deg, #fff 0%, #d4d4d4 100%)"
    >
      <StandardImage
        src="hamburger-menu-icon.svg"
        alt="Menu"
        @click="switchSidebarVisibility"
        class="cursor-pointer"
      />
      <div class="flex flex-col justify-center items-start gap-8 self-stretch">
        <RouterLink
          v-for="menuItem in menuItems"
          :key="menuItem.text"
          :to="menuItem.url"
          class="flex justify-between items-center self-stretch"
        >
          <span class="text-xl">{{ menuItem.text }}</span>
          <StandardImage :src="menuItem.icon" class="h-10 w-10" />
        </RouterLink>
      </div>
    </div>
  </nav>
</template>

<script setup lang="ts">
import { computed, ref } from 'vue'
import StandardImage from './StandardImage.vue'

const menuItems = ref([
  { text: 'To-Do-List', url: '/', icon: 'home-icon.svg' },
  { text: 'Add To-Do', url: '/add-to-do', icon: 'plus-icon.svg' },
  { text: 'Friends', url: '/friends', icon: 'friends-icon.svg' },
  { text: 'Logout', url: '/logout', icon: 'logout.svg' }
])

const date = computed(() => {
  const currentDate = new Date()
  return currentDate.toLocaleDateString()
})

const sidebarActive = ref(false)

const switchSidebarVisibility = () => {
  sidebarActive.value = !sidebarActive.value
}
</script>
