document.querySelector(".cm-content").addEventListener("keydown", function(event) {
    var textarea = event.target;
    console.log(window.getSelection());
    
 	
        // 엔터 키가 눌렸을 때
        if (event.key === 'Enter') {
            // 이전 라인 넘버 요소 가져오기
            var previousLineNumber = document.querySelector('.cm-gutterElement:last-child');
            // 이전 라인 넘버 값 가져오기
            var previousLineNumberValue = parseInt(previousLineNumber.textContent);

            // 새로운 라인 넘버 생성
            var newLineNumber = document.createElement('div');
            newLineNumber.classList.add('cm-gutterElement');
            newLineNumber.style.height = '19.59px';
            newLineNumber.textContent = previousLineNumberValue + 1;

            // 기존 라인 넘버 바로 아래에 새로운 라인 넘버 삽입
            previousLineNumber.parentNode.insertBefore(newLineNumber, previousLineNumber.nextSibling);
						   			
   			
		
        }
   
    

});

var contentEditable = document.querySelector(".cm-content");

contentEditable.addEventListener("input", function() {
    var caretOffset = getCaretOffset(contentEditable);
    console.log("Caret offset:", caretOffset);
});

function getCaretOffset(element) {
    var selection = window.getSelection();
    var range = selection.getRangeAt(0);
    var preCaretRange = range.cloneRange();
    preCaretRange.selectNodeContents(element);
    preCaretRange.setEnd(range.endContainer, range.endOffset);
    return preCaretRange.toString().length;
}