console.log("login js......................");

//로그인 페이지 진입 시
const cb_saveId = document.querySelector("#saveId");
let checkYn =null;
cb_saveId.addEventListener('change',(e)=>
{
    checkYn = e.target.checked;
    console.log("체크여부"+checkYn);
});
if(localStorage.getItem('saveId')!=null)
{
    console.log("저장된 id가 존재합니다.");
    const savedId = localStorage.getItem("saveId");
    document.getElementById("id").value = savedId;
    //그리고 체크박스 checked 상태로..
    cb_saveId.checked = true;

}
//로그인 버튼 눌렀을 때
document.loginFrm.addEventListener("submit",(e)=>{
    const lFrm = e.target;
    const userId = lFrm.id;
    console.log("userid:: "+userId.value);
    
    //체크를 했다면 현재 입력한 아이디를 저장하고
    if(cb_saveId.checked)
    {
        //key:saveId - value: userId
        localStorage.setItem("saveId",userId.value);
    }
    //체크하지 않은 상태라면 제거해버린다.
    else
    {
        localStorage.clear();
    }
});