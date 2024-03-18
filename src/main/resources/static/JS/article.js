// id가 delete-btn인 엘리먼트 조회
const deleteButton = document.getElementById('delete-btn');
// id가 delete-btn인 엘리먼트가 존재하면
if (deleteButton) {
    // 클릭 이벤트가 감지되면 삭제 API 요청
    deleteButton.addEventListener('click', event => {
        // id가 article-id인 엘리먼트의 value 조회
        let id = document.getElementById('article-id').value;

        // fetch API로 DELETE /api/articles/{id} 요청
        fetch(`/api/articles/${id}`, {
            method: 'DELETE' // HTTP 메소드는 DELETE
        }).then(() => { // 정상적으로 완료되면
            alert('삭제가 완료되었습니다');
            location.replace('/articles'); // 목록 페이지로 이동
        });
    });
}

// id가 modify-btn인 엘리먼트 조회
const modifyButton = document.getElementById('modify-btn');

if (modifyButton) {
    // 클릭 이벤트가 감지되면 수정 API 요청
    modifyButton.addEventListener('click', event => {
        let params = new URLSearchParams(location.search); // URL 쿼리스트링 파싱
        let id = params.get('id'); // id 파라미터 조회

        // PUT /api/articles/{id} 요청
        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json", // 요청 바디의 타입은 JSON
            },
            body: JSON.stringify({ // 요청 바디에 JSON 형식으로 데이터 전송
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        }).then(() => {
            alert('수정이 완료되었습니다');
            location.replace(`/articles/${id}`);
        });
    });
}


// 생성 기능
const createButton = document.getElementById('create-btn');

if (createButton) { // createButton이 존재하면
    createButton.addEventListener('click', event => { // 클릭 이벤트가 감지되면
        fetch(`/api/articles`, { // POST /api/articles 요청
            method: 'POST',
            headers: {
                "Content-Type": "application/json" // 요청 바디의 타입은 JSON
            },
            body : JSON.stringify({ // 요청 바디에 JSON 형식으로 데이터 전송
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            }),
        }).then(() => {
            alert('등록 완료되었습니다');
            location.replace("/articles");
        })
    })
}