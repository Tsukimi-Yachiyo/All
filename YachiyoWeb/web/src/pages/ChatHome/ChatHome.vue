<script setup>
import { ref } from 'vue';
import { useChatHome } from '../../composables/useChatHome.js';
import AppHeader from '../../components/AppHeader/AppHeader.vue';
import Live2DModel from '../../components/Live2DModel/Live2DModel.vue';
import { useModelLoading } from '../../composables/useModelLoading.js';

const userProfileIsVisible = ref(false);

const {
  username,
  userAvatar,
  conversations,
  currentConversationId,
  messages,
  inputMessage,
  isLoading: isChatLoading,
  isTyping,
  isCreating,
  messageListRef,
  inputRef,
  isSidebarOpen,
  sidebarRef,
  isVoiceClickable,
  getVoiceStatus,
  toggleSidebar,
  closeSidebar,
  onTouchStart,
  onTouchEnd,
  selectConversation,
  createNewConversation,
  sendMessage,
  playVoice,
  logout
} = useChatHome();

// 获取模型加载状态
const { isLoading: isModelLoading, loadProgress, loadStatus } = useModelLoading();


</script>

<template src="./templates/ChatHome.html"></template>

<style scoped src="./styles/ChatHome.css"></style>
