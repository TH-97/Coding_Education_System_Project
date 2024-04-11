var isPopup = null;

function popup(){
    if(isPopup==null || isPopup.closed){
        window.open('/room','roomList', 'width = 500, height = 600, top = 200, left = 0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes')
    }
}