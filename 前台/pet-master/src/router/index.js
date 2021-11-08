import Vue from 'vue'
import Router from 'vue-router'
//首页
import Home from '../pages/home/home.vue'
//宠物用品
import BuyPetList from '../pages/buy-pet/buy-pet-list/buy-pet-list.vue'
//共享集宠
import AdoptPetList from '../pages/adopt/adopt-list/adopt-list.vue'
//发布帖子
import BbsList from '../pages/bbs/bbs-list/bbs-list.vue'
//帮医疗
import helpPet from "../pages/helpHospital/helpPet.vue";
//领养宠物界面
import adoptPetFrame from "../pages/adopt/adoptPetFrame.vue";
//更多帖子
import moreDynamic from "../pages/moreDynamic/moreDynamic.vue";
//保护动物
import protectPet from "../pages/protectPet/protectPet.vue";


Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    { path:'',redirect:'/Home'},
    { path:'/Home',name:'Home',component:Home, meta: { title:'首页' }},
    { path:'/BuyPetList',name:'BuyPetList',component:BuyPetList,meta: { title:'买宠'}},
    { path:'/AdoptPetList',name:'AdoptPetList',component:AdoptPetList,meta: { title:'领养'}},
    { path:'/BbsList',name:'BbsList',component:BbsList, meta: {title:'社区'}},
    { path:'/helpPet',name:'helpPet',component:helpPet, meta: {title:'爱医疗'}},
    { path:'/adoptPetFrame',name:'adoptPetFrame',component:adoptPetFrame, meta: {title:'萌宠领养'}},
    { path:'/moreDynamic',name:'moreDynamic',component:moreDynamic, meta: {title:'朋友圈'}},
    { path:'/protectPet',name:'protectPet',component:protectPet, meta: {title:'保护协议'}},
  ]
})
