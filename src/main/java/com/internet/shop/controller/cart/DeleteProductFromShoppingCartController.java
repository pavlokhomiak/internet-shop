package com.internet.shop.controller.cart;

import com.internet.shop.controller.LoginController;
import com.internet.shop.lib.Injector;
import com.internet.shop.model.Product;
import com.internet.shop.model.ShoppingCart;
import com.internet.shop.service.ProductService;
import com.internet.shop.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/shopping-carts/products/delete")
public class DeleteProductFromShoppingCartController extends HttpServlet {
    private final ShoppingCartService shoppingCartService =
            (ShoppingCartService) Injector.getInstance(ShoppingCartService.class);
    private final ProductService productService
            = (ProductService) Injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long userId = (Long) req.getSession().getAttribute(LoginController.USER_ID);
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);
        String productId = req.getParameter("id");
        long id = Long.parseLong(productId);
        Product product = productService.getById(id);
        shoppingCartService.deleteProduct(shoppingCart, product);
        resp.sendRedirect(req.getContextPath() + "/shopping-carts/products/");
    }
}
