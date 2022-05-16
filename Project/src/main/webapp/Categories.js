


async function addData(){
            
    var c=document.getElementById("category").value;
    var img=document.getElementById("image").value;
    if(c==""){
        checkCategoryField();
    }
    else if(img==""){
        checkImageField();
    }
    else{
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        
    if (this.readyState == 4 && this.status == 200) {
    var f=0;
    var response = xhttp.responseText;
    var data=JSON.parse(response);
    for(let i=0;i<data.length;i++)
    {

    if(document.getElementById("category").value==data[i].category){
        f=1;
        break;
    }
    }
    if(f==1){
    var x = document.getElementById("snackbar");
        x.innerHTML="Category Already Exists"
        x.className = "show";
        setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
   }
   else{
        var y = document.getElementById("snackbar1");
        y.innerHTML="Added Successfully"
        y.className = "show";
        setTimeout(function(){ y.className = y.className.replace("show", ""); }, 3000);

        //document.getElementById("myModal").close();
        //window.location.href="Categories.html"
    }
    
   }
   
   };
xhttp.open("GET", "http://localhost:8081/Project/getCategories", true);

xhttp.send();

    
    if(1){
        
        url="http://localhost:8081/Project/addCategories"
        let data={
        category:c,
        image:img
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
    .then(data>console.log(data));
    //document.getElementById("myModal").close();
    }
    }
    
  }

function closeModal(){
    document.getElementById('myModal').close()
    window.location.href="Categories.html"
    
}

  async function loadCategories(){
    const url = 'http://localhost:8081/Project/getCategories' 
            const result = await fetch(url)
           .then(response => response.json());
            console.log('Fetched from: ' + url);
            console.log(result);
                addCategoriesBtn=`
                <div class="col-sm-4" style="padding: 10px;">
                    <button class="catButton" type="button" style="background-color:#0d6efd ;width:100%;height:100%;border:2px solid #0d6efd;border-radius:15px;color:white" id="addCatBtn">
                        <div class="card" style="border-radius: 15px;border:0px;background-color:#0d6efd">
                            <div class="card-body">
                              <h3>Add Category</h3>
                            </div>
                          </div>
                    </button>
                </div>
                `
                data=""
                data+=addCategoriesBtn
            for(i=0;i<result.length;i++){

                data+=`
                <div class="col-sm-4" style="padding: 10px;">
                    <div class="card" style="border-radius: 15px;">
                    <button class="catButton" type="button" style="border: 0px;background-color: white;padding:0%;width:100%" id=${result[i].categoryId}>
                        <img class="card-img-top" src=${result[i].image} alt="Card image cap" id="teja" style="border-top-left-radius: 15px;border-top-right-radius: 15px;">
                        </button>
                        <div class="card-body" style="padding-top:0%;">
                          <div class="row">
                              <div class="col-sm-8">
                                  <h3>${result[i].category}</h3>
                                   <p id=${"cat"+result[i].categoryId}></p>
                              </div>
                              <div class="col-sm-4" style="padding: 20px 10px;">
                                  <button type="button" style="background-color: rgb(241, 70, 70);border-radius: 10px;border: 0px;padding: 5px 10px;color: white;" id=${"del"+result[i].categoryId}>Delete</button>
                              </div>
                          </div>
                        </div>
                      </div>
                </div>
                `    
            }
            document.getElementById("cat").innerHTML=data;
            document.getElementById("addCatBtn").addEventListener("click",function(){
                document.getElementById("myModal").showModal();
            })
            for(i=0;i<result.length;i++){
                
                document.getElementById(result[i].categoryId).addEventListener("click",function(){
                    getItems(this.id)
                })
                document.getElementById("del"+result[i].categoryId).addEventListener("click",function(){

                    confirmDeleteCategory(parseInt(this.id.substring(3)))
                })
                
            }
             
  }

