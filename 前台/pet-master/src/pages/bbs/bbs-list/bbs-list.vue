<template>
    <div>
        <div class="container">
            <div style="margin-top: 40px">
                <ul class="clearfix flall">
                     <li class="br2 bss bc6 mr15"><a href="" class="mr20 cr11 fs16"><i class="iconfont mr10">&#xe65e;</i>发布新贴</a></li>
                </ul>
                  <!--发布新贴-->
                <div class="post">
                    <input type="text" placeholder="这里输入标题文字" v-model="title" class="w100 bss bc5 bw1 h40px mt30">
                  <!--图片上传-->
                    <div style="display: flex;width: 100%;margin-top: 10px;height: 100px;padding-top: 10px">
                      <label for="uploadImg">
                        <img :src="uploadImgUrl" alt="未发现图片" style="height: 100px;width: 100px">
                      </label>
                      <input type="file" id="uploadImg" style="display: none" accept="image/jpeg,image/x-png,image/gif" v-on:change="uploadImg($event)">
                    </div>
                  <!--帖子内容-->
                    <textarea placeholder="这里输入帖子内容" class="w100 bss bc5 bw1 mtb30 h150px" v-model="content">
                    </textarea>
                    <a href="javaScript:void(0);" class="btn plr25 ptb10 bg43 crw bdn on fr fs16 mb50" style="border-radius:3px" @click="upload">发布帖子</a>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { publishDynamicApi , BASEURL } from '../../../request/api'
import axios from 'axios';
export default {
    data () {
        return {
          /*有个默认上传图片*/
            uploadImgUrl:require('../../../assets/MyIcon/uploadImg.png'),
            title:'',
            content:'',
            //上传之后的真实地址
            img:'',
        }
    },
    methods:{
      uploadImg(e){
          let that = this;
          let file = e.target.files[0];
          /*上传文件*/
          let formData = new FormData();
          formData.append("file",file);
          /*修改请求配置*/
          let config = {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          };
          //KB单位
          let imgSize = file.size/1024;
          // console.log(imgSize);
          let reader = new FileReader();
          reader.readAsDataURL(file);
          reader.onloadend = function () {
          // 图片的 base64 格式, 可以直接当成 img 的 src 属性值
          let dataURL = reader.result;
          // console.log(dataURL);
          //  修改展示图片
          that.uploadImgUrl = dataURL;
          axios.post(BASEURL+"/api/image/upload",formData,config).then(res=>{
            // console.log(res);
            that.img = res.data.data;
          }).catch(err=>{
            console.log(err)
          })
        }
      },
      upload(){
        let title = this.title;
        let content = this.content;
        let img = this.img;
        let userId = localStorage.getItem('userId');
        publishDynamicApi({
          content:content,
          img:img,
          title:title,
          userId:userId
        }).then(res=>{
          if(res.status === 200){
            alert("发布成功");
          //  跳转路由
            this.$goRoute('/home');
          }
        }).catch(err=>{
          console.log("失败");
          console.log(err)
        })
      }
    }
}

</script>


<style scoped>

@media(max-width:768px){

}

</style>
