/**
 * 웹소켓 작성
 * -endPoint(/echo)로 최초 웹 소켓 연결 요청.(echo말고 다른 이름으로 해도 ok)
 *
 */
const ws = new WebSocket(`ws://${location.host}${contextPath}/echo`);

/**
 * 핸들러 바인딩
 */
ws.addEventListener('open',(e)=>{
   console.log('open',e);
});

ws.addEventListener('message',(e)=>{
    console.log('message',e);

    const container = document.querySelector("#notification-container");
    const {id,memberId,content,type,checked,regDate} = JSON.parse(e.data);//파싱먼저하고
    const html =
        `
        <li class="w-full px-4 py-2 border-b border-gray-200 rounded-t-lg">
        <a href="#" class="hover:underline text-blue-500">${content}</a>
        </li>
        `;
    container.insertAdjacentHTML('afterbegin',html);//첫번째 자식요소로 insert

    //벨 흔들리는 애니메이션
    const bell = document.querySelector("#notification");
    bell.classList.add("animate-bell");
});

ws.addEventListener('error',(e)=>{
    console.log('error',e);
});

ws.addEventListener('close',(e)=>{
    console.log('close',e);
});


window.addEventListener('DOMContentLoaded',()=>{
    document.querySelector("#notification").addEventListener('mouseover',(e)=>{
        e.target.classList.remove('animate-bell'); //마우스오버시 애니메이션 삭제
    });
});
