package kr.sparta.livevoting.websocket;

import kr.sparta.livevoting.jwt.JwtUtil;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
public class StompHandler implements ChannelInterceptor {
    private final JwtUtil jwtUtil;

    public StompHandler(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String bearerToken = accessor.getFirstNativeHeader("Authorization");

            if (bearerToken != null) {

                String token = jwtUtil.resolveToken(bearerToken);
                Long userId = jwtUtil.getUserId(token);

                accessor.setUser(new StompPrincipal(userId));
            }
        }
        return message;
    }
}
