(function () {

    jQuery(main);

    var tbody;
    var template;
    var fld;
    var $usernameFld;
    var $passwordFld;
    var $firstNameFld;
    var $lastNameFld;
    var $roleFld;
    var userService = new UserServiceClient();

    function main() {
        tbody = $('tbody');
        template = $('.wbdv-template');
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");
        $roleFld = $("#roleFld");
        $('.wbdv-createUser').click(createUser);

        findAllUsers();
    }
    

    function findAllUsers(){
        userService
        .findAllUsers()
        .then(renderUsers);
    }
    
    function createUser() {
        var username = $usernameFld.val();
        var password = $passwordFld.val();
        var firstName = $firstNameFld.val();
        var lastName = $lastNameFld.val();
        var role = $roleFld.val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            role: role
        };

       userService.createUser(user).then().then(findAllUsers);;
    }

    function renderUsers(users) {
    	tbody.empty();
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            var clone = template.clone();
            
            clone.attr('id', user.id);
            clone.find('.wbdv-username').html(user.username)
            clone.find('.wbdv-actions').find('.wbdv-delete').click(deleteUser);
            clone.find('.wbdv-actions').find('.wbdv-edit').click(editUser);
            clone.find('.wbdv-password').html(user.password);
            clone.find('.wbdv-firstName').html(user.firstName);
            clone.find('.wbdv-lastName').html(user.lastName);
            clone.find('.wbdv-role').html(user.role);
            tbody.append(clone);
        }
    }
    
    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .parent()
            .attr('id');
        userService
            .deleteUser(userId)
            .then(findAllUsers);
    }

    function editUser(event) {
    	var editBtn = $(event.currentTarget);
    	var userId = editBtn
    		.parent()
    		.parent()
    		.parent()
    		.attr('id');
    	userService
    		.findUserById(userId)
    		.then(renderUser);
    	
    	$(".wbdv-updateUser").click(userId,updateUser);
    	
    	}
    
    function updateUser(event) {
    	
        var user = {
                username: $usernameFld.val(),
                firstName: $firstNameFld.val(),
                lastName: $lastNameFld.val(),
                role: $roleFld.val()
            };
    	userService
    		.updateUser(event.data,user)
    		.then(success);
    	
    	}
    
    function success(){
    	findAllUsers();
    	alert("success");
        $usernameFld.val("");
        $passwordFld.val("");
        $firstNameFld.val("");
        $lastNameFld.val("");
        $roleFld.val("");
    }
    
    function renderUser(user) {
        $usernameFld = $("#usernameFld");
        $passwordFld = $("#passwordFld");
        $firstNameFld = $("#firstNameFld");
        $lastNameFld = $("#lastNameFld");
        $roleFld = $("#roleFld");
        $usernameFld.val(user.username);
        $passwordFld.val(user.password);
        $firstNameFld.val(user.firstName);
        $lastNameFld.val(user.lastName);
        $roleFld.val(user.role);
    	}


})();