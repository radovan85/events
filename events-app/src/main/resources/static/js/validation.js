function validateCategory(){
	var title = document.getElementById("title").value;
	var titleError = document.getElementById("titleError");
	
	var returnValue = true;
	
	if(title === "" || title.length > 50){
		titleError.style.visibility = "visible";
		returnValue = false;
	}else {
		titleError.style.visibility = "hidden";
	}
	
	return returnValue;
}

function validateLocation(){
	var locationName = document.getElementById("locationName").value;
	var locationDetails = document.getElementById("locationDetails").value;
	var imageUrl = document.getElementById("imageUrl").value;
	
	var locationNameError = document.getElementById("locationNameError");
	var locationDetailsError = document.getElementById("locationDetailsError");
	var imageUrlError = document.getElementById("imageUrlError");
	
	var returnValue = true;
	
	if(locationName === "" || locationName.length > 50){
		locationNameError.style.visibility = "visible";
		returnValue = false;
	}else {
		locationNameError.style.visibility = "hidden";
	}
	
	if(locationDetails === "" || locationDetails.length > 90){
		locationDetailsError.style.visibility = "visible";
		returnValue = false;
	}else {
		locationDetailsError.style.visibility = "hidden";
	}
	
	if(imageUrl === "" || imageUrl.length > 255){
		imageUrlError.style.visibility = "visible";
		returnValue = false;
	}else {
		imageUrlError.style.visibility = "hidden";
	}
	
	return returnValue;
	
}

function validateEvent(){
	var eventTitle = document.getElementById("eventTitle").value;
	var eventDateStr = document.getElementById("eventDateStr").value;
	var location = document.getElementById("location").value;
	var category = document.getElementById("category").value;
	
	var eventTitleError = document.getElementById("eventTitleError");
	var eventDateStrError = document.getElementById("eventDateStrError");
	var locationError = document.getElementById("locationError");
	var categoryError = document.getElementById("categoryError");
	
	var returnValue = true;
	
	if(eventTitle === "" || eventTitle.length > 50){
		eventTitleError.style.visibility = "visible";
		returnValue = false;
	}else {
		eventTitleError.style.visibility = "hidden";
	}
	
	if(eventDateStr === "" ){
		eventDateStrError.style.visibility = "visible";
		returnValue = false;
	}else {
		eventDateStrError.style.visibility = "hidden";
	}
	
	if(category === "" ){
		categoryError.style.visibility = "visible";
		returnValue = false;
	}else {
		categoryError.style.visibility = "hidden";
	}
	
	if(location === "" ){
		locationError.style.visibility = "visible";
		returnValue = false;
	}else {
		locationError.style.visibility = "hidden";
	}
	
	return returnValue;
}

function validateRegForm(){
	var firstName = document.getElementById("firstName").value;
	var lastName = document.getElementById("lastName").value;
	var email = document.getElementById("email").value;
	var password = document.getElementById("password").value;
	var avatarUrl = document.getElementById("avatarUrl").value;
	
	var firstNameError = document.getElementById("firstNameError");
	var lastNameError = document.getElementById("lastNameError");
	var emailError = document.getElementById("emailError");
	var passwordError = document.getElementById("passwordError");
	var avatarUrlError = document.getElementById("avatarUrlError");
	
	var regEmail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/g;
	var returnValue = true;
	
	if (firstName === "" || firstName.length > 30) {
		firstNameError.style.visibility = "visible";
		returnValue = false;
	} else {
		firstNameError.style.visibility = "hidden";
	}
	

	if (lastName === "" || lastName.length > 30) {
		lastNameError.style.visibility = "visible";
		returnValue = false;
	} else {
		lastNameError.style.visibility = "hidden";
	}
	

	if (email === "" || !regEmail.test(email) || email.length > 50) {
		emailError.style.visibility = "visible";
		returnValue = false;
	} else {
		emailError.style.visibility = "hidden";
	}
	

	if(password.length < 6 || password.length > 30 ){
		passwordError.style.visibility = "visible";
		returnValue = false;
	}else {
		passwordError.style.visibility = "hidden";
	}
	
	if (avatarUrl === "" || avatarUrl.length > 255) {
		avatarUrlError.style.visibility = "visible";
		returnValue = false;
	} else {
		avatarUrlError.style.visibility = "hidden";
	}
	
	return returnValue;
}

function validatePassword() {
	var password = document.getElementById("password").value;
	var confirmpass = document.getElementById("confirmpass").value;
	if (password != confirmpass) {
		alert("Password does Not Match.");
		return false;
	}
	return true;
};

function validateComment(){
	var text = document.getElementById("text").value;
	var textError = document.getElementById("textError");
	text = text.trim();
	
	var returnValue = true;
	
	if(text === "" || text.length > 255){
		textError.style.visibility = "visible";
		returnValue = false;
	}else {
		textError.style.visibility = "hidden";
	}
	
	return returnValue;
}

function validateKeyword(keyword){
	var returnValue = false;
	
	if(keyword.length < 3 || keyword.length > 10){
		alert("Minimum 3 and Maximum 10 letters allowed!");
	}else{
		returnValue = true;
	}
	
	return returnValue;
}