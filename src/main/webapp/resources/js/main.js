/**
 * Created by Ivan Tkachuk on 06.12.2015.
 */

/*menu handler*/
$(function(){
    function stripTrailingSlash(str) {
        if(str.substr(-1) == '/') {
            return str.substr(0, str.length - 1);
        }
        return str;
    }

    var url = window.location.pathname;
    var activePage = stripTrailingSlash(url);

    $("ul.nav li a").each(function(){
        var currentPage = stripTrailingSlash($(this).attr('href'));

        if (activePage == currentPage) {
            $(this).parent().addClass('active');
            if ($(this).parent().parent().parent().hasClass('dropdown')) {
                $(this).parent().parent().parent().addClass('active');
            }
        } else {
            $(this).parent().removeClass('active');
        }
    });

//    $("a#accountItem").click(function() {
//        $("a#accountItem").removeClass('active');
//        $(this).addClass('active');
//    });
});

//$('.nav li a').on('click', function () {
//    function stripTrailingSlash(str) {
//        if(str.substr(-1) == '/') {
//            return str.substr(0, str.length - 1);
//        }
//        return str;
//    }
//
//    var url = window.location.pathname;
//    var activePage = stripTrailingSlash(url);
//
//    $('.nav li a').each(function(){
//        var currentPage = stripTrailingSlash($(this).attr('href'));
//
//        if (activePage == currentPage) {
//            $(this).parent().addClass('active');
//            if ($(this).parent().parent().parent().hasClass('dropdown')) {
//                $(this).parent().parent().parent().addClass('active');
//            }
//        } else {
//            $(this).parent().removeClass('active');
//        }
//    });
//});

// Summary page functions
function newRecordButtonClick(recordType) {
    var addNewRecordForm = $("#addNewRecordForm");

    if (recordType == "income") {
        addNewRecordForm.find("#isExpense").val('0');
    } else {
        addNewRecordForm.find("#isExpense").val('1');
    }
    $("#newRecordButtonsPanel").hide();
    addNewRecordForm.show();
}

function saveRecordButtonClick() {
    $("#newRecordButtonsPanel").show();
    $("#addNewRecordForm").hide();
}

function cancelButtonClick() {
    $("#newRecordButtonsPanel").show();
    $("#addNewRecordForm").hide();
}

function formatDate(dateString) {
    var parsedDate = Date.parse(dateString);
    return parsedDate.getDate().toString();
}



// Account admin page functions
function newAccountButtonClick() {
    $("#newAccountButtonPanel").hide();
    $("#accountEditorForm").show();
}

function saveAccountButtonClick() {
//    $("#newAccountButtonPanel").show();
//    $("#accountEditorForm").hide();
}

function cancelAccountButtonClick(url) {
//    $("#newAccountButtonPanel").show();
//    $("#accountEditorForm").hide();
    window.location.href = url;
}
