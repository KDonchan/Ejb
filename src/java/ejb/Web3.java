/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import data.UserTbl;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author j-knakagami2
 */
@Stateless
public class Web3 {
    @PersistenceContext
    private EntityManager em;
    
    public UserTbl loginUserTbl(String wId , String wPass){
        String wsql="select u from UserTbl u where u.userId=?1 and u.userPass=?2";
        TypedQuery<UserTbl>wquery=  em.createQuery(wsql,UserTbl.class);
        UserTbl wUser=null;
        
        return wUser;
    }
    
    public UserTbl findUserTbl(String wId){        
        UserTbl wUser = em.find(UserTbl.class, wId);
        return wUser;
    }
    
    public void createUserTbl(UserTbl wUser){
        em.persist(wUser);
    }
}
