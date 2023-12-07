package com.sh.menu;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet 클래스
 * 웹 요청 처리하는 자바클래스
 *
 */
@WebServlet("/menuOrder.do")
public class order extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        //인코딩처리
        req.setCharacterEncoding("utf-8");
        System.out.println("메뉴 서블릿 호출");


        String mainMenu = req.getParameter("mainMenu");
        String sideMenu = req.getParameter("sideMenu");
        String drinkMenu = req.getParameter("drinkMenu");


        System.out.println(mainMenu);
        System.out.println(sideMenu);
        System.out.println(drinkMenu);

        //사용자가 선택한 주메뉴,사이드메뉴,음료메뉴에 따라 합계를 계산한다.
        int totalPrice=0;
//        String mainPrice = "";
//        String sidePrice = "";
//        String drinkPrice = "";
//        switch(mainMenu)
//        {
//            case "한우버거":
//                mainPrice="5000";
//                break;
//            case "밥버거":
//                mainPrice="4500";
//                break;
//            case "치즈버거":
//                mainPrice="4000";
//                break;
//        }
//
//        switch(sideMenu)
//        {
//            case "감자튀김":
//                sidePrice="1500";
//                break;
//            case "어니언링":
//                sidePrice="1700";
//                break;
//        }
//
//        switch(drinkMenu)
//        {
//            case "콜라":
//                drinkPrice="1000";
//                break;
//            case "사이다":
//                drinkPrice="1000";
//                break;
//            case "커피":
//                drinkPrice="1500";
//                break;
//            case "밀크쉐이크":
//                drinkPrice="2500";
//                break;
//        }


        //아래의 스위치문을 사용하려면 default문을 반드시 사용해야한다.
        //안써보니 에러났음.
        int mainPrice =
                switch(mainMenu)
                {
                    case "한우버거"->5000;
                    case "밥버거"->4500;
                    case "치즈버거"->4000;
                    default -> 0;
                };
        int sidePrice =
                switch(sideMenu)
                {
                    case "감자튀김"->1500;
                    case "어니언링"->1700;
                    default -> 0;
                };
        int drinkPrice =
                switch(drinkMenu)
                {
                    case "콜라","사이다"->1000;
                    //case "사이다"->1000;
                    case "커피"->1500;
                    case "밀크쉐이크"->2500;
                    default -> 0;
                };

        totalPrice = mainPrice+sidePrice+drinkPrice;
                //Integer.parseInt(mainPrice)+Integer.parseInt(sidePrice)+Integer.parseInt(drinkPrice);
        req.setAttribute("totalPrice", totalPrice);
        //jsp포워딩 (RequsetDispatcher) = send
        //jsp 경로작성
        //파일경로는 절대경로로.. src/main/webapp(웹 루트) 이하부터 작성..
        RequestDispatcher reqDispatcher =
                req.getRequestDispatcher("/WEB-INF/views/menuEnd.jsp");
        reqDispatcher.forward(req,resp);

    }
}
