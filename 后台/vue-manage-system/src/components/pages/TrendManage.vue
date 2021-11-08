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

                <el-table-column prop='dynamicId' label='动态id' align='center' min-width='50'></el-table-column>
                <el-table-column prop='pName' label='发布者' align='center' min-width='50'></el-table-column>
                <el-table-column prop="message" label="动态标题" min-width="120" align="center"></el-table-column>
                <el-table-column prop="content" label="动态内容" min-width='120' align='center'></el-table-column>
                <el-table-column prop='time' label='发布时间' min-width='100' align='center'></el-table-column>

                <el-table-column label="操作" min-width='120' align="center">
                    <template slot-scope="scope">
                        <!--<el-button
                            type="text"
                            icon="el-icon-edit"
                            class='font'
                            @click="handleEdit(scope.$index, scope.row)"
                        >封禁</el-button>-->
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
    </div>
</template>

<script>
    export default {
        name: 'TrendManage',
        data(){
            return{
                tableData: [],
                query: {
                    pageIndex: 1,
                    pageSize: 7
                },
                pageTotal: 2,
            }
        },
        created() {
            this.getData();
        },
        methods: {
            getData(){
                this.$axios.get('/trend/get_trend')
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
            // 编辑操作
            handleEdit(index, row) {
            },
            // 保存编辑
            saveEdit() {
            },
            handleDelete(index,row){

                let id = row.dynamicId;
                this.$confirm('确定删除该动态吗?','提示',{
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(()=>{

                    this.$axios.delete('/trend/delete_trend/' + id)
                        .then(res =>{
                            this.$message.success("删除动态成功!");
                            this.getData();
                        })
                        .catch(res =>{
                            this.$message.error("删除动态失败!");
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