async function confirmDeleteCategory(x){
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
          confirmButton: 'btn btn-success mx-2',
          cancelButton: 'btn btn-danger mx-2'
        },
        buttonsStyling: false
      })
      
      swalWithBootstrapButtons.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, delete it!',
        cancelButtonText: 'No, cancel!',
        reverseButtons: true
      }).then((result) => {
        if (result.isConfirmed) {
            
          swalWithBootstrapButtons.fire(
            'Deleted!',
            'Your file has been deleted.',
            'success'
          )
          deleteCategories(x)
        }  
      })
}

  async function deleteCategories(x){
    
    url="http://localhost:8081/Project/deleteCategories"
    let data={
        categoryId:x
    };

    params={
        method:'DELETE',
        headers:{
            'Access-Control_Allow_Origin':"*",
            'Content-Type':'application/json',
            'mode':'no-cors',
            'Accept':'application/json'
        },
        body : JSON.stringify(data)
    }
    await fetch(url,params).then((response)=>{response.json()})
    .then(data>console.log(data));
      refresh()
  }

  
async function getItems(x){
    const url = ('http://localhost:8081/Project/getByCategoryId?' +
    new URLSearchParams({ categoryId: x }).toString()
    );

    const result = await fetch(url)
    .then(response => response.json());

    //console.log('Fetched from: ' + url);
    //console.log(result);
//ul of items to append it to category li
    document.getElementById("itemHeading").innerHTML=result[0][3]
    document.getElementById("items").innerHTML=" ";
    row1=document.createElement("tr")
    th1=document.createElement("th")
    th2=document.createElement("th")
    th3=document.createElement("th")
    th1.appendChild(document.createTextNode("Item"))
    th2.appendChild(document.createTextNode("Price"))
    //th3.appendChild(document.createTextNode("delete"))
    row1.appendChild(th1)
    row1.appendChild(th2)
    row1.appendChild(th3)
    document.getElementById("items").appendChild(row1)
    
    for(var i=0;i<result.length;i++){
          row=document.createElement("tr")
          col=document.createElement("td")
          col1=document.createElement("td")
          col2=document.createElement("td")
          rupeeIcon=document.createElement("i")
          rupeeIcon.className="fa-solid fa-indian-rupee-sign"
          col.appendChild(document.createTextNode(result[i][1]))
          col.className="itemName"
          col1.appendChild(rupeeIcon)
          col1.appendChild(document.createTextNode(result[i][2]))
          delbtn=document.createElement("button")
          delbtn.style.border="0px"
          delbtn.type="button"
          delbtn.id="btn"+result[i][0]
          delicon=document.createElement("i")
          delicon.className="fa-solid fa-trash-can"
          delbtn.appendChild(delicon);
          col2.appendChild(delbtn)
          row.appendChild(col)
          row.appendChild(col1)
          row.appendChild(col2)
          document.getElementById("items").appendChild(row)
          document.getElementById("btn"+result[i][0]).addEventListener("click",function(){
              //deleteItem(parseInt(this.id.substring(3)));
              conformDelete(parseInt(this.id.substring(3)));
          })

    }
    document.getElementById("itemsTable").scrollIntoView();
  }
  
  async function loadItems(){
    const url = 'http://localhost:8081/Project/getItems' 
            const result = await fetch(url)
           .then(response => response.json());
            console.log('Fetched from: ' + url);
            console.log(result);
            document.getElementById("itemHeading").innerHTML="All Items"
            document.getElementById("items").innerHTML=" ";
            row1=document.createElement("tr")
            th1=document.createElement("th")
            th2=document.createElement("th")
            th3=document.createElement("th")
            th1.appendChild(document.createTextNode("Item"))
            th2.appendChild(document.createTextNode("Price"))
            //th3.appendChild(document.createTextNode("delete"))
            row1.appendChild(th1)
            row1.appendChild(th2)
            row1.appendChild(th3)
            document.getElementById("items").appendChild(row1)
          for(var i=0;i<result.length;i++){
          row=document.createElement("tr")
          col=document.createElement("td")
          col1=document.createElement("td")
          col2=document.createElement("td")
          rupeeIcon=document.createElement("i")
          rupeeIcon.className="fa-solid fa-indian-rupee-sign"
          col.appendChild(document.createTextNode(result[i].item))
          col.className="itemName"
          col1.appendChild(rupeeIcon)
          col1.appendChild(document.createTextNode(result[i].price))
          delbtn=document.createElement("button")
          delbtn.style.border="0px"
          delbtn.type="button"
          delbtn.id="bt"+result[i].itemId
          delicon=document.createElement("i")
          delicon.className="fa-solid fa-trash-can"
          delbtn.appendChild(delicon);
          col2.appendChild(delbtn)
          row.appendChild(col)
          row.appendChild(col1)
          row.appendChild(col2)
          document.getElementById("items").appendChild(row)
          document.getElementById("bt"+result[i].itemId).addEventListener("click",function(){
              conformDelete(parseInt(this.id.substring(2)));
          })

    }
    //document.getElementById("itemsTable").scrollIntoView();

  }
