$(document).ready(function(){
   
    $.getJSON('js/json.json',function(data){
        //console.log(data.title);
        //$('titleh1').innerHTML=data.title;-doesnt work idk why
        //console.log(data);
        
        
        //console.log(myJSON.title);
    //  console.log(myJSON.slides[0].caption);
        document.getElementById('titleh1').innerHTML=data.title;//sets title
        document.getElementById('comments').innerHTML=data.slides[0].caption;
        document.getElementById('image').src='../img/'+data.slides[0].image_file_name;
        document.getElementById('next').onclick = nextSlide;
    });
   
})
function nextSlide(){
       // document.getElementById('img').innerHTML = i;
        document.getElementById('comments').innerHTML=data.slides[i+1].caption;
    }
    