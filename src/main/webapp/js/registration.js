$(document).ready(function() {

    let selectTextFieldFromButton = function($button) {
        return $button.closest(".row").find(".name-input");
    };

    let disableField = function($button, disable) {
        let textField = selectTextFieldFromButton($button);

        textField.val("");
        textField.attr("disabled", disable);
    };

    $(".not-attending").each(function() {
        if ($(this).attr("checked")) {
            disableField($(this), true);
        }
    });

    $(".not-attending").on("click", function() {
        disableField($(this), true);
    });

    $(".attending").on("click", function() {
        disableField($(this), false);
    });
});
