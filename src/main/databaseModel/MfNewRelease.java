/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.databaseModel;

import java.util.List;
import support.DateBean;
import support.database.DataBaseManager;

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
            {"typeName","F_TYPE_NAME","String"},
            {"url","F_URL","String"},
            {"img","F_IMG","String"},
            {"sysFlag","F_SYS_FLAG","String"},
            {"createTime","F_CREATETIME","String"},
            {"order","F_ORDER","Integer"}
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
    
    public void save() throws Exception{
        DataBaseManager dbm=new DataBaseManager();
        List oldData=dbm.selectObject(this.getClass(),"where t.F_NAME ='"+name+"' and t.F_TYPE_NAME ='"+typeName+"' and t.f_sys_flag='1'");
        if(oldData.size()>0){
            MfNewRelease mfr =(MfNewRelease) oldData.get(0);
            mfr.setName(name);
            mfr.setTypeName(typeName);
            mfr.setImg(img);
            mfr.setUrl(url);
            dbm.save(mfr);
            return;
        }
        
        Integer order=0;
        oldData=dbm.selectObject("select COALESCE(max(t.f_order)+1,0) as num from T_MFNEW_RELEASE t"
                + " where t.F_TYPE_NAME ='"+typeName+"' and t.f_sys_flag='1'");
        if(oldData.size()>0)
            order=Integer.parseInt(((Object[])oldData.get(0))[0].toString());
        
        this.setOrder(order);
        this.setCreateTime(DateBean.getSysdateTime());
        this.setSysFlag("1");
        dbm.save(this);
    }
}
