var arr=new Array();

async function loadCategories(){
    const url = 'http://localhost:8081/Project/getCategories' 
    const result = await fetch(url)
    .then(response => response.json());
    console.log('Fetched from: ' + url);
    console.log(result);

    data=""
    for(i=0;i<result.length;i++){
        data+=`
        <button type="button" class="list-group-item list-group-item-action" id="${"c"+result[i].categoryId}" onclick="getItems(${result[i].categoryId})">${result[i].category}</button>
        `
        //alert("c"+result[i].categoryId)
        // document.getElementById("c"+result[i].categoryId).onmouseover=function(){
        //     document.getElementById("c"+result[i].categoryId).style.color="#0d6efd"
        //     document.getElementById("c"+result[i].categoryId).style.background="#0d6dfd20"

        // }
       
    }
    document.getElementById("categories").innerHTML=data
    
}
async function loadItems(){
    const url = 'http://localhost:8081/Project/getItems'
    
    const result = await fetch(url).then(response => response.json());
    
      tab=""
      tab+=`
        <thead>
            <tr>
                <th scope="col">Item</th>
                <th scope="col">Price</th>
                <th scope="col"></th>
            </tr>
        </thead>
        `
      for(var i=0;i<result.length;i++){
        tab+=`
        <tr>
            <td id="${"i"+result[i].itemId}">${result[i].item}</td>
            <td id="${"p"+result[i].itemId}">₹${result[i].price}</td>
            <td><button class="btn btn-primary rounded p-1" id="${result[i].itemId}" onclick="checkIfExists(${result[i].itemId},${"i"+result[i].itemId},${"p"+result[i].itemId})">ADD</button></td>
        </tr>
    `  
      }
      document.getElementById("itemCat").innerHTML="All Items"
      document.getElementById("itemsTable").innerHTML=tab
}
async function getItems(x){

    const url = (
        'http://localhost:8081/Project/getByCategoryId?' +
        new URLSearchParams({ categoryId: x }).toString()
      );
    
      const result = await fetch(url).then(response => response.json());
    
      tab=""
      tab+=`
        <thead>
            <tr>
                <th scope="col">Item</th>
                <th scope="col">Price</th>
                <th scope="col"></th>
            </tr>
        </thead>
        `
      for(var i=0;i<result.length;i++){
        tab+=`
        <tr>
            <td id="${"i"+result[i][0]}">${result[i][1]}</td>
            <td id="${"p"+result[i][0]}">₹${result[i][2]}</td>
            <td><button class="btn btn-primary rounded p-1" id="${result[i][0]}" onclick="checkIfExists(${result[i][0]},${"i"+result[i][0]},${"p"+result[i][0]})">ADD</button></td>
        </tr>
    `  
      }
      document.getElementById("itemCat").innerHTML=result[0][3]
      document.getElementById("itemsTable").innerHTML=tab
      
}
function checkIfExists(id,item,price){
    document.getElementById("hiddenDiv").style.visibility="visible"
    var x=id
    f=0
    for(i=0;i<arr.length;i++){
        if(arr[i]==id){
            f=1;
        }
    }
    if(f==0){
        arr[arr.length]=id;
        //alert(arr)
        var row = document.getElementById("orderTable").insertRow();
        row.id="r"+x
        var cell1 = row.insertCell();
        var cell2 = row.insertCell();
        cell2.id="price"+x
        var cell3=row.insertCell();
        var cell4=row.insertCell();
        cell4.id="t"+x
        cell1.innerHTML = item.innerHTML;
        cell2.innerHTML = price.innerHTML.substring(1);
        b=""
        b=`<button id=${"m"+x} class="btn btn-prim p-0 px-2" onclick="decreaseQuantity(this.id)">- </button> <input disabled type="number" min="0" max="2000" value="1" id=${"input"+x} style="width: 30px;"> <button id=${"p"+id} class="btn btn-prim p-0 px-2" onclick="increaseQuantity(this.id)">+</button>`
        cell3.innerHTML=b
        //alert(parseInt(document.getElementById("p"+x).innerHTML))
        cell4.innerHTML=parseInt(document.getElementById("p"+x).innerHTML.substring(1))*parseInt(document.getElementById("input"+x).value)
        document.getElementById("itemTotal").innerHTML=(parseInt(document.getElementById("itemTotal").innerHTML)+parseInt(cell4.innerHTML)).toFixed(2)
        document.getElementById("taxes").innerHTML=((parseInt(document.getElementById("itemTotal").innerHTML)*10)/100).toFixed(2)
        document.getElementById("toPay").innerHTML=(parseInt(document.getElementById("itemTotal").innerHTML)+parseInt(document.getElementById("taxes").innerHTML)).toFixed(2)

    }
    else{
        //alert("already exists")
    }

}

