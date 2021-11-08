<template>
    <div>
        <div class="mb60 tac">
            <div class="dib clearfix">
              <!--上一页-->
              <nav aria-label="Page navigation">
                <ul class="pagination">
                  <li>
                    <a href="javaScript:void(0);" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                    </a>
                  </li>
                  <li v-for="(item,index) in count" :key="index"><a href="javaScript:void(0);" @click="sendMsg(index+1)">{{index + 1}}</a></li>
                  <li>
                    <a href="javaScript:void(0);" aria-label="Next">
                      <span aria-hidden="true">&raquo;</span>
                    </a>
                  </li>
                </ul>
              </nav>
            </div>
        </div>
    </div>
</template>
<script>

    export default {
        props:[
          'count'
        ],
        data () {
            return {

            }
        },
        methods:{
          sendMsg(index){
            // console.log(index)
            this.$emit('index',index)
          }
        }
    }

</script>


<style scoped>


</style>
