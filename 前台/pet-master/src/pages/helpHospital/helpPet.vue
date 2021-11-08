<template>
    <div class="container">
      <div style="text-align: center">
        <a href="#AC">
          <span>爱宠小知识</span>
        </a>
        <a href="#location" style="margin-left: 20px">
          <span>线下医疗点</span>
        </a>
      </div>

      <div class="list-group" id="AC">
        <h3>爱宠小知识</h3>
        <div class="tips" v-for="(item,index) in tipList" :key="index" >
          <a href="#" class="list-group-item active">
            <h4 class="list-group-item-heading">{{item.title}}</h4>
            <p class="list-group-item-text">{{item.content}}</p>
          </a>
        </div>
      </div>
      <div id="location">

      </div>
      <div class="hospLocation" >
        <h3>线下宠物医疗点</h3>

        <div class="panel panel-default" v-for="(item,index) in hospLocationList" :key="index">
          <div class="panel-heading">{{item.name}}</div>
          <div class="panel-body">
            <div>位置:{{item.location}}</div>
            <div>联系方式:{{item.phone}}</div>
          </div>
        </div>

      </div>
      <div style="text-align: center;margin-bottom: 10px">
        <a href="#AC">
          <span>爱宠小知识</span>
        </a>
        <a href="#location" style="margin-left: 20px">
          <span>线下医疗点</span>
        </a>
      </div>
    </div>
</template>

<script>
    // import {tipList,hospLocationList} from '../../data/aiYiLiao'
    import {tipKnowApi,getYiYuanApi} from '../../request/api'
    export default {
        name: "helpPet",
        data() {
          return {
            tipList:[],
            hospLocationList:[]
          }
        },
      created() {
          let that = this;
          /*小知识*/
          tipKnowApi().then(res=>{
            // console.log(res);
            if(res.status === 200){
              that.tipList = res.data;
            }
          }).catch(err=>{
            console.log(err)
          });
          /*医疗点*/
          getYiYuanApi().then(res=>{
            // console.log(res);
            if(res.status === 200){
              that.hospLocationList = res.data;
            }
          }).catch(err=>{
            console.log(err)
          })
      }
    }
</script>

<style scoped>
  .tips{
    margin-top: 20px;
  }
</style>
