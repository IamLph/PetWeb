<template>
    <div v-show="isShow">
        <div class=" w550px h320px bg0 pf l50 t50" style="z-index:10001;margin-left:-275px;margin-top:-110px">
            <ul class="tabs clearfix">
                <li class="tac">
                    <a href="javascript:;" class="tac fs16 tab-link ptb10 wh100 db crw" @click="changeType('register')" :class="{'cur':curId===0}">注册</a>
                </li>
                <li class="tac">
                     <a href="javascript:;" class="tac fs16 tab-link ptb10 wh100 db crw" @click="changeType('login')" :class="{'cur':curId===1}">登录</a>
                </li>
            </ul>
            <div class="cards">
                <!--注册-->
                <div class="w100 clearfix mtb40 " v-show="curId===0">
                    <div class="fl ml30">
                        <div class="w100  plr10 br5 h120px bss bw1 bc4" style="width:280px;">
                            <div class="bsd bb1 bc4">
                                <i class="iconfont cr4 fs18" v-html="fontreg"></i>
                                <input type="text" placeholder="用户名" class="bg0 cr0 blackInput" v-model="registerName">
                            </div>
                            <div class="bsd bb1 bc4">
                                <i class="iconfont cr4 fs18" v-html="fontlogin"></i>
                                <input type="password" placeholder="输入密码" class="bg0 cr0 blackInput" v-model="registerPwd1">
                            </div>
                            <div>
                                <i class="iconfont cr4 fs18" v-html="validate"></i>
                                <input type="password" placeholder="确认密码" class="bg0 cr0 blackInput" v-model="registerPwd2">
                            </div>
                        </div>
<!--                        <router-link to="/user-Reg" class="btn bg42 br1000 w100 tac  mt30 ptb12 bghr43  on  bshn bdn" style="border:none;" >注册</router-link>-->
                        <button @click="register()" class="btn bg42 br1000 w100 tac  mt30 ptb12 bghr43  on  bshn bdn" style="border:none;" >注册</button>

                    </div>
                    <div class="fr bss bl1 bc4 pr" style="width:220px">
                        <div class="pa  bg0 h30px cr10 w30px pt30 pb40 cr7" style="left:-10px;top:30%;">或</div>
                        <a href="#" class="btn  br5 tac w100 bg42 ml20 db" style="width:70%"><i class="crw iconfont fs18 mr10" v-html="weibo"></i><span class="crw">微博帐号登录</span></a>
                        <a href="#"  class="btn  br5 tac w100 bss bw1 bc3 db ml20 mtb35 bghr42 tran qqtubiao"  style="width:70%"><i class="iconfont cr43 mr10 fs18"  v-html="qqtubiao"></i><span class="cr43 crhrw ">QQ帐号注册</span></a>
                        <a href="#"  class="btn  br5 tac w100 bss bw1 bc3 db ml20 bghr42 tran wxtubiao"  style="width:70%"><i class="iconfont cr43 mr10 fs18"  v-html="wxtubiao"></i><span class="cr43 crhrw">微信帐号注册</span></a>
                    </div>
                </div>
                <!--登录-->
                <div class="w100 clearfix mtb40 " v-show="curId===1">
                    <div class="fl ml30">
                        <div class="w100  plr10 br5 h80px bss bw1 bc4" style="width:280px">
                            <div class="bsd bb1 bc4">
                                <i class="iconfont cr4 fs18" v-html="fontreg"></i>
                                <input type="text" placeholder="用户名" class="bg0 cr0 blackInput" v-model="loginName">
                            </div>
                            <div >
                                <i class="iconfont cr4 fs18" v-html="fontlogin"></i>
                                <input type="password" placeholder="输入密码" class="bg0 cr0 blackInput" v-model="loginPwd">
                            </div>
                        </div>
                        <div class="clearfix mt20">
                            <div class="fl"><input  type="checkbox" value="" class="w15px h15px"/><span class="fs12 cr7 ml5 fwb">十天内免登录</span></div>
                            <div class="fr"><a href="#" class="cr7 fs12 fwb">忘记密码？</a></div>
                        </div>

