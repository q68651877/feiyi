package com.example.mqttfactorydemo.util;

public enum ApiResultCode {

    //全局异常
    success(200, "SUCCESS"),
    fail(400, "服务异常"),
    unauthorized(401, "未授权或验签失败"),
    unauthorized_developer(401, "授权已过期，请重新登录"),
    not_active(401, "应用未激活"),
    not_found(404, "接口不存在"),
    json_error(405, "JSON参数格式错误"),
    url_time_out(406, "链接已过期，请登录系统进行签署"),
    login_time_out(999, "会话已过期，请重新登录"),

    //    归档接口
    contract_archive_folder_name_is_not_found(450, "文件名不能为空"),
    contract_archive_folder_is_not_found(450, "文件不存在"),
    contract_archive_folder_exist(450, "文件已存在"),

    //    app
    app_not_login(500, "未登陆平台，无操作权限"),
    app_key_error(501, "应用密钥错误"),

    //    签署者
    no_signer_permission(550, "没有签署权限"),

    //模板接口
    template_user_id_notfound(600, "用户不存在"),
    template_id_notfound(601, "模板不存在"),
    template_file_notfound(602, "模板文件不能为空"),
    template_parameter_notfound(603, "模板文件需要替换的占位符不能为空"),
    template_seq_error(604, "合同签署顺序类型不存在"),
    template_file_notsupport(605, "不支持该格式的模板"),
    template_name_notfound(606, "模板名不能为空"),
    template_parameters_format_error(607, "参数格式错误"),

    //实名认证接口
    authenticate_bank_info_notfound(650, "对公账户信息不能为空"),
    authenticate_type_notfound(651, "实名认证类型不存在"),
    authenticate_file_type_notfound(652, "上传照片文件类型错误"),
    authenticate_authenticate_file_type_notfound(653, "上传授权书文件类型错误"),
    authenticate_authenticate_query_parameter_notfound(654, "企业名称或企业工商注册号必须填写一个"),
    authenticate_authenticate_query_verify_notfound(655, "没有需要确认打款金额的申请"),
    authenticate_authenticate_amount_vefify_failed(656, "打款金额验证失败"),
    authenticate_authenticate_notfound(657, "无正在进行中的实名认证申请"),
    authenticate_authenticate_exist(658, "您已提交过实名认证，请至实名认证查询处查看认证结果"),
    authenticate_authenticate_success(659, "实名验证已通过，请勿重复申请"),
    authenticate_registernooragentmobile_notfound(660, "统一社会信用代码或经办人手机号不正确"),
    authenticate_idCardormobile_notfound(661, "身份证号码或手机号不正确"),
    //用户接口t
//    权限
    menu_routerName_exist(680, "菜单代码已存在"),
    menu_name_exist(681, "菜单名称已存在"),

    //用户接口
    user_company_exist(700, "公司已存在"),
    user_id_notfound(701, "userId不能为空"),
    user_personal_exist(702, "个人用户已存在"),
    user_enterprise_exist(703, "企业用户已存在,请确认您的统一社会信用代码"),
    user_not_found(704, "用户不存在"),
    user_name_notfound(705, "姓名不能为空"),
    user_mobile_notfound(706, "联系电话不能为空"),
    user_idcard_notfound(707, "身份证号码不能为空"),
    user_enterprisename_notfound(708, "公司名不能为空"),
    user_enterprisetype_notfound(709, "公司类型不能为空"),
    user_enterprisetype_error(715, "公司类型不存在"),
    user_enterpriseregisteredno_notfound(710, "组织机构代码、工商注册号或者统一社会信用代码不能为空"),
    user_enterpriselegalperson_notfound(711, "公司法人不能为空"),
    user_enterpriselegalpersonidcard_notfound(712, "公司法人身份证不能为空"),
    user_email_notfound(713, "经办人邮箱不能为空"),
    user_idcard_already_exist(714, "身份证号已存在"),
    user_cert_not_exist(716, "用户证书不存在"),
    user_params_info_error(717, "用户证书不存在"),
    user_idcard_error(718, "身份证长度输入错误"),
    user_login_name_exist(719, "登录名已存在"),
    user_verify_error(720, "您提交的信息与实名认证信息不符"),
    user_verify_certification_error(721, "您的实名认证还未审核完毕"),
    user_verify_certification_reject(722, "您的实名认证信息未通过，请前往实名认证页面重新提交"),
    user_agent_exist(723, "经办人已存在"),
    user_legal_person_error(724, "法人姓名与实名认证信息不符"),
    user_legal_idcard_error(725, "法人身份证与实名认证信息不符"),
    user_enterprise_name_error(726, "企业名称与实名认证信息不符"),
    user_register_no_error(727, "企业注册号与实名认证信息不符"),
    user_name_error(728, "用户姓名与实名认证信息不符"),
    user_identitycard_error(729, "用户姓名与实名认证信息不符"),
    user_mobile_error(729, "用户手机号与实名认证信息不符"),
    user_sign_num_zero(730, "签约用户次数已用完"),
    sign_user_num(741, "签约用户次数不应小于已用次数"),
    launch_sign_num(742, "发起签署次数不应小于已用次数"),
    user_owner_notexist(743, "甲方管理员不能为空，请调用获取甲方列表来获取"),

