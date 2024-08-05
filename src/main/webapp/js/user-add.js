
    document.getElementById('fullName').addEventListener('input', function() {
        var fullName = this.value.trim()
        var spaceIndex = fullName.lastIndexOf(' ')

        if (spaceIndex !== -1) {
            var firstName = fullName.substring(0, spaceIndex)
            var lastName = fullName.substring(spaceIndex + 1)
            document.getElementById('first_name').value = first_name
            document.getElementById('last_name').value = last_name
        } else {
            document.getElementById('first_name').value = fullName
            document.getElementById('last_name').value = ''
        }
    })