<!--                        <router-link to="/user-login" class="btn bg42 br1000 w100 tac  mt30 ptb12 bghr43  on  bshn bdn" style="border:none;" >登录</router-link>-->
                        <button @click="login()" class="btn bg42 br1000 w100 tac  mt30 ptb12 bghr43  on  bshn bdn" style="border:none;" >登录</button>
                    </div>
                    <div class="fr bss bl1 bc4 pr" style="width:220px">
                        <div class="pa  bg0 h30px cr7 w30px pt30 pb40" style="left:-10px;top:30%;">或</div>
                        <a href="#" class="btn  br5 tac w100 bg42 ml20 db" style="width:70%"><i class="crw iconfont fs18 mr10" v-html="weibo"></i><span class="crw">微博帐号登录</span></a>
                        <a href="#"  class="btn  br5 tac w100 bss bw1 bc3 db ml20 mtb35 bghr42 tran qqtubiao"  style="width:70%"><i class="iconfont cr43 mr10 fs18"  v-html="qqtubiao"></i><span class="cr43 crhrw ">QQ帐号注册</span></a>
                        <a href="#"  class="btn  br5 tac w100 bss bw1 bc3 db ml20 bghr42 tran wxtubiao"  style="width:70%"><i class="iconfont cr43 mr10 fs18"  v-html="wxtubiao"></i><span class="cr43 crhrw">微信帐号注册</span></a>
                    </div>
                </div>
            </div>
        </div>
        <div class="pf t0 r0 b0 l0 bgb70" style="z-index:10000;">
            <span @click="closePop" title="close" class="pa r0 t0 fs36 w100px h100px tac lh100 crw cp">X</span>
        </div>
    </div>
</template>

<script>
  import { LoginApi , RegisterApi} from '../../../request/api'
  export default {
    props:{
        type: {
            type: String,
            default: 'login'
        },

    },
    data () {
        return {
          fontreg:'&#xe6c6;',
          fontlogin:'&#xe6b2;',
          validate:'&#xe6b2;',
          weibo:'&#xe65d;',
          qqtubiao:'&#xe670;',
          wxtubiao:'&#xe65f;',
          registerName:"",
          registerPwd1:"",
          registerPwd2:"",
          loginName:"",
          loginPwd:"",
          isShow:true
        }
    },
    computed : {
        curId: function(){
            let type = this.type;
            switch(type){
                case 'register':
                    return 0;
                    break;
                case 'login':
                    return 1;
                    break;
                default:
                    return 1;
            }
        }
    },
    methods: {
      closePop(){
          this.$emit('closePopHalder');
      },
      changeType(newValue){
          this.$emit('update:type', newValue)
      },
      register(){
        if(this.registerName === '' || this.registerPwd1 === '' || this.registerPwd2 === ''){
          alert("请输入完整的注册信息")
        }else{
          if(this.registerPwd1 !== this.registerPwd2){
            alert("密码不一致")
          }else{
            let userName = this.registerName;
            let passWord = this.registerPwd2;
            RegisterApi({
              userName:userName,
              passWord:passWord
            }).then(res=>{
              alert("注册成功,请前往登陆");
              console.log(res)
            }).catch(err=>{
              alert("账号已被注册")
            })
          }
        }
      },
      login(){
        let that = this;
        // alert("登陆")
        if(this.loginName === '' || this.loginPwd === ''){
          alert("请输入完整的登陆信息")
        }else{
          // alert("登陆成功");
          let userName = this.loginName;
          let passWord = this.loginPwd;
          // localStorage.setItem('userName',this.loginName);
          //获取信息
          // let name = localStorage.getItem('userName');
          // console.log(name)
          LoginApi({
            userName:userName,
            passWord:passWord
          }).then(res=>{
            // console.log("成功");
            //登陆成功，本地存储账号密码
            localStorage.setItem('userName',userName);
            localStorage.setItem('passWord',passWord);
            //获取到的账号,需要时再弄
            let userId = res.data.id;
            localStorage.setItem('userId',userId);
            // alert(userId);
            //  跳转路由
            this.$goRoute('/home');
            that.isShow = false;
            // console.log(res);
            location.reload();
          }).catch(err=>{
            alert("登陆失败,请检查账号或者密码");
          })
        }
      }
    }
}

</script>

<style scoped>
.blackInput{
  color: black;
}

.tabs{
    display: -webkit-box;
    display: -moz-box;
    display: -moz-inline-box;
    -webkit-box-orient: horizontal;
    -moz-box-orient: horizontal;

}
.tabs>li{

    -webkit-box-flex: 1;
    -moz-box-flex: 1;

    background-color:#13d5af;
    color:#fff;
}

.tabs .cur{

    background-color:#f2f2f2 ;
    color:#333;
    transition: .3s;
}

.wxtubiao:hover>i{
    color:white !important;
}

.qqtubiao:hover>i{
     color:white !important;

}

.wxtubiao:hover{
    color:white !important;

}

.qqtubiao:hover{
    color:white !important;
}
</style>
