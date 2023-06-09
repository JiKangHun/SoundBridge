import axios from "axios";
// local vue api axios instance
function apiInstance() {
  const instance = axios.create({
    // "https://j8a703.p.ssafy.io"
    baseURL: import.meta.env.VITE_API_BASE_URL, //env로 설정 해줘야함
    headers: {
      "Content-Type": "application/json;charset=utf-8",
      accept: "application/json,",
    },
  });
  return instance;
}

export { apiInstance };
