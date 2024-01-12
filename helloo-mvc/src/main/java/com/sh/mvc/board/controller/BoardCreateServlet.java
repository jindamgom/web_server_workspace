package com.sh.mvc.board.controller;

import com.sh.mvc.board.model.entity.Attachment;
import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.service.BoardService;
import com.sh.mvc.board.model.vo.BoardVo;
import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.entity.Role;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@WebServlet("/board/boardCreate")
public class BoardCreateServlet extends HttpServlet
{
    private BoardService boardService = new BoardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/board/boardCreate.jsp").forward(req,resp);
    }

    /**
     * 파일 업로드 처리..
     * 1.commons-io,commons-fileupload 의존성 추가(pom.xml)
     * 2.form[method=post][enctype=multipart/form-data]
     * 3.DiskFileItemFacroty / ServletFileUpload 요청처리
     *  - 저장경로
     *  - 파일 최대 크기
     * 
     * 
     * 
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.사용자 입력값 처리 및 파일 업로드
        File repository = new File("C:\\Workspaces\\web_server_workspace\\helloo-mvc\\src\\main\\webapp\\upload\\board");
        int sizeThreshold = 10 * 1024 * 1024; //10MB

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(repository);
        factory.setSizeThreshold(sizeThreshold);

        BoardVo board = new BoardVo();


        //실제 요청을 핸들링할 객체..
        ServletFileUpload s = new ServletFileUpload(factory);
        try {
            //전송된 값을 하나의 FileItem으로 처리
            List<FileItem> fileItemList = s.parseRequest(req);
            for(FileItem item: fileItemList)
            {
                String name = item.getFieldName();//input의 name
                if(item.isFormField()){
                    //일반 텍스트 : board 객체에 설정

                    String value = item.getString("utf-8");
                    System.out.println("board - ServletFileUpload:  "+name+"/"+value);
                    //Board객체에 설정자 로직 구현..
                    board.setValue(name,value);

                }
                else {
                    //파일 : 서버 컴에 저장 파일정보를 attachment 객체로 만들어서 db에 저장
                    if(item.getSize() > 0) {
                        System.out.println(name);
                        String originalFileName = item.getName();
                        System.out.println("원본 파일명: " + originalFileName);
                        System.out.println("파일크기: " + item.getSize() + "byte");

                        int dotIndex = originalFileName.lastIndexOf(".");
                        String ext = dotIndex >- 1? originalFileName.substring(dotIndex) : "";
                        System.out.println("extextextextextext: " + ext ); //.png

                        UUID uuid = UUID.randomUUID();//랜덤 uuid 발급 b9c2bc5f-9a0c-4e7f-9d87-7d6feb70e244
                        String renamedFileName = uuid+ext; //저장된 파일명(덮어쓰기방지,인코딩이슈방지) 위에 발급된 uuid에 원본의 확장자명을 붙여서 새 파일명만든다.
                        System.out.println("새 파일명 : "+renamedFileName);

                        //서버 컴퓨터 파일 저장
                        File upFile = new File(repository,renamedFileName);
                        item.write(upFile);

                        //Attachment 객체 생성
                        Attachment attach = new Attachment();
                        //현재 할 수 있는건 원본명과, 새이름 set.

                        attach.setOriginalFilename(originalFileName);
                        attach.setRenamedFilename(renamedFileName);
                        board.addAttachment(attach);


                    }
                }
            }
        } 
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        System.out.println("board+attach : " +board);//board객체,attach객체 모두 확인되어야함.


        //2.업무로직
        int result = boardService.insertBoard(board);
        req.getSession().setAttribute("msg","게시글을 성공적으로 등록하였습니다ㅎㅎㅎㅎ");

        //3.게시판 목록페이지로 redirect
        resp.sendRedirect(req.getContextPath()+"/board/boardList");
    }
}