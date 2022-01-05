










//functions and variables for the contact form//////////////////////////////////////////////////////////////////////////////////////////////////////////////


 var fields={};


 document.addEventListener("DOMContentLoaded", function() {
   fields.firstName = document.getElementById('firstName');
   fields.lastName = document.getElementById('lastName');
   fields.email = document.getElementById('email');

   fields.question = document.getElementById('question');
 })

 function isNotEmpty(value) {
   if (value == null || typeof value == 'undefined' ) return false;
   return (value.length > 0);
 }

 function isNumber(num) {
   return (num.length > 0) && !isNaN(num);
 }

 function isEmail(email) {
   let regex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
   return regex.test(String(email).toLowerCase());
 }

 function fieldValidation(field, validationFunction) {
   if (field == null) return false;

   let isFieldValid = validationFunction(field.value)
   if (!isFieldValid) {
     field.className = 'placeholderRed';
   } else {
     field.className = '';
   }

   return isFieldValid;
 }

 function isValid() {
   var valid = true;

   valid &= fieldValidation(fields.firstName, isNotEmpty);
   valid &= fieldValidation(fields.lastName, isNotEmpty);
   valid &= fieldValidation(fields.email, isEmail);
   valid &= fieldValidation(fields.question, isNotEmpty);


   return valid;
 }

 class User {
   constructor(firstName, lastName, email, question) {
     this.firstName = firstName;
     this.lastName = lastName;

     this.email = email;

     this.question = question;
   }
 }

 function sendContact() {

  if (isValid()) {

    let usr = new User(firstName.value, lastName.value, email.value, question.value);

    alert(`${usr.firstName}   Thanks for leaving a messsage, I will reply soon!`)
  } else {

    alert("There was an error, check input fields are filled and a valid email address is used.")
  }

}


/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */




function myFunction() {
  document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown menu if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}

/*#####################################################################################################################################*/