async function decreaseQuantity(x){
    var m=x.substring(1)
    var inputId="input"+m
    var inp=parseInt(document.getElementById(inputId).value)

    if(inp>0){
        document.getElementById(inputId).value=inp-1
        document.getElementById("t"+m).innerHTML=parseInt(document.getElementById("price"+m).innerHTML)*parseInt(document.getElementById("input"+m).value)
        document.getElementById("itemTotal").innerHTML=(parseInt(document.getElementById("itemTotal").innerHTML)-parseInt(document.getElementById("price"+m).innerHTML)).toFixed(2)
        document.getElementById("taxes").innerHTML=((parseInt(document.getElementById("itemTotal").innerHTML)*10)/100).toFixed(2)
        document.getElementById("toPay").innerHTML=(parseInt(document.getElementById("itemTotal").innerHTML)+parseInt(document.getElementById("taxes").innerHTML)).toFixed(2)
        
    } 
    if(parseInt(document.getElementById(inputId).value)==0){
        
        var row1 = document.getElementById("r"+m);
        row1.parentNode.removeChild(row1);
        
        for(k=0;k<arr.length;k++){
          if(parseInt(m)===parseInt(arr[k])){
               spl=arr.splice(k,1)
               break
          }  
        }
        
    }
    
}

async function increaseQuantity(x){
    var p=x.substring(1)
    var inputId="input"+p
    document.getElementById(inputId).value=parseInt(document.getElementById(inputId).value)+1
    document.getElementById("t"+p).innerHTML=parseInt(document.getElementById("price"+p).innerHTML)*parseInt(document.getElementById("input"+p).value)
    document.getElementById("itemTotal").innerHTML=(parseInt(document.getElementById("itemTotal").innerHTML)+parseInt(document.getElementById("price"+p).innerHTML)).toFixed(2)
    document.getElementById("taxes").innerHTML=((parseInt(document.getElementById("itemTotal").innerHTML)*10)/100).toFixed(2)
    document.getElementById("toPay").innerHTML=(parseInt(document.getElementById("itemTotal").innerHTML)+parseInt(document.getElementById("taxes").innerHTML)).toFixed(2)
}

function deleteRow(x){
    var i = x.rowIndex;
    document.getElementById("orderTable").deleteRow(i+1);
}

function askContact(){
    document.getElementById("myModal").showModal();
}
function checkLength(){
    num=document.getElementById("phone").value;
    if(num.length<10 || num.length>10){
        document.getElementById("phone").style.borderColor="red"
        txt=document.getElementById("checkNum")
        txt.innerHTML="*Enter a valid contact number"
    
    }
    else{
        document.getElementById("checkNum").innerHTML=""
        document.getElementById("phone").style.borderColor="#0d6efd"
        //addInvoice();
    }
}

async function addToInvoice(){
    num=document.getElementById("phone").value;
    if(num.length<10 || num.length>10){
        document.getElementById("phone").style.borderColor="red"
        txt=document.getElementById("checkNum")
        txt.innerHTML="*Enter a valid contact number"
    
    }
    else{
        
        var today = new Date();
        var d = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
        const url="http://localhost:8081/Project/addInvoices"

        let data={
            date:d,
            contact:document.getElementById("phone").value,
            quantity:parseInt(document.getElementById("orderTable").rows.length)-1,
            totalAmount:parseInt(document.getElementById("toPay").innerHTML)
        };

        params={
            method:'POST',
            headers:{
                'Access-Control_Allow_Origin':"*",
                'Content-Type':'application/json',
                'mode':'no-cors',
                'Accept':'application/json'
            },
            body : JSON.stringify(data)
        }
        await fetch(url,params).then((response)=>{response.json()})


        const url2 = 'http://localhost:8081/Project/getInvoices' 
        const res = await fetch(url2).then(response => response.json());
        console.log('Fetched from: ' + url2);
        console.log(res);
        invoiceid=res[0].invoiceId;

        
        
        const url3="http://localhost:8081/Project/addOrders"
        var orders =[]

        for(i=1;i<document.getElementById("orderTable").rows.length;i++){
            
           
            let d1={
                itemId:arr[i-1]
            }
            let d2={
                invoiceId:invoiceid
            }
            let data1={
                itemName:document.getElementById("orderTable").rows[i].cells[0].innerHTML,
                date:d,
                contact:document.getElementById("phone").value,
                price:parseInt(document.getElementById("orderTable").rows[i].cells[1].innerHTML),
                quantity:document.getElementById("input"+arr[i-1]).value,
                total:document.getElementById("orderTable").rows[i].cells[3].innerHTML,
                item:d1, 
                invoice:d2

            };
           
            orders.push(data1)

        }
 
        params={
            method:'POST',
            headers:{
            'Access-Control_Allow_Origin':"*",
            'Content-Type':'application/json',
            'mode':'no-cors',
            'Accept':'application/json'
            },
            body : JSON.stringify(orders)
        }


        
       await fetch(url3,params).then((response)=>{response.json()});
       window.location.href="BillPage.html"
    }


}