	$(document).ready(function(){
           //1. hide error Section
              $("#emailError").hide();
             
              //2. define error variable 
              var emailError = false;
			  
			   //3. validate function
            function validate_patientEmailInUserTable() {
                var val = $("#email").val();
                var exp = /\S+@\S+\.\S+/;
                if(val=='') {
                    $("#emailError").show();
                    $("#emailError").html("*<b>Code</b> Can not be empty")
                    $("#emailError").css('color','red');
                    emailError = false;
                } else if(!exp.test(val)) {
                    $("#emailError").show();
                    $("#emailError").html("*<b>Code</b> must be 4-12 chars only")
                    $("#emailError").css('color','red');
                    emailError = false;
                } else {
	                var id = 0; //for register
	                if($("#id").val()!=undefined) { //edit page
						emailError = true;
						id = $("#id").val();
					}
                    $.ajax({
						url:'/patient/checkEmail',
						data: {"email": val,"id":id},
						success:function(resTxt) {
							if(resTxt!='') {
								$("#emailError").show();
                   				$("#emailError").html(resTxt);
                    			$("#emailError").css('color','red');
                    			emailError = false;
							} else {
								$("#emailError").hide();
								emailError = true;
							}
						}
						
					});
                }
                return emailError;
            }


            //5. on submit
            $("#patForm").submit(function(){
                
				validate_patientEmailInUserTable();

                if(emailError)
                    return true;
                else return false;
            });
        });