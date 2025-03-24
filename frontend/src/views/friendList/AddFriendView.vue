<template>
  <div class="flex flex-col items-center gap-12 w-full h-full">
    <PageHeader />
    <main class="flex flex-col justify-center items-start gap-12 self-stretch">
      <div class="flex justify-between items-center self-stretch">
        <span class="text-2xl underline">Add new friend</span>
      </div>
      <div class="flex flex-col justify-center items-start gap-8 self-stretch">
        <div class="flex flex-col items-start gap-3 self-stretch pb-0 border-b border-black">
          <FormInput text="Name" />
        </div>
        <div class="flex flex-col items-start gap-3 self-stretch pb-0 border-b border-black">
          <FormInput text="E-mail" />
        </div>
      </div>
      <div class="flex items-center justify-between w-full self-stretch">
        <StandardButton text="Send friend request" icon="messages-icon.svg" />
      </div>
    </main>
  </div>
</template>

<script setup lang="ts">
import FormInput from '@/components/shared/FormInput.vue'
import StandardButton from '@/components/shared/StandardButton.vue'
import PageHeader from '@/components/shared/PageHeader.vue'
</script>
