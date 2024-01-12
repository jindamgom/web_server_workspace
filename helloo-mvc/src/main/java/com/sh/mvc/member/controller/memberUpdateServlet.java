package com.sh.mvc.member.controller;

import com.sh.mvc.member.model.entity.Gender;
import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.entity.Role;
import com.sh.mvc.member.model.service.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@WebServlet("/member/memberUpdate")
public class memberUpdateServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.인코딩 설정
        req.setCharacterEncoding("utf-8");

        //2.사용자 입력값 가져오기
        Member loginMember = (Member)req.getSession().getAttribute("loginMember"); //return type:object라 앞에 member 형변환
        String id1 = loginMember.getId(); //이런식으로도 ..가져올 수 있다!

        String id = req.getParameter("id"); //수정,읽기용으로 한번 가져옴 update....where=id;
        System.out.println("memberUpdateServlet do post id"+id);
        String _name = req.getParameter("name"); //이름
        String _birthday = req.getParameter("birthday"); //생년월일
        String _gender = req.getParameter("gender");
        String _email = req.getParameter("email"); //메일
        String _phone = req.getParameter("phone"); //핸드폰번호
        String[] _hobby = req.getParameterValues("hobby"); //복수개라 getParameterValues
        System.out.println(_name+"/"+_birthday+"/"+_gender+"/"+_email+"/" +_phone+"/"+ Arrays.toString(_hobby));

        //2.5
        //input:date는 text계열이라 작성하지않아도 빈문자열("")이 전송된다.
        //그래서 빈문자열인지 아닌지 검사해줘야 한다.
        //기존 필수입력 속성 지우고 테스트해보면 빈문자열로 전송됨.
        LocalDate birthday = _birthday !=null && !"".equals(_birthday) ?
                    LocalDate.parse(_birthday, DateTimeFormatter.ISO_DATE) : null;

        Gender gender = _gender != null ? Gender.valueOf(_gender):null;

        List<String> hobby = _hobby != null ? Arrays.asList(_hobby) : null;




        //기존 멤버 값에 새 값으로 setter 수정한다.
        //나같은 경우엔 null값인 경우를 고려 안했는데 위의 삼항연산자로 변경했다.
        MemberService memberService = new MemberService();
        Member member = memberService.findById(id);
        member.setName(_name);
        member.setGender(gender);
        member.setBirthday(birthday);
        member.setEmail(_email);
        member.setPhone(_phone);
        member.setHobby(hobby);

        //아래로 퉁침
//        Member member = new Member
//                (id,null,_name, null, gender, birthday, _email,_phone,hobby,0,null);


        //3.업무로직 : 세션에 저장된 값과 db의 값이 일치하는지..?
        //db정보가 성공적으로 수정되었다면 해당내용으로 session 속성 loginMember 업데이트해줘야! 반영이됨
        Member memberUpadted = memberService.findById(id);
        req.getSession().setAttribute("loginMember",member);

        //4.redirect : /mbc/memeber/memberDetail
        resp.sendRedirect(req.getContextPath()+"/member/memberDetail");
    }


}