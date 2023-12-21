//1220 게시판 작성글 수정



console.log("boardUpdate....... 글 수정하기~~~ ");
document.boardUpdateFrm.addEventListener('submit',()=> {
    const frm = e.target;
    const title = frm.title;
    const content = frm.content;

    //제목 유효성 검사
    if(!/^.+$/.test(title.value.trim()))
    {
        alert('제목을 반드시 작성해주세요.');
        e.preventDefault();
        return; //제출막기..
    }

    //내용 유효성 검사
    //정규표현식의 .에는 \n이 포함되지 않는다.
    if(!/^(.|\n)+$/.test(content.value.trim()))
    {
        alert('내용을 반드시 작성해주세요.');
        e.preventDefault();
        return; //제출막기..
    }

});