(function () {

    jQuery(main);

    var tbody;
    var template;
    var userService = new UserServiceClient();

    function main() {
        tbody = $('tbody');
        template = $('.template');
        $('#createUser').click(createUser);

        findAllUsers();
    }

    function findAllUsers(){
        userService
        .findAllUsers()
        .then(renderUsers);
    }
    
    function createUser() {
        console.log('createUser');

        var username = $('#usernameFld').val();
        var password = $('#passwordFld').val();
        var firstName = $('#firstNameFld').val();
        var lastName = $('#lastNameFld').val();
        var date = $('#dateFld').val();

        var user = {
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            date: date
        };

       userService.createUser(user).then().then(findAllUsers);;
    }

    function renderUsers(users) {
    	tbody.empty();
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            var clone = template.clone();
            
            clone.attr('id', user.id);
            clone.find('.username').html(user.username)
            clone.find('.delete').click(deleteUser);
            clone.find('.edit').click(deleteUser);
            clone.find('.password').html(user.password);
            clone.find('.firstName').html(user.firstName);
            clone.find('.lastName').html(user.lastName);
            clone.find('.date').html(user.date);
            tbody.append(clone);
        }
    }
    
    function deleteUser(event) {
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .attr('id');

        userService
            .deleteUser(userId)
            .then(findAllUsers);
    }

    function editUser(event) {
        console.log('editUser');
        console.log(event);
    }

})();