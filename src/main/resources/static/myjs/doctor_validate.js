$(document).ready(function(){
              //1. hide error Section
              $("#emailError").hide();
             
              //2. define error variable 
              var emailError = false;

              //3. validation function
              function validate_patEmail(){
                  var val = $("#email").val();
                  var exp = /^[A-Z]{4,10}$/;
                  if(val=='')
                  {
                      $("#emailError").show();
                      $("#emailError").html("**<b>code </b> cannot be empty")
                      $("#emailError").css('color','red');
                      emailError = false;  
                  }else if(!exp.test(val))
                  {
                      $("#emailError").show();
                      $("#emailError").html("**<b>code </b> Must be 4-12 chars")
                      $("#emailError").css('color','red');
                      emaiError = false;  
                  }
                  else{
                	  Var id = 0 ; // For Register
                	  if($("#id").val() != undefined) // Edit Page 
                		  {
                		     emailError = true;
                		     id = $("#id").val();
                		  }
                	  
                	  $.ajax({
                		  url:'checkEmail',
                		  data:{"email":val, "id":id},
                		  success:function(resTxt){
                			  if(respText != '')
                				  {
                				    $("#emailError").show();
                                    $("#emailError").html(resTxt);
                                    $("#emailError").css('color','red');
                                    emailError = false; 
                				  
                				  }else{
                					  $("#emailError").hide();
                                      emailError = true;
                				  }
                		  }
                	  });
                      
                  }
                  return emailError;
              }

            

              // Link to a event 
              $("#email").keyup(function(){
                validate_patEmail();
              });

             


              // on submit
              $("#patForm").submit(function(){
                  validate_patEmail();
                  if(emailError) return true;
                  else return false;


              });

             });