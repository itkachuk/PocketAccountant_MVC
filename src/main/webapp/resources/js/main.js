/**
 * Created by Ivan Tkachuk on 06.12.2015.
 */

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
