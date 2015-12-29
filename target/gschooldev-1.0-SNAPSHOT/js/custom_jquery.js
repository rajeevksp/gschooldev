var Script = function ()  {
	"use strict";
	
	/* ==============================================
     Adding Sticky menu in Tablet and Desktop devices
     =============================================== */
	if($(window).width() > 767){
		
		$(window).scroll(function() {
			if ($(this).scrollTop() > 110) {
				$('.navbar.navbar-default').addClass('navbar-fixed-top');
				$('.navbar.navbar-default').addClass('sticky');
				
			} else {
				$('.navbar.navbar-default').removeClass('navbar-fixed-top');
				$('.navbar.navbar-default').removeClass('sticky');
			}
		});
		
	}
	
	/* ==============================================
     Adding fade effect to Main Navigation dropdown
     =============================================== */
	if( $(window).width() > 767){
		
	   $('.navbar-nav > li > a').click(function(){
		   $('.navbar-nav > li > a').parent().removeClass('active');
		   $(this).parent().addClass("active");
	   });
	
	   $('.navbar-nav > li.dropdown a').mouseenter(function(){
		   $(this).parent().children('.dropdown-menu').animate({'opacity':'1'},200).addClass('active');
	   });
	   
	   $('.navbar-nav > li.dropdown a').mouseleave(function(){
		   $(this).parent().children('.dropdown-menu').css({'opacity':'0'}).removeClass('active');
		   
	   });
	   
	   $('li.dropdown .dropdown-menu').mouseenter(function(){
		   $(this).addClass('active').css({'opacity':'1'});
	   });
	   
	   $('li.dropdown .dropdown-menu').mouseleave(function(){
		   $(this).removeClass('active').css({'opacity':'0'});
	   });
	   
	}
	
	/* ===================================================================
     Adding Click event to Main Navigation dropdown in Mobile devices
     ==================================================================== */
	$('.dropdown-nested').click(function(event){
		$('.navbar-collapse .dropdown .dropdown-nested .dropdown-menu').css('display','none');
    	event.stopPropagation();
      	var dropdown = $(this).children('.dropdown-menu');
     	 dropdown.toggle();
	});
	
	/* ===============================================
     Adding scroll to navigation in One page
     =============================================== */
	$('a.page_scroll').bind('click', function(event) {
		
		var $anchor = $(this);
		$('html, body').stop().animate({
			scrollTop: ($($anchor.attr('href')).offset().top)
		}, 1500, 'easeInOutExpo');
		event.preventDefault();
	});
	
	/* ===============================================
     Adding scroll to back to top link
     =============================================== */
	$('.go-top').click(function(event) {
		event.preventDefault();
		$('html, body').animate({scrollTop: 0}, 1000);
	});
	
	/* ===============================================
     Adding fade effect to back to top link
     =============================================== */
	$(window).scroll(function() {
		if ($(this).scrollTop() > 200) {
			$('.go-top').fadeIn(200);
		} else {
			$('.go-top').fadeOut(200);
		}
	});
	
	/* ===============================================
     Initializing Popup-Gallery
     =============================================== */
	$('.popup-gallery').magnificPopup({
		delegate: 'a',
		type: 'image',
		tLoading: 'Loading image #%curr%...',
		mainClass: 'mfp-img-mobile',
		gallery: {
			enabled: true,
			navigateByImgClick: true,
			preload: [0,1] // Will preload 0 - before current, and 1 after the current image
		},
		image: {
			tError: '<a href="%url%">The image #%curr%</a> could not be loaded.',
			titleSrc: function(item) {
				return item.el.attr('title') + '<small>By Inventive Templates</small>';
			}
		}
	});
	
	/* ===============================================
     Initializing Counter
     =============================================== */
	
	$('.counter').counterUp({
		delay: 2,
		time: 500
	});
	
	/* ===============================================
     Adding Owl Slider to Home page Slider
     =============================================== */
	var owl = $('#slider_carousel');
	owl.owlCarousel({
		singleItem	: 	true,
		navigation	:	true,
		pagination 	:	true,
		autoPlay   	: 	true,
		rewindSpeed : 	1000,
		transitionStyle : "fade"
 	});
	
	/* ===============================================
     Adding Owl Slider Time intervel
     =============================================== */
    $('.carousel').carousel({
        interval: 3000
    });
	
	/* ===========================================================
     Adding Owl Slider to Testimonials block
     =========================================================== */
	var owl_2 = $('#test_carousel');
	owl_2.owlCarousel({
		singleItem : true,
		pagination : true,
		autoPlay   : true,
		rewindSpeed : 1000,
		transitionStyle : "goDown"
 	});
	
	/* ===============================================
     Adding Owl Slider to Clients block
     =============================================== */
	var owl_3 = $('#clients_carousel');
  	owl_3.owlCarousel({
		items : 4,      
		itemsDesktop : [1199,4],
     	itemsDesktopSmall : [979,3],
		pagination : false,
		autoPlay   : true,
		rewindSpeed : 700
 	});
	
	/* ===============================================
     Adding Owl Slider to Blog items
     =============================================== */
	var owl4 = $('#blog_carousel');
	owl4.owlCarousel({
		singleItem	: 	true,
		navigation	:	true,
		pagination 	:	false,
		paginationSpeed : 200,
		autoPlay   	: 	true,
		rewindSpeed : 	500,
		transitionStyle : "goDown"
 	});
	
	/* ==========================================================
     Adding Owl Slider with fade Transition in shortcodes page
     ========================================================== */
	var owl5 = $('#carousel_example_1');
	owl5.owlCarousel({
		singleItem	: 	true,
		navigation	:	true,
		pagination 	:	false,
		paginationSpeed : 200,
		autoPlay   	: 	true,
		rewindSpeed : 	500,
		transitionStyle : "fade"
 	});
	
	/* ==========================================================
     Adding Owl Slider with goDown Transition in shortcodes page
     ========================================================== */
	var owl6 = $('#carousel_example_2');
	owl6.owlCarousel({
		singleItem	: 	true,
		navigation	:	true,
		pagination 	:	false,
		paginationSpeed : 200,
		autoPlay   	: 	true,
		rewindSpeed : 	500,
		transitionStyle : "goDown"
 	});
	
	/* ==========================================================
     Adding Owl Slider with fadeUp Transition in shortcodes page
     ========================================================== */
	var owl7 = $('#carousel_example_3');
	owl7.owlCarousel({
		singleItem	: 	true,
		navigation	:	true,
		pagination 	:	false,
		paginationSpeed : 200,
		autoPlay   	: 	true,
		rewindSpeed : 	500,
		transitionStyle : "fadeUp"
 	});
	
	/* ==========================================================
     Adding Owl Slider with backSlide Transition in shortcodes page
     ========================================================== */
	var owl8 = $('#carousel_example_4');
	owl8.owlCarousel({
		singleItem	: 	true,
		navigation	:	true,
		pagination 	:	false,
		paginationSpeed : 200,
		autoPlay   	: 	true,
		rewindSpeed : 	500,
		transitionStyle : "backSlide"
 	});
	
	/* ===============================================
     Initializing tooltips in shortcodes page
     =============================================== */
	$(function () {
		$('[data-toggle="tooltip"]').tooltip();
	});
	
	/* ===============================================
     Initializing popover in shortcodes page
     =============================================== */
	$(function () {
		$('[data-toggle="popover"]').popover();
	});
	
	/* ===============================================
     Initializing tabs in shortcodes page
     =============================================== */
	$('#myTab a').click(function (e) {
		e.preventDefault();
		$(this).tab('show');
	});
	
	/* ===============================================
     Initializing tabs style_2 in shortcodes page
     =============================================== */
	$('#myTab2 a').click(function (e) {
		e.preventDefault();
		$(this).tab('show');
	});
		
		$('#myTab3 a').click(function (e) {
		e.preventDefault();
		$(this).tab('show');
	});
	
	
$.material.options.autofill = true;
$.material.init();


    $('.bs-component [data-toggle="popover"]').popover();
    $('.bs-component [data-toggle="tooltip"]').tooltip();


$('#salary_range').bootstrapSlider({tooltip:'always',tooltip_position:'top'});
 
 
 $('#fee_range').bootstrapSlider({tooltip:'always',tooltip_position:'top'});
 
 
  $('#rating1').bootstrapSlider({tooltip:'always',tooltip_position:'top'});
  $('#rating2').bootstrapSlider({tooltip:'always',tooltip_position:'top'});
  $('#rating3').bootstrapSlider({tooltip:'always',tooltip_position:'top'});
  $('#rating4').bootstrapSlider({tooltip:'always',tooltip_position:'top'});
  $('#rating5').bootstrapSlider({tooltip:'always',tooltip_position:'top'});
 

$(".dropdown-menu li a").click(function(){
  var selText = $(this).text();
  $(this).parents('.btn-group').find('.dropdown-toggle').html(selText+' <span class="caret"></span>');
});







	 $("#focusedInput").autoSuggest("http://localhost:8080/gschooldev/webresources/autoComplete", {minChars: 1, matchCase: false, asHtmlID:'location', selectedItemProp: "name",selectionLimit:2, searchObjProps: "value,name", selectedValuesProp: "value", preFill: $('#prefill').val()});


 $("#userLocation").autoSuggest("http://localhost:8080/gschooldev/webresources/autoComplete", {minChars: 1, matchCase: false, asHtmlID:'userLocation', selectedItemProp: "name",selectionLimit:1, searchObjProps: "value,name", selectedValuesProp: "value"});


$(".target_class li").click(function() {
    $("#target_class").val($(this).text());
});


$(".target_board li").click(function() {
    $("#board").val($(this).text());
});


initialize();
}();


