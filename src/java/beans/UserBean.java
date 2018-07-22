/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import data.UserTbl;
import ejb.Web3;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.Id;

/**
 *
 * @author j-knakagami2
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {
    @Id
    private String userId;
    private String userPass,userName,userKana;
    private String userIdMsg,userPassMsg,userNameMsg,userKanaMsg;
    private boolean editable;
    
    @EJB
    private Web3 db;
    
    public UserBean() { }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserKana() {
        return userKana;
    }

    public void setUserKana(String userKana) {
        this.userKana = userKana;
    }

    public String getUserIdMsg() {
        return userIdMsg;
    }

    public void setUserIdMsg(String userIdMsg) {
        this.userIdMsg = userIdMsg;
    }

    public String getUserPassMsg() {
        return userPassMsg;
    }

    public void setUserPassMsg(String userPassMsg) {
        this.userPassMsg = userPassMsg;
    }

    public String getUserNameMsg() {
        return userNameMsg;
    }

    public void setUserNameMsg(String userNameMsg) {
        this.userNameMsg = userNameMsg;
    }

    public String getUserKanaMsg() {
        return userKanaMsg;
    }

    public void setUserKanaMsg(String userKanaMsg) {
        this.userKanaMsg = userKanaMsg;
    }    

    
    public String userAddNext(){
        String nextPage=null;
        UserTbl wUser = new UserTbl();
        wUser.setUserId(userId);
        wUser.setUserPass(userPass);
        wUser.setUserName(userName);
        wUser.setUserKana(userKana);
        try{
        db.createUserTbl(wUser);
        nextPage = "index";
        }catch(Exception ex){
            System.out.println("ユーザ新規登録エラー:" + ex.getMessage());
        }
        return nextPage;
    }
    
    //7月9日　ログイン処理
    public String loginNext(){
        String nextPage = null;
        UserTbl aUser = db.loginUserTbl(userId, userPass);
        if(aUser !=null){
            userName=aUser.getUserName();
            userKana = aUser.getUserKana();
            nextPage = "user";
        }
        return nextPage;
    }
    
    //7月9日 ID重複チェック用リスナーメソッド
    public void findId(){
        editable = false;
        UserTbl wUser = null;
        wUser = db.findUserTbl(userId);
        if(wUser == null){
            userIdMsg="";
            editable = true;
        }
        else
            userIdMsg="登録済みIDです";
    }
}
