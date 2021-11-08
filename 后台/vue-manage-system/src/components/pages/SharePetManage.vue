<template>
    <div class='container'>
        <div class='border'>
            <el-table
                :data="tableData.slice((this.query.pageIndex-1)*(this.query.pageSize),(this.query.pageIndex)*(this.query.pageSize))"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
            >

                <el-table-column prop='petId' label='宠物id' align='center' min-width='100'></el-table-column>
                <el-table-column prop="userId" label="领养人id" min-width="100" align="center"></el-table-column>
                <el-table-column prop="startTime" label="领养开始时间" min-width='100' align='center'></el-table-column>
                <el-table-column prop="endTime" label="领养结束时间" min-width='100' align='center'></el-table-column>
                <el-table-column prop="idCard" label="身份信息" min-width='100' align='center'></el-table-column>

                <el-table-column label="操作" min-width='120' align="center">
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
                            class="red font"s
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
        <el-dialog title="添加共享宠物信息" :visible.sync="addVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="用户名">
                    <el-input v-model="form.userName"></el-input>
                </el-form-item>
                <el-form-item label="密码">
                    <el-input v-model="form.passWord"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveAdd">确 定</el-button>
            </span>
        </el-dialog>

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑共享宠物信息" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="用户编号">
                    <el-input v-model="form.userId"></el-input>
                </el-form-item>
                <el-form-item label="开始日期">
                    <el-date-picker
                        v-model="form.startTime"
                        type="date"
                        placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="结束日期">
                    <el-date-picker
                        v-model="form.endTime"
                        type="date"
                        placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="身份信息">
                    <el-input v-model="form.idCard"></el-input>
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
        name: 'SharePetManage',
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
                    petId: '',
                    userId: '',
                    startTime: '',
                    endTime: '',
                    idCard: ''
                }
            }
        },
        created() {
            this.getData();
        },
        methods: {
            getData(){
                this.$axios.get('/pet/get_share_pet')
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

                console.log(this.form);
                /*let data = { userName: this.form.userName,passWord :this.form.passWord};
                console.log(this.form);
                //发送添加用户请求
                this.$axios.post('/user/add_user',this.$qs.stringify(data)
                ).then(res =>{
                    this.$message.success("添加用户成功!");
                    this.addVisible = false;
                    this.getData();
                }).catch(res =>{
                    this.$message.error("添加用户失败!");
                    this.addVisible = false;
                });*/
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

                let userId = this.form.userId;
                let petId = this.form.petId;
                console.log(petId);
                let startTime = this.dateFormat(this.form.startTime);
                let endTime = this.dateFormat(this.form.endTime);
                let idCard = this.form.idCard;
                let data = {petId:petId,userId:userId,startTime:startTime,endTime:endTime,idCard: idCard};
                console.log(data);
                this.$axios.put('/pet/update_share_pet',data)
                    .then(res =>{
                        this.$message.success("修改共享宠物信息成功!");
                        this.editVisible = false;
                        this.getData();
                    })
                    .catch(res =>{
                        this.$message.error("修改共享宠物信息失败!");
                        this.editVisible = false;
                    });
            },
            handleDelete(index,row){

                this.$confirm('确定删除该用户吗?','提示',{
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(()=>{
                    console.log(row);
                    let userId = row.userId;
                    let petId = row.petId;
                    this.$axios.delete('/pet/delete_share_pet/' + userId + "/" + petId)
                        .then(res =>{
                            this.$message.success("删除用户成功!");
                            this.getData();
                        })
                        .catch(res =>{
                            this.$message.error("删除用户失败!");
                        })

                }).catch(()=>{

                })
            },
            // 上传图片
            uploadAddSuccess(res){
                console.log(res);
                this.$message.success("上传成功!")
                this.form.head = res.data;
                console.log(this.form.head);
            },
            uploadAddError(res){
                console.log(res);
                this.$message.error("上传失败!");
            },

            dateFormat(dateData) {
                let date = new Date(dateData)
                let y = date.getFullYear()
                let m = date.getMonth() + 1
                m = m < 10 ? ('0' + m) : m
                let d = date.getDate()
                d = d < 10 ? ('0' + d) : d
                const time = y + '-' + m + '-' + d
                return time
            }
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
