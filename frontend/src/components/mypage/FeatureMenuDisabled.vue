<template>
  <div>
    <hr />
    <p>TO Do : 자주쓰느말, 발음 피드백 눌렀을때 보이는 화면이 달라져야함.</p>

    <router-link to="/mypaged/frequsedphrase">
      <span>자주 쓰는말 |</span>
    </router-link>
    <router-link to="/mypaged/meetings">
      <span>발음 피드백 |</span>
    </router-link>
    <router-link to="/voiceselect">
      <span>목소리 선택 |</span>
    </router-link>
    <router-view></router-view>
  </div>
</template>

<script setup>
import MyMeetings from "./MyMeetings.vue";
import FreqUsedPhrase from "../pronounce/FreqUsedPhrase.vue";
</script>

<style lang="scss" scoped></style>
