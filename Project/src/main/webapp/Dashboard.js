async function getItemsCount(){
                    const url1 = 'http://localhost:8081/Project/getItems' 
                    const result1 = await fetch(url1)
                   .then(response => response.json());
                    console.log('Fetched from: ' + url1);
                    console.log(result1);
                    document.getElementById("totalitems").innerHTML=result1.length;

                    const url = 'http://localhost:8081/Project/getCategories' 
                    const result = await fetch(url)
                   .then(response => response.json());
                    console.log('Fetched from: ' + url);
                    console.log(result);
                    document.getElementById("totalcategories").innerHTML=result.length;

                    const url2 = 'http://localhost:8081/Project/getInvoices' 
                    const result2 = await fetch(url2)
                   .then(response => response.json());
                    console.log('Fetched from: ' + url2);
                    console.log(result2);
                    s=0;
                    l=0
                    var today = new Date();
                    var d = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();

                    for(i=0;i<result2.length;i++){
                        s+=result2[i].totalAmount
                        
                    }

                    const url3 = 'http://localhost:8081/Project/getTodaysData' 
                    const result3 = await fetch(url3)
                   .then(response => response.json());
                    console.log('Fetched from: ' + url3);
                    console.log(result3);
                    let dollarIndianLocale = Intl.NumberFormat('en-IN');
                    document.getElementById("totalrevenue").innerHTML= "₹ "+dollarIndianLocale.format(s) 
                    document.getElementById("totalorders").innerHTML=result2.length
                    document.getElementById("todaytotalorders").innerHTML=result3[0][0]
                    document.getElementById("todaytotalrevenue").innerHTML="₹ "+dollarIndianLocale.format(result3[0][1])
}

async function getRevenueByYear(){

                    const url1 = 'http://localhost:8081/Project/getRevenueByYear' 
                    const result1 = await fetch(url1)
                   .then(response => response.json());
                    console.log('Fetched from: ' + url1);
                    console.log(result1);

                    var xValues = [];
                    var yValues = [];
                    for(i=0;i<result1.length;i++){
                        xValues[i]=result1[i][0]
                        yValues[i]=result1[i][1]
                    }
                    var barColors = "#0d6efd";

                    new Chart("myChart", {
                    type: "bar",
                     data: {
                       labels: xValues,
                     datasets: [{
                       backgroundColor: barColors,
                       data: yValues
                       }]
                      },
                     options: {
                       legend: {display: false},
                       scales: {
                       yAxes: [{
                     ticks: {
                       beginAtZero: true
                      }
                      }],
                     },
                     title: {
                        display: true,
                        text: 'MONTHLY GENERATED REVENUE (2022-2023)'
                    },
                    }
                });

}

async function getRevenueByMonth(){
    const url1 = 'http://localhost:8081/Project/getRevenueByMonth' 
                    const result1 = await fetch(url1)
                   .then(response => response.json());
                    console.log('Fetched from: ' + url1);
                    console.log(result1);

                    var xValues = [];
                    var yValues = [];
                    for(i=0;i<result1.length;i++){
                        xValues[i]=result1[i][0]
                        yValues[i]=result1[i][1]
                    }
                    var barColors = "#0d6efd";

                    new Chart("myChart", {
                    type: "bar",
                     data: {
                       labels: xValues,
                     datasets: [{
                       backgroundColor: barColors,
                       data: yValues
                       }]
                      },
                     options: {
                       legend: {display: false},
                       scales: {
                       yAxes: [{
                     ticks: {
                       beginAtZero: true
                      }
                      }],
                     }
                    }
                });

}

async function getMostOrderedItems(){

    const url1 = 'http://localhost:8081/Project/getTopItems' 
                    const result1 = await fetch(url1)
                   .then(response => response.json());
                    console.log('Fetched from: ' + url1);
                    console.log(result1);

                    tab=""
                    for(i=0;i<result1.length;i++){
                        tab+=`
                        
                    <div class="col-2 pt-2">#${i+1}</div>
                    <div class="col-6 pb-2">
                        <p class="m-0">${result1[i][0]}</p>
                        <p class="m-0"><i class="fa-solid fa-indian-rupee-sign" style="font-size:small;"></i>${result1[i][1]}</p>
                    </div>
                    <div class="col-4"><p><i class="fa-solid fa-chart-line" style="color: #0d6efd;font-size:18px;"></i> <i class="fa-solid fa-indian-rupee-sign" style="font-size:small;"></i>${result1[i][2]}</p></div>
                  
                        `
                    }
                    document.getElementById("topfive").innerHTML=tab

}