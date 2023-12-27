package com.sh.mvc.photo.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sh.mvc.common.LocalDateTimeSerializer;
import com.sh.mvc.photo.model.entity.Photo;
import com.sh.mvc.photo.model.service.PhotoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

//비동기로 더보기 처리 하는 서블릿단
@WebServlet("/photo/page")
public class PhotoPageServlet extends HttpServlet {

    private PhotoService photoService = new PhotoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.사용자 입력값 처리
        System.out.println("PhotoPageServlet.........");
        int page = Integer.parseInt(req.getParameter("page"));
        final int limit = 5; //한번당 보여줄 사진 수는 5개다.
        Map<String,Object> param = Map.of("page",page,"limit",limit);
        System.out.println(param);

        //2.업무로직
        List<Photo> photos = photoService.findAll(param);
        System.out.println(photos);

        //3.json 응답처리
        resp.setContentType("application/json; charset=utf-8");//json형태로 전송

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        Gson gson = gsonBuilder.create();
        System.out.println("photos:::::::::"+photos);
        gson.toJson(photos,resp.getWriter());
    }
}