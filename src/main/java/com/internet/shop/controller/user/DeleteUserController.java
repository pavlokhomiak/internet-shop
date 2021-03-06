package com.internet.shop.controller.user;

import com.internet.shop.lib.Injector;
import com.internet.shop.service.ShoppingCartService;
import com.internet.shop.service.UserService;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users/delete")
public class DeleteUserController extends HttpServlet {
    private final UserService userService
            = (UserService) Injector.getInstance(UserService.class);
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) Injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String id = req.getParameter("id");
        long userId = Long.parseLong(id);
        long shoppingCartId = shoppingCartService.getByUserId(userId).getId();
        shoppingCartService.delete(shoppingCartId);
        userService.delete(userId);
        resp.sendRedirect(req.getContextPath() + "/users/all");
    }
}
