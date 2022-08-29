
async function replyPreferHandler(replyNo,btn,boardNo){
	console.log(replyNo,btn,boardNo);
	let url="/reply/prefer/modify/"+replyNo+"/"+btn;
	let detailUrl="/reply/detail/"+replyNo;
	let replyLiId="replyLi"+replyNo;

	
	
	const msgModal = new bootstrap.Modal('#msgModal');
	const msgModalMsg=document.getElementById("msgModalMsg");
	let msg=(btn=="true")?"좋아요":"싫어요";
	try{
		let res=await fetch(url);
		if(res.status==200){
			
			let state=await res.json();
			if(state>0){
				if(state==1){
					msg+=" 저장 성공";
				}else if(state==2){
					msg+=" 삭제 성공";
				}else if(state==3){
					msg+=" 수정 성공";
				}
				let res=await fetch(detailUrl);
				let htmlText=await res.text();
					
				console.log(htmlText);
				document.getElementById(replyLiId).innerHTML=htmlText;
			}else{
				msg+="실패(db 오류)";
			}
		}else if(res.status==400){
			msg=" <a href='/user/login.do'>로그인 하세요!</a>";
		}else{
			msg="잘못된 시도입니다. 다시 시도하세요!(db,server 오류)";
		}
	}catch(err){
			msg="잘못된 시도입니다. 다시 시도하세요!(js 오류)";
	}
	msgModalMsg.innerHTML=msg;
	msgModal.show();

}