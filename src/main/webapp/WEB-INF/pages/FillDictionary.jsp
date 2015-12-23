<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="js/blockCapsLockAndKeyboardLayout.js"></script>
<title>FillDictionary</title>

<script>
	$(document)
			.ready(
					function() {
						var host = window.location.href.split("?")[0];
						var wsLocation = host.replace("http", "ws")
						console.log(wsLocation);

						$("#inputWorld").keypress(function(event) {
							if (event.which != 13) {
								blockCapsLockAndKeyboardLayout(event, this);
								return false;
							}
						});

						$("#inputTruns").keypress(function(event) {
							if (event.which != 13) {
								blockCapsLockAndKeyboardLayout(event, this);
								return false;
							}
						});

						setInterval(function() {
							$("#img").attr("src", "images/eyes1.jpg");
							setTimeout(function() {
								$("#img").attr("src", "images/eyes.jpg");
							}, 100);
						}, 3000);

						ws = new WebSocket(
								wsLocation);

						ws.onmessage = function(message) {
							if (message.data.indexOf("catch") != -1) {
								var existContent = message.data.split(":")[1];
								existContent = existContent.substring(1,
										existContent.length - 1);
								resaltString = resaltString + existContent
										+ ","

								var array = existContent.split(",");
								currentInputId = currentInputId + array.length;
								console.log(array);
								array.forEach(function(item, i, arr) {
									item = item.trim();
									console.log(item);
									$("#transArea").append(makeInput(item));
								});

							}
						}
						ws.onerror = function(error) {
							alert("Ошибка " + error.message);
						};

						$("#inputWorld").attr("lang", "eng");
						$("#inputTruns").attr("lang", "rus");
						$("#inputWorld").attr("checkCase", "low");
						$("#inputTruns").attr("checkCase", "low");

						$("#inputWorld").focus();

						$("#byGoogle").on("click", function() {
							clear();
							$("#enterTranlation").hide(1000);
							$("#goog").show(1000);
							$("#inputWorld").focus();
						})

						$("#mySelf").on("click", function() {
							clear();
							$("#enterTranlation").show(1000);
							$("#goog").hide(1000);
						})

						var resaltString;
						var currentInputId = 1;
						var currentOutputId = 1;

						var input;
						var directOfTrans = ($("#en-ru").prop("checked")) ? "en-ru:"
								: "ru-en:";
						;

						$("#en-ru").on("click", function() {
							directOfTrans = "en-ru:";
							clear();
							$("#inputWorld").attr("lang", "eng");
							$("#inputTruns").attr("lang", "rus");

						})

						$("#ru-en").on("click", function() {
							directOfTrans = "ru-en:";
							clear();
							$("#inputWorld").attr("lang", "rus");
							$("#inputTruns").attr("lang", "eng");
						})

						function clear() {
							$("#transArea").empty();
							$('#inputWorld').val('').focus();
							$("#inputTruns").val('');
							$("#transFromGoogle").empty()
						}

						function makeInput(value) {
							var value = value
							input = '<input id="outputWorld'+currentInputId+'"	name="OutputWorld'+currentInputId+'" value="'+value+'" size="25" ></br>';
							return input;
						}

						function makeOutput(value) {
							var value = value
							input = '<input id="googlWorld'+currentInputId+'"	name="googlWorld'+currentInputId+'" value="'+value+'" size="25" ></br>';
							return input;
						}

						function trunslateByGoogle(word, directOfTrans) {
							$("#watch").show();
							var word = word;
							resaltString = directOfTrans + word + ":"

							var array = directOfTrans.split("-");
							var dirOfTr = "#" + array[0] + "/"
									+ array[1].replace(":", "/");

							$.get("http://localhost:8080/CheckWorldsReserve/",
									{
										google : word,
										directOfTrans : dirOfTr
									}, onAjaxSuccess);

							function onAjaxSuccess(data) {

								$("#watch").hide();
								handlerOfResponse(data);
							}

							return false;
						}

						function handlerOfResponse(data) {
							$("#transArea")
									.append(
											"<br><input type='button' id='submit' value='Enter'><br><br>")
							$("#submit").on("click", function() {
								ws.send(resaltString);
								clear();
							})
							data = data.substring(1);
							var array = data.split("%");
							array
									.forEach(function(entry) {
										var innerArray = entry.split("&");
										var pos = innerArray.shift();
										$("#transFromGoogle").append(
												"<p id='pos'>" + pos + "</p>");
										innerArray
												.forEach(function(entry) {
													var deepArray = entry
															.split("$")
													var freq = deepArray
															.shift();

													var word = deepArray
															.shift();

													if (word != undefined) {
														$("#transFromGoogle")
																.append(
																		"<p id='raer'>"
																				+ freq
																				+ "</p>");
														var buttonElem = "<input type='button' id=button"+currentOutputId+" value="+word+">";
														$("#transFromGoogle")
																.append(
																		buttonElem);
														var button = $("#button"
																+ currentOutputId);
														button
																.on(
																		"click",
																		function() {
																			var value = button
																					.attr("value");
																			console
																					.log(value);
																			$(
																					"#transArea")
																					.append(
																							makeInput(value));
																			resaltString = resaltString
																					+ value
																					+ ",";
																			$(
																					"#button")
																					.remove();

																		})

														currentOutputId++;
														var opt = deepArray
																.shift();
														$("#transFromGoogle")
																.append(opt)
													}
												})
									});

						}

						$('#enterWorld')
								.submit(
										function() {
											var value = $("#inputWorld").val();
											var field = $("#inputWorld");
											if (value == '') {
												alert("You have enter word for translation!!!");
												return false;
											}

											var stringForCheck = "checkMe:"
													+ directOfTrans + value;
											console.log(stringForCheck);
											ws.send(stringForCheck);

											if ($("#mySelf").prop("checked")
													&& value != '') {
												resaltString = directOfTrans
														+ value + ":"
												$("#inputTruns").focus();
												return false;
											} else {
												trunslateByGoogle(value,
														directOfTrans)
												return false;
											}
										});

						$('#enterTranlation').submit(function() {
							var value = $("#inputTruns").val();
							if (value != '') {
								$("#transArea").append(makeInput(value));
								value = value.trim();
								resaltString = resaltString + value + ",";
								currentInputId = currentInputId + 1;
								console.log(resaltString);
								$("#inputTruns").val('');
							} else {
								console.log(resaltString);
								ws.send(resaltString);
								$("#transArea").empty();
								$('#inputWorld').val('').focus();
							}
							return false;
						});

						$("#understand").on("click", function() {
							$("#rules").hide(1000);
							$("#nonunderstand").show();
							$("#inputWorld").focus();
						});

						$("#nonunderstand").on("click", function() {
							//			ion.sound.play("light_bulb_breaking");
							$("#rules").show(1000);
							$("#nonunderstand").hide();
							$("#inputWorld").focus();
						});

						$(window).unload(function() {
							ws.close();
						});

					});
