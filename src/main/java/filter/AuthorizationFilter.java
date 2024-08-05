package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizationFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo nếu cần
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        
        // Giả sử rằng bạn đã lưu trữ vai trò của người dùng trong session
        String role = (session != null) ? (String) session.getAttribute("userRole") : null;
        
        String path = req.getServletPath();
        
        // Kiểm tra quyền truy cập dựa trên vai trò và đường dẫn yêu cầu
        if (role == null || (path.startsWith("/admin") && !"admin".equals(role))) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        
        chain.doFilter(request, response);
    }

    public void destroy() {
        // Hủy bỏ nếu cần
    }
}

