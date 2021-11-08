<template>
    <div>
      <div class="container" >
        <a href="javascript:void(0)" @click="goBack()">< 返回</a>
        <div style="text-align: center">
          <h2>保护动物,人人有责</h2>
          <p style="text-align: left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;生命是平等的，我们人类以万物之灵自居，在社会发展的同时，侵占了太多土地，破坏各种生态环境，很多物种在灭绝。
            物种的灭绝也为人类敲响了警钟，人类在发展的同时，不要忘了保护环境，地球不是人类的地球，而是各种生命共同的家园。<br>
          1.关爱生命，我们拥有一个共同的地球。<br>
          2.一花一章皆生命,一枝一叶总关情。<br>
          3.放弃贪婪的食欲,不再居戮放过弱小的生命,不再滥捕放下嗜血的屋刀,不再游杀。世界动一起行动起来,保护动物,人人有责!<br>
          4.爱护动物,珍惜生命。<br>
          5.地球上没有动物,那是一个没有活力的世界<br>
          6.保护环境,功在当代利在干秋<br>
          7.不要虐待动物，它们也是有感情的。</p>
        </div>
      </div>
    </div>
</template>

<script>
    export default {
      name: "protectPet",
      methods:{
        goBack(){
          this.$router.go(-1);
        }
      }
    }
</script>

<style scoped>
  .container{
    width: 80%;
    margin-left: 10%;
    margin-bottom: 300px;
  }
</style>
