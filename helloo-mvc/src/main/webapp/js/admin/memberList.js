const frm = document.memberRoleUpdateFrm;
const id = frm.id;
const role = frm.role;


document.querySelectorAll(".member-role").forEach((select)=>{
    select.addEventListener('change',(e)=>{


        //그럼 해당되는 id는 어떻게 찾을 것인가?
        /**
         * dataset
         * - html 태그에 데이터를 저장하는 속성
         *   SETTER
         * - data-xxx (data- 로 시작)
         * - 여러 단어인 경우 단어단위로 - 연결 (대문자x)
         * - data-id , data-user-pet
         *
         *   GETTER
         * - 가져올 땐 태그객체의 dataset 속성으로부터 key값을 참조(이때는 camelCasing)
         */

            //console.log(e.target.dataset);
        const {value} = e.target;
        const {id:idVal} = e.target.dataset;
        // console.log(idVal);
        // console.log(role.value);

        if(confirm(`${idVal}회원을 ${value=='A'?'관리자':'일반회원'}로 변경하시겠습니까?`))
        {
            role.value = value;
            id.value = idVal;
            frm.submit();
        }
        else
        {
            //원래 선택했던 값으로 복귀 (selected 속성이 있는)
            e.target.querySelector("[selected]").selected = true;
        }

    });
});