</script>
<style>
body {
	background: url(images/BigBen.jpg) no-repeat;

	background-size: cover;
}
</style>

</head>
<body>
	<div id="radio">
		<div id="nonunderstand">
			<input type="button" value="Rules">
		</div>
		<div id="radio1">
			<form id="groupe1">
				<input id="en-ru" type="radio" name="group1" value="en-ru" checked>
				En-ru <input id="ru-en" type="radio" name="group1" value="ru-en">
				Ru-en
			</form>
		</div>
		<div id="radio2">
			<form id="groupe2">
				<input id="mySelf" type="radio" name="group2" value="Myself" checked>
				Myself <input id="byGoogle" type="radio" name="group2"
					value="ByGoogle"> By Google
			</form>
		</div>
		<h1 id="fake">H</h1>
	</div>
	<div id="owneself">
		<div id="slideL">
			<div id="fillArea">
				<form id="enterWorld">
					<h3>Enter the word:</h3>
					<input id="inputWorld" name="InputWorld" size="25">
				</form>
				<form id="enterTranlation">
					<h3>Enter translation:</h3>
					<input id="inputTruns" name="InputTruns" size="25">
				</form>
			</div>
			<div id=trans>
				<h3>Translations:</h3>
				<div id="transArea"></div>
			</div>
		</div>
	</div>
	<div id="goog">
		<div id="slideR">
			<h3>Translations from Google:</h3>
			<div id="transFromGoogle"></div>
		</div>
	</div>
	<div id="rules">
		<fieldset>
			<legend>Rules</legend>
			<p>Please look for these rules before the game</p>
			<ul>
				<li>Some rules</li>

			</ul>
			<div id="understand">
				<input type="button" value="If you understand click here">
			</div>
		</fieldset>
	</div>
	<div id="watch">
		<button id="getSessions">
			<img id="img" src="images/eyes.jpg" alt=""
				style="vertical-align: middle"> Wait a second
		</button>
	</div>
</body>
</html>