function setStateGet(url,datas){

    rul=url; 
		$htmlObj=$.ajax({
		 type:"GET",
		url:rul,
        ifModified:true,
	    dataType:"html",
		data: datas,
        cache: false,
		
		success: function(result) {
			           // $('#focusedInput').tokenfield('setTokens', 'blue,red,white');
				 }
		 });
}

function formNav(dir){
    if(dir == 'next'){
    $('#personalized_search_head').html('<h2>Your Details</h2><p>Don&lsquo;t worry, Your details are safe with us.</p>');$('#personalized_search1').fadeOut(300,function (){$('#personalized_search2').fadeIn();});
}
        else if (dir == "back"){
       $('#personalized_search_head').html('<h2>Search Parameters</h2><p>Search and find best school which suits your requirement.</p>');$('#personalized_search2').fadeOut(300,function (){$('#personalized_search1').fadeIn();});
        }
}


function validateUser(){
    
    var ret = true;
    
    if($('.full_name input').val().length == 0){
       $('.full_name').popover('show');
        ret  = false;
    }
    
    if($('.email input').val().length > 0){
       if(!validateEmail($('.email input').val())) {
           $('.email').popover('show');
           ret  = false;
       }
    }
     
    if($('.mobile input').val().length > 0){
       if(!validateMobile($('.mobile input').val())) {
           $('.mobile').popover('show');
            ret  = false;
       }
    }
    return ret;
}

