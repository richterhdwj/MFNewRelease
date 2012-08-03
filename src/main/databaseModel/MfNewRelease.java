/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.databaseModel;

/**
 *
 * @author hdwjy
 */
public class MfNewRelease {
    private String tableName="T_MFNEW_RELEASE";
    
    private String pid;
    private String name;
    private String typeName;
    private String url;
    private String img;
    private String sysFlag;
    private String createTime;
    private Integer order;
    
    public String[][] getTableModelSet(){
        return new String[][]{
            {"tableName",tableName,"TableName"},
            {"pid","F_PID","mainKey"},
            {"name","F_NAME","String"},
            {"code","F_TYPE_NAME","String"},
            {"password","F_URL","String"},
            {"createTime","F_IMG","String"},
            {"sysFlag","F_SYS_FLAG","String"},
            {"createTime","F_CREATETIME","String"},
            {"order","INTEGER","Integer"}
        };
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSysFlag() {
        return sysFlag;
    }

    public void setSysFlag(String sysFlag) {
        this.sysFlag = sysFlag;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
    
}
