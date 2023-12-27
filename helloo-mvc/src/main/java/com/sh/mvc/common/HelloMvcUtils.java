package com.sh.mvc.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


public class HelloMvcUtils {
    /**
     * <pre>
     *  암호화
     *  1.MessageDigest (암호화)
     *    - 단방향 암호화 알고리즘 sha512
     *    - salt 값을 사용해서 보안성 높이기..
     *  2.Encoding (이진데이터 to Text)
     *
     *
     *
     *
     *
     * </pre>
     */
    public static String getEncryptedPassword(String password, String salt)
    {
        String encryptedPassword = null;
        //1.암호화 (hasing)
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            //입력한 비밀번호를 byte배열로 변환한다.
            byte[] input = password.getBytes("UTF-8");
            //소금치기(재료:사용자 아이디)
            byte[] saltInput = salt.getBytes("UTF-8");
            //먹인다.
            md.update(saltInput);
            //소화된 결과물을 확인한다.ㅋ
            byte[] output = md.digest(input);//이진데이터가 나옴

            //2.인코딩 (encoding) 단순 변환작업
            /**
             * 컴퓨터 분야에서 쓰이는 Base 64란.. 8비트 이진 데이터를 문자 코드에 영향을 받지 않는
             * 공통 ASCII 영역의 문자들로만 이루어진 일련의 문자열로 바꾸는 인코딩 방식을 가리키는 개념이다.
             * 영대소문자(52개)
             * 숫자(10개)
             * +, / (2개)
             * padding(=)
             * 
             * 암호화된 결과 = 해쉬
             */
            Base64.Encoder encoder = Base64.getEncoder();
            encryptedPassword = encoder.encodeToString(output);
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
        {
            throw new RuntimeException(e);
        }

        return encryptedPassword;
    }


    static String previous = """
            <li>
                        <a href="%s" class="flex items-center justify-center px-3 h-8 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                            <span class="sr-only">Previous</span>
                            <svg class="w-2.5 h-2.5 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 1 1 5l4 4"/>
                            </svg>
                        </a>
                    </li> """;

    /**
     * svg란? : scalable vector graphic 태그..
     * 도형 등을 수학적으로 그려서 스케일에 관계 없이 선명한 그림 작성
     */

    static String previousDisabled = """
            <li>
                        <a href="#" class="flex items-center justify-center px-3 h-8 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg cursor-not-allowed dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                            <span class="sr-only">Previous</span>
                            <svg class="w-2.5 h-2.5 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                                <path stroke="currentColor" style="stroke:#9095a0ab" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 1 1 5l4 4"/>
                            </svg>
                        </a>
                    </li>
            """;

    static String currentPageNo = """
            <li>
                <a href="%s" aria-current="page" class="z-10 flex items-center justify-center px-3 h-8 leading-tight text-blue-600 border border-blue-300 bg-blue-50 hover:bg-blue-100 hover:text-blue-700 dark:border-gray-700 dark:bg-gray-700 dark:text-white">%d</a>
            </li>""";

    
    //현재 페이지가 아닌
    static String notCurrentPageNo = """
            <li>
                <a href="%s" class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">%d</a>
            </li>
            """;

    static String next = """
             <li>
                        <a href="%s" class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                            <span class="sr-only">Next</span>
                            <svg class="w-2.5 h-2.5 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 9 4-4-4-4"/>
                            </svg>
                        </a>
                    </li>""";

    static String nextDisabled = """
           <li>
                        <a href="#" class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg cursor-not-allowed dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
                            <span class="sr-only">Next</span>
                            <svg class="w-2.5 h-2.5 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
                                <path stroke="currentColor" style="stroke:#9095a0ab" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 9 4-4-4-4"/>
                            </svg>
                        </a>
                    </li>
            """;
    /**
     *
     * 2.pagebar
     * - page : 현재 페이지
     * - limit : 한 페이지당 표시할 개체 수 (ex 한 페이지에 10개, 20개 등
     * - totalContent : 전체 개체수
     * - totalPage : 전체 페이지수
     * - pagebarSize : 페이지바의 숫자개수 (10개씩 보이고싶다, 5개씩 보이고싶다.
     * - pageNo : 페이지 증감변수
     * - pagebarStart | pagebarEnd : 페이지 증감변수의 범위
     * - url : 요청 url
     *
     */

    public static String getPagebar(int page, int limit, int totalCount, String url)
    {
        StringBuilder pagebar = new StringBuilder();

        //mvc/admin/memberList
        //mvc/admin/searchMember?search-type=xxx&search-keyword=yy
        //검색 이후 바뀐 url에도 페이징하기 위해 3항연산자 사용
        url += (url.contains("?")) ? "&page=" : "?page=";

        //전체 페이지수..
        int totalPage = (int)Math.ceil((double)totalCount/limit);
        System.out.println("전체 페이지 수 : "+ totalPage);
        int pagebarSize = 5;
        //1 2 3 4 5
        //6 7 8 9 10
        //11 12 13 14 15

        int pagebarStart = (page-1) / pagebarSize * pagebarSize+1;
        int pagebarEnd= pagebarStart+ pagebarSize-1 ; //limit
        int pageNo = pagebarStart;

        //1.이전 버튼
        if(pageNo ==1)
        {
            //비활성화 이전 , 페이지가 1번째면 더 이상 이전으로 갈 페이지가 없으므로 비활성화.
            pagebar.append(previousDisabled);
        }
        else {
            //활성화 이전 즉, 이전페이지로 갈 수있는 버튼
            pagebar.append(previous.formatted(url+(pageNo-1)));
            //mvc/admin/memberList?/page=5
        }

        //2.페이지 넘버
        //11,12,13,14,15 페이지까지 만들었는데 12페이지 까지 필요할 때, 아래의 and 연산자 사용
        while(pageNo <= pagebarEnd && pageNo <= totalPage)
        {
            if(pageNo == page)
            {
                //현재 페이지
                pagebar.append(currentPageNo.formatted(url+pageNo,pageNo));
            }
            else {
                //현재 페이지 아닌, 그냥 페이지
                pagebar.append(notCurrentPageNo.formatted(url+pageNo,pageNo));
            }
            pageNo++;
        }

        //3.다음 버튼
        if(pageNo>totalPage)
        {
            //비활성화 다음(더 이상 넘어갈 페이지가 없을 때, 비활성화)
            pagebar.append(nextDisabled);
        }
        else {
            //활성화 다음
            pagebar.append(next.formatted(url+pageNo)); //여기서 +1안하는 이유 위에 while에서 이미 해당 pageno+1값이기때문에
        }




        return pagebar.toString();

    }

    //1219 개행문자를 <br>로 변환하는 메소드
    public static String convertLineFeedToBr(String str)
    {
        return str.replaceAll("\n","<br/>");
    }

    public static String escapeHtml(String str) {

        return str.replaceAll("<","&lt;").replaceAll(">","&gt;");
    }
}