    //合同接口
    contract_user_id_notfound(800, "用户不存在"),
    contract_id_notfound(801, "合同不存在"),
    contract_file_notfound(802, "合同文件不能为空"),
    contract_page_id_notfound(803, "contractPageId不能为空"),
    offset_x_notfound(804, "offset_x不能为空"),
    offset_y_notfound(805, "offset_y不能为空"),
    contract_verify_failed(806, "合同验证失败"),
    contract_not_complete(807, "合同尚未签署完成"),
    contract_not_such_sign_verify(808, "无此种验证类型"),
    contract_sign_verify_notfound(809, "验证类型不能为空"),
    contract_cannot_publish(810, "合同签署已完成,无法发起"),
    contract_signer_notadd(811, "未添加签署者"),
    contract_placeholder_notfound(812, "占位符不存在"),
    contract_seq_notfound(813, "合同签署顺序不存在"),
    contract_signer_notfound(814, "合同签署者不存在"),
    contract_file_notsupport(815, "合同文件类型不支持"),
    contract_hash_error(816, "合同hash_id错误"),
    contract_stamper_notfound(817, "合同占位符不存在"),
    contract_completed(818, "合同已完成"),
    contract_not_exist_page(819, "合同不存在此页"),
    contract_axis_not_found(820, "合同位置不能为空"),
    contract_is_signing(821, "该合同已发起签署，无法添加签署者"),
    word_to_pdf_fail(822, "模板转换失败"),
    contract_initing(823, "合同正在生成中，请稍等"),
    contract_cannot_be_deleted(824, "合同正在使用中，无法被删除"),
    contract_failed(825, "合同生成失败"),
    contract_not_match(826, "合同文件不匹配"),

    //    区块链
    user_contract_secret_not_found(850, "此用户没有权限"),
    user_contract_type_error(851, "类型错误"),
    time_stamp_fail(852, "上链时间戳获取失败"),
    deal_hash_notfound(853, "交易hash不存在"),
    chain_contract_not_match(853, "合同不匹配"),
    chain_signer_not_match(853, "签署者不匹配"),


    //印章接口
    seal_type_not_support(900, "印章类型错误"),
    seal_color_not_supported(901, "印章颜色错误"),
    seal_font_not_supported(902, "印章字体错误"),
    seal_size_not_supported(903, "印章大小错误"),
    seal_name_is_empty(904, "印章名称不能为空"),
    seal_user_uuid_is_empty(905, "用户不能为空"),
    seal_content_is_empty(906, "印章内容不能为空"),
    seal_person_content_is_too_long(907, "私人印章文字太长， 已经超过了10个字符"),
    seal_file_type_error(908, "上传的印章文件类型错误，请上传png, jpg 或者jpeg图片印章"),
    seal_name_is_too_long(909, "印章名称太长， 已经超过了50个字符"),
    seal_remark_is_too_long(910, "印章备注太长， 已经超过了512个字符"),
    seal_company_content_is_too_long(911, "企业印章文字太长， 已经超过了32个字符，您可以上传自定义印章图片"),
    seal_company_serial_is_too_long(912, "企业印章编码太长， 已经超过了13个字符"),
    seal_company_center_content_is_too_long(913, "企业印章中心文字太长， 已经超过了32个字符，您可以上传自定义印章图片"),
    seal_company_horizonal_content_is_too_long(914, "企业印章横排文字太长， 已经超过了32个字符，您可以上传自定义印章图片"),
    seal_status_notfound(915, "印章状态输入错误"),
    seal_id_notfound(916, "印章id不能为空"),
    seal_file_notfound(917, "印章文件不能为空"),
    seal_default_status_error(918, "印章默认状态错误"),
    seal_not_found(919, "印章不存在"),
    person_not_create_companySeal(920, "个人用户只能创建个人章"),
    company_not_create_personSeal(921, "企业用户只能创建企业章"),
    seal_create(922, "印章创建失败"),
    seal_signed(923, "印章存在签署记录，无法删除"),
    default_seal_notfound(933, "操作失败，签署者无默认章"),
    default_seal_notunique(934, "操作失败，签署者发现多个默认章"),
    seal_center_content_notempty(935, "印章中心内容不能为空"),
    seal_not_match(936, "印章与用户不匹配"),
    //证书相关
    generate_certificate_failed(950, "生成证书失败，请重试"),
    certificate_not_found(951, "此用户无证书"),

    validate_token_failed(999, "登录信息已失效"),


    //Web相关
    web_contrat_some_error(1000, "xxx"),
    web_seal_not_sign(1001, "手写签名未完成");


    private Integer code;
    private String msg;

    private ApiResultCode(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    ApiResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
