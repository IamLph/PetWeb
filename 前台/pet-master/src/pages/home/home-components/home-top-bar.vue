<template>
    <div>
        <div class="container pf w100 oh banner"  @click="inputclick"  style="height:300px;z-index:0;padding-left: 0;padding-right: 0"  :class="{'green':isA,'isgreen':!isA}">

          <!--背景图片-->
            <div class="indexBgImgContainer">
              <img :src="bgImgUrl" class="bgImg" alt="未发现图片">
            </div>
             <!--搜索框-->
<!--            <div class="row pa l0 r0 b0 searchbox">-->
<!--                <div class="col-sm-3"></div>-->
<!--                <div class="col-sm-6">-->
<!--                    <div class="row mb15all">-->
<!--                        <div class="col-sm-9">-->
<!--                            <div class="pr br1000">-->
<!--                                <form action="#" method="get">-->
<!--                                    <input type="search" class="br1000 brfs0 bgw  dnbtnfocus  w100 p10 ti5 searchBox" placeholder="请输入关键字搜索" v-model="value">-->
<!--                                    &lt;!&ndash;搜索下拉提示框&ndash;&gt;-->
<!--                                    <ul class="bg0 w100 dropdownBox pa dnhr bghr1all plr20all ptb12all cp cr11-a keyword dn"  style="z-index:999999">-->
<!--                                        <li v-for="(item,index) in items" :key="index">-->
<!--                                            <a target="_blank" class="flall">-->
<!--                                                <div class="tno"> {{ value }}</div>-->
<!--                                                <div><i class="glyphicon glyphicon-search cr6 mr5"></i>{{ item.message }}</div>-->
<!--                                            </a>-->
<!--                                        </li>-->
<!--                                    </ul>-->
<!--                                    <a target="_blank" class="glyphicon glyphicon-search pa dib search" aria-hidden="true"></a>-->
<!--                                </form>-->
<!--                            </div>-->
<!--                        </div>-->
<!--                        <div class="col-sm-3">-->
<!--                            <a target="_blank" class="w100 bg42 db tac br1000  crw tn fs16  bghr43 tranbackground crhrw p11">晒 宠</a>-->
<!--                        </div>-->
<!--                    </div>-->
<!--                </div>-->
<!--                <div class="col-sm-3"></div>-->
<!--            </div>-->
        </div>

    </div>
</template>

<script>
  import { bgImgUrl } from '../../../data/AllData'
    export default {
        data () {
            return {
               value:'',
               isA:false,
               bgImgUrl:bgImgUrl,
               items:[
                { message:'搜售宠'},
                { message:'搜商品'},
               /* { message:'搜服务'},
                { message:'搜知识'}*/
             ],
            }
        },
        methods: {
            inputclick: function(){
                this.isA = !this.isA;
            }
        }
    }

</script>

<style scoped>
    .green{
        color:#0fc698;
    }
    .isgreen{
        color:white;
    }
/*======================背景样式设置=========================*/
.indexBgImgContainer{
  background-color: red;
  /*position:absolute;*/
  width: 100%;
  margin: 0;
  /*height: 200px;*/
}
/*背景图片*/
.bgImg{
  position:absolute;
  width: 100%;
  height: auto;
  margin-top: -200px;
}
/*===============================================*/

/*搜索关键词*/

.keyword>li>a>div:nth-child(1){
    width:calc(100% - 70px) !important
}

.keyword>li>a>div:nth-child(2){
    float:right;
    width:70px
}

/*搜索框*/
.searchbox{
    top:30%;
}

/*搜索关键词*/

.keyword>li>a>div:nth-child(1){
    width:calc(100% - 70px) !important
}

.keyword>li>a>div:nth-child(2){
    float:right;
    width:70px
}

/*搜索图标*/
.search {
    background-color: #fff !important;
    border-color: #fff !important;
    color: #a6a6a6 !important;
    right:20px;margin-top:12px
}

</style>
