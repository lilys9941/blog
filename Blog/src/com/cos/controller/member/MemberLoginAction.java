package com.cos.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.action.Action;
import com.cos.dao.MemberDAO;
import com.cos.dto.MemberVO;
import com.cos.util.SHA256;
import com.cos.util.Script;

public class MemberLoginAction implements Action {
   private static String naming = "MemberLoginAction : ";
   @Override
   public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String url = "index.jsp";
      
      MemberDAO dao = new MemberDAO();
      MemberVO member = new MemberVO();

      String id = request.getParameter("id");
      String salt = "";
      if(dao.select_salt(id)!= null)salt=dao.select_salt(id);
      System.out.println(salt);
      String password = request.getParameter("password");
      password = SHA256.getEncrypt(password, salt);
      member.setId(id);
      member.setPassword(password);

    
      
      int result = dao.login(member);
      if(result == 1) {
         HttpSession session = request.getSession();
         session.setAttribute("id", member.getId());
         Script.moving(response, "로그인 되었습니다.", url);
      }else if(result == -1) {
         System.out.println(naming + "SQL error");
         Script.moving(response, "데이터베이스 에러");
      }else if(result == 2) {
          HttpSession session = request.getSession();
          session.setAttribute("id", member.getId());
          Script.moving(response, "email 인증을 하지 않았습니다.", url);
       }

   }
}