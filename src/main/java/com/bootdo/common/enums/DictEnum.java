package com.bootdo.common.enums;

/**
 * 数据字典 
 * @program: agentServer
 * @description: 数据字典枚举类
 * @author: caopeilun  
 * @create: 2019-02-01 13:23
 **/
public enum DictEnum {
    USER_STATE_CD("userStateCd","用户状态"),
    ;

    private String pCode;

    private String name;

    DictEnum(String pCode, String name) {
        this.pCode = pCode;
        this.name = name;
    }

    public String getpCode() {
		return pCode;
	}

	public String getName() {
        return name;
    }
}
