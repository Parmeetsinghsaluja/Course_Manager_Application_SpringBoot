(function() {
	
	var $usernameFld;
	var $passwordFld;
	var $verifyPasswordFld;
	var $changePasswordBtn;
    var userService = new UserServiceClient();
	$(main);
	
    function main() {
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $verifyPasswordFld = $("#verifyPasswordFld");
        $changePasswordBtn = $("#changePasswordBtn")
            .click(changePassword);  	
    }
    
    function changePassword(){
    	var username = $usernameFld.val();
    	var verifyPassword = $verifyPasswordFld.val();
    	var password =$passwordFld.val();
        if (verifyPassword === password){
       	 userService
       	 .findUserIdByUsername()
    	 .then(function(response){
			   return response.json();
			   }).then(updatePassword,failure);
       }
        else{
        	alert("Password Do Not Match");
        }
        
    }
        function updatePassword(){
        	console.log("Change");
        }
        
        function failure(){
        	alert("Wrong Username");
        }
    	
})();

    