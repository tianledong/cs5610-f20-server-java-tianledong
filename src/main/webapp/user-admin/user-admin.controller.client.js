(function () {
    const userService = new AdminUserServiceClient();
    let template;
    let clone;
    let tbody;
    let $usernameFld;
    let $firstNameFld;
    let $lastNameFld;
    let $roleFld;
    let $updateBtn;

    const createUser = () => {
        const username = $usernameFld.val();
        const firstName = $firstNameFld.val();
        const lastName = $lastNameFld.val();
        const role = $roleFld.val();
        const newUser = {
            username: username,
            firstName: firstName,
            lastName: lastName,
            role: role
        };
        userService.createUser(newUser)
            .then(clearFld)
            .then(findAllUsers);
    };

    const findAllUsers = () => {
        userService.findAllUsers()
            .then((users) => renderUsers(users));
    };

    const findUserById = (id) => {
        return userService.findUserById(id);
    }

    const selectUser = (id) => {
        findUserById(id)
            .then(user => {
                const username = user.username;
                const firstName = user.firstName;
                const lastName = user.lastName;
                const role = user.role;

                $usernameFld.val(username);
                $firstNameFld.val(firstName);
                $lastNameFld.val(lastName);
                $roleFld.val(role);
                $updateBtn.off('click');
                $updateBtn.click(() => updateUser(id));
            })
    };
    const updateUser = (id) => {
        const username = $usernameFld.val();
        const firstName = $firstNameFld.val();
        const lastName = $lastNameFld.val();
        const role = $roleFld.val();
        const user = {
            username: username,
            firstName: firstName,
            lastName: lastName,
            role: role
        };
        userService.updateUser(id, user)
            .then(findAllUsers)
            .then($updateBtn.off('click'))
            .then(clearFld);
    };

    const deleteUser = (id) => {
        userService.deleteUser(id)
            .then(findAllUsers);
    };

    const renderUsers = (users) => {
        tbody.empty();
        for (let i = 0; i < users.length; i++) {
            renderUser(users[i]);
        }
    };

    const renderUser = (user) => {
        clone = template.clone();
        clone.removeClass('wbdv-hidden');
        clone.find(".wbdv-username").html(user.username);
        clone.find(".wbdv-first-name").html(user.firstName);
        clone.find(".wbdv-last-name").html(user.lastName);
        clone.find(".wbdv-role").html(user.role);
        clone.find(".wbdv-remove").click(() => deleteUser(user._id));
        clone.find(".wbdv-edit").click(() => selectUser(user._id));
        tbody.append(clone);
    }

    const clearFld = () => {
        $usernameFld.val('');
        $firstNameFld.val('');
        $lastNameFld.val('');
        $roleFld.val('')
    }

    const main = () => {
        template = $('tr.wbdv-template').clone();
        tbody = $('tbody');

        $updateBtn = $('.wbdv-update');
        $usernameFld = $('#usernameFld');
        $firstNameFld = $('#firstNameFld');
        $lastNameFld = $('#lastNameFld');
        $roleFld = $('#roleFld');
        $('.wbdv-create').click(createUser);
        findAllUsers();
    }
    $(main)
})()
