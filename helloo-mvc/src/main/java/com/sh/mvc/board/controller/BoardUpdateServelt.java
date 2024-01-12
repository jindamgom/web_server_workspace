package com.sh.mvc.board.controller;

import com.sh.mvc.board.model.entity.Attachment;
import com.sh.mvc.board.model.service.BoardService;
import com.sh.mvc.board.model.vo.BoardVo;
import com.sh.mvc.member.model.entity.Member;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/board/BoardUpdate")
public class BoardUpdateServelt extends HttpServlet
{
    BoardService boardService = new BoardService();

    //기존의 값을 읽어오고
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.사용자 입력값 처리
        long id = Long.parseLong(req.getParameter("id"));
        System.out.println("BoardUpdateServelt : "+id);

        //2.업무로직
        BoardVo board = boardService.findById(id);
        req.setAttribute("board",board);

        //3.forwarding
        req.getRequestDispatcher("/WEB-INF/views/board/boardUpdate.jsp").forward(req,resp);

    }


    //수정한 값을 update하고.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BoardVo board = new BoardVo();

        DiskFileItemFactory factory = new DiskFileItemFactory();
        //절대 주소로 지정(상대주소x)
        File repository = new File("C:\\Workspaces\\web_server_workspace\\helloo-mvc\\src\\main\\webapp\\upload\\board");
        factory.setRepository(repository);
        factory.setSizeThreshold(10 * 1024 * 1024); //10MB

        ServletFileUpload s = new ServletFileUpload(factory);
        //1.사용자 입력값 처리 및 파일 업로드
        try {
            //전송된 값을 하나의 FileItem으로 처리
            // //input[name=title] intput[name=content] input[name=upFile] input[name=upFile]
            //위의 인풋태그들 값이 FileItem 객체 하나하나가 됨.
            List<FileItem> fileItemList = s.parseRequest(req);
            for(FileItem item: fileItemList)
            {
                String name = item.getFieldName();//input의 name
                //그리고 아래의 if문으로 formfield인지 아닌지 체크
                if(item.isFormField())
                {
                    String value = item.getString("utf-8");
                    System.out.println(name+"/"+value);
                    board.setValue(name,value);
                    //setValeu역할 setName, setContent..
                }
                else
                {
                    //파일 : 서버 컴에 저장 파일정보를 attachment 객체로 만들어서 db에 저장
                    if(item.getSize() > 0) {
                        String originalFileName = item.getName();
                        int dotIndex = originalFileName.lastIndexOf(".");
                        String ext = dotIndex >- 1? originalFileName.substring(dotIndex) : "";

                        UUID uuid = UUID.randomUUID();//랜덤 uuid 발급 b9c2bc5f-9a0c-4e7f-9d87-7d6feb70e244
                        String renamedFileName = uuid+ext; //저장된 파일명(덮어쓰기방지,인코딩이슈방지) 위에 발급된 uuid에 원본의 확장자명을 붙여서 새 파일명만든다.
                        System.out.println("새 파일명 : "+renamedFileName);

                        //Attachment 객체 생성
                        Attachment attach = new Attachment();
                        attach.setOriginalFilename(originalFileName);
                        attach.setRenamedFilename(renamedFileName);
                        board.addAttachment(attach);

                        //서버 컴퓨터 파일 저장
                        File upFile = new File(repository,renamedFileName);
                        item.write(upFile);
                    }
                }
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        System.out.println(board);



        //2.업무 로직
        int result = boardService.updateBoard(board);
        req.getSession().setAttribute("msg","게시글을 성공적으로 수정하였습니다");


        //3.게시판 목록페이지로 redirect
        //resp.sendRedirect(req.getContextPath()+"/board/BoardDetail?id="+board.getId());



    }
}