package com.sh.ajax.json;

import com.google.gson.Gson;
import com.sh.ajax.celeb.model.entity.Celeb;
import com.sh.ajax.celeb.model.service.CelebService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/json/celeb/findAll")
public class JsonCelebFindAllServlet extends HttpServlet
{
    private CelebService celebService = new CelebService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.사용자 입력 값 처리

        //2.업무 로직
        List<Celeb> celebs = celebService.findAll();

        //3.응답 처리 : 자바 컬렉션 데이터를 json으로 변환 후에 출력 by gson

        //header : 미디어타입:application/json
        resp.setContentType("application/json; charset=utf-8");

        /**
         * Gson
         * toJson : java->json
         * fromJson : json->java
         */
//        String jsonData = new Gson().toJson(celebs);
//        System.out.println(jsonData);
//        resp.getWriter().print(jsonData);
        //위의 세 줄을 아래 한줄로.
        new Gson().toJson(celebs, resp.getWriter());
    }
}