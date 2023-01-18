package com.javaweb.bank.web.servlet;

import com.javaweb.bank.exceptions.MoneyNotEnoughException;
import com.javaweb.bank.utils.DBUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "AccountTransferServlet", value = "/AccountTransferServlet")
public class AccountTransferServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        double money = Double.parseDouble(request.getParameter("money"));


        // Connect database and query all department
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getConnection();
            String sql = "select banlance from t_act where actno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,fromActno);
            resultSet = preparedStatement.executeQuery();


            if (resultSet.next()){
                double balance = resultSet.getDouble("balance");
                if(balance < money){
                    throw new MoneyNotEnoughException("No enough money");
                }

            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(connection,preparedStatement,resultSet);
        }



    }
}
