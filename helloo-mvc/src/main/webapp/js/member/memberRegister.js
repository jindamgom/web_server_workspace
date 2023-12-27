console.log("memberRegister.js........");
//1227 아이디 중복검사
document.querySelector("#id").addEventListener('keyup',(e)=> {
    const value = e.target.value;
    console.log(value);

    //아이디 사용,불가능을 알려주는 span태그들
    const guideOk = document.querySelector(".guide.ok");
    const guideError = document.querySelector(".guide.error");
    const idValid = document.querySelector("#idValid");
    if(/^\w{4,}/.test(value))
    {
       $.ajax({
          url : `${contextPath}/member/checkIdDuplicate`,
          data : {
            id : value
          },
           success(response){
              console.log(response);
              const {result} = response;
              if(result)
              {
                  //아이디 사용가능
                  guideError.classList.add('hidden');
                  guideOk.classList.remove('hidden');
                  idValid.value=1;
              }
              else
              {
                  //아이디 사용불가능
                  guideOk.classList.add('hidden');
                  guideError.classList.remove('hidden');
                  idValid.value=0;
              }
           }
       });
    }
    else
    {
        //지우고 다시쓰기하는 경우
        guideOk.classList.add('hidden');
        guideError.classList.add('hidden');
        idValid.value=0;
    }

});



const hobbyEtc = document.querySelector("#hobby-etc");
hobbyEtc.addEventListener('keyup',(e)=>{
    
    //enter 누른 경우 입력완료로 간주
    if(e.keyCode === 13)
    {
        //자동으로 생겨난 <br>태그 제거, blur 처리
        console.log('keyup13131313');
        //현재 상황: 기타 취미 입력 후 엔터누를 시 개행이 됨
        
        //1.생겨난 <br>제거
        e.target.innerHTML = e.target.innerHTML.replace(/<br>/g,'');
        //2.블러처리
        e.target.blur();
    }
});
hobbyEtc.addEventListener('blur',(e)=> {
    const value = e.target.innerHTML;
    if (value && value != '직접입력') {

    const html = `<div class="inline-flex items-center mr-4">
                            <input id="hobby-${value}" type="checkbox" name="hobby" value="${value}" checked class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 focus:ring-2" >
                            <label for="hobby-${value}" class="ms-2 text-sm font-medium text-gray-900">${value}</label>
                        </div>`;


    /**
     * 특정요소 기준으로 새 요소 추가
     * beforebegin : 시작태그 앞 : 이전형제 요소로 추가
     * afterbegin :시작 태그 뒤 : 첫 자식요소 추가
     * beforeend : 종료태그 앞 : 마지막 자식요소로 추가
     * afterend :종료태그 뒤 : 다음 형제요소로 추가
     * e.target.parentElement: label#hobby-etc를 감싼 div 태그
     */


    e.target.parentElement.insertAdjacentHTML('beforebegin', html);
    e.target.innerHTML = '직접입력'; //다시 원래 메세지를 붙여줌.
    }
});




/**
 * 회원가입 유효성 검사 js 페이지
 */

document.memberRegisterFrm.addEventListener("submit",(e)=>{
    const frm = e.target;
    const id = frm.id;
    const confirmPassword  = document.querySelector("#confirm-password");
    const password = frm.password;
    const name = frm.name;
    const email = frm.email;
    const idValid = frm.idValid;
    console.log(idValid);
    //addEventListener = return false x e.preventDefault() 처리

    //아이디:영문자 숫자 4글자 이상
    if(!/^\w{4,}/.test(id.value))
    {
        alert('아이디는 영 대/소문자 4글자 이상 작성해주세요..')
        e.preventDefault();
    }

    //아이디 중복검사 통과 여부
    if(idValid.value!== "1"){
        alert('사용가능한 아이디를 입력해주세요..');
        e.preventDefault();
        return;
    }




    //비밀번호:영문자 숫자 특수문자 4글자 이상
    const reg_password = [

        {
            re: /[A-Za-z]/,
            msg: '비밀번호는 영문자를 하나 이상 포함해주세요.'
        },
        {
            re: /\d/,
            msg: '비밀번호는 숫자를 하나 이상 포함해주세요.'
        },
        {
            re: /[!@#$%]/,
            msg: '비밀번호는 특수문자 !@#$% 중 하나 이상 포함해주세요.'
        },
        {
            re: /^.{4,}$/,
            msg: '비밀번호는 4글자 이상이여야 합니다.'
        }

    ];

    for(let i=0; i<reg_password.length; i++)
    {
        const{re,msg} = reg_password[i];
        if(!re.test(password.value))
        {
            alert(msg);
            e.preventDefault();
            return;
        }
    }

    if(!/^\w{4,}/.test(password.value))
    {
        alert('패스워드는 영 대/소문자+숫자+특수문자 4글자 이상 ..')
        e.preventDefault();
    }

    //비밀번호 확인
    if(password.value!==confirmPassword.value)
    {
        alert('두 비밀번호가 다릅니다. 다시 확인해주세요');
        e.preventDefault();
        return;

    }

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