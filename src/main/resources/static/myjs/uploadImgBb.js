 function uploadFile(){
            var file = document.getElementById("fileOb");
            var form = new FormData();
            form.append("image",file.files[0]);
            var inputs  = {
              url : 'https://api.imgbb.com/1/upload?key=0765905b6ccab866f54bcda3704969db',
              method : "POST",
              timeout : 0,
              processData : false,
              mimeType : "multipart/form-data",
              contentType : false,
              data : form


            };

            $.ajax(inputs).done(function(response){
               var job =  JSON.parse(response);
             //  $("#imagePage").attr('src',job.data.url);
               $("#photoLoc").val(job.data.url);

            });

        }
