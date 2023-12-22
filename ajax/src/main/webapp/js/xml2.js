document.querySelector("#btn-product").onclick = () =>{
    console.log("실습문제 버튼 클릭.");
    $.ajax({
        url : `${contextPath}/xml/sample-product.xml`,
        method : "get",
        dataType : "xml",
        success(xmlDoc){
            //xml문서를 응답받아서 pasing, javascript 객체(document)로 반환->success 인자로 넘어옴
            console.log(xmlDoc);//xml 문서를 파싱해서 document 객체로 반환

            const tbody = document.querySelector("#products tbody");
            tbody.innerHTML ='';//초기화

            const root = xmlDoc.querySelector(":root");
            const products = xmlDoc.querySelectorAll("Product");
            products.forEach((Product)=>
            {
                //console.log(Product); //Product태그 묶음으로 나옴.
                const childTags = Product.children;
                for(let i=0; i<childTags.length; i++)
                {
                    console.log(childTags[i].textContent);
                }
                const [Product_ID, SKU, Name,Product_URL,Price] = Product.children; //Product 태그안의 자식태그들
                //console.log(Product_ID.textContent,SKU.textContent,Name.textContent);
                //  const [Product_ID, SKU, Name,Product_URL,Price] = Product.children; //Product 태그안의 자식태그들
               //  console.log(subject.textContent,title.textContent,author.textContent);
               //  tbody.innerHTML+=`
               // <tr>
               //      <td>${subject.textContent}</td>
               //      <td>${title.textContent}</td>
               //      <td>${author.textContent}</td>
               // </tr>
               // `;
            });
        }
    })
}
