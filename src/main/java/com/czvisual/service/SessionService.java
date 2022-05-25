package com.czvisual.service;

import com.czvisual.entity.User;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

@Service
public class SessionService {
    @Autowired
    private static DefaultWebSessionManager sessionManager;

    public static boolean stopSessionByUserid(int id){
        Collection<Session> collection =  sessionManager.getSessionDAO().getActiveSessions();
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
