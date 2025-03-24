<template>
  <div
    class="flex w-full px-3 py-2 flex-col justify-center items-start border-b"
    :class="itemclass"
  >
    <div class="flex pl-11 justify-between items-center self-stretch">
      <span class="opacity-30 text-xs">{{ formatDate(todo.date) }}</span>
      <span class="opacity-30 text-xs" v-if="todo.fromFriend">{{ todo.creatorName }}</span>
    </div>
    <div class="flex justify-between items-center self-stretch gap-3">
      <CheckBox
        v-model="checked"
        :checked="checked"
        @update:model-value="(isChecked: boolean) => updateChecked(isChecked)"
      />
      <span class="w-full text-lg">{{ todo.title }}</span>
      <div
        class="flex justify-center w-4 -ml-4 cursor-pointer"
        @click="$emit('openContextMenu', $event, props.todo.todoId)"
      >
        <StandardImage src="button-options.svg" class="h-full" />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import CheckBox from '../shared/CheckBox.vue'
import StandardImage from '../shared/StandardImage.vue'
import { computed, type PropType, ref } from 'vue'
import { format } from 'date-fns'

import type { Todo } from '@/api/types'
import { useTodosStore } from '@/stores/todos'

const props = defineProps({
  todo: {
    type: Object as PropType<Todo>,
    required: true
  }
})

const checked = ref<boolean>(props.todo.checked)

const itemclass = computed(() => {
  console.log(props.todo)
  return {
    important: props.todo.important,
    normal: !props.todo.important,
    checked: props.todo.checked,
    friend: props.todo.fromFriend
  }
})

const updateChecked = (isChecked: boolean) => {
  console.log('updateChecked')
  const updatedTodo = props.todo
  updatedTodo.checked = isChecked
  useTodosStore().updateTodoItem(updatedTodo)
}

const formatDate = (date: Date) => {
  return format(new Date(date), 'dd/MM/yyyy')
}
</script>

<style scoped>
.normal {
  color: black;
  border-color: black;
}

.important {
  color: #db1010;
  border-color: #db1010;
}

.friend {
  color: #27891e;
  border-color: #27891e;
}

.checked {
  color: #9e9090;
  border-color: #9e9090;
  text-decoration: line-through;
}
</style>
