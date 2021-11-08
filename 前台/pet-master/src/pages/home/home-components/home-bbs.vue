<template>
    <div>
            <!--猫狗社区-->
            <div class="container pt50 column column1">
                <div class="row mb20">
                    <div class="col-lg-8">
                        <div class="bb1 bsd bc2 oh pb30 mb60">
                            <h4 class="dib">
                                <a href="" class="crb fs18 mr30 crhr43">萌宠朋友圈</a>
                            </h4>
                          <!--社区功能，查看更多以及发帖-->
<!--                            <a href="" target="_blank" class="fs16 cr9 fr btn bc1 bghr43 tran crhrw bchr43" style="padding:6px 25px;" @click="more()">+ 更多</a>-->
                            <p class="fs16 cr9 fr btn bc1 bghr43 tran crhrw bchr43" style="padding:6px 25px;" @click="more()">+ 更多</p>
                            <p class="fs16 cr9 fr btn bc1 bghr43 tran crhrw bchr43" style="padding:6px 25px;margin-right: 20px" @click="publish()">+ 发贴</p>
                        </div>

                        <!--社区列表内容组件-->
                        <HomeBbsObj v-for="(item,index) in title"
                                    :message="item.message"
                                    :content="item.content"
                                    :dynamicId="item.dynamicId"
                                    :zan="item.zan"
                                    :comment="item.comment"
                                    :time="item.time"
                                    :pName="item.pName"
                                    :pId="item.pId"
                                    :url="item.url"
                                    :turl="item.turl"
                                    :cai="item.cai"
                                    :key="index">
                        </HomeBbsObj >
                      <!--时间:&#xe635;-->
                    </div>
                    <!--右侧萌宠展示-->
                    <div class="col-lg-4">
                       <div class="bb1 bsd bc2 pb30"><h4 class="tac cr11 fs18">萌宠SHOW</h4></div>
                       <div class="row mt60 mb20 mb10all tac crw-a"></div>
                        <!--幻灯片-->
                        <div class="swiper-container mt50 pr" >
                            <div class="swiper-wrapper">
                              <!--图片标题以及内容-->
                                <HomeSlide :url="tupian.url" :biaoti="tupian.biaoti"  v-for="(tupian,index) in picture" :key="index" class="swiper-slide" ></HomeSlide>
                            </div>
                            <!-- Add Pagination -->
                            <div class="swiper-pagination pr20"></div>
                        </div>
                    </div>
                </div>
            </div>
    </div>
</template>
<script>
import HomeBbsObj  from '../home-components/home-bbs/home-bbs-obj'
import HomeSlide from '../home-components/home-slide/home-slide.vue'
import '../../../assets/css/swiper.min.css'
import '../../../assets/js/swiper.min.js'
import { petComPicture} from '../../../data/AllData'
import { getThreeDynamicApi } from '../../../request/api.js'
export default {
        components: {
            HomeBbsObj,
            HomeSlide
        },
        data () {
            return {
              /*萌宠社区朋友圈*/
                title:[],
              /*萌宠轮播*/
                picture:petComPicture,
            }
        },
        mounted() {
            this.swiper();
        },
        //首页打开是发送请求获取动态
        created() {
          let that = this;
          //获取首页动态
          getThreeDynamicApi().then(res=>{
            //动态数据打印
            // console.log(res);
            if(res.status === 200){
              // console.log("========首页获取数据=======")
              // console.log(res)
              that.title = res.data;
            }else{
              alert("网络异常")
            }
          }).catch(err=>{
            console.log(err)
          })
        },
        methods: {
          swiper : function(){
              var swiper = new Swiper('.swiper-container', {
                pagination: '.swiper-pagination',
                paginationClickable: true
              });
          },
          //获取更多朋友圈
          more(){
            if(this.$isLogin()){
              /*跳转到社区（即朋友圈）*/
              this.$goRoute('/moreDynamic');
            }else{
              alert("请先登陆!");
            }
          },
          //发布
          publish(){
            if(this.$isLogin()){
              // alert("发帖")
              this.$goRoute('/BbsList');
            }else{
              alert("请先登陆!");
            }

          }
        },
    }

</script>

<style>

/*幻灯片播放*/
.swiper-container {
    width: 100%; max-width: 100%;
    height: 320px;
    max-height: 100%;
    margin: 20px auto;
}

.swiper-slide {
    text-align: center;
    font-size: 18px;
    /* Center slide text vertically */
    display: -webkit-box;
    display: -ms-flexbox;
    display: -webkit-flex;
    display: flex;
    -webkit-box-pack: center;
    -ms-flex-pack: center;
    -webkit-justify-content: center;
    justify-content: center;
    -webkit-box-align: center;
    -ms-flex-align: center;
    -webkit-align-items: center;
    align-items: center;
}



/*猫狗社区*/
.CommunityPhoto {
    height: 120px;
}


/*猫图片高度调整,猫狗社区图片调整*/

@media(max-width:499px) {
    .pictureHeight {
        height: 220px;
    }

    .CommunityPhoto {
        height: 220px;
        margin-bottom:15px;
    }
}

@media(min-width:500px) and (max-width:600px) {
    .pictureHeight {
        height: 250px;
    }
    .CommunityPhoto {
        height: 250px;
        margin-bottom:20px;
    }
}

@media(min-width:601px) and (max-width:767px) {
    .pictureHeight {
        height: 330px;
    }

    .CommunityPhoto {
        height: 330px;
        margin-bottom:25px;
    }
}

@media(min-width: 768px) and (max-width: 991px) {
    .pictureHeight {
        height: 400px;
    }

    .CommunityPhoto {
        height: 110px;
        margin-bottom:30px;
    }
}

@media(min-width:992px)and (max-width: 1199px) {
    .pictureHeight {
        height: 180px;
    }

    .CommunityPhoto{
        height: 120px;
    }
}


</style>
