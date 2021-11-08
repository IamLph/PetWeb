export function isLogin() {
  let userId = localStorage.getItem('userId');
  if(userId !== null){
    // alert("已经登陆")
    return true;
  }else{
    // alert("没有登陆")
    return false
  }
}
