import axios from 'axios';
import QS from 'qs'

//请求后台ip地址
export const baseUrl = "http://www.pet.com";

//设置请求地址
// axios.defaults.baseURL = "http://192.168.187.1:85/leaveAppApi";
axios.defaults.baseURL = baseUrl;
//十秒请求超时
axios.defaults.timeout = 10000;
// axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
//post请求头设置
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8;application/json';

//响应拦截
axios.interceptors.response.use(
  response =>{
    if(response.status === 200){
      return Promise.resolve(response);
    } else {
      return Promise.reject(response);
    }
  },
  error => {
    if(error.response.status){
      switch (error.response.status) {
        case 400:
          // alert("操作失败");
          break;
        case 404:
          // alert("未找到");
          break;
        case 500:
          // alert("500");
          break;
        default:
          // alert("服务异常")
      }
      return Promise.reject(error.response);
    }
  }
);
//封装get
export function get(url,params) {
  return new Promise((resolve, reject) =>{
    axios.get(url, {
      params: params
    }).then(res => {
      resolve(res.data);
    }).catch(err =>{
      reject(err.data)
    })
  });
}
//封装post
export function post(url, params) {
  return new Promise((resolve, reject) => {
    axios.post(url,QS.stringify(params)).then(res=>{
      resolve(res.data);
    }).catch(err=>{
      reject(err.data)
    })
  });
}
