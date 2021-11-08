import {get,post,baseUrl} from './http'

//登陆接口
// export const LoginApi = params => post('/appLogin/'+params.leaveUserNum+'/'+params.leaveUserPwd,params);
export const BASEURL = baseUrl;

//登陆
export const LoginApi = params => post('/api/user/login',params);

//注册
export const RegisterApi = params =>post('/api/user/register',params);

//获取所有宠物用品列表
export const getPetsApi = params => get('/api/article/get_all',params);

//分页获取宠物用品
export const getPetsByPagesApi = params => get('/api/article/get_page_article',params);

//用户发布动态
export const publishDynamicApi = params => post('/api/trend/create_trend',params);

//首页获取动态
export const getDynamicApi = params => get('/api/trend/get_trend',params);

//获取更多动态
export const getThreeDynamicApi = params => get('/api/trend/get_page_trend',params);

//根据动态获评论
export const getPingLunApi = params => get('/api/trend/get_comment',params);

//发表评论
export const publishPlApi = params => post('/api/trend/create_comment',params);

//点赞
export const zanApi = params => post('/api/trend/star_trend',params);

//踩
export const caiApi = params => post('/api/trend/unstar_trend',params);

//帮助医疗
export const tipKnowApi = params => get('/api/tip/get_all',params);

//医疗地点
export const getYiYuanApi = params => get('/api/medicine/get_all',params);

//共享宠物
export const getSharePetsApi = params => get('/api/pet/get_all_pet',params);

//分页获取宠物
export const getSharePetsByPageApi = params => get('/api/pet/get_page_pet',params);

//根据宠物id获取宠物信息
export const getPetByIdApi = params => get('/api/pet/get_pet',params);

//领养宠物
export const getPetApi = params =>post('/api/pet/insert_share_pet',params);
