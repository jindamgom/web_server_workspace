/**
 * 1213
 * 회원정보 수정 유효성 검사 후 정보 수정.
 *
 */


document.memberUpdateFrm.addEventListener("submit",(e)=>{
    const frm = e.target;
    const name = frm.name;
    const email = frm.email;

    //이름:한글 2글자 이상
    const reg_name = /[가-힣]{2,}/;
    if(!reg_name.test(name.value))
    {
        alert('이름은 한글 2글자 이상 작성하세요!');
        e.preventDefault();
        return;

    }
    //이메일 양식에 맞게
    if(!/^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/.test(email.value))
    {
        alert('양식에 맞는 이메일을 작성해주세요.');
        e.preventDefault();
        return;
    }

});

function memberDelete()
{
    console.log("function memberDelete");
}