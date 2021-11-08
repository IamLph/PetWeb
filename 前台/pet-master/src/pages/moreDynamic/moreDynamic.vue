<template>
    <div>
      <!--猫狗社区-->
      <div class="container pt50 column column1">
        <div class="row mb20" style="margin-left: 12%">
          <div class="col-lg-9">
            <div class="bb1 bsd bc2 oh pb30 mb60">
              <h4 class="dib">
                <a href="javaScript:void(0);" class="crb fs18 mr30 crhr43" @click="goBack()">< 返回</a>
              </h4>
            </div>

            <!--社区列表内容组件-->
            <HomeBbsObj :message="item.message"
                        :content="item.content"
                        :dynamicId="item.dynamicId"
                        :zan="item.zan"
                        :comment="item.comment"
                        :time="item.time"
                        :pName="item.pName"
                        :url="item.url"
                        :turl="item.turl"
                        :cai="item.cai"
                        v-for="(item,index) in title"
                        :key="index">
            </HomeBbsObj >
            <!--时间:&#xe635;-->
          </div>

        </div>
      </div>
    </div>
</template>

<script>
  import HomeBbsObj  from '../home/home-components/home-bbs/home-bbs-obj'
  import { getDynamicApi } from '../../request/api'
  export default {
    name: "moreDynamic",
    components: {
      HomeBbsObj,
    },
    data () {
      return {
        /*萌宠社区朋友圈*/
        title:[],
      }
    },
    //页面创建时
    created() {
      let that = this;
      getDynamicApi().then(res=>{
        console.log(res);
        if(res.status === 200){
          // console.log(res.data)
          that.title = res.data;
        }else{
          alert("网络异常")
        }
      }).catch(err=>{
        console.log(err)
      })
    },
    methods:{
      goBack(){
        this.$goRoute('/Home');
      }
    }
  }
</script>

<style scoped>

</style>
