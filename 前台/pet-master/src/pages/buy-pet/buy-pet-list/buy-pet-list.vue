<template>
    <div>
        <div class="container">
            <div class="row mtb50">
               <div class="col-xs-7">
                   <div class="mlr10all mtb10all">
                        <a href="javaScript:void(0);" class="btn bdn on crw plr25 ptb6 bg43 fs16">宠物用品</a>
                   </div>
               </div>
            </div>
            <div class="row mb25all mb30">

                <div class="col-md-5ths " v-for="(buy,index) in pets" :key="index">
                    <BuyPetListObj
                      :id="buy.id"
                      :imgUrl="buy.imgUrl"
                      :pName="buy.pName"
                      :money="buy.money"
                      :guiGe="buy.guiGe"
                      :yongFa="buy.yongFa"
                      :zhuYi="buy.zhuYi"
                      :shopUrl="buy.shopUrl">
                    </BuyPetListObj>
                </div>
            </div>
             <SareListPageTurning :count="count" @index="getIndex"></SareListPageTurning>
         </div>
     </div>
</template>

<script>
  import BuyPetListObj from '../../buy-pet/buy-pet-list/buy-pet-list-obj/buy-pet-list-obj.vue'
  import SareListPageTurning from '../../../share/list/share-list-page-turning/share-list-page-turning.vue'
  import {getPetsByPagesApi} from '../../../request/api'

  export default {
    components: {
        BuyPetListObj,
        SareListPageTurning
    },
    data () {
        return {
          pets:[],
          //默认当前页
          currentPage:1,
          //默认每次获取的数据
          pageSize:10,
          //总条数
          total:0,
          //总页数
          count:0,
        }
    },
    created() {
      //获取宠物用品 第一页
      this.getPetsByPage();
    },
    methods:{
        //获取页数
        getAllPagesCount(){
          if((this.total / this.pageSize)%1 === 0){
            this.count = this.total / this.pageSize
          }else{
            this.count = parseInt((this.total / this.pageSize)+'') + 1;
          }
        },
        getPetsByPage(){
          let that = this;
          getPetsByPagesApi({
            currentPage:this.currentPage,
            pageSize:this.pageSize
          }).then(res=>{
            // console.log(res);
            if(res.result.status === 200){
              that.pets = res.result.data;
              // console.log(res.total);
              //设置条数
              that.total = res.total;
              that.getAllPagesCount();
            }
          }).catch(err=>{
            console.log(err)
          })
        },
        getIndex(data){
          //获取页数查询
          // this.getPetsByPage2(data);
          let that = this;
          getPetsByPagesApi({
            currentPage:data,
            pageSize:that.pageSize
          }).then(res=>{
            if(res.result.status === 200){
              that.pets = res.result.data;
            }
          }).catch(err=>{
            console.log(err)
          })
        },
    }
}

</script>

<style scoped>
    .active{
        color:#0fc698;
    }
</style>
