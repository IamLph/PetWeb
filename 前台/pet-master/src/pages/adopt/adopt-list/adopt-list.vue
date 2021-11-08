<template>
    <div>
        <div class="container">
            <div class="row mtb50">
               <div class="col-xs-6">
                   <div class="mlr10all mtb10all">
                        <a class="btn bdn on crw plr25 ptb6 bg43 fs16">萌宠领养</a>
                   </div>
               </div>
            </div>
            <div class="row mb25all mb50">
                 <div class="col-md-5ths" v-for="(find,index) in lingPet" :key="index">
                    <AdoptListObj
                      :id="find.id"
                      :img="find.img"
                      :name="find.name"
                      :age="find.age"
                      :description="find.description"
                      :user_ID="find.userId">
                    </AdoptListObj>
                 </div>
            </div>
             <SareListPageTurning :count="count" @index="getIndex"></SareListPageTurning>
        </div>
    </div>
</template>

<script>
    import AdoptListObj from '../../adopt/adopt-list/adopt-list-obj/adopt-list-obj.vue'
    import SareListPageTurning from '../../../share/list/share-list-page-turning/share-list-page-turning.vue'
    // import { lingPet } from '../../../data/lingPet'
    import { getSharePetsByPageApi } from '../../../request/api'
    export default {
      components: {
          AdoptListObj,
          SareListPageTurning
      },
      data () {
          return {
            lingPet:[],
            currentPage:1,
            pageSize:10,
            //总条数
            total:0,
            //总页数
            count:0,
          }
      },
      created() {
        //  获取宠物
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
        //根据分页count获取那一页的宠物信息
        getPetsByPage(){
          let that = this;
          //先分页获取宠物信息,并且设置参数
          getSharePetsByPageApi({
            currentPage:this.currentPage,
            pageSize:this.pageSize
          }).then(res=>{
            // console.log(res);
            if(res.result.status === 200){
              that.lingPet = res.result.data;
              //设置条数
              that.total = res.total;
              //父子传值
              that.getAllPagesCount();
            }
          }).catch(err=>{
            console.log(err)
          })
        },
        getIndex(data){
          let that = this;
          //  分页查询
          getSharePetsByPageApi({
            currentPage:data,
            pageSize:this.pageSize
          }).then(res=>{
            // console.log(res);
            if(res.result.status === 200){
              that.lingPet = res.result.data;
            }
          }).catch(err=>{
            console.log(err)
          })
        }
      }
    }

</script>

<style scoped>
    .active{
        color:#0fc698;
    }

</style>
