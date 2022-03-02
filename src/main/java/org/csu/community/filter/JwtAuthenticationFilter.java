package org.csu.community.filter;

import org.csu.community.utils.JwtUtil;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.PathMatcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final PathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            if(isProtectedUrl(request)){
                if(!request.getMethod().equals("OPTIONS")){
                    request = JwtUtil.validateTokenAndAddUserIdToHeader(request);
                    System.out.println(request);
                }
            }
        }catch (Exception e){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getMessage());
            return;//不将异常请求通过过滤器
        }
        //对前端请求通过解析和重构后，向下一步转发
        System.out.println("解析后的token是"+request.getHeader(JwtUtil.USER_NAME));
        filterChain.doFilter(request,response);
    }

    private boolean isProtectedUrl(HttpServletRequest request){
        List<String> protectedPaths;
        protectedPaths = new ArrayList<>();
        protectedPaths.add("/ums/user/info");
        protectedPaths.add("/post/create");
        protectedPaths.add("/post/update");
        protectedPaths.add("/post/delete/*");
        protectedPaths.add("/comment/add_comment");
        protectedPaths.add("/relationship/subscribe/*");
        protectedPaths.add("/relationship/unsubscribe/*");
        protectedPaths.add("/relationship/validate/*");
        boolean bFind = false;
        for (String passedPath:protectedPaths){
            bFind = pathMatcher.match(passedPath,request.getServletPath());
            if (bFind){
                break;
            }
        }
        return bFind;
    }

//    static {
//        protectedPaths = new ArrayList<>();
//        protectedPaths.add("/ums/user/info");
//        protectedPaths.add("/post/create");
//        protectedPaths.add("/post/update");
//        protectedPaths.add("/post/delete/*");
//        protectedPaths.add("/comment/add_comment");
//        protectedPaths.add("/relationship/subscribe/*");
//        protectedPaths.add("/relationship/unsubscribe/*");
//        protectedPaths.add("/relationship/validate/*");
//    }
}
