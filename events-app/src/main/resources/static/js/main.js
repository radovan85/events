window.onload = redirectHome;


function redirectHome(){
	$("#ajaxLoadedContent").load("/home");
}

function redirectLogin(){
	$("#ajaxLoadedContent").load("/login");
}

function redirectAllCategories(){
	$("#ajaxLoadedContent").load("/categories/allCategories");
}

function redirectAddCategory(){
	$("#ajaxLoadedContent").load("/categories/createCategory");
}

function redirectUpdateCategory(categoryId){
	$("#ajaxLoadedContent").load("/categories/updateCategory/" + categoryId);
}

function redirectAllLocations(){
	$("#ajaxLoadedContent").load("/locations/allLocations");
}

function redirectAddLocation(){
	$("#ajaxLoadedContent").load("/locations/addLocation");
}

function redirectUpdateLocation(locationId){
	$("#ajaxLoadedContent").load("/locations/updateLocation/" + locationId);
}

function redirectAllEvents(){
	$("#ajaxLoadedContent").load("/events/allEvents");
}

function redirectAddEvent(){
	$("#ajaxLoadedContent").load("/events/addEvent");
}

function redirectUpdateEvent(eventId){
	$("#ajaxLoadedContent").load("/events/updateEvent/" + eventId);
}

function redirectAllConsumers(){
	$("#ajaxLoadedContent").load("/consumers/allConsumers");
}

function redirectRegister(){
	$("#ajaxLoadedContent").load("/registration");
}

function redirectConsumerDetails(consumerId){
	$("#ajaxLoadedContent").load("/consumers/consumerDetails/" + consumerId);
}

function redirectAllComments(eventId){
	$("#ajaxLoadedContent").load("/comments/allComments/" + eventId);
}

function redirectAddComment(eventId){
	$("#ajaxLoadedContent").load("/comments/addComment/" + eventId);
}

function redirectSearchEvent(keyword){
	$("#ajaxLoadedContent").load("/events/allEvents/" + keyword);
}


function confirmLoginPass() {
	$.ajax({
		url : "http://localhost:8080/loginPassConfirm",
		type : "POST"
	})
	.done(function(){
		checkForSuspension();
	})
	.fail(function(){
		$("#ajaxLoadedContent").load("/loginErrorPage");
	})
}



function checkForSuspension() {
	$.ajax({
		url : "http://localhost:8080/suspensionChecker",
		type : "POST"
	})
	.done(function(){
		window.location.href = "/";
	})
	.fail(function(){	
		alert("Account Suspended!");
		redirectLogout();
	})	
};

function redirectLogout() {
	$.ajax({
		type : "POST",
		url : "http://localhost:8080/loggedout"
	})
	.done(function(){
		window.location.href = "/";
	})
	.fail(function(){
		alert("Logout error!");
	})
}

function deleteCategory(categoryId){
	if(confirm("Remove this category?\nIt will affect all related events!")){
		$.ajax({
			url: "http://localhost:8080/categories/deleteCategory/" + categoryId,
			type: "GET"
		})
		.done(function(){
			redirectAllCategories();
		})
		.fail(function(){
			alert("Failed!");
		})
	}
}

function deleteLocation(locationId){
	if(confirm("Remove this location?\nIt will affect all related events!")){
		$.ajax({
			url: "http://localhost:8080/locations/deleteLocation/" + locationId,
			type: "GET"
		})
		.done(function(){
			redirectAllLocations();
		})
		.fail(function(){
			alert("Failed");
		})
	}
}

function deleteComment(commentId,eventId){
	if(confirm("Remove this comment?")){
		$.ajax({
			url: "http://localhost:8080/comments/deleteComment/" + commentId + "/" + eventId,
			type: "GET"
		})
		.done(function(){
			redirectAllComments(eventId);
		})
		.fail(function(){
			alert("Failed!");
		})
	}
}

function deleteConsumer(consumerId){
	if(confirm("Remove this consumer?\nIt will affect all related comments!")){
		$.ajax({
			url: "http://localhost:8080/consumers/deleteConsumer/" + consumerId,
			type: "GET"
		})
		.done(function(){
			redirectAllConsumers();
		})
		.fail(function(){
			alert("Failed!");
		})
	}
}

function suspendUser(userId){
	if(confirm("Are you sure you want to suspend this user?")){
		$.ajax({
			url: "http://localhost:8080/consumers/suspendUser/" + userId,
			type: "GET"
		})
		.done(function(){
			redirectAllConsumers();
		})
		.fail(function(){
			alert("Failed!");
		})
	}
}

function reactivateUser(userId){
	if(confirm("Are you sure you want to reactivate this user?")){
		$.ajax({
			url: "http://localhost:8080/consumers/reactivateUser/" + userId,
			type: "GET"
		})
		.done(function(){
			redirectAllConsumers();
		})
		.fail(function(){
			alert("Failed!");
		})
	}
}

function deleteEvent(eventId){
	if(confirm("Remove this event")){
		$.ajax({
			url: "http://localhost:8080/events/deleteEvent/" + eventId,
			type: "GET"
		})
		.done(function(){
			redirectAllEvents();
		})
		.fail(function(){
			alert("Failed!");
		})
	}
}




