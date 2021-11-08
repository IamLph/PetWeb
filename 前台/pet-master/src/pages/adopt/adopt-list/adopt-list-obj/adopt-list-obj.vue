<template>
    <div>
        <div class="pictureHeight  h180px oh">
            <a href="#">
                <img :src="img" alt="" class="w100 h100 hr2d1">
            </a>
        </div>

        <div class="clearfix ptb15 plr10 bgw">
            <a href="#"  class="cr7 fs12 lh30">
              <span style="font-size: 15px;font-weight: 600">{{ name }}</span>
            </a>
            <a class="btn cr11 bc3 fr plr20 ptb4 bghr43 bchr43" @click="adopt(id)">领养</a>
        </div>

        <div style="width: 100%;height: 80px;position:relative;margin-top: -10px">
          <div style="position:absolute;margin-left: 10px;color: rgb(166,166,166)">
            <!--地址-->
            <p style="text-align: left;margin-bottom: 0">年龄：{{age}}</p>
            <p style="text-align: left;">描述：{{description}}</p>
          </div>
        </div>
    </div>
</template>

<script>
  import adoptPetFrame from "../../adoptPetFrame.vue";
    export default {
      props:[
        'id',
        'img',
        'name',
        'age',
        'description',
        'user_ID'
      ],
      components:{
        adoptPetFrame
      },
      data(){
        return{
          dialog:false
        }
      },
      methods:{
        adopt(id){
          // alert(id)
          // console.log(id);
          if(this.$isLogin()){
            this.$goRoute('/adoptPetFrame?id='+id);
          }else{
            alert("请先登陆!");
          }

        }
      }
    }

</script>
