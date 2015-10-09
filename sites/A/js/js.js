$(document).ready(function(){
/*    
var myArray = [
{
"title": "",
"comments": ""
}
]
 
var xmlhttp = new XMLHttpRequest();
var url = "../json.json";

xmlhttp.onreadystatechange = function() {
if (xmlhttp.readyState === 4&&xmlhttp.status == 200) {
    var myArr = JSON.parse(xmlhttp.responseText);
    myFunction(myArr);
    }
}


xmlhttp.open("GET", url, true);
xmlhttp.send();
document.getElementById("titleh1").innerHTML=myArr.title;
console.log(myArr);
console.log("s44");

function myFunction(arr) {
    var out = "";
    var i;
    for(i = 0; i < arr.length; i++) {
        out += '<a href="' + arr[i].url + '">' + arr[i].display + '</a><br>';
    }
    document.getElementById("image").innerHTML = out;
}
    
*/        var myJSON ;
    $.getJSON("../json.json",function(data){
        myJSON =data;
    });
    console.log(myJSON);
    console.log("aaaaa");
    document.getElementById("titleh1").innerHTML=myJSON.title;

});
