package servlet;

import entity.Commodity;
import entity.User;
import entity.myCommodity;
import org.junit.Test;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class LoginServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User loginUser = userService.login(new User(userName, password));
        if (loginUser == null) {
            request.setAttribute("msg", "用户或密码错误！");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute(KAPTCHA_SESSION_KEY);
        session.removeAttribute(KAPTCHA_SESSION_KEY);
        String code = request.getParameter("code");
        if(token!=null && token.equalsIgnoreCase(code)){
            session.setAttribute("userName", userName);
            showCommodity(request, response);
        }else {
            request.setAttribute("msg", "验证码错误！");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }




    }

    protected void showCommodity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Commodity> commodities = userService.showCommodity();
        request.setAttribute("commodities", commodities);
        request.getRequestDispatcher("/pages/login_success.jsp").forward(request, response);
    }

    protected void myCommodity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = session.getAttribute("userName") + "";
        List<myCommodity> myCommodities = userService.myCommodity(userName);



        request.setAttribute("myCommodities", myCommodities);
        request.getRequestDispatcher("/pages/myCommodities.jsp").forward(request, response);

    }

    protected void addCommodity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = session.getAttribute("userName") + "";
        String commodityId = request.getParameter("addCommodityId");
        userService.addCommodity(commodityId, userName);
        showCommodity(request, response);

    }

    protected void deleteCommodity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = session.getAttribute("userName") + "";
        String id = request.getParameter("deleteCommodityId");
        userService.deleteCommodity(id, userName);
        myCommodity(request, response);
    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        int register = userService.register(new User(userName, password));
        if (register==1){




            request.setAttribute("registerSuccess","注册成功，请登录!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else if(register==0){
            request.setAttribute("error","用户名已存在");
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
        }else if(register==2){
            request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
        }

    }


    protected void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("userName", "");
        request.setAttribute("useName", "");
        request.setAttribute("password", "");
        request.getRequestDispatcher("/index.jsp").forward(request, response);


    }


}













