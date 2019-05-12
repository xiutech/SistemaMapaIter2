/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){
   var colores = $('#colores').children();
   colores.each(function(){
       $(this).mouseover(function(){
           var color = $(this).css("background-color");
           console.log(color);
           $('section').css({'background':color});
       });
   });
//   initMap();
});

//                
           
