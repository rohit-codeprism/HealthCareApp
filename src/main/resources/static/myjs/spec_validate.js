$(document).ready(function(){
              // hide error
              $("#specCodeError").hide();
              $("#specNameError").hide();
              $("#specNoteError").hide();

              // define error variable 
              var specCodeError = false;
              var specNameError = false;
              var specNoteError = false;

              // validation function
              function validate_specCode(){
                  var val = $("#specCode").val();
                  var exp = /^[A-Z]{4,10}$/;
                  if(val=="")
                  {
                      $("#specCodeError").show();
                      $("#specCodeError").html("**<b>code </b> cannot be empty");
                      $("#specCodeError").css("color","red");
                      specCodeError = false;  
                  }else if(!exp.test(val))
                  {
                      $("#specCodeError").show();
                      $("#specCodeError").html("**<b>code </b> Must be 4-12 chars");
                      $("#specCodeError").css("color","red");
                      specCodeError = false;  
                  }
                  else{
                	  Var id = 0 ; // For Register
                	  if($("#id").val() != undefined)
                		  {
                		     specCodeError = true;
                		     id = $("#id").val();
                		  }
                	  
                	  $.ajax({
                		  url:'checkCode',
                		  data:{"code":val, "id":id},
                		  success:function(respTxt){
                			  if(respText != '')
                				  {
                				    $("#specCodeError").show();
                                    $("#specCodeError").html("*respTxt");
                                    $("#specCodeError").css("color","red");
                                    specCodeError = false; 
                				  
                				  }else{
                					  $("#specCodeError").hide();
                                      specCodeError = true;
                				  }
                		  }
                	  });
                      
                  }
                  return specCodeError;
              }

              function validate_specName(){
                  var val = $("#specName").val();
                  var exp = /^[A-Za-z0-9\s\.\-\,]{4,60}$/;
                  if(val=="")
                  {
                      $("#specNameError").show();
                      $("#specNameError").html("**<b>Spec Name</b> can not be empty");
                      $("#specNameError").css("color","red");
                      specNameError = false;
                  } else if(!exp.test(val))
                  {
                      $("#specNameError").show();
                      $("#specNameError").html("**<b>Spec Name</b> Must be 4-25 chars");
                      $("#specNameError").css("color","red");
                      specNameError = false;
                  } 
                  else {
                      $("#specNameError").hide();
                      specNameError = true;
                  }
                  return specNameError;
              }
              function validate_specNote(){
                  var val = $("#specNote").val();
                  var exp = /^[A-Za-z0-9\s\.\-\,]{10,250}$/;
                  if(val =="")
                  {
                     $("#specNoteError").show();
                     $("#specNoteError").html("** Please enter spec Note");
                     $("#specNoteError").css("color","red");
                     specNoteError = false;
                  }else if(!exp.test(val))
                  {
                      
                     $("#specNoteError").show();
                     $("#specNoteError").html("** Please enter 10-150 char");
                     $("#specNoteError").css("color","red");
                     specNoteError = false;
                  }
                  else{
                      $("#specNoteError").hide();
                      specNoteError = true;
                  }
                  return specNoteError;
              }



              // Link to a event 
              $("#specCode").keyup(function(){
                $(this).val($(this).val().toUpperCase());
                validate_specCode();
              });

              $("#specName").keyup(function(){
        
                validate_specName();
              });

              $("#specNote").keyup(function(){
                  validate_specNote();

              });


              // on submit
              $("#specForm").submit(function(){
                  validate_specCode();
                  validate_specName();
                  validate_specNote();
                  if(specCodeError && specNameError && specNoteError) return true;
                  else return false;


              });

             });