<template>
  <div class="">
    <p class="">내 목소리</p>
    <div style="filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25))">
      <div
        class="flex justify-center items-center flex-grow-0 flex-shrink-0 relative gap-2 px-2"
      >
        <svg
          width="80"
          height="81"
          viewBox="0 0 80 81"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
          class="flex-grow-0 flex-shrink-0 w-20 h-20"
          preserveAspectRatio="none"
        >
          <circle cx="40" cy="40.5" r="40" fill="#BAE4FF"></circle>
          <path
            d="M54.7621 57.6428V53.8333C54.7621 51.8126 53.9594 49.8747 52.5305 48.4458C51.1017 47.017 49.1637 46.2143 47.143 46.2143H31.9049C29.8842 46.2143 27.9463 47.017 26.5175 48.4458C25.0886 49.8747 24.2859 51.8126 24.2859 53.8333V57.6428M47.143 30.9762C47.143 35.1841 43.7319 38.5952 39.524 38.5952C35.3161 38.5952 31.9049 35.1841 31.9049 30.9762C31.9049 26.7683 35.3161 23.3571 39.524 23.3571C43.7319 23.3571 47.143 26.7683 47.143 30.9762Z"
            stroke="#0B76BB"
            stroke-width="2.66667"
            stroke-linecap="round"
            stroke-linejoin="round"
          ></path>
        </svg>
        <div>
          <p
            class="flex-grow-0 flex-shrink-0 w-[151px] h-4 text-base font-bold text-left text-black"
          >
            목소리 이름
          </p>
          <p class=""><span>#성별 #나이대 #목소리 톤 #분위기</span><br /></p>
        </div>
      </div>
      <div>
        <div style="box-shadow: 0px 1px 2px 0 rgba(16, 24, 40, 0.05)">
          <p>선택하기</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>
