<template>
  <div class="container">
    <div style="margin-top: 20px;width: 60%;margin-left: 20%;margin-bottom: 200px">
      <form>
        <div class="form-group">
          <label for="petStatus">萌宠当前状态</label>
          <input type="text" class="form-control My_input" id="petStatus" disabled :value="petStatusValue">
        </div>

        <div class="form-group">
          <label for="userName">领养人</label>
<!--          <input type="text" class="form-control My_input" id="userName" placeholder="请输入姓名" disabled :value="name">-->
          <input type="text" class="form-control My_input" id="userName" placeholder="请输入姓名" disabled :value="petStatus_userInfo">
        </div>

        <div class="form-group">
          <label for="userId">身份证号</label>
          <input type="text" class="form-control My_input" id="userId" placeholder="请输入身份证号" v-model="idCard">
        </div>

        <div>
          <label>饲养日期</label>
          <div>
            <a-range-picker @change="getData" />
          </div>
        </div>


        <div class="checkbox">
          <label>
            <input type="checkbox" @click="checkbox($event)">
            <a href="javascript:void(0)" @click="protect()">同意保护饲养动物的协议</a>
          </label>
        </div>
        <button @click="applyPet" type="button" class="btn btn-default" style="background-color: powderblue;color: black;width: 100px">申请领养</button>
        <button @click="goBack()" type="button" class="btn btn-default" style="background-color: rgba(103,117,119,0.43);color: black;margin-left: 20px;width: 100px">取消</button>
      </form>
    </div>

  </div>
</template>

<script>
import { getPetByIdApi , getPetApi } from '../../request/api'
import { checkIDCard } from '../utils/IdCardUtils'
export default {
  name: "adoptPetFrame",
  data(){
    return{
      name:"",
      idCard:"",
      startTime:"",
      endTime:"",
      petStatusValue:"",
      petStatus_userInfo:'无',
      isCheck:false,
      petId:''
    }
  },
  /*页面加载时获取宠物信息*/
  created() {
    //获取萌宠Id
    this.petId = this.$route.query.id;
    this.getPetById(this.$route.query.id);
  },
  methods:{
      protect(){
        this.$goRoute('/protectPet');
      },
      //根据宠物Id获取宠物信息
      getPetById(id){
        let that = this;
        getPetByIdApi({
          id:id
        }).then(res=>{
          console.log(res)
          if(res.status === 200){
            let petName = res.data.name;
            let userId = res.data.userId;
            if(userId !== ''){
              that.petStatusValue = petName+"已被领养";
              //领养人的Id
              that.petStatus_userInfo = userId;
            }else{
              that.petStatusValue = petName+"可领养";
              that.petStatus_userInfo = '无';
            }
          }
        }).catch(err=>{
          // console.log("失败")
          console.log(err)
        })
      },
      getData(date, dateString) {
        let dataArray = dateString;
        this.startTime = dataArray[0];
        this.endTime = dataArray[1];
      },
      checkbox(e){
        this.isCheck = e.target.checked
      },
      goBack(){
          this.$goRoute('/AdoptPetList');
      },
      //申请领养
      applyPet(){
        if( this.petStatus_userInfo !== '无'){
          alert("不可领养")
        }else {
        //  可以领养，判空处理
          if(this.idCard === "" || this.startTime === "" || this.endTime === ""){
            alert("请输入完整信息")
          }else{
            if(!checkIDCard(this.idCard)){
              alert("请输入有效的身份证号！")
            }else{
              if(!this.isCheck){
                alert("请同意宠物保护协议")
              }else{
                //宠物Id
                let petId = this.petId;
                //身份证号
                let idCard = this.idCard;
                //开始结束时间
                let startTime = this.startTime;
                let endTime = this.endTime;
                //领养人账号
                let userId = localStorage.getItem('userId');
                // console.log(name+"\n"+idNum+"\n"+startTime+"\n"+endTime+"\n"+userId);
                console.log(checkIDCard(idCard));
                //请求
                getPetApi({
                  startTime:startTime,
                  endTime: endTime,
                  petId:petId,
                  userId:userId,
                  idCard:idCard
                }).then(res=>{
                  console.log(res);
                  if(res.status === 200){
                    alert("领养成功");
                    // that.$goRoute('/home');
                    location.reload();
                  }
                }).catch(err=>{
                  console.log(err)
                })
              }
            }
          }
        }
      }
    }
}
</script>

<style scoped>
  .My_input{
    /*width: 60%;*/
  }
</style>
