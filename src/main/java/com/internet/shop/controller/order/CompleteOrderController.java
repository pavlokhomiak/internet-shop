package com.internet.shop.controller.order;

import com.internet.shop.controller.LoginController;
import com.internet.shop.lib.Injector;
import com.internet.shop.model.Order;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.service.OrderService;
import com.internet.shop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/orders/complete")
public class CompleteOrderController extends HttpServlet {
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) Injector.getInstance(ShoppingCartService.class);
    private final OrderService orderService
            = (OrderService) Injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(LoginController.USER_ID);
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        Order order = orderService.completeOrder(shoppingCart);
        req.setAttribute("orderId", order.getId());
        req.getRequestDispatcher("/WEB-INF/views/orders/orderCreated.jsp").forward(req, resp);
    }
}
