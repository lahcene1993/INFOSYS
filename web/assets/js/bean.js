var req = true;
$(document).on('submit', '.form-show', function(e) {
    e.preventDefault();
    $.post("",
            {
                SHOW: true,
                id: this.id.value
            },
    function(data, status) {
        toastr.success("", status);
        if ($(data).attr('id') === 'ajax') {
            $('#ajax').replaceWith(data);
        }
        if ($(data).attr('class').indexOf('modal') > -1) {
            $(data).modal();
        }
    }
    );
});
$(document).on('submit', '.form-delete', function(e) {
    e.preventDefault();
    form = $(this);
    bootbox.confirm("Vous etes sur ?", function(result) {
        if (result === true) {
            $.post("",
                    {
                        DELETE: true,
                        id: $(form).find('input[name="id"]').val()
                    }
            )
                    .done(function(data, status) {
                        toastr.success("", status);
                        $(form).closest("tr").remove();
                    })
                    .fail(function(resultat, status, erreur) {
                        toastr.error("", status);
                    })
                    .always(function(resultat, statut) {

                    });


        }
    });

    return false;
});
$(".form-search").submit(function(e) {
    e.preventDefault();
    if (req) {
        req = false;
        $.post("", $(this).serialize())
                .done(function(data, status) {
                    toastr.success("", status);
                    $("#ajax").replaceWith(data);
                })
                .fail(function(result, statut, error) {
                    toastr.error(error, statut);
                })
                .always(function(resultat, statut) {
                    req = true;
                });
    } else {
        toastr.info("Please Wait", "");
    }
});
$("#create").click(function(event) {
    $.post("",
            {CREATE: true},
    function(data, status) {
        toastr.success("", status);

        if ($(data).attr('id') === 'ajax') {
            $('#ajax').replaceWith(data);
        }
        if ($(data).attr('class').indexOf('modal') > -1) {
            $(data).modal();
        }

    }
    );
    return false;
});
$(document).on('submit', '#form-stock', function(e) {
    e.preventDefault();
    var form = $(this);
        alert()
        $.post("", $(form).serialize()
                )
                .done(function(data, status) {
                    toastr.success("", status);
                    var tr = '<tr><td></td><td></td><td></td><td><button type="button" class="btn btn-danger btn-xs btn-block btn-del"><span class="fa fa-remove"></span></button> </td></tr>';
                    $('#table-body').append(tr);
                })
                .fail(function(result, statut, error) {
                    toastr.error(error, statut);
                })
                .always(function(resultat, statut) {

                });
});
$(document).on('submit', '.form-remove', function(e) {
    e.preventDefault();
    alert($(this).serialize())
    var form=$(this);
    if (req) {
        req = false;
        $.post("", $(this).serialize())
                .done(function(data, status) {
                    toastr.success("", status);
                    if ($(data).attr("id") !== undefined) {
                        if ($(data).attr('id') === 'ajax') {
                            $('#ajax').replaceWith(data);
                        }
                    }
                    if ($(data).attr("class") !== undefined) {
                        if ($(data).attr("class").indexOf('modal') > -1) {
                            $(data).modal();
                        }
                    }
                    $(form).closest('tr').remove();
                })
                .fail(function(resultat, status, erreur) {
                    toastr.error(error, statut);
                })
                .always(function(resultat, statut) {
                    req = true;
                });
    } else {
        toastr.info("Please Wait", "");
    }
});
$(document).on('click', '#row-add', function(e) {
    e.preventDefault();
    $.post("",
            {
                SHOW: true,
                id: this.id.value
            },
    function(data, status) {
        var tr = '<tr>' +
                '<td>1</td>' +
                '<td>2</td>' +
                '<td>3</td>' +
                '<td><form><button class="btn btn-primary btn-xs"> <span class="fa fa-edit"></span> </button></form></td>' +
                '<td><form class="form-remove"><button class="btn btn-danger btn-xs"> <span class="fa fa-remove"></span> </button></form></td>' +
                '</tr>';
        $('#table-body').append(tr);
    }
    );
});
$(document).on('submit', '.ajax-form', function(e) {
    e.preventDefault();
    if (req) {
        req = false;
        $.post("password.ajax", $(this).serialize())
                .done(function(data, status) {
                    toastr.success("", status);
                    if ($(data).attr("id") !== undefined) {
                        if ($(data).attr('id') === 'ajax') {
                            $('#ajax').replaceWith(data);
                        }
                    }
                    if ($(data).attr("class") !== undefined) {
                        if ($(data).attr("class").indexOf('modal') > -1) {
                            $(data).modal();
                        }
                    }
                })
                .fail(function(resultat, status, erreur) {
                    toastr.error(error, statut);
                })
                .always(function(resultat, statut) {
                    req = true;
                });
    } else {
        toastr.info("Please Wait", "");
    }
});
$(document).on('submit', '.form', function(e) {
    e.preventDefault();
    if (req) {
        req = false;
        $.post("", $(this).serialize())
                .done(function(data, status) {
                    
                    if ($(data).attr("id") !== undefined) {
                        if ($(data).attr('id') === 'ajax') {
                            $('#ajax').replaceWith(data);
                        }
                    }else if ($(data).attr("class") !== undefined) {
                        if ($(data).attr("class").indexOf('modal') > -1) {
                            $(data).modal();
                        }
                    }else{
                        toastr.success(data, status);
                    }
                })
                .fail(function(resultat, status, erreur) {
                    toastr.error(error, statut);
                })
                .always(function(resultat, statut) {
                    req = true;
                });
    } else {
        toastr.info("Please Wait", "");
    }
});
$(document).on('submit', '.form-mail', function(e) {
    e.preventDefault();
    if (req) {
        req = false;
        $.post("index.html", $(this).serialize())
                .done(function(data, status) {
                    toastr.success(data, status);
                    if ($(data).attr("id") !== undefined) {
                        if ($(data).attr('id') === 'ajax') {
                            $('#ajax').replaceWith(data);
                        }
                    }
                    if ($(data).attr("class") !== undefined) {
                        if ($(data).attr("class").indexOf('modal') > -1) {
                            $(data).modal();
                        }
                    }
                    $(data).children("script").each(function(index) {
                        eval($(this).text());
                    });
                })
                .fail(function(resultat, status, erreur) {
                    toastr.error(error, statut);
                })
                .always(function(resultat, statut) {
                    req = true;
                });
    } else {
        toastr.info("Please Wait", "");
    }
});

$(function() {

});