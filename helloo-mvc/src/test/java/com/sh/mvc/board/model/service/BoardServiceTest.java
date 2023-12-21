package com.sh.mvc.board.model.service;
import com.sh.mvc.board.model.entity.Board;
import com.sh.mvc.board.model.vo.BoardVo;
import com.sh.mvc.member.model.entity.Gender;
import com.sh.mvc.member.model.entity.Member;
import com.sh.mvc.member.model.entity.Role;
import org.junit.jupiter.api.*;

import javax.swing.border.Border;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


/**
 * 1215 Board Test
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BoardServiceTest {

    BoardService boardService; //test 대상(fixture)

    @BeforeEach
    public void beforeEach()
    {
        this.boardService = new BoardService();
    }

    //첫 테스트는 해당 객체가 존재 하는지 아닌지 부터 확인하는 것.

    /**
     * myBatis는 ResultSet의 데이터를 vo클래스개체로 자동변환한다.
     * 컬럼명과 필드 명이 일치 해야한다.(언더스코어->카멜케이싱) mapUnderscoreToCamelCase
     * varchar2 / char <-> String
     * number          <-> int/double
     * date            <-> java.util.Date(기본값), java.time.localDate
     * number(0 or 1)  <-> boolean
     * varchar2 / char <-> Enum#name
     */
    @DisplayName("boardService 객체는 null이 아니다.")
    @Test
    public void test0() {
        assertThat(boardService).isNotNull();

    }

    @Disabled
    @DisplayName("게시글 전체 조회를 할 수 있다. ")
    @Test
    public void test1() {
        List<Board> boards = boardService.findAll();
        //반환타입은 리스트이다.
        assertThat(boards)
                .isNotNull()
                .isNotEmpty();
        //Consumer 타입 람다식:매개변수가 하나 있고, 리턴타입은 없음.
        boards.forEach((board) ->
        {
            System.out.println(board);
            assertThat(board.getId()).isNotNull(); //게시글 id(pk)
            assertThat(board.getTitle()).isNotNull(); //게시글 제목
            assertThat(board.getContent()).isNotNull(); //게시글 내용
            assertThat(board.getMemberId()).isNotNull(); //게시글 작성자
            assertThat(board.getReadCount()).isNotNull(); //게시글 조회수
            assertThat(board.getRegDate()).isNotNull(); //게시글 작성날짜
        });
    }

    @Disabled
    //게시글 한건 조회 BoardService#findById(id:long):Board
    @DisplayName("게시글 조회 시 게시글 고유 id로 특정 게시글을 조회할 수 있다. ")
    @Test
    public void test2() {
        long id = 10;
        Board board = boardService.findById(id); //

            System.out.println(board);
            assertThat(board.getId()).isNotNull(); //게시글 id(pk)
            assertThat(board.getTitle()).isNotNull(); //게시글 제목
            assertThat(board.getContent()).isNotNull(); //게시글 내용
            assertThat(board.getMemberId()).isNotNull(); //게시글 작성자
            assertThat(board.getReadCount()).isNotNull(); //게시글 조회수
            assertThat(board.getRegDate()).isNotNull(); //게시글 작성날짜
    }

    @Disabled
    @DisplayName("board table에 새 게시글을 추가 할 수 있다.")
    @Test
    public void test3()
    {
        BoardVo board = new BoardVo();
//        BoardVo board =
//                new Board(0, "Test용 타이틀입니다!", "농담곰","농담곰귀여워", 0, LocalDateTime.now());

        Throwable th = catchThrowable(()->
        {
            int result = boardService.insertBoard(board);
        });
        //오류가 던져졌는지 확인
        assertThat(th).isInstanceOf(Exception.class);
    }

    @Disabled
    //BoardService#updateBoard(board:Board):int
    @DisplayName("board table의 게시글을 수정 할 수 있다. 제목/내용 수정")
    @Test
    public void test4()
    {
        //수정할 제목과 내용 임시 지정
        String title = "수정할수있습니끄아아악";
        String content = "수정가능합니다ㅋㅋ";

        Board board = boardService.findById(30);

        //위에서 만든 값으로 set해주고..
        board.setTitle(title);
        board.setContent(content);

        System.out.println(board);
        //update 쿼리를 날리러..service 일시키기.
        int result = boardService.updateBoard((BoardVo) board);
        assertThat(result).isGreaterThan(0);
    }

    @Disabled
    @DisplayName("게시글 삭제")
    @Test
    public void test5()
    {
        //게시글 고유 id로 삭제하기
        long myBoardId = 40;
        Board board = boardService.findById(myBoardId);
        assertThat(board).isNotNull(); //삭제 전 null 확인

        int result = boardService.deleteBoard(myBoardId);
        assertThat(result).isGreaterThan(0);
    }

    @DisplayName("전체 게시글 수 조회 count(*)")
    @Test
    public void test6()
    {
        int boardTotalCount = boardService.getTotalCount();
        System.out.println("게시글 전체 갯수 : " + boardTotalCount);
    }

    //게시글 페이징 조회
    //BoardService#findAll(param:Map<String, Object>):List<Board>
    @DisplayName("게시글 페이징 조회")
    @Test
    public void test7()
    {
        int page = 1;
        int limit = 15; //한 페이지당 글 15개씩 보이도록 지정함.
        Map<String,Object> param = Map.of("page",page,"limit",limit);
        List<BoardVo> boards = boardService.findAll(param);
        assertThat(boards)
                .isNotNull()
                .isNotEmpty();
        //작성날짜 최근 기준으로 15개 출력
        boards.forEach((board) ->
        {
            System.out.println(board);
            assertThat(board.getId()).isNotNull(); //게시글 id(pk)
            assertThat(board.getTitle()).isNotNull(); //게시글 제목
            assertThat(board.getContent()).isNotNull(); //게시글 내용
            assertThat(board.getMemberId()).isNotNull(); //게시글 작성자
            assertThat(board.getReadCount()).isNotNull(); //게시글 조회수
            assertThat(board.getRegDate()).isNotNull(); //게시글 작성날짜
        });


    }
}