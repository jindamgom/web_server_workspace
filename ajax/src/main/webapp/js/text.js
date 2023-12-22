document.querySelector("#btn1").addEventListener("click",(e)=>{
    console.log("1234...");

    $.ajax({
        url:`${contextPath}/text`,
        method:"get",
        data:{
          name:"농담곰",
          num:123
        },
        dataType:"text",
        beforeSend()
        {
            console.log("beforeSend");
        },
        success(response){
            console.log(response);

        },
        error(jqXHR, textStatus,errorThrown)
        {
            console.log("error");
            console.log(jqXHR,textStatus,errorThrown);
        },
        complete(){
            console.log("complete");
        },

    });
});



$("#studentSearch").autocomplete({
    source(request,callback)
    {
        // console.log(request);
        // console.log(response); //콜백함수
        $.ajax({
            url:`${contextPath}/text/studentSearch`,
            method:'get',
            data: request,
            dataType: 'text',
            success(response) {
                console.log(response);
                /**
                 * 1.이름
                 * 2.이름
                 * 3.이름
                 * {
                 *    label:'이름1', //노출값
                 *    value:'이름1'  //내부값
                 * }
                 */
                if(response)
                {
                    const temp = response.split("\r\n");
                    const students = temp.map((student)=>{
                       const[id,name] = student.split(',');
                       return{
                            label :`${name}(${id})`, //노출되는값
                                value : id //실제 값
                        };
                    });
                    console.log(students);
                    callback(students);
                }
            }
        });
    },
    //드롭박스에 나오는 이벤트 핸들러
    select(e,selected) {
        console.log(e,selected);
        const{item : {value}} = selected;
        location.href = `${contextPath}/student/studentDetail?id=${value}`;
    }
});