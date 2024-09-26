const deleteButton = document.querySelector("#delete-button");
if (deleteButton) {
    deleteButton.addEventListener("click", e => {
        let id = document.querySelector("#article-id").value;

        fetch(`/api//articles/${id}`, {
            method: "DELETE"
        })
            .then(() => {
                alert("삭제 되었습니다.");
                location.replace("/articles")
            });
    });
}

const modifyButton = document.querySelector("#modify-button");
if (modifyButton) {
    modifyButton.addEventListener("click", e => {
        let params = new URLSearchParams(location.search);
        let id = params.get("id");

        fetch(`/api/articles/{id}`, {
            method: "PUT",
            headers: {
                "Content-type": "application/json"
            },
            body: JSON.stringify({
                title: document.querySelector("#title").value,
                content: document.querySelector("#content").value
            })
        })
            .then(() => {
                alert("수정이 완료되었습니다.");
                location.replace(`/articles/${id}`);
            });
    });
}

const createButton = document.querySelector("#create-button");
if (createButton) {
    createButton.addEventListener("click", e => {
        fetch("/api//articles", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                title: document.querySelector("#title").value,
                content: document.querySelector("#content").value
            })
        })
            .then(() => {
                alert("생성이 완료 되었습니다.")
                location.replace(`/articles`);
            })
    })
}

