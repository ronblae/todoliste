<template>
  <div class="flex flex-col items-center gap-12 w-full h-full">
    <div class="flex flex-col justify-center items-start gap-12 self-stretch">
      <div class="flex justify-between items-center self-stretch">
        <span v-if="props.mode === 'add'" class="text-2xl underline">New To-Do</span>
        <span v-else class="text-2xl underline">Edit details</span>
      </div>
      <div class="flex flex-col justify-center items-start gap-8 self-stretch">
        <div class="flex flex-col items-start gap-3 self-stretch pb-0">
          <FormInput text="Title" v-model="todoRef.title" />
        </div>
        <div class="flex flex-col items-start gap-3 self-stretch pb-0">
          <label class="flex w-full flex-col items-start gap-3 font-bold text-lg"
            >Description
            <textarea
              class="w-full h-full border bg-white shadow-custom p-1"
              cols="40"
              rows="5"
              v-model="todoRef.description"
            />
          </label>
        </div>
        <div class="flex flex-col items-start gap-3 self-stretch pb-0">
          <span class="text-lg font-bold">To do until</span>
          <VueDatePicker v-model="selectedDate" :enable-time-picker="false" :format-locale="de" />
        </div>
        <div class="flex items-center justify-between self-stretch pb-3">
          <span class="font-bold text-lg text-custom-red">Important</span>
          <CheckBox v-model="todoRef.important" :checked="todoRef.important" />
        </div>
      </div>
      <div class="flex items-center justify-between w-full gap-5 self-stretch">
        <StandardButton text="Save" icon="icon-checkmark.svg" @click.prevent="postOrUpdateTodo" />
        <StandardButton text="Cancel" icon="icon-cancel.svg" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import FormInput from '@/components/shared/FormInput.vue'
import CheckBox from '@/components/shared/CheckBox.vue'
import StandardButton from '@/components/shared/StandardButton.vue'

import type { Todo } from '@/api/types'

import { ref, type PropType, computed } from 'vue'
import { de } from 'date-fns/locale'
import VueDatePicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'
import { useTodosStore } from '@/stores/todos'
import { useRouter } from 'vue-router'

const router = useRouter()

const props = defineProps({
  todo: {
    type: Object as PropType<Todo>,
    default: (): Todo => {
      return {
        title: '',
        important: false,
        checked: false,
        date: new Date(),
        description: '',
        fromFriend: false
      }
    }
  },
  mode: {
    type: String,
    required: true,
    validator: (type: string) => {
      return type === 'add' || type === 'edit'
    }
  }
})

const selectedDate = ref<Date>(props.todo.date)
const todoRef = computed(() => {
  return { ...props.todo, date: selectedDate.value }
})

const postOrUpdateTodo = async () => {
  if (props.mode === 'add') {
    await useTodosStore().postTodoItem(todoRef.value)
  } else {
    await useTodosStore().updateTodoItem(todoRef.value)
  }
  router.push({ name: 'TodoList' })
}
</script>
