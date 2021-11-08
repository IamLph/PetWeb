<template>
    <div>
        <!--社区列表内容组件-->
        <!--渲染朋友圈功能-->
        <ul>
            <li>
                <div class="row" style="">
                    <div class="col-sm-3" >
                        <div class="oh CommunityPhoto wh100  mb40" >
                          <!--左边宠物图片-->
                            <p @click="getId()"> <img :src="turl" alt="" class=" wh100  hr2d1"/></p>
                        </div>
                    </div>
                    <div class="col-sm-9">
                        <div class="mb45">
                            <a href="javaScript:void(0);" @click="getId()">
                                 <h3 class="cr11 fwb500 tno" style="margin-top:-1px;">{{ message }}</h3>
                            </a>
                            <a href="javaScript:void(0);" @click="getId()">
                                <p class="cr7 mt20" >{{ content }}</p>
                            </a>
                            <div class="clearfix mt15">
                                <div class="fl">
                                    <div class="HeadpPortrait fl mr10 w20px h20px" ><a href="javaScript:void(0);"><img :src="url" alt="" class="w100 h100 br1000"></a></div>
                                    <p class="fr">{{ pName }}</p>
                                </div>
                                <div class="fr pl10all cr7 mt15all">
                                  <!--点赞数-->
                                  <span class="fs12"><i class="iconfont icon-zanpress" @click="dianZan()"></i> {{ zanNum}}</span>
                                  <!--踩数-->
                                  <span class="fs12"><i class="iconfont icon-qinziAPPtubiao-" @click="dianCai()"></i> {{ caiNum }}</span>
                                  <!--评论数-->
                                  <span class="fs12"><i class="iconfont icon-pinglun" @click="pingLun()"></i> {{ commentNum }}</span>
                                  <!--时间 未说时间，暂不处理-->
<!--                              <span class="fs12"><i class="iconfont mr5" v-html="hour"></i>{{ time }}</span>-->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
              <!--评论位置-->
                <div v-show="isPing" style="width: 100%;height:auto;margin-top: -30px;position: relative;padding:0 20px 0 20px;">
                  <!--评论人行-->
                  <div style="height: 35px;display: inline-block;width: 100%;line-height: 30px;" v-for="(item,index) in pingLunList" :key="index">
                      <!--头像-->
                      <img :src="item.img" alt="未发现" style="height: 30px;width: 30px;border-radius: 50%">
                      <!--名字-->
                      <span style="font-size: 16px;font-weight: 600">{{item.userName}} :</span>
                      <!--评论内容-->
                      <span style="font-size: 14px;font-weight: 400">{{item.content}}</span>
                  </div>

<!--                  <div style="position:absolute;">评论区</div>-->
                  <!--发表行-->
<!--                  评论区-->
                  <div style="width: 100%;height: 80px;padding-top: 10px;border-bottom: 1px solid lightgray">
                    <input type="text" v-model="pingLunContent" placeholder="评论" style="width: 88%;height: 38px;background-color: rgb(245,246,250);">
                    <button @click="publishPingLun" style="background-color: rgb(0,202,252);float: right;height: 38px;width: 10%">发表</button>
                  </div>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
  // import { pingLunList } from '../../../../data/petCommunity'
  import { getPingLunApi,publishPlApi,zanApi,caiApi } from '../../../../request/api.js'

    export default {
      props:[
          'dynamicId',
          'message',
          'content',
          'zan',
          'cai',
          'comment',
          'time',
          'pName',
          'pId',
          'url',
          'turl'
      ],
      data(){
          return{
            // pingLunList:pingLunList,
            pingLunList:[],
            //是否显示评论区域
            isPing:false,
            pingLunContent:"",
            zanNum:this.zan,
            caiNum:this.cai,
            commentNum:this.comment,
          }
      },
      methods:{
        getId(){

        },
        dianZan(){
          if(this.$isLogin()){
            let trendId = this.dynamicId;
            let userId = localStorage.getItem('userId');
            let that = this;
            //发送点赞的请求
            zanApi({
              trendId:trendId,
              userId:userId
            }).then(res=>{
              // console.log(res);
              if(res.status === 200){
                that.zanNum++;
              }
            }).catch(err=>{
              console.log(err)
            })
          }else{
            alert("请先登陆！");
          }

        },
        dianCai(){
          if(this.$isLogin()){
            // alert("点踩");
            let trendId = this.dynamicId;
            let userId = localStorage.getItem('userId');
            let that = this;
            caiApi({
              trendId:trendId,
              userId:userId
            }).then(res=>{
              // console.log(res)
              if(res.status === 200){
                that.caiNum ++;
              }
            }).catch(err=>{
              console.log(err)
            })
          }else{
            alert("请先登陆！");
          }
        },
        //打开评论面板
        pingLun(){
          //如果登陆的话
          if(this.$isLogin()){
            this.isPing = this.isPing !== true;
            // 获取评论
            this.getPingL();
          }else{
            alert("请先登陆！");
          }
        },
        /*获取评论列表*/
        getPingL(){
          let that = this;
          let dynamicId = this.dynamicId;
          getPingLunApi(
            {
              trendId:dynamicId
            }
          ).then(res=>{
            // console.log(res);
            that.pingLunList = res.data;
          }).catch(err=>{
            console.log(err)
          })
        },
        //评论方法
        publishPingLun(){
          let userId = localStorage.getItem('userId');
          let dynamicId = this.dynamicId;
          let pingLunContent = this.pingLunContent;
          let that = this;
          // console.log(userId);
          if(pingLunContent === ''){
            alert("评论内容不可为空")
          }else{
            publishPlApi({
              content:pingLunContent,
              trendId:dynamicId,
              userId:userId
            }).then(res=>{
              console.log(res);
              if(res.status === 200){
                //重新获取评论
                that.commentNum ++;
                that.getPingL();
                //修改评论内容
                that.pingLunContent = '';
              }else{
                alert("评论失败");
              }
            }).catch(err=>{
              console.log(err)
            })
          }

          /*
          * 1.需要判断用户是否登陆了
          * 2.如果登陆需要获取用户的信息，未登陆需要跳转打开登陆界面
          * 3.发送请求
          * */
          // alert(this.pingLunContent);
          // alert(this.)
        }
      }
    }
</script>
