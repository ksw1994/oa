/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bootdo.common.exception;

/**
 * @author fengshuonan
 * @Description 所有业务异常的枚举
 * @date 2016年11月12日 下午5:04:51
 */
public enum BizExceptionEnum {

    /**
     * 公共
     */
    DELETE_ERROR(400,"删除错误"),
    INSERT_ERROR(400,"添加错误"),
    SELECT_ERROR(400,"查询错误"),
    UPDATE_ERROR(400,"修改错误"),

    /**
     * 字典
     */
    DICT_EXISTED(400, "字典已经存在"),
    ERROR_CREATE_DICT(500, "创建字典失败"),
    ERROR_WRAPPER_FIELD(500, "包装字典属性失败"),
    ERROR_CODE_EMPTY(500, "字典类型不能为空"),

    /**
     * 文件上传
     */
    FILE_READING_ERROR(400, "FILE_READING_ERROR!"),
    FILE_NOT_FOUND(400, "FILE_NOT_FOUND!"),
    UPLOAD_ERROR(500, "上传图片出错"),
    FILE_DOES_NOT_EXIST_ERROR(501,"文件不存在"),
    
    FILE_EXCEL_NULL_ERROR(500,"Excel信息为空"),

    /**
     * 权限和数据问题
     */
    DB_RESOURCE_NULL(400, "数据库中没有该资源"),
    NO_PERMITION(405, "权限异常"),
    REQUEST_INVALIDATE(400, "请求数据格式不正确"),
    INVALID_KAPTCHA(400, "验证码不正确"),
    CANT_DELETE_ADMIN(600, "不能删除超级管理员"),
    CANT_FREEZE_ADMIN(600, "不能冻结超级管理员"),
    CANT_CHANGE_ADMIN(600, "不能修改超级管理员角色"),

    /**
     * 账户问题
     */
    USER_ALREADY_REG(401, "该用户已经注册"),
    NO_THIS_USER(400, "没有此用户"),
    USER_NOT_EXISTED(400, "没有此用户"),
    ACCOUNT_FREEZED(401, "账号被冻结"),
    OLD_PWD_NOT_RIGHT(402, "原密码不正确"),
    PWD_NOT_EXISTED(403, "密码不能为空"),
    TWO_PWD_NOT_MATCH(405, "两次输入密码不一致"),
    SHIRO_VERIFICATION_ROLE_ERROR(406, "登录角色异常"),

    ID_NUMBER_ERR(407, "该身份证已经注册"),

    /**
     * 错误的请求
     */
    MENU_PCODE_COINCIDENCE(400, "菜单编号和副编号不能一致"),
    EXISTED_THE_MENU(400, "菜单编号重复，不能添加"),
    DICT_MUST_BE_NUMBER(400, "字典的值必须为数字"),
    REQUEST_NULL(400, "请求有错误"),
    SESSION_TIMEOUT(400, "会话超时"),
    SERVER_ERROR(500, "服务器异常"),

    /**
     * token异常
     */
    TOKEN_EXPIRED(700, "token过期"),
    TOKEN_ERROR(700, "token验证失败"),

    /**
     * 签名异常
     */
    SIGN_ERROR(700, "签名验证失败"),

    /**
     * 其他
     */
    AUTH_REQUEST_ERROR(400, "账号密码错误"),
    AUTH_SQL_ERROR(401, "sql执行异常"),
    /**
     * API 接口
     */
    API_SUCCESS(200,"请求成功"),
    API_SYSTEM_ERROR(800,"系统错误"),
    API_PARAM_ERROR(801,"参数错误"),
    //登录错误
    API_LOGIN_ROLE_ERROR(901,"登录角色错误"),
    API_LOGIN_ERROR(902,"登录异常"),

    //OSS错误信息
    API_OSS_UPLOADOBJECT2OSS_ERROR(1001,"上传阿里云OSS服务器异常"),
    API_OSS_PARAM_ERROR(1002,"缺少所需的参数、用户ID和文件类型"),

    /**
     * excel 错误
     */
    API_EXCEL_ERROR(1101,"Excel 操作错误"),
    API_EXCEL_TEMPLATE_ERROR(1102,"Excel 模板不能为空"),
    API_EXCEL_NOT_NULL_ERROR(1103,"Excel 文件不能为空"),

    /**
     * 定时任务
     */
    TIME_CRON_ERROR(1201, "时间表达式出错"),

    /**
     * redis 异常
     */
    REDIS_SERVER_ERROR(1301, "redis服务异常"),

    /**
     * email 异常
     */
    EMAIL_SEND_ERROR(1401, "email发送失败"),

    /**
     * 更新排期异常
     */
    UPDATE_SCHEDULE_ERROR(1501, "更新排期信息失败"),

    /**
     * 提交预约信息异常
     */
    SUBMIT_APPONTMENT_NULL_ERROR(1601, "排期标识和预约标识和代理商标识不能为空!"),

    /**
     * 视频异常信息
     */
    VIDEO_THUMB_REPEAT(1701, "请勿重复点赞!"),
    VIDEO_THUMB_DELETE(1702, "无删除数据!"),
    
    /**
     * 视频异常信息
     */
    DORM_BED_NO(1801, "该宿舍床位不足！！"),

    /**
     * 查找直营代理异常
     */
    DEF_AGENT_NOT_FOUND(1801, "找不到默认的直营代理，请联系管理员确认！"),

    /**
     * saas 化异常
     */
    SAAS_CUSTID_ERROR(1901, "您输入的邀请码有误，请联系提供的人员确认"),

    ;

    BizExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
