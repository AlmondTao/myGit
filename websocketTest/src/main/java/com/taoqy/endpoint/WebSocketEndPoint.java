package com.taoqy.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/websocket")
@Component
public class WebSocketEndPoint {
    private static Logger logger = LoggerFactory.getLogger(WebSocketEndPoint.class);
    private Session session;
    @OnOpen
    public void connectionSuccess(Session session){
        this.session = session;
        try {

            String id = session.getId();
            //同步的，必须上一条发送完才能发下一条
            this.session.getBasicRemote().sendText("连接成功");
            //异步
            //            session.getAsyncRemote()

        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.warn("websocket连接成功");
    }

     @OnError
     public void onError(Session session, Throwable error){
                System.out.println("发生错误");
                error.printStackTrace();
        }

    @OnClose
     public void onClose(){
                  //从set中删除
                         //在线数减1
               System.out.println("有一连接关闭！当前在线人数为" );
            }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.warn("接受到信息："+message);
        try {
            session.getBasicRemote().sendText("已接受");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
