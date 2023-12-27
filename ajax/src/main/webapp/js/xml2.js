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
            products.forEach((product) => {
                console.log(product);
                //const childTags = Product.children;
                let tr = "<tr>";
                [...product.children].forEach((attr)=>{
                    console.log(attr);
                    tr += `<td>${attr.textContent}</td>`;
                });
                tr+="</tr>";
                tbody.innerHTML+=tr;
            });
        }
    })
}
