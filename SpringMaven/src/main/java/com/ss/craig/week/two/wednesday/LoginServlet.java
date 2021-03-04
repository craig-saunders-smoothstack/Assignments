package com.ss.craig.week.two.wednesday;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String hardcoded_username = "craig.saunders@smoothstack.com";
    private static final String hardcoded_password = "java-feb-2021";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet()
    {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.getWriter().append("This Servlet does not take get requests.");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = request.getParameter("SpringMaven-Username");
        String password = request.getParameter("SpringMaven-Password");
        PrintWriter writer = response.getWriter();
        if (hardcoded_username.equals(username) && hardcoded_password.equals(password))
        {
            writer.println("<h2>Thank you " + username + "</h2>");
            writer.println("<h3>You are now logged in!</h3>");
        }
        else
        {
            writer.println("<h2>Wrong username or password!</h2>");
            writer.println("<h3>Please go back and try again.</h3>");
        }
        writer.close();
    }

}
