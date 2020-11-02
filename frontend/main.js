window.onload = function() {
	const data = loadMailsData();
}

function loadMailsData() {
	const xhr = new XMLHttpRequest();
	xhr.open("POST", "http://localhost:8080/email");
	xhr.send({
		token: 
	});

	xhr.onload = () => checkData(xhr);
}

function checkData(xhr) {
	console.log(xhr.response);
}