/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ktwtr;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ram
 */
public class SigninForm {
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        String login = request.getParameter( "login" );
        String password = request.getParameter( "motPasse" );
    }
}
