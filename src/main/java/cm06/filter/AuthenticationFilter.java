package cm06.filter;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//urlPattern : Đường dẫn client gội sẽ kích hoạt filter
@WebFilter(filterName = "authenFilter", urlPatterns = {"/user-add", "/login"})
public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//Lấy link servlet mà client đang gọi
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		boolean isExist = false;
		
		// Lấy cookies từ request và kiểm tra null
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cook : cookies) {
                String nameCookie = cook.getName();
                if (nameCookie.equals("logined")) {
                    isExist = true;
                }
            }
        }
		
		String context = req.getContextPath();
		String path = req.getServletPath();
		
		switch (path) {
		case "/user-add": 
			if(isExist) {
				chain.doFilter(request, response);
			} else {
				resp.sendRedirect(context + "/login");
			}
			break;
		
		case "/login":
			if(isExist) {
				resp.sendRedirect(context + "/user-add");
			} else {
				chain.doFilter(request, response);
			}
			break;
			
		default:
		    System.out.println("Kiem tra thong tin thay link");	
		}
		
//		//cho phép đi tiếp
//		chain.doFilter(request, response);
	}

}
