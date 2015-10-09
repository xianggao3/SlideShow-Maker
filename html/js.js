$(document).ready(function(){
    var myJSON ;
    $.getJSON('js/json.json',function(data){
        myJSON = data;
        //console.log(data.title);
        document.getElementById('titleh1').innerHTML=data.title;
        //$('titleh1').innerHTML=data.title;-doesnt work idk why
        //console.log(data);
        
        document.getElementById('comment').innerHTML=data.slides[0].caption;
        var relativepath = '../img/';
        var imgpath = data.slides[0].image_file_name;
        console.log(relativepath+imgpath);
        document.getElementById('img').innerHTML=relativepath+imgpath;
        
    });
    document.getElementById('rightbutton').onclick = nextSlide;
    function nextSlide(){
        
    }
   
})
    