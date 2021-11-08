<template>
    <div class='container'>
        <div class='border'>
            <el-table
                :data="tableData.slice((this.query.pageIndex-1)*(this.query.pageSize),(this.query.pageIndex)*(this.query.pageSize))"
                border
                class="table"
                ref="multipleTable"
                header-cell-class-name="table-header"
                @selection-change=""
            >
                <el-table-column prop="name" label="用户姓名" min-width="50" align="center"></el-table-column>
                <el-table-column  label="用户性别" min-width='50' align='center'>
                    <template slot-scope='scope'>
                        {{sexFormat(scope.row.sex)}}
                    </template>
                </el-table-column>
                <el-table-column label="用户头像" align="center" min-width='50'>
                    <template slot-scope="scope">
                        <el-image
                            class="table-td-thumb"
                            :src="scope.row.head"
                            :preview-src-list="[scope.row.head]"
                        ></el-image>
                    </template>
                </el-table-column>
                <el-table-column prop="email" label="邮箱" min-width='100' align='center'></el-table-column>
                <el-table-column prop="address" label="地址" min-width='100' align='center'></el-table-column>

                <el-table-column label="操作" min-width='120' align="center">
                    <template slot-scope="scope">
                        <el-button
                            type="text"
                            icon="el-icon-edit"
                            class='font'
                            @click="handleEdit(scope.$index, scope.row)"
                        >编辑</el-button>
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

        <!-- 编辑弹出框 -->
        <el-dialog title="编辑用户详细信息" :visible.sync="editVisible" width="30%">
            <el-form ref="form" :model="form" label-width="70px">
                <el-form-item label="姓名">
                    <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="性别">
                    <el-radio-group v-model="form.sex">
                        <el-radio label='0' >男</el-radio>
                        <el-radio label='1' >女</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="form.email"></el-input>
                </el-form-item>
                <el-form-item label="头像">
                    <el-upload class="uploadImage"
                               action="http://www.pet.com/api/image/upload" :on-success="uploadAddSuccess" :on-error="uploadAddError"
                    >
                        <el-button icon="el-icon-circle-plus-outline" type="primary">选取头像
                        </el-button>
                    </el-upload>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input v-model="form.address"></el-input>
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
        name: 'UserManage',
        data(){
            return{
                tableData: [],
                query: {
                    pageIndex: 1,
                    pageSize: 7
                },
                pageTotal: 2,
                editVisible: false,
                form:{
                    id: '',
                    name: '',
                    sex: 0,
                    head: '',
                    email: '',
                    address: ''
                }
            }
        },
        created() {
            this.getData();
        },
        methods: {
            getData(){
                this.$axios.get('/user/get_all_info')
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
            sexFormat(sex){
                return sex === '0' ? '男':'女';
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
                let data = {id:this.form.id,name:this.form.name,sex:this.form.sex,head:this.form.head,email:this.form.email,address:this.form.address};
                this.$axios.put('/user/update_user_info',this.$qs.stringify(data))
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
