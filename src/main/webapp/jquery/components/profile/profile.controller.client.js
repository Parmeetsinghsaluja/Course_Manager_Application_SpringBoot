(function() {
    $(init);

    var $staticUsername;
    var $phone;
    var $email;
    var $dob;
    var $role;
    var $updateBtn;
    var $logoutBtn;
    var userService = new UserServiceClient();

    function init() {
        var url = ($(this).attr('URL'));
        var username = url.split('?')[1].split("=")[1];
        $staticUsername = $("#staticUsernameFld");
        findUserIdByUsername(username);
        $staticUsername.val(username);
        $phone = $("#phoneFld");
        $email = $("#emailFLd");
        $dob = $("#dobFld");
        $role = $("#roleFld");
        $updateBtn = $("#updateBtn")
            .click(updateUser);
        $logoutBtn = $("#logoutBtn")
        .click(logout);
    }

    function updateUser() {
        var user = {
            username: $staticUsername.val(),
            phone: $phone.val(),
            email: $email.val(),
            dateOfBirth: $dob.val(),
            role: $role.val()
        };

        userService
            .updateProfile(user)
            .then(success);
    }
    
    function logout(){
    	location.href="http://localhost:8080/jquery/components/login/login.template.client.html";
    }

    function success(response) {
        if(response == null) {
            alert('Cannot Update the Profile');
        } else {
            alert('Successfully Updated Profile');
        }
    }

    
    function findUserByUserId(userId) {
        userService
            .findUserById(userId)
            .then(renderUser);
    }
    
    function findUserIdByUsername(username) {
        userService
            .findUserIdByUsername(username)
            .then(findUserByUserId);
    }
    
    function renderUser(user) {
        $phone.val(user.phone);
        $email.val(user.email);
        $dob.val(user.dateOfBirth);
        $role.val(user.role);
    }
})();