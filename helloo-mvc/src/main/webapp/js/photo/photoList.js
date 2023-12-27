const getPhotos = (page) => {
    //비동기 요청으로 photo 5건씩 조회하기
    $.ajax({
       url : `${contextPath}/photo/page`,
       //기본메소드 get 생략
       data : {
           page //page:page 속성명:속성값인 경우 이렇게도 작성가능.
        },
        success(photos)
        {
            console.log("success ???");
            console.log(photos);
            const container = document.querySelector("#photo-container");

            container.innerHTML += photos.reduce((html,photo)=>{
                const {id,memberId,content,renamedFilename,readCount,regDate}=photo;
               return `${html}
               <div class="my-5 max-w-sm bg-white border border-gray-200 rounded-lg shadow hover:shadow-lg">
               <img class="rounded-t-lg"
                src="${contextPath}/upload/photo/${renamedFilename}"
                alt="" />
            <div class="p-5">
            <h5 class="mb-2 inline text-sm font-bold tracking-tight text-gray-900">${memberId}</h5>
            <h6 class="inline text-sm text-gray-500">${regDate}</h6>
            <p class="mb-3 font-sans text-gray-700">${content}</p>
            <h6 class="inline text-sm text-gray-500">조회수</h6>
            <span class="text-sm text-gray-500">${readCount}</span>
            <h6 class="like inline text-sm cursor-pointer">🤍</h6>
        </div>
        </div>
               `;
            },'');


        },
        error: function() {
            console.log("photoList.js - 에러 발생");
        },
        complete()
        {
            //더보기 버튼의 page 처리
            document.querySelector("#page").innerHTML = page; //span tag
        }
    });

}
//더보기 버튼 - 클릭 시 현재 페이지의 값을 읽어와서 +1 해준다.
document.querySelector("#btn-page").addEventListener('click',(e)=>{
    const page = Number(document.querySelector("#page").innerHTML)+1;
    const totalPage = Number(document.querySelector("#totalPage").innerHTML);
    //if(page<=totalPage) getPhotos(page);//그리고 갱신된 page값을 getPhotos에 넘겨준다.
    page<=totalPage && getPhotos(page); //페이지가 전체 페이지보다 작거나 같을때까지만 다음페이지로 넘김.
});



//페이지 로드 시 실행..
window.addEventListener('DOMContentLoaded',()=>{
    console.log("photoList.js - window load")
   getPhotos(1);
});