function validateEmail(email) 
{
    var re = /\S+@\S+\.\S+/;
    return re.test(email);
}

function validateMobile(mobile) 
{
    var mob = /^[7-9]{1}[0-9]{9}$/;
    return mob.test(mobile);
}

 function viewSchool( schoolcode){
     alert(schoolcode);
     //$('#hdnBtn_'+schoolcode).click();
 }
var geocoder;
  var map;
  function initialize() {
    geocoder = new google.maps.Geocoder();
    var latlng = new google.maps.LatLng(17.3700, 78.4800);
    var mapOptions = {
      zoom: 14,
      center: latlng,
       mapTypeId : google.maps.MapTypeId.ROADMAP
    }
    map = new google.maps.Map(document.getElementById("mapPointers"), mapOptions);
    
    initMap();
}

 function initMap(){
     
 var addr = document.getElementById("prefill").value;
  // addr_list = addr.split(",");
   
   var address = addr;
 
    var position;
 
    geocoder.geocode( { 'address': address}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        
            map.setCenter(results[0].geometry.location);
       
            
            position = results[0].geometry.location;
       
      } else {
        alert("Geocode was not successful for the following reason: " + status);
      }
    });
  
     
    
      var infowindow = new google.maps.InfoWindow();
        
        
    $('.latlong').each(function (index){
   
  
  
     var image = 'images/pointer.png';
      if($(this).children(".sponsored_result").val() == 1)
          image = 'images/sponsored_pointer.png';
         
        marker = new google.maps.Marker({
            position: new google.maps.LatLng(parseFloat($(this).children(".lat_str").val()),parseFloat($(this).children(".long_str").val())),
            map: map,
            title: $(this).children('.school_name').val(),
            icon: image
        });
        
    school_name =  $(this).children('.school_name').val();
    mapcontent = $(this).children('.map_content').val();
    school_code = $(this).children('.school_code').val();
   
   marker.content = '<div class="map_content"><h5  class="map_heading" onClick="viewSchool(\''+school_code.trim()+'\');">'+school_name+'</h5><div class="map_body">'+mapcontent+'</div></div>' ;

  google.maps.event.addListener(marker,'mouseover', (function(marker,mapcontent,infowindow){ 
        return function() {
          
           infowindow.setContent( marker.content);
           infowindow.open(map,marker);
        };
    })(marker,content,infowindow)); 

    });
    
    
 }
 
 
