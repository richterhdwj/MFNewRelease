/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.databaseModel.MfNewRelease;
import support.ClientKeyWord;

/**
 *
 * @author Richter
 */
public class DataBaseCenter {
    public static void main(String[] args){
       DataBaseCenter dbc = new DataBaseCenter();
       dbc.setInDataBase();
    }
    
    /**
     * 将查询到的数据保存到数据库中
     */
    private void setInDataBase(){
        ClientKeyWord ckw=new ClientKeyWord();
        
        String title=ckw.getUrlTitle(null);
        
        List<String[]> newBook=ckw.getUrlContact(null, null);
        for(String[] books:newBook){
            MfNewRelease mf=new MfNewRelease();
            mf.setName(books[0]);
            mf.setUrl(books[1]);
            mf.setImg(books[2]);
            mf.setTypeName(title);
            try {
                mf.save();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
