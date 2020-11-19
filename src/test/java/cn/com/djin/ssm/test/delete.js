function delete(r)
{
    var tr = $(r).parent().parent();
    var tb = $(tr).parent();
    var l = $(tb).find("tr:[name=del]").length;
    if (l > 1){
        $(tr).remove();
    }else{
        $(tr).find("td").each(function(){
            $(this).find("input:text").each(function(){
                $(this).val("");
            });
        });
    }
    if (l > 1){
        for (var len=1;len<1-1;len++){
            var e_tr=$(trs)[len];
            $(e_tr).find("td")[0].innerText=(len);
            $($(e_tr).find("td")[1]).find("input")[0].name="te_name_"+len;
        }
    }
}