async function conformDelete(x){
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
          confirmButton: 'btn btn-success mx-2',
          cancelButton: 'btn btn-danger mx-2'
        },
        buttonsStyling: false
      })
      
      swalWithBootstrapButtons.fire({
        title: 'Are you sure?',
        text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, delete it!',
        cancelButtonText: 'No, cancel!',
        reverseButtons: true
      }).then((result) => {
        if (result.isConfirmed) {
            
          swalWithBootstrapButtons.fire(
            'Deleted!',
            'Your file has been deleted.',
            'success'
          )
          deleteItem(x)
        }  
      })
}
  async function deleteItem(x){
    const url1 = 'http://localhost:8081/Project/getItems' 
            const data1 = await fetch(url1)
           .then(response => response.json());
            console.log('Fetched from: ' + url1);
            console.log(data1);


    for(var i=0;i<data1.length;i++)
    {
        if(data1[i].itemId==x){

            var itm=data1[i].item;
            var p=data1[i].price;
            var c=data1[i].category;
            var catId=data1[i].categoryClass.categoryId;
        }
    }
    
    url="http://localhost:8081/Project/deleteItems"
    var id=x;

    let res={
        categoryId:catId
    }
    let data={
        itemId:id,
        item:itm,
        price:p,
        category:c,
        categoryClass:res
    };

    params={
        method:'DELETE',
        headers:{
            'Access-Control_Allow_Origin':"*",
            'Content-Type':'application/json',
            'mode':'no-cors',
            'Accept':'application/json'
        },
        body : JSON.stringify(data)
    }
    await fetch(url,params).then((response)=>{response.json()})
    .then(data>console.log(data));
    // var y = document.getElementById("snackbar2");
      //   y.innerHTML="Deleted Successfully"
        // y.className = "show";
        // setTimeout(function(){ y.className = y.className.replace("show", ""); }, 3000);
    window.location.href="Categories.html"
  }
function addItem(){
    window.location.href="form.html"
}
  
function checkCategoryField(){
    
    var cat=document.getElementById("category").value;
    if(cat==""){
        document.getElementById("catDiv").innerHTML="*this field is required"
        document.getElementById("category").style.borderColor="red"
    }
    else{
        document.getElementById("catDiv").innerHTML=""
        document.getElementById("category").style.borderColor="gray"   
    }
}

function checkImageField(){
    var img=document.getElementById("image").value;
    if(img==""){
        document.getElementById("imgDiv").innerHTML="*this field is required"
        document.getElementById("image").style.borderColor="red"
    }
    else{
        document.getElementById("imgDiv").innerHTML=""
        document.getElementById("image").style.borderColor="gray"   
    }
}
function refresh(){
    window.location.href="Categories.html"
}