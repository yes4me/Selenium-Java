function shuffle(o){
    for(var j, x, i = o.length; i; j = Math.floor(Math.random() * i), x = o[--i], o[i] = o[j], o[j] = x);
    return o;
}

$(document).ready(function(){

  var arr = [];
  var count_count = $('#sortable li').length;
  for(var i = 0; i < count_count; i++)
    arr.push(i + 1);
  arr = shuffle(arr);

  $('#sortable li').each(function(index){
    $(this).attr('id', 'slot_' + (index + 1));
    $(this).text(arr[index]);
  });

  $( "#sortable" ).sortable();
  $( "#sortable" ).disableSelection();
});
