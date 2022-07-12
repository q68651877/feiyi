package com.example.mqttfactorydemo.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.smart.util.JsonDateDeserializer;
import com.smart.util.JsonDateSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "tbl_sys_log")
public class SysLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 动作,在code表里面配置，如申请调用服务、申请下载资源、提交接入开通申请等
     */
    private String action;

    /**
     * 操作人
     */
    @Column(name = "action_user_id")
    private Long actionUserId;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 类型。OPEN:开通申请；ACCESS:接入申请；SERVICE:申请服务；DATA:申请数据
     */
    private String type;

    @Column(name = "ref_id")
    private Long refId;

    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "last_modified_by")
    private Long lastModifiedBy;

    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

    private Integer version;

    @Transient
    private String operator;

    @Transient
    private String actionDesc;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取动作,在code表里面配置，如申请调用服务、申请下载资源、提交接入开通申请等
     *
     * @return action - 动作,在code表里面配置，如申请调用服务、申请下载资源、提交接入开通申请等
     */
    public String getAction() {
        return action;
    }

    /**
     * 设置动作,在code表里面配置，如申请调用服务、申请下载资源、提交接入开通申请等
     *
     * @param action 动作,在code表里面配置，如申请调用服务、申请下载资源、提交接入开通申请等
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 获取操作人
     *
     * @return action_user_id - 操作人
     */
    public Long getActionUserId() {
        return actionUserId;
    }

    /**
     * 设置操作人
     *
     * @param actionUserId 操作人
     */
    public void setActionUserId(Long actionUserId) {
        this.actionUserId = actionUserId;
    }

    /**
     * 获取备注信息
     *
     * @return remarks - 备注信息
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注信息
     *
     * @param remarks 备注信息
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return created_date
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return created_by
     */
    public Long getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     */
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return last_modified_by
     */
    public Long getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * @param lastModifiedBy
     */
    public void setLastModifiedBy(Long lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * @return last_modified_date
     */
    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * @param lastModifiedDate
     */
    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * @return version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    public SysLog(String action, Long actionUserId, String remarks, String type, Long refId) {
        this.action = action;
        this.actionUserId = actionUserId;
        this.remarks = remarks;
        this.type = type;
        this.refId = refId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public SysLog() {
    }

    public String getActionDesc() {
//        if (StringUtils.isEmpty(this.actionDesc) && this.action != null) {
//            CodeService codeService = (CodeService) SpringUtils.getBean(Constants.BeanName.CODE_SERVICE);
//            this.actionDesc = codeService.getCodeDesc(FundConstants.code.SYS_LOG, this.action);
//        }
        return actionDesc;
    }

    public void setActionDesc(String actionDesc) {
        this.actionDesc = actionDesc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }
}
