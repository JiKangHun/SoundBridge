<template>
  <div>
    <!-- <input type="image" src="" alt="" /> -->
    <div id="profileImg">
      <img
        :src="`../src/assets/img/${member.profile}`"
        @click="changeProfile"
        alt="profile"
        id="profile"
      />
      <div id="div_modifyImg">
        <input
          type="file"
          @change="imgUploadEvent"
          accept=".jpg,.jpeg,.png"
          id="imgInput"
          style="display: none"
        />
        <label for="imgInput">
          <!--여기 이미지가 수정버튼 이미지-->
          <img src="@/assets/img/new.png" id="modifyProfile" />
        </label>
      </div>
    </div>
    <div>
      <div id="nickname" v-if="!save">
        <span id="nickNameSpan">{{ member.nickname }} </span>
        <span @click="modifyNameInput">
          <font-awesome-icon icon="fa-solid fa-pen" />
        </span>
      </div>
      <div v-else>
        <form id="modifyInput" @submit="modifyName" onsubmit="return false">
          <input type="text" v-model="name" /><br />
          <button type="button" @click="modifyName">수정</button>
        </form>
        <span v-if="checkNickname" id="checkNickname">
          2~15글자내외, 특수문자 사용 불가능
        </span>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed, ref } from "vue";
import { useMypage } from "@/store/Member";
import { storeToRefs } from "pinia";
const memberStore = useMypage();

const { accessToken, member } = memberStore;
const maxSize = 2 * 1024 * 1024;

let name = ref(member.nickname);
let checkNickname = ref(false);

let save = ref(false);

const modifyNameInput = () => {
  save.value = !save.value;
};

let reg_nickname = /^[A-z가-힣0-9_-]{2,15}$/;
const modifyName = (e) => {
  console.log("닉네임 수정 버튼?", name.value);
  if (reg_nickname.test(name.value)) {
    memberStore.modifyNickName(name.value);
  } else {
    console.log("닉네임 규칙에 맞게");
    if (!checkNickname.value) {
      checkNickname.value = !checkNickname.value;
    }
  }
  name = ref(member.nickname);
  save.value = !save.value;
  console.log("save ", save.value);
};

const imgUploadEvent = (e) => {
  console.log(accessToken, "  id ", member.profile);
  changeProfile(e.target.files);
};

let reg_img = ["jpg", "jpeg", "png"];
const changeProfile = (f) => {
  const modifyImgName = f[0].name;

  const formData = new FormData();
  formData.append("memberId", 1);
  formData.append("profile", f[0]);

  const ext = modifyImgName.split(".").pop().toLowerCase();
  if (reg_img.includes(ext) && f[0].size < maxSize) {
    memberStore.modifyMyProfile(formData);
    console.log("end profile", member.profile);
  } else {
    alert("2MB이하의 jpg, jpeg, png 이미지만 가능합니다!");
    console.log("이미지 규칙에 맞게");
  }
};
</script>

<style lang="scss" scoped>
#profile {
  width: 200px;
  height: 200px;
}

#div_modifyImg {
  border-radius: 5px;
  border: 2px solid black;
  position: absolute;
  top: 48%;
  left: 48%;
  transform: translate(-50%, -50%);
}

#modifyProfile {
  background-color: white;
  width: 30px;
  height: 30px;
}

#nickNameSpan {
  margin-right: 10px;
}
</style>
<!-- style="display: none" -->
