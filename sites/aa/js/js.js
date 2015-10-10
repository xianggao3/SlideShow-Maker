$(document).ready(function(){
   var n=0;
   var playing = 0;
   
  var timerId = 0;

    $.getJSON('js/json.json',function(data){
        //console.log(data.title);
        //$('titleh1').innerHTML=data.title;-doesnt work idk why
        //console.log(data);
        
        
        //console.log(myJSON.title);
    //  console.log(myJSON.slides[0].caption);
        document.getElementById('titleh1').innerHTML=data.title;//sets title
        document.getElementById('comments').innerHTML=data.slides[n].caption;
        document.getElementById('img').src='img/'+data.slides[n].image_file_name;
        document.getElementById('next').onclick = nextSlide;
        document.getElementById('prev').onclick=prevSlide;
        document.getElementById('play').onclick=playpause;
        document.getElementById('pause').onclick=playpause;
        $('#pause').hide();
        
    function nextSlide(){
       n=(n+1)%data.slides.length;
        document.getElementById('comments').innerHTML=data.slides[n].caption;
        document.getElementById('img').src='img/'+data.slides[n].image_file_name;

    }    
        
    function prevSlide(){
        n=(n-1)%data.slides.length;
        document.getElementById('comments').innerHTML=data.slides[n].caption;
        document.getElementById('img').src='img/'+data.slides[n].image_file_name;

    } 
    function playpause(){
        playing++;
        if(playing%2===0){
            $('#pause').hide();
            $('#play').show();
            
            clearInterval(timerId);
        }
        else if(playing%2===1){
           $('#play').hide();
          $('#pause').show();
          timerId=setInterval(function() {
              nextSlide();
            }, 3000);
        }
    }
        
        
    });
   
})

    