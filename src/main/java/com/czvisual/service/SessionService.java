package com.czvisual.service;

import com.czvisual.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

@Service
public class SessionService {

    public  boolean stopSessionByUserid(int id){
        Collection<Session> collection = ((DefaultWebSessionManager)((DefaultWebSecurityManager)SecurityUtils.getSecurityManager()).getSessionManager()).getSessionDAO().getActiveSessions();
        Iterator<Session> it = collection.iterator();
        while(it.hasNext()){
            Session current = it.next();
            if(((User)current.getAttribute("user")).getId() == id){
                current.stop();
                return true;
            }
        }
        return false;
    }
}
