/**
 *  댓글 등록 폼..
 */

/**
 * 이벤트 버블링속성 자식->부모로 퍼짐
 * 을 이용해서 최상위 document객체에 submit 핸들러 연결해보기.
 * 폼이 제출되기 전 submit 이벤트가 발생하고, 상위로 전파(bubbling)되어서 이 핸들러 호출
 * 즉, 동적으로 뒤늦게 만들어지는 핸들러도 이 방식으로 호출
 * 기존:document.boardCommentCreateFrm.addEventListener
 * 수정:document.addEventListener
 */
document.addEventListener('submit',(e)=> {
    
    //분기
    //정적으로 생성된 폼 과 동적으로 생성된 폼을 가리지 않고 모두 적용.

    if(e.target.matches("[name=boardCommentCreateFrm]")) //is:jQuery, js:matches
    {
        const frm = e.target;
        const memberId = frm.memberId;
        const content = frm.content;
        if (!memberId.value) {
            alert('로그인 후 댓글을 작성해주세요🐾');
            e.preventDefault();
            return;

        }
        if (!/^(.|\n)+$/.test(content.value.trim()))
        {
            alert('댓글 내용을 작성해주세요💌');
            e.preventDefault();
            return;
        }
    }


});

/**
 * 답글 버튼 클릭 핸들러
 */

document.querySelectorAll(".btn-reply").forEach((button)=>{
    button.addEventListener('click',(e)=>
    {
        console.log(e.target.value);
        console.log("데이타셋", e.target.dataset); //button.btn-reply의 data속성
        const parentCommentId=e.target.value;
        //data-set활용해서 boardId,memberId등을 받아옴 [전역변수도 써도되긴하지만 이 방법도있다!!]
        const {contextPath, boardId, loginMemberId} = e.target.dataset;
        /*
        * jsp가 아닌 js라 el을 사용할 수 없다 아래의 ${boardId}는 el이 아니라 단순히 변수값을 받을 수 있는 역할만함.
        *
        * */

        //대댓글 입력 폼 tr
        const html = `
              <tr>
                <td colspan="4">
                  <form name="boardCommentCreateFrm" action="${contextPath}/board/boardCommentCreate" method="post">
                    <input type="hidden" name="boardId" value="${boardId}">
                    <input type="hidden" name="memberId" value="${loginMemberId}">
                    <input type="hidden" name="commentLevel" value="2"> 
                    <input type="hidden" name="parentCommentId" value="${parentCommentId}">
                    
                    <div class="flex items-center px-3 py-2 bg-white hover:bg-gray-50 border-b">
                        <textarea id="content" name="content" required rows="1" class="resize-none block mx-4 p-2.5 w-full text-sm text-gray-900 bg-white rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500" placeholder="답글을 작성하세요..."></textarea>
                        <button type="submit" class="inline-flex justify-center p-2 text-blue-600 rounded-full cursor-pointer hover:bg-blue-100">
                          <svg class="w-5 h-5 rotate-90 rtl:-rotate-90" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 18 20">
                              <path d="m17.914 18.594-8-18a1 1 0 0 0-1.828 0l-8 18a1 1 0 0 0 1.157 1.376L8 18.281V9a1 1 0 0 1 2 0v9.281l6.758 1.689a1 1 0 0 0 1.156-1.376Z"/>
                          </svg>
                        </button>
                    </div>
                  </form>
                </td>
              </tr>
        `;

        const tr  = e.target.parentElement.parentElement;
        console.log(tr); //해당 답글 버튼이 속한 tr
        tr.insertAdjacentHTML('afterend',html);
        
        //beforebegin:이전 형제요소로 추가
        //afterbegin:첫번째 자식요소로 추가
        //beforeend:마지막자식요소
        //afterend:다음형제요소
        
        
        
    },{
        once:true //한번만 실행..
    })
})