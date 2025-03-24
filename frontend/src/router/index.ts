import { createRouter, createWebHistory } from 'vue-router'
import TodoView from '@/views/todoList/TodoView.vue'
import DetailsView from '@/views/todoList/DetailsView.vue'
import EditView from '@/views/todoList/EditView.vue'
import AddTodoView from '@/views/todoList/AddTodoView.vue'
import FriendView from '@/views/friendList/FriendView.vue'
import FriendDetailsView from '@/views/friendList/FriendDetailsView.vue'
import AddFriendView from '@/views/friendList/AddFriendView.vue'
import SendView from '@/views/todoList/SendView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'TodoList',
      component: TodoView
    },
    {
      path: '/details/:id',
      name: 'Details',
      component: DetailsView
    },
    {
      path: '/edit/:id',
      name: 'Edit',
      component: EditView
    },
    {
      path: '/add-to-do',
      name: 'AddTodo',
      component: AddTodoView
    },
    {
      path: '/friends',
      name: 'Friends',
      component: FriendView
    },
    {
      path: '/friends/details/:id',
      name: 'FriendDetails',
      component: FriendDetailsView
    },
    {
      path: '/friends/add-friend',
      name: 'AddFriend',
      component: AddFriendView
    },
    {
      path: '/send',
      name: 'SendTodo',
      component: SendView
    },
    {
      path: '/logout',
      name: 'Logout',
      component: {
        beforeRouteEnter(to, from, next) {
          window.location.href = '/logout'
          next(false)
        }
      }
    }
  ]
})

export default router
