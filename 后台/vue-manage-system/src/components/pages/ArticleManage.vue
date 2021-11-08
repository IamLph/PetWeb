<template>
    <div class='container'>
        <div class='head'>
            <el-button
                type="primary"
                icon="el-icon-delete"
                class="handle-del mr10"
                @click="handleAdd"
            >添加用品</el-button>
        </div>
        <div class='border'>
            <el-table
                :data="tableData.slice((this.query.pageIndex-1)*(this.query.pageSize),(this.query.pageIndex)*(this.query.pageSize))"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
            >

                <el-table-column prop='id' label='用品id' align='center' min-width='70'></el-table-column>
                <el-table-column label="用品图片" align="center" min-width='70'>
                    <template slot-scope="scope">
                        <el-image
                            class="table-td-thumb"
                            :src="scope.row.imgUrl"
                            :preview-src-list="[scope.row.imgUrl]"
                        ></el-image>
                    </template>
                </el-table-column>
                <el-table-column prop="pName" label="名称" min-width='70' align='center'></el-table-column>
                <el-table-column prop="money" label="价格" min-width='70' align='center'></el-table-column>
                <el-table-column prop="guiGe" label="规格" min-width='80' align='center'></el-table-column>
                <el-table-column prop="yongFa" label="用法" min-width='100' align='center'></el-table-column>
                <el-table-column prop="zhuYi" label="注意事项" min-width='100' align='center'></el-table-column>
                <el-table-column prop="shopUrl" label="店铺链接" min-width='180' align='center'></el-table-column>

                <el-table-column label="操作" min-width='120' align="center" fixed='right'>
                    <template slot-scope="scope">
                        <el-button
                            type="text"
                            icon="el-icon-edit"
                            class='font'
                            @click="handleEdit(scope.$index, scope.row)"
                        >编辑</el-button>
                        <el-button
                            type="text"
                            icon="el-icon-delete"
                            class="red font"
                            @click="handleDelete(scope.$index, scope.row)"
                        >删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <div class='foot'>
            <div class="pagination">
                <el-pagination
                    background
                    layout="total, prev, pager, next"
                    :current-page="query.pageIndex"
                    :page-size="query.pageSize"
                    :total="pageTotal"
                    @current-change="handlePageChange"
                ></el-pagination>
            </div>
        </div>

        <!-- 添加弹出框 -->
        <el-dialog title="添加宠物用品信息" :visible.sync="addVisible" width="35%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="用品名称">
                    <el-input v-model="form.pName"></el-input>
                </el-form-item>
                <el-form-item label="用品图片">
                    <el-upload class="uploadImage"
                               action="http://www.pet.com/api/image/upload" :on-success="uploadAddSuccess" :on-error="uploadAddError"
                    >
                        <el-button icon="el-icon-circle-plus-outline" type="primary">选取图片
                        </el-button>
                    </el-upload>
                </el-form-item>
                <el-form-item label="用品价格">
                    <el-input v-model="form.money"></el-input>
                </el-form-item>
                <el-form-item label="用品规格">
                    <el-input v-model="form.guiGe"></el-input>
                </el-form-item>
                <el-form-item label="用品用法">
                    <el-input v-model="form.yongFa"></el-input>
                </el-form-item>
                <el-form-item label="注意事项">
                    <el-input v-model="form.zhuYi"></el-input>
                </el-form-item>
                <el-form-item label="店铺链接">
                    <el-input v-model="form.shopUrl"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveAdd">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑宠物用品信息" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="用品名称">
                    <el-input v-model="form.pName"></el-input>
                </el-form-item>
                <el-form-item label="用品图片">
                    <el-upload class="uploadImage"
                               action="http://www.pet.com/api/image/upload" :on-success="uploadAddSuccess" :on-error="uploadAddError"
                    >
                        <el-button icon="el-icon-circle-plus-outline" type="primary">选取图片
                        </el-button>
                    </el-upload>
                </el-form-item>
                <el-form-item label="用品价格">
                    <el-input v-model="form.money"></el-input>
                </el-form-item>
                <el-form-item label="用品规格">
                    <el-input v-model="form.guiGe"></el-input>
                </el-form-item>
                <el-form-item label="用品用法">
                    <el-input v-model="form.yongFa"></el-input>
                </el-form-item>
                <el-form-item label="注意事项">
                    <el-input v-model="form.zhuYi"></el-input>
                </el-form-item>
                <el-form-item label="店铺链接">
                    <el-input v-model="form.shopUrl"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="editVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveEdit">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: 'ArtitcleManage',
        data(){
            return{
                tableData: [],
                query: {
                    pageIndex: 1,
                    pageSize: 7
                },
                pageTotal: 2,
                editVisible: false,
                addVisible: false,
                form:{
                    id: '',
                    imgUrl: '',
                    pName: '',
                    money: '',
                    guiGe: '',
                    yongFa: '',
                    zhuYi: '',
                    shopUrl: ''
                }
            }
        },
        created() {
            this.getData();
        },
        methods: {
            getData(){
                this.$axios.get('/article/get_all')
                    .then(res =>{
                        console.log("111111111111111");
                        console.log(res.data.data);
                        this.tableData = res.data.data;
                        this.pageTotal = res.data.data.length;
                    })
                    .catch(res =>{
                        this.$message.warning("暂无数据!");
                    })
            },
            handlePageChange(val) {
                this.$set(this.query, 'pageIndex', val);
            },
            handleAdd(){
                this.addVisible = true;
            },
            saveAdd(){

                let data = {name:this.form.pName,img:this.form.imgUrl,money:this.form.money,item:this.form.zhuYi,shop:this.form.shopUrl,spec:this.form.guiGe,apply:this.form.yongFa};
                console.log(this.form);
                //发送添加用户请求
                this.$axios.post('/article/add_article',data
                ).then(res =>{
                    this.$message.success("添加用品成功!");
                    this.addVisible = false;
                    this.getData();
                }).catch(res =>{
                    this.$message.error("添加用品失败!");
                    this.addVisible = false;
                });
            },
            // 编辑操作
            handleEdit(index, row) {
                this.idx = index;
                this.form = row;
                console.log(row);
                this.editVisible = true;
            },
            // 保存编辑
            saveEdit() {

                let data = {id:this.form.id,name:this.form.pName,img:this.form.imgUrl,money:this.form.money,item:this.form.zhuYi,shop:this.form.shopUrl,spec:this.form.guiGe,apply:this.form.yongFa};
                this.$axios.put('/article/update_article',data)
                    .then(res =>{
                        this.$message.success("修改用品成功!");
                        this.editVisible = false;
                        this.getData();
                    })
                    .catch(res =>{
                        this.$message.error("修改用品失败!");
                        this.editVisible = false;
                    });
            },
            handleDelete(index,row){

                this.$confirm('确定删除该用品吗?','提示',{
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(()=>{

                    let id = row.id;
                    console.log(id);
                    this.$axios.delete('/article/delete_article/' + id)
                        .then(res =>{
                            this.$message.success("删除用品成功!");
                            this.getData();
                        })
                        .catch(res =>{
                            this.$message.error("删除用品失败!");
                        })

                }).catch(()=>{

                })
            },
            // 上传图片
            uploadAddSuccess(res){
                console.log(res);
                this.$message.success("上传成功!")
                this.form.imgUrl = res.data;
            },
            uploadAddError(res){
                console.log(res);
                this.$message.error("上传失败!");
            },
        },
        watch: {
            addVisible(){
                if (this.addVisible === false){
                    this.form = {};
                }
            },
            editVisible(){
                if(this.editVisible === false){
                    this.form = {};
                }
            }
        }
    };
</script>

<style scoped>
    .border {
        margin-top: 2%;
    }
    .handle-box {
        margin-bottom: 20px;
    }

    .handle-select {
        width: 120px;
    }

    .handle-input {
        width: 300px;
        display: inline-block;
    }
    .table {
        width: 100%;
        font-size: 14px;
    }
    .red {
        color: #ff0000;
    }
    .mr10 {
        margin-right: 10px;
    }
    .table-td-thumb {
        display: block;
        margin: auto;
        width: 40px;
        height: 40px;
    }
    .font {
        font-size: 14px;
    }
    .uploadImage{
        float: left;
    }
</style>
