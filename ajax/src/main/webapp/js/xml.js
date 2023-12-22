document.querySelector("#btn1").onclick = () =>{
    console.log("버튼클릭");
    $.ajax({
        url : `${contextPath}/xml/sample.xml`,
        method : "get",
        dataType : "xml",
        success(xmlDoc){
            //xml문서를 응답받아서 pasing, javascript 객체(document)로 반환->success 인자로 넘어옴
            console.log(xmlDoc);//xml 문서를 파싱해서 document 객체로 반환

            const tbody = document.querySelector("#books tbody");
            tbody.innerHTML ='';//초기화

            const root = xmlDoc.querySelector(":root");
            console.log(root);
            const value  = root.getAttribute("myattr"); //사용자 속성
            console.log(value); //hello
            // const books = root.children();
            const books = xmlDoc.querySelectorAll("book");
            books.forEach((book)=>{
               console.log(book);
               const [subject, title, author] = book.children;
               console.log(subject.textContent,title.textContent,author.textContent);
               tbody.innerHTML+=`
               <tr>
                    <td>${subject.textContent}</td>
                    <td>${title.textContent}</td>
                    <td>${author.textContent}</td>
               </tr>
               `;
            });
        }
    })
}
