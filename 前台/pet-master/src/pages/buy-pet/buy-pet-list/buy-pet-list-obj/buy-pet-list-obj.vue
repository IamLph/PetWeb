<template>
    <div>
      <div class="tac" style="">
          <div class="pictureHeight  h180px oh">
              <a :href="shopUrl" target="_blank">
                  <img :src="imgUrl" alt="" class="w100 h100 hr2d1">
              </a>
          </div>
          <div class="clearfix mtb20all plr10all" style="height: 40px;">
              <div class="fl">
                <!--地址-->
                <a :href="shopUrl" target="_blank" class="cr7 fs12">
                  <p style="font-size: 15px">商品名称:{{pName}}</p>
                </a>
              </div>
                <!--价格-->
<!--              <p class="fr fs16 cr43">¥<span class="ml5">{{ money }}</span></p>-->
          </div>
          <!--其他介绍-->
          <div style="width: 100%;height: 100px;position:relative;">
              <div style="position:absolute;margin-left: 10px;color: rgb(166,166,166)">
                <!--地址-->
                <p style="text-align: left;font-size: 15px;height: 6px">库存：{{guiGe}}</p>
                <p style="text-align: left;font-size: 15px;height: 6px">规格：{{yongFa}}</p>
                <p style="text-align: left;font-size: 15px;height: 6px">介绍：{{zhuYi}}</p>
              </div>
          </div>
      </div>
    </div>
</template>

<script>

    export default {
        props:[
          'id',
          'imgUrl',
          'pName',
          'money',
          'guiGe',
          'yongFa',
          'zhuYi',
          'shopUrl'
        ]
    }

</script>
