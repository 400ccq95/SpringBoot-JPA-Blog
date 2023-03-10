let index = {

	init: function() {
		$("#btn-save").on("click", () => { // function(){} 대신 ()=> 사용하는 이유는 this를 바인딩하기 위해서!
			this.save();
		});
		$("#btn-update").on("click", () => { // function(){} 대신 ()=> 사용하는 이유는 this를 바인딩하기 위해서!
			this.update();
		});
	},

	save: function() {
		// alert('user의 save함수 호출됨');
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // bttp body데이터 // json형식
			contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 모든것은 문자열. (생긴게 json이라면)=>javascript 오브젝트로 변경해줌
		}).done(function(resp) {
			alert("회원가입이 완료되었습니다.");

			//console.log(resp);
			location.href = "/"; //회원가입 성공후 /blog로 이동
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
	update: function() {
		// alert('user의 save함수 호출됨');
		let data = {
			id: $("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), // bttp body데이터 // json형식
			contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지(MIME)
			dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 모든것은 문자열. (생긴게 json이라면)=>javascript 오브젝트로 변경해줌
		}).done(function(resp) {
			alert("회원수정이 완료되었습니다.");

			//console.log(resp);
			location.href = "/"; //회원가입 성공후 /blog로 이동
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
	
}
index.init();