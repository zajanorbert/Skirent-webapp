package com.codecool.web.servlet;

import com.codecool.web.dao.ProductDao;
import com.codecool.web.dao.database.DatabaseProductDao;
import com.codecool.web.model.Product;
import com.codecool.web.service.ProductService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimpleProductSevice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/products")
public class ProductsServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        resp.setContentType("text/html;charset=UTF-8");
        try(Connection connection = getConnection(req.getServletContext())){
            ProductDao productDao = new DatabaseProductDao(connection);
            ProductService productService = new SimpleProductSevice(productDao);
            List<Product> productList = productService.findAll();
            sendMessage(resp, HttpServletResponse.SC_OK, productList);
        }catch (SQLException e ){
            handleSqlError(resp, e);
        }catch (ServiceException e ){
            sendMessage(resp,HttpServletResponse.SC_UNAUTHORIZED,e.getMessage());
        }
    }
}
