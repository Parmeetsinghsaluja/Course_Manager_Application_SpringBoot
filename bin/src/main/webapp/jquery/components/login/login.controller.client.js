(function() {
	
	
	var $usernameFld; 
	var $passwordFld;
    var $loginBtn;
    var userService = new UserServiceClient();
    $(main);

    function main() {
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $loginBtn = $("#loginBtn")
            .click(login);
    	
    }
    function login(event) {
    	var username = $usernameFld.val();
    	var password = $passwordFld.val();
    	userService.loginUser(username,password)
    			   .then(function(response){
    				   return response.json();
    			   }).then(success,failure);
    }
    
    function success(responseJSON){
    	var user = responseJSON;
    	location.href="https://saluja-summer1-2018.herokuapp.com/jquery/components/profile/profile.template.client.html?username="+user.username;
    	}
    
    function failure(){
    	alert("Wrong Username/Password");
    }

})();