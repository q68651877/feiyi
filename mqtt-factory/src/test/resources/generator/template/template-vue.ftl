<!-- ${author} created on ${date}-->
<style lang="less">
    @import '../../styles/common.less';

</style>

<template>
    <div class="smart-crud-container">

        <div class="smart-crud-top">
            <Row>
                <Col span="24">
                <Button class="" type="primary" @click="add">添加</Button>
                <Input class="pull-right" v-model="searchModel"
                       icon="ios-search" placeholder="搜索..." style="width: 200px"/>
                </Col>
            </Row>
        </div>
        <br>
        <div class="smart-crud-bottom">
            <Row>
                <Col span="24">
                <Table :loading="loading" :columns="columns" :data="data"></Table>

                <div style="margin: 10px;overflow: hidden">
                    <div style="float: right;">
                        <Page v-model="pageInfo"
                              :current="pageInfo.pageNum"
                              :total="pageInfo.total"
                              :page-size="pageInfo.pageSize"
                              size="small"
                              :page-size-opts="[5,10,15]"
                              @on-change="changePage"
                              @on-page-size-change="changePageSize"
                              show-elevator
                              show-sizer
                              show-total></Page>
                    </div>
                </div>
                </Col>
            </Row>
        </div>

        <Modal
                v-model="editModal"
                width="700"
                :loading="isSaving"
                @on-ok="handleSubmit"
                @on-cancel="handleReset"
                ok-text="保存"
                cancel-text="取消"
                title="编辑${name}">
            ${EditForm}
        </Modal>

        <Modal v-model="deleteModal" width="360">
            <p slot="header" style="color:#f60;text-align:center">
                <Icon type="information-circled"></Icon>
                <span>删除${name}</span>
            </p>
            <div style="text-align:center">
                <p>删除该${name}，将无法恢复！</p>
                <p>是否删除?</p>
            </div>
            <div slot="footer">
                <Button type="error" size="large" long :loading="isDeleting" @click="deleteItem">删除</Button>
            </div>
        </Modal>
    </div>
</template>
<script>
    export default {
        name: '${name}',
        data() {
            return {
                searchModel: undefined,
                ${name}Form: ${editFormData},
                ${name}FormRule: ${editFormDataRule},
                loading: false,
                keepalive: false,
                isSaving: false,
                isDeleting: false,
                pageInfo: {},
                editModal: false,
                deleteModal: false,
                deleteIndex: '',
                columns: ${columns},
                data: []
            }
        },
        methods: {
            getList() {
                this.loading = true;
                const self = this;
                const params = {
                    page: this.pageInfo.pageNum || 1,
                    size: this.pageInfo.pageSize || 10
                };
                this.$http.get('/${name}', params).then((res) => {
                    self.loading = false;
                    if (res.code === 200) {
                        const result = res.data;
                        self.data = result && result.list;
                        self.pageInfo.total = result && result.total;
                    } else {
                        self.$Message.error('获取数据失败！' + res.code);
                    }

                })
            },

            reloadList() {
                this.pageInfo.pageNum = 1;
                this.getList();
            },

            changePage(currentPage) {
                this.pageInfo.pageNum = currentPage;
                this.getList();
            },

            changePageSize(pageSize) {
                this.pageInfo.pageSize = pageSize;
                this.getList();
            },

            add() {
                this.isSaving = false;
                this.${name}Form = ${editFormData};
                this.editModal = true;
            },

            edit(index) {
                this.isSaving = false;
                const self = this;
                this.$refs.${name}Form.resetFields();
                this.editModal = true;
                this.$http.get('/${name}/' + self.data[index].id, {}).then((res) => {
                    if (res.code === 200) {
                        self.${name}Form = res.data;
                    } else {
                        self.$Message.error('获取${name}失败！' + res.code);
                    }

                });
            },

            handleSubmit() {
                this.isSaving = true;
                let self = this;
                this.$refs.${name}Form.validate((valid) => {
                    if (valid) {
                        if (this.${name}Form.id) {
                            this.$http.put('/${name}', self.${name}Form).then((res) => {
                                if (res.code === 200) {
                                    self.isSaving = false;
                                    self.editModal = false;
                                    self.reloadList();
                                    self.$Message.success('更新成功！');
                                } else {
                                    self.$Message.error('更新失败！' + res.code);
                                }
                            })
                        } else {
                            this.$http.post('/${name}', self.${name}Form).then((res) => {
                                if (res.code === 200) {
                                    self.isSaving = false;
                                    self.editModal = false;
                                    self.reloadList();
                                    self.$Message.success('添加成功！');
                                } else {
                                    self.$Message.error('添加失败！' + res.code);
                                }
                            })
                        }
                    } else {
                        self.isSaving = false;
                        self.$Message.error('表单验证失败！');
                    }
                })
            },
            handleReset() {
                this.editModal = false;
            },
            remove(index) {
                this.deleteModal = true;
                this.deleteIndex = index;
                this.isDeleting = false;
            },
            deleteItem() {
                this.isDeleting = true;
                const self = this;
                this.$http.delete('/${name}/' + self.data[self.deleteIndex].id, {}).then((res) => {
                    if (res.code === 200) {
                        self.isDeleting = false;
                        self.deleteModal = false;
                        self.reloadList();
                        self.$Message.success('删除成功！');
                    } else {
                        self.$Message.error('删除失败！' + res.code);
                    }
                });
            }
        },

        created() {
            this.getList();
        },

        activated() {
            if (this.keepalive) {
                this.keepalive = false;
                this.getList();
            }
        },

        deactivated() {
            this.keepalive = true;
        }

    }

</script>
