/**
 * Created by Ivan Tkachuk on 06.12.2015.
 */

function newRecordButtonClick(recordType) {
    if (recordType == "income") {

    } else {

    }
    $("#newRecordButtonsPanel").hide();
    $("#addNewRecordForm").show();
}

function saveRecordButtonClick() {
    $("#newRecordButtonsPanel").show();
    $("#addNewRecordForm").hide();
}

function cancelButtonClick() {
    $("#newRecordButtonsPanel").show();
    $("#addNewRecordForm").hide();
}
