document.querySelector("#btn-celeb").addEventListener('click',(e)=>
{
    $.ajax({
        url:`${contextPath}/json/celeb/findAll`,
        success(celebs){
            //응답받은 json 데이터를 파싱(json.parse)후 , js 객체로 반환.
            console.log(celebs);
            const tbody = document.querySelector("#celebs tbody");
            tbody.innerHTML= '';

            celebs.forEach(({id,name,profile,type})=>
            {
                tbody.innerHTML +=`
                    <tr>
                        <td>${id}</td>
                        <td>${name}</td>
                        <td><img src="${contextPath}/images/celeb/${profile}"></td>
                        <td>${type}</td>
                        <td><button>수정</button></td>
                        <td><button>삭제</button></td>
                    </tr>`;

            });
        }
    });
});

//폼을 동기적으로 제출하는 것을 방지
//document.celebSearchForm.addEventListener('submit',(e)=> e.preventDefault());

document.querySelector("#btn-celeb-search").addEventListener('click',(e)=>{

    const celebId = document.querySelector("#id").value;
    console.log("내가 검색한 아이디 값 : "+celebId);

    $.ajax({
        url:`${contextPath}/json/celeb/findById`,
        data:{
            id:id.value
        },
        success(celeb){
            //응답받은 json 데이터를 파싱(json.parse)후 , js 객체로 반환.
            console.log("celebs :"+celeb);
            if(celeb)
            {
                const {id,name,profile,type} = celeb;
                const table = document.querySelector("table#celeb");
                table.querySelector(".celeb-id").innerHTML=id;
                table.querySelector(".celeb-name").innerHTML=name;
                table.querySelector(".celeb-profile").innerHTML = `<img src="${contextPath}/images/celeb/${profile}"/>`;
                table.querySelector(".celeb-type").innerHTML=type;
            }
            else
            {
                alert(`해당하는 celeb가 없습니다.`);
            }
        }
    });
});

/**
 *  submit 버튼을 눌러 submit 이벤트는 발생하지만 
 *  form 제출(동기적)은 아닌 비동기적으로 요청 처리
 *
 *  비동기 파일업로드
 *  -form[enctype=multipart/form-data]에 상응하는 jQuery.ajax 설정
 *   -FormData 객체 사용
 *   -contentType : false
 *   -processData : false
 */
document.celebRegisterFrm.addEventListener("submit",(e)=>
{
    e.preventDefault();//동기적 제출 방지

    const frm = e.target;
    const frmData = new FormData(frm); //input태그의 사용자 입력값이 모두 등록이 된다.

    $.ajax({
        url : `${contextPath}/json/celeb/register`,
        method: 'post',
        data : frmData,
        contentType : false, //기본값:application/x-www-form-urlencoded 처리하지 않음
        processData: false, //직렬화처리 하지 않음.multipart로 처리
       success(response)
       {
           console.log(response);
           const {msg} = response;
           alert(msg);
       },
        complete() {
           frm.reset();
        }
    });
});