package mx.edu.utez.controller;

import com.google.gson.Gson;
import mx.edu.utez.model.category.BeanCategory;
import mx.edu.utez.model.game.BeanGame;
import mx.edu.utez.model.game.DaoGame;
import mx.edu.utez.model.user.DaoUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
@WebServlet(name = "ServletGame", urlPatterns = {"/readGames", "/createGame", "/updateGame", "/deleteGame"})
public class ServletGame extends HttpServlet {
    private Map map = new HashMap();
    final private Logger CONSOLE = LoggerFactory.getLogger(ServletGame.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if(session.getAttribute("session") != null) {
            //request.setAttribute("listGame", new DaoGame().findGame());
            //request.getRequestDispatcher("views/game/game.jsp").forward(request,response);
            map.put("listGame", new DaoGame().findGame());
            write(response,map);

        }
        request.setAttribute("listGame", new DaoGame().findGame());
        request.getRequestDispatcher("views/game/games.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        DaoGame daoGame = new DaoGame();
        BeanGame beanGame = new BeanGame();
        BeanCategory beanCategory = new BeanCategory();

        switch (action) {
            case "create":
                Part part = request.getPart("image");
                InputStream image = part.getInputStream();

                beanCategory.setIdCategory(Integer.parseInt(request.getParameter("Idcategory")));
                beanGame.setNameGame(request.getParameter("name"));
                beanGame.setDatePremiere(request.getParameter("date"));
                beanGame.setCategory_idCategory(beanCategory);

                boolean flag = daoGame.create(beanGame,image);
                if(flag) {
                    map.put("message","Se ha registrado correctamente");
                } else {
                    map.put("message","No se registro correctamente");
                }
                write(response,map);
                break;

            case "update":
                beanCategory.setIdCategory(Integer.parseInt(request.getParameter("Idcategory")));

                beanGame.setIdGame(Integer.parseInt("IdGame"));
                beanGame.setNameGame(request.getParameter("name"));
                beanGame.setDatePremiere(request.getParameter("date"));
                beanGame.setCategory_idCategory(beanCategory);

                boolean flag1 = daoGame.update(beanGame);
                if(flag1) {
                    CONSOLE.error("Se ha registrado correctamente");
                } else {
                    CONSOLE.error("No se registro correctamente");
                }
                break;

            case "delete":
                if(new DaoGame().delete(Integer.parseInt(request.getParameter("idGame")))) {

                } else {

                }
                break;

            default:
                request.getRequestDispatcher("/").forward(request,response);

        }
        response.sendRedirect(request.getContextPath() + "/readGames");
    }
    private  void write(HttpServletResponse response, Map<String, Object> map) throws IOException {
        response.setContentType("aplication/json");
        response.getWriter().write( new Gson().toJson(map));

    }
}
