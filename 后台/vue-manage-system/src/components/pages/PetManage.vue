<template>
    <div class='container'>
        <div class='head'>
            <el-button
                type="primary"
                icon="el-icon-delete"
                class="handle-del mr10"
                @click="handleAdd"
            >添加宠物</el-button>
        </div>
        <div class='border'>
            <el-table
                :data="tableData.slice((this.query.pageIndex-1)*(this.query.pageSize),(this.query.pageIndex)*(this.query.pageSize))"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change=""
            >
                <el-table-column prop="name" label="宠物名称" min-width="50" align="center"></el-table-column>
                <el-table-column prop='age'  label="宠物年龄" min-width='50' align='center'></el-table-column>
                <el-table-column label="宠物头像" align="center" min-width='50'>
                    <template slot-scope="scope">
                        <el-image
                            class="table-td-thumb"
                            :src="scope.row.img"
                            :preview-src-list="[scope.row.img]"
                        ></el-image>
                    </template>
                </el-table-column>
                <el-table-column prop="description" label="描述信息" min-width='120' align='center'></el-table-column>
                <el-table-column label="宠物状态" min-width='100' align='center'>
                    <template slot-scope='scope'>
                        <el-tag style='font-size: 14px'
                            :type="scope.row.status===1?'success':(scope.row.status===0?'danger':'')"
                        >{{formatStatus(scope.row.status)}}</el-tag>
                    </template>
                </el-table-column>

                <el-table-column label="操作" min-width='120' align="center">
                    <template slot-scope="scope">
                        <!--<el-button
                            type="text"
                            icon="el-icon-edit"
                            class='font'
                            @click="handlePetUser(scope.$index, scope.row)"
                        >共同饲养</el-button>-->
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

        <el-dialog title="添加宠物信息" :visible.sync="addVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="宠物名称">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="宠物年龄">
                    <el-input v-model="form.age"></el-input>
                </el-form-item>
                <el-form-item label="描述信息">
                    <el-input v-model="form.description"></el-input>
                </el-form-item>
                <el-form-item label="宠物头像">
                    <el-upload class="uploadImage"
                               action="http://www.pet.com/api/image/upload" :on-success="uploadAddSuccess" :on-error="uploadAddError"
                    >
                        <el-button icon="el-icon-circle-plus-outline" type="primary">选取头像</el-button>
                    </el-upload>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveAdd">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑用户详细信息" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="宠物名称">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="宠物年龄">
                    <el-input v-model="form.age"></el-input>
                </el-form-item>
                <el-form-item label="描述信息">
                    <el-input v-model="form.description"></el-input>
                </el-form-item>
                <el-form-item label="宠物头像">
                    <el-upload class="uploadImage"
                               action="http://www.pet.com/api/image/upload" :on-success="uploadAddSuccess" :on-error="uploadAddError"
                    >
                        <el-button icon="el-icon-circle-plus-outline" type="primary">选取头像</el-button>
                    </el-upload>
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
        name: 'PetManage',
        data(){
            return{
                tableData: [],
                query: {
                    pageIndex: 1,
                    pageSize: 7
                },
                pageTotal: 2,
                addVisible: false,
                editVisible: false,
                form:{
                    id: '',
                    name: '',
                    img: '',
                    age: '',
                    description: '',
                    status: '',
                    userId: ''
                }
            }
        },
        created() {
            this.getData();
        },
        methods: {
            getData(){
                this.$axios.get('/pet/get_all_pet')
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
            formatStatus(status){
                return status === 0?'未被领养':'已被领养';
            },
            handlePetUser(index,row){

            },
            handleAdd(){
              this.addVisible = true;
            },
            saveAdd(){

                let data = {name:this.form.name,description:this.form.description,img:this.form.img,age:this.form.age};
                this.$axios.post('/pet/add_pet',data)
                    .then(res =>{
                        this.$message.success("添加成功!");
                        this.addVisible = false;
                        this.getData();
                    })
                    .catch(res =>{
                        this.$message.error("添加失败!");
                        this.addVisible = false;
                    })
            },
            // 编辑操作
            handleEdit(index, row) {
                this.idx = index;
                this.form = row;
                this.editVisible = true;
            },
            // 保存编辑
            saveEdit() {

                //发送修改请求
                let data = {id:this.form.id,name:this.form.name,description:this.form.description,img:this.form.img,age:this.form.age};
                this.$axios.put('/pet/update_pet',data)
                    .then(res =>{
                        this.$message.success("修改成功!");
                        this.editVisible = false;
                        this.getData();
                    })
                    .catch(res =>{
                        this.$message.error("修改失败!");
                        this.editVisible = false;
                    })
            },
            handleDelete(index,row){

                this.$confirm('确定删除该宠物吗?','提示',{
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(()=>{
                    if (row.status === 1){
                        this.$message.error("该宠物已被领养,无法删除!");
                    }else{
                        let id = row.id;
                        this.$axios.delete('/pet/delete_pet/' + id)
                            .then(res =>{
                                this.$message.success("删除宠物成功!");
                                this.getData();
                            })
                            .catch(res =>{
                                this.$message.error("删除宠物失败!");
                            })
                    }
                }).catch(()=>{

                })
            },
            // 上传图片
            uploadAddSuccess(res){
                console.log(res);
                this.$message.success("上传成功!")
                this.form.img = res.data;
            },
            uploadAddError(res){
                console.log(res);
                this.$message.error("上传失败!");
            },
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
