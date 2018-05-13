package pia.Filter;

import pia.dao.RightsDao;
import pia.data.Rights;
import pia.data.RightsJ;
import pia.data.User;
import pia.data.UserJ;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Kontroluje zda uzivatel muze uppravit sam sebe ( nepouzito)
 * Created by jakub on 30.12.2016.
 */
@WebFilter(filterName = "ModifSelf")
public class ModifSelf implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        UserJ user;
        if((user = (UserJ) request.getSession().getAttribute("user")) != null){

            RightsJ right = user.getIdRights();

            if(right.getModifSelf() != 1){
                response.sendRedirect("profile");
            }
        }
        else{
            response.sendRedirect("index